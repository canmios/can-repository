package Empleado;

import Callcenter.Dispatcher;
import Callcenter.Gestionador;
import Callcenter.Llamada;
import Entidades.SupervisorEstado;
import Interface.EmpleadoInterface;
import Util.ClaseUtil;
import Util.Constantes;

/**
 * /**
 * Clase Supervisor que se encarga de ejecutar un hilo en intervalos de tiempo
 * para verificar la asignación de llamadas a los supervisores
 *
 * @author Jotalvaro
 */
public class Supervisor implements Runnable, EmpleadoInterface {
// ------------------------------ CAMPOS ------------------------------

    private long finllamada;
    private final int id;
    private boolean running;
    private SupervisorEstado status;

// --------------------------- CONSTRUCTOR ---------------------------
    /**
     *
     * @param id
     */
    public Supervisor(int id) {
        super();
        this.id = id;
        this.status = SupervisorEstado.LIBRE;
        Gestionador.getSupervisores().put(id, status);
    }

// ------------------------ MÉTODOS INTERFACE ------------------------
// --------------------- Interface Runnable ---------------------
    @Override
    public void run() {
        while (running) {
            verificarLlamada();
            sleep();
        }
    }

// -------------------------- OTROS MÉTODOS --------------------------
    /**
     * Método encargado de verificar el estado por medio de una clase
     * gestionadora,si existen operadores ocupados y si existen supervisores
     * disponibles será asignada la llamada por el método dispathCall, por lo
     * cual si no cumple las condiciones no se asignará y la llamada no será
     * desencolada. el método también controla el estado y duración de la
     * llamada del Supervisor
     */
    public void verificarLlamada() {
        if (Gestionador.operadoresOcupados() && status == SupervisorEstado.LIBRE) {
            //Invocación al dispatchCall, si existen llamadas y cumplen las condiciones será asignada al supervisor
            Llamada call = Dispatcher.dispatchCall();
            if (call != null) {
                ClaseUtil.log(Constantes.Mensajes.RESPONDIENDO
                        + call.getNumero(), Supervisor.class.getSimpleName() + " "
                        + id + "");
                finllamada = ClaseUtil.terminacion(call.getDuracion());
                status = SupervisorEstado.EN_LLAMADA;
                asignarSupervisor();
            }
        } else //Se verifica si la llamada ha terminado
        if (System.currentTimeMillis() > finllamada && finllamada != 0) {
            ClaseUtil.log(Constantes.Mensajes.COLGANDO, Supervisor.class.getSimpleName()
                    + " " + id + "");
            status = SupervisorEstado.LIBRE;
            asignarSupervisor();
            finllamada = 0;
        }
    }

    /**
     * Asignación estado al gestionador
     */
    public void asignarSupervisor() {
        Gestionador.getSupervisores().put(id, status);
    }

    /**
     * Método encargado de inicializar el hilo
     */
    public void start() {
        running = true;
        new Thread(this).start();
    }

    /**
     * Método encargado de parar la ejecución del hilo
     */
    public void stop() {
        running = false;
    }

    /**
     * Método encargado de dormir el hilo con el fin de observar más
     * detenidamente los intervalos de la ejecución entre las llamadas
     */
    private void sleep() {
        try {
            Thread.sleep(Constantes.Parametros.SLEEP);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
