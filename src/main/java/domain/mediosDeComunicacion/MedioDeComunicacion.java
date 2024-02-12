package domain.mediosDeComunicacion;

import domain.Usuario;

public interface MedioDeComunicacion {
  void notificar(Usuario usuario, Mensaje mensaje);
}
