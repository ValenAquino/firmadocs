package domain.procesos;

import domain.Documento;
import domain.Usuario;
import domain.mediosDeComunicacion.Mensaje;

public class ProcesoSinOrden extends ProcesoDeFirma {

  public ProcesoSinOrden(Usuario iniciador, Documento documento) {
    super(iniciador, documento);
  }

  @Override
  public void notificarInicio() {
    Mensaje mensaje = new Mensaje(
        "Proceso de firma iniciado",
        "El proceso de firma ha sido iniciado"
    );

    this.solicitudesPendientes.forEach(s -> s.getUsuario().notificar(mensaje));
  }

  @Override
  public void notificarPendientes() {
    Mensaje mensaje = new Mensaje(
        "Proceso de firma pendiente",
        "El proceso de firma tiene solicitudes pendientes"
    );

    this.solicitudesPendientes.forEach(s -> s.getUsuario().notificar(mensaje));
  }

}
