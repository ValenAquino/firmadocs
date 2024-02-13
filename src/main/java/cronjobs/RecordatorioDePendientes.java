package cronjobs;

import domain.procesos.RepositorioDeProcesos;

public class RecordatorioDePendientes {
    // El cron configurado para ejecutarse diariamente debería llamar a este método
  public static void main(String[] args) {
    RepositorioDeProcesos repo = new RepositorioDeProcesos();
    repo.notificarPendientes();
  }
}
