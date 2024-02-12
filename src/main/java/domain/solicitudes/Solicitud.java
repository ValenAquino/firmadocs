package domain.solicitudes;

import domain.Usuario;
import domain.procesos.ProcesoDeFirma;

public abstract class Solicitud {
  Usuario usuario;

  public Solicitud(Usuario usuario) {
    this.usuario = usuario;
  }

  abstract public void resolverEn(ProcesoDeFirma procesoDeFirma);

  public Usuario getUsuario() {
    return usuario;
  }
}
