package domain;

import domain.mediosDeComunicacion.EnviadorDeMails;
import domain.procesos.ProcesoEnOrden;
import domain.procesos.RepositorioDeProcesos;
import domain.solicitudes.SolicitudFirma;
import domain.solicitudes.SolicitudLectura;
import interfacesExternas.GeneradorDeFirmas;
import interfacesExternas.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ControlDeProcesoDeFirmaTest {
  GeneradorDeFirmas generadorDeFirmas;
  MailSender mailSender;

  @BeforeEach
  public void setUp() {
    generadorDeFirmas = mock(GeneradorDeFirmas.class);
    mailSender = mock(MailSender.class);
  }

  // 5)
  // Como colaborador, deseo poder listar los procesos de firma que tengo compartidos.

  @Test
  public void sePuedeListarLosProcesosDeFirmaCompartidos() {
    RepositorioDeProcesos repo = new RepositorioDeProcesos();
    ProcesoEnOrden proceso = procesoEnOrden();
    repo.agregar(proceso);

    Usuario colaborador = usuario("colaborador");
    SolicitudFirma solicitudFirma = solicitudFirma(colaborador);

    proceso.agregarSolicitud(solicitudFirma);
    proceso.iniciar();

    assertTrue(repo.obtenerPorUsuario(colaborador).contains(proceso));
  }

  // 6)
  // Como iniciador o colaborador, debe poder listar las personas que leyeron el documento
  // al momento actual del proceso de firma

  // 9)
  // Como lector, deseo poder marcar como leído un documento del que sea colaborador de su proceso
  @Test
  public void sePuedeVerQuienLeyoElDocumento() {
    ProcesoEnOrden proceso = procesoEnOrden();
    Usuario colaborador = usuario("colaborador");
    SolicitudLectura solicitudLectura = new SolicitudLectura(colaborador);

    proceso.agregarSolicitud(solicitudLectura);
    proceso.iniciar();

    solicitudLectura.resolverEn(proceso);

    assertTrue(proceso.lectores.contains(colaborador));
  }

  // 7)
  // Como iniciador o colaborador, debe poder listar las personas que firmaron el documento
  // al momento actual del proceso de firma

  // 8)
  // Como firmante, deseo poder firmar un documento del que sea colaborador de su proceso.
  @Test
  public void sePuedeVerQuienFirmoElDocumento() {
    ProcesoEnOrden proceso = procesoEnOrden();
    Usuario colaborador = usuario("colaborador");
    SolicitudFirma solicitudFirma = solicitudFirma(colaborador);

    proceso.agregarSolicitud(solicitudFirma);
    proceso.iniciar();

    solicitudFirma.resolverEn(proceso);

    assertSame(proceso.firmas.get(0).usuario, colaborador);
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
