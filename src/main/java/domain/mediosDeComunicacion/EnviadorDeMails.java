package domain.mediosDeComunicacion;

import domain.Usuario;
import interfacesExternas.MailSender;

public class EnviadorDeMails implements MedioDeComunicacion {
  MailSender mailSender;

  public EnviadorDeMails(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void notificar(Usuario usuario, Mensaje mensaje) {
    mailSender.sendMail(usuario.email, mensaje.asunto, mensaje.cuerpo);
  }
}
