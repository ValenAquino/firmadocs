package domain.procesos;

import domain.Documento;
import domain.FirmaElectronica;
import domain.Usuario;
import domain.mediosDeComunicacion.Mensaje;
import domain.solicitudes.SolicitudFirma;
import domain.solicitudes.SolicitudLectura;

public class ProcesoEnOrden extends ProcesoDeFirma {
  public ProcesoEnOrden(Usuario iniciador, Documento documento) {
    super(iniciador, documento);
  }

  @Override
  public void notificarInicio() {
    Mensaje mensaje = new Mensaje(
        "Proceso de firma iniciado",
        "El proceso de firma ha sido iniciado"
    );

    solicitudesPendientes.get(0).getUsuario().notificar(mensaje);
  }

  @Override
  public void confirmarLectura(Usuario usuario, SolicitudLectura solicitudLectura) {
    super.confirmarLectura(usuario, solicitudLectura);
    if (!solicitudesPendientes.isEmpty()) {
      notificarInicio();
    }
  }


  @Override
  public void agregarFirma(FirmaElectronica firma, SolicitudFirma solicitudFirma) {
    super.agregarFirma(firma, solicitudFirma);
    if (!solicitudesPendientes.isEmpty()) {
      notificarInicio();
    }
  }

  @Override
  public void notificarPendientes() {
    Mensaje mensaje = new Mensaje(
        "Proceso de firma pendiente",
        "El proceso de firma tiene solicitudes pendientes"
    );

    solicitudesPendientes.get(0).getUsuario().notificar(mensaje);
  }
}
