package domain.procesos;

import domain.Usuario;
import domain.procesos.ProcesoDeFirma;

import java.util.List;

public class RepositorioDeProcesos {
  List<ProcesoDeFirma> procesos;

  public void agregarProceso(ProcesoDeFirma proceso) {
    procesos.add(proceso);
  }

  public List<ProcesoDeFirma> obtenerPorUsuario(Usuario usuario) {
    return procesos.stream().filter(proceso -> proceso.iniciador == usuario).toList();
  }
}
