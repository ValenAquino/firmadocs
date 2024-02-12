package domain.solicitudes;

import domain.Usuario;
import domain.procesos.ProcesoDeFirma;

public class SolicitudLectura extends Solicitud {

  public SolicitudLectura(Usuario usuario) {
    super(usuario);
  }

  @Override
  public void resolverEn(ProcesoDeFirma procesoDeFirma) {
    procesoDeFirma.confirmarLectura(usuario, this);
  }
}
