package domain;

import domain.mediosDeComunicacion.EnviadorDeMails;
import domain.procesos.EstadoDelProceso;
import domain.procesos.ProcesoEnOrden;
import domain.procesos.ProcesoSinOrden;
import domain.solicitudes.SolicitudFirma;
import domain.solicitudes.SolicitudLectura;
import interfacesExternas.GeneradorDeFirmas;
import interfacesExternas.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CreacionProcesoDeFirmaTest {
  GeneradorDeFirmas generadorDeFirmas;
  MailSender mailSender;

  @BeforeEach
  public void setUp() {
    generadorDeFirmas = mock(GeneradorDeFirmas.class);
    mailSender = mock(MailSender.class);
  }

  // 1)
  // Como iniciador, deseo poder iniciar el proceso de firma de un documento, indicando el PDF.

  // 2)
  // Como iniciador, deseo poder especificar si el proceso tendrá orden o será de orden indistinto.

  // 3)
  // Como iniciador, deseo poder asignarle colaboradores a un proceso de firma,
  // especificando si serán lectores o firmantes.

  // 4)
  // Como iniciador, deseo poder liberar un proceso de firma,
  // enviando notificaciones a cada uno de los colaboradores,
  // en orden (o a todos a la vez, si es orden indistinto).

  @Test
  public void sePuedeIniciarUnProcesoDeFirma() {
    Documento pdf = new Documento();
    EnviadorDeMails enviadorDeMails = new EnviadorDeMails(mailSender);
    Firmador firmador = new Firmador(generadorDeFirmas);

    Usuario valen = new Usuario(
        "Valentin",
        "Aquino",
        "valen@gmail.com",
        "1123344",
        enviadorDeMails
    );

    ProcesoSinOrden procesoDeFirma = new ProcesoSinOrden(valen, pdf);
    SolicitudLectura solicitudLectura = new SolicitudLectura(valen);
    SolicitudFirma solicitudFirma = new SolicitudFirma(valen, firmador);

    procesoDeFirma.agregarSolicitud(solicitudLectura);
    procesoDeFirma.agregarSolicitud(solicitudFirma);
    procesoDeFirma.iniciar();

    assertSame(procesoDeFirma.estado, EstadoDelProceso.INICIADO);
  }

  @Test
  public void elProcesoSinOrdenNotificaAtodosAlIniciar() {
    Documento pdf = new Documento();
    EnviadorDeMails enviadorDeMails = new EnviadorDeMails(mailSender);
    Firmador firmador = new Firmador(generadorDeFirmas);

    Usuario valen = new Usuario(
        "Valentin",
        "Aquino",
        "valen@gmail.com",
        "1123344",
        enviadorDeMails
    );

    Usuario nicole = new Usuario(
        "Nicole",
        "Aquino",
        "nicole@gmail.com",
        "1123344",
        enviadorDeMails
    );

    ProcesoSinOrden procesoDeFirma = new ProcesoSinOrden(valen, pdf);
    SolicitudLectura solicitudLectura = new SolicitudLectura(valen);
    SolicitudFirma solicitudFirma = new SolicitudFirma(nicole, firmador);

    procesoDeFirma.agregarSolicitud(solicitudLectura);
    procesoDeFirma.agregarSolicitud(solicitudFirma);
    procesoDeFirma.iniciar();

    verify(mailSender, times(2)).sendMail(anyString(), anyString(), anyString());
  }

  @Test
  public void elProcesoConOrdenNotificaSoloAlPrimeroAlIniciar() {
    Documento pdf = new Documento();
    EnviadorDeMails enviadorDeMails = new EnviadorDeMails(mailSender);
    Firmador firmador = new Firmador(generadorDeFirmas);

    Usuario valen = new Usuario(
        "Valentin",
        "Aquino",
        "valen@gmail.com",
        "1123344",
        enviadorDeMails
    );

    Usuario nicole = new Usuario(
        "Nicole",
        "Aquino",
        "nicole@gmail.com",
        "1123344",
        enviadorDeMails
    );

    ProcesoEnOrden procesoDeFirma = new ProcesoEnOrden(valen, pdf);
    SolicitudLectura solicitudLectura = new SolicitudLectura(valen);
    SolicitudFirma solicitudFirma = new SolicitudFirma(nicole, firmador);

    procesoDeFirma.agregarSolicitud(solicitudLectura);
    procesoDeFirma.agregarSolicitud(solicitudFirma);
    procesoDeFirma.iniciar();

    verify(mailSender, times(1)).sendMail(anyString(), anyString(), anyString());
  }
}
