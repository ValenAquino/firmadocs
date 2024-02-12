package domain;

import domain.FirmaElectronica;
import domain.Usuario;
import interfacesExternas.GeneradorDeFirmas;

public class Firmador {
  GeneradorDeFirmas generadorDeFirmas;

  public Firmador(GeneradorDeFirmas generadorDeFirmas) {
    this.generadorDeFirmas = generadorDeFirmas;
  }

  public FirmaElectronica firmar(Usuario usuario) {
    String firma = generadorDeFirmas.generarFirma(
        usuario.nombre,
        usuario.apellido,
        usuario.email,
        usuario.celular
    );

    return new FirmaElectronica(usuario, firma);
  }
}
