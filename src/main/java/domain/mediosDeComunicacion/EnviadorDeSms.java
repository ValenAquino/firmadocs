package domain.mediosDeComunicacion;

import domain.Usuario;
import interfacesExternas.SmsSender;

public class EnviadorDeSms implements MedioDeComunicacion {
  SmsSender smsSender;

  public EnviadorDeSms(SmsSender smsSender) {
    this.smsSender = smsSender;
  }


  @Override
  public void notificar(Usuario usuario, Mensaje mensaje) {
    smsSender.sendSms(usuario.celular, "%s %s".formatted(mensaje.asunto, mensaje.cuerpo));
  }
}
