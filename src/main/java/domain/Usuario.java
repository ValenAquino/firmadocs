package domain;

import domain.mediosDeComunicacion.MedioDeComunicacion;
import domain.mediosDeComunicacion.Mensaje;

public class Usuario {
  public String nombre;
  public String apellido;
  public String email;
  public String celular;
  public MedioDeComunicacion medioDeComunicacion;

  public Usuario(
      String nombre,
      String apellido,
      String email,
      String celular,
      MedioDeComunicacion medioDeComunicacion
  ) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.celular = celular;
    this.medioDeComunicacion = medioDeComunicacion;
  }

  public void notificar(Mensaje mensaje) {
    medioDeComunicacion.notificar(this, mensaje);
  }

  public void cambiarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
    this.medioDeComunicacion = medioDeComunicacion;
  }
}
