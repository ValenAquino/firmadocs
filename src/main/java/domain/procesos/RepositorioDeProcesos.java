package domain.procesos;

import domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeProcesos {
  List<ProcesoDeFirma> procesos;

  public RepositorioDeProcesos() {
    this.procesos = new ArrayList<>();
  }

  public List<ProcesoDeFirma> obtenerPorUsuario(Usuario usuario) {
    return procesos.stream().filter(proceso -> proceso.colaboradores().contains(usuario)).toList();
  }

  public void agregar(ProcesoEnOrden proceso) {
    procesos.add(proceso);
  }

  public void notificarPendientes() {
    procesos.stream()
        .filter(p -> p.estado == EstadoDelProceso.INICIADO)
        .forEach(ProcesoDeFirma::notificarPendientes);
  }
}
