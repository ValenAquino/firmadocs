package domain.procesos;

import domain.Documento;
import domain.FirmaElectronica;
import domain.Usuario;
import domain.mediosDeComunicacion.Mensaje;
import domain.solicitudes.Solicitud;
import domain.solicitudes.SolicitudFirma;
import domain.solicitudes.SolicitudLectura;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcesoDeFirma {
  public Usuario iniciador;
  Documento documento;
  public EstadoDelProceso estado;
  List<Solicitud> solicitudesPendientes;
  public List<Usuario> lectores;
  public List<FirmaElectronica> firmas;

  public ProcesoDeFirma(Usuario iniciador, Documento documento) {
    this.iniciador = iniciador;
    this.documento = documento;
    this.estado = EstadoDelProceso.NO_INICIADO;
    this.solicitudesPendientes = new ArrayList<>();
    this.lectores = new ArrayList<>();
    this.firmas = new ArrayList<>();
  }

  abstract public void notificarInicio();

  abstract public void notificarPendientes();

  public void iniciar() {
    this.estado = EstadoDelProceso.INICIADO;
    this.notificarInicio();
  }

  public void agregarSolicitud(Solicitud unaSolicitud) {
    solicitudesPendientes.add(unaSolicitud);
  }

  public void confirmarLectura(Usuario usuario, SolicitudLectura solicitudLectura) {
    lectores.add(usuario);
    iniciador.notificar(getMensajeLectura());
    removerSolicitud(solicitudLectura);
  }

  public void agregarFirma(FirmaElectronica firma, SolicitudFirma solicitudFirma) {
    firmas.add(firma);
    iniciador.notificar(getMensajeFirma());
    removerSolicitud(solicitudFirma);
  }

  private void removerSolicitud(Solicitud solicitud) {
    solicitudesPendientes.remove(solicitud);

    if (solicitudesPendientes.isEmpty()) {
      this.estado = EstadoDelProceso.FINALIZADO;
      iniciador.notificar(getMensajeFinalizado());
    }
  }

  public Mensaje getMensajeInicio() {
    return new Mensaje(
        "Proceso de firma iniciado",
        "El proceso de firma ha sido iniciado"
    );
  }

  public Mensaje getMensajeFinalizado() {
    return new Mensaje(
        "Proceso de firma finalizado",
        "El proceso de firma ha sido finalizado"
    );
  }

  public Mensaje getMensajeLectura() {
    return new Mensaje(
        "Documento leído",
        "Un nuevo colaborador leyó el documento"
    );
  }

  public Mensaje getMensajeFirma() {
    return new Mensaje(
        "Documento firmado",
        "Un nuevo Colaborador firmó el documento"
    );
  }

  public List<Usuario> colaboradores() {
    return solicitudesPendientes.stream().map(Solicitud::getUsuario).toList();
  }

  public void anular() {
    if (this.estado == EstadoDelProceso.INICIADO) {
      this.estado = EstadoDelProceso.ANULADO_POST_INICIO;
    } else {
      this.estado = EstadoDelProceso.ANULADO_SIN_INICIO;
    }
  }
}
