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
 * para verificar la asignaci�n de llamadas a los supervisores
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

// ------------------------ M�TODOS INTERFACE ------------------------
// --------------------- Interface Runnable ---------------------
    @Override
    public void run() {
        while (running) {
            verificarLlamada();
            sleep();
        }
    }

// -------------------------- OTROS M�TODOS --------------------------
    /**
     * M�todo encargado de verificar el estado por medio de una clase
     * gestionadora,si existen operadores ocupados y si existen supervisores
     * disponibles ser� asignada la llamada por el m�todo dispathCall, por lo
     * cual si no cumple las condiciones no se asignar� y la llamada no ser�
     * desencolada. el m�todo tambi�n controla el estado y duraci�n de la
     * llamada del Supervisor
     */
    public void verificarLlamada() {
        if (Gestionador.operadoresOcupados() && status == SupervisorEstado.LIBRE) {
            //Invocaci�n al dispatchCall, si existen llamadas y cumplen las condiciones ser� asignada al supervisor
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
     * Asignaci�n estado al gestionador
     */
    public void asignarSupervisor() {
        Gestionador.getSupervisores().put(id, status);
    }

    /**
     * M�todo encargado de inicializar el hilo
     */
    public void start() {
        running = true;
        new Thread(this).start();
    }

    /**
     * M�todo encargado de parar la ejecuci�n del hilo
     */
    public void stop() {
        running = false;
    }

    /**
     * M�todo encargado de dormir el hilo con el fin de observar m�s
     * detenidamente los intervalos de la ejecuci�n entre las llamadas
     */
    private void sleep() {
        try {
            Thread.sleep(Constantes.Parametros.SLEEP);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
