package domain.solicitudes;

import domain.Firmador;
import domain.Usuario;
import domain.procesos.ProcesoDeFirma;

public class SolicitudFirma extends Solicitud {
  Firmador firmador;

  public SolicitudFirma(Usuario usuario, Firmador firmador) {
    super(usuario);
    this.firmador = firmador;
  }

  @Override
  public void resolverEn(ProcesoDeFirma procesoDeFirma) {
    procesoDeFirma.agregarFirma(firmador.firmar(usuario), this);
  }
}
