package domain;

public class FirmaElectronica {
  String firma;
  Usuario usuario;

  public FirmaElectronica(Usuario usuario, String firma) {
    this.usuario = usuario;
    this.firma = firma;
  }

}
