package domain;

import domain.mediosDeComunicacion.EnviadorDeMails;
import domain.procesos.ProcesoEnOrden;
import domain.procesos.RepositorioDeProcesos;
import domain.solicitudes.SolicitudFirma;
import interfacesExternas.GeneradorDeFirmas;
import interfacesExternas.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SolicitudesTest {
  GeneradorDeFirmas generadorDeFirmas;
  MailSender mailSender;

  @BeforeEach
  public void setUp() {
    generadorDeFirmas = mock(GeneradorDeFirmas.class);
    mailSender = mock(MailSender.class);
  }

  // 10
  // Como colaborador, deseo recibir recordatorios cuando no haya realizado mi acción.
  @Test
  public void sePuedeRecibirRecordatorios() {
    RepositorioDeProcesos repo = new RepositorioDeProcesos();
    ProcesoEnOrden proceso = procesoEnOrden();
    repo.agregar(proceso);

    Usuario colaborador = usuario("colaborador");
    SolicitudFirma solicitudFirma = solicitudFirma(colaborador);
    proceso.agregarSolicitud(solicitudFirma);
    proceso.iniciar();
    repo.notificarPendientes();

    // 1 para iniciar y otro para notificar el pendiente
    verify(mailSender, times(2)).sendMail(anyString(), anyString(), anyString());
  }

  public SolicitudFirma solicitudFirma(Usuario colaborador) {
    Firmador firmador = new Firmador(generadorDeFirmas);
    return new SolicitudFirma(colaborador, firmador);
  }

  public Usuario usuario(String nombre) {
    EnviadorDeMails enviadorDeMails = new EnviadorDeMails(mailSender);
    return new Usuario(
        nombre,
        "Aquino",
        nombre + "@gmail.com",
        "1123344",
        enviadorDeMails
    );
  }

  public ProcesoEnOrden procesoEnOrden() {
    Documento pdf = new Documento();
    return new ProcesoEnOrden(usuario("iniciador"), pdf);
  }
}
