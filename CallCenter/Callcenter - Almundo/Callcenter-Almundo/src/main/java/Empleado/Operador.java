package Empleado;

import Callcenter.Dispatcher;
import Callcenter.Gestionador;
import Callcenter.Llamada;
import Entidades.OperadorEstado;
import Interface.EmpleadoInterface;
import Util.ClaseUtil;
import Util.Constantes;

/**
 * Clase Operador que se encarga de ejecutar un hilo en intervalos de tiempo
 * para verificar la asignaci�n de llamadas a los operadores
 *
 * @author Jotalvaro
 */
public class Operador implements Runnable, EmpleadoInterface {
// ------------------------------ CAMPOS ------------------------------

    private long finllamada;
    private final int id;
    private boolean running;
    private OperadorEstado status;

// --------------------------- CONSTRUCTORES ---------------------------
    /**
     *
     * @param id
     */
    public Operador(int id) {
        super();
        this.id = id;
        this.status = OperadorEstado.LIBRE;
        Gestionador.getOperadores().put(id, status);
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
     * Asignaci�n estado al gestionador
     */
    public void asignarOperador() {
        Gestionador.getOperadores().put(id, status);
    }

    /**
     * M�todo encargado de verificar el estado, si existen operadores
     * disponibles ser� asignada la llamada al operador por el m�todo
     * dispathCall, por lo cual si no cumple las condiciones no se asignar� y la
     * llamada no ser� desencolada. el m�todo tambi�n controla el estado y
     * duraci�n de la llamada del Operador
     *
     */
    public void verificarLlamada() {
        if (status == OperadorEstado.LIBRE) {
            //Invocaci�n al dispatchCall, si existen llamadas y cumplen las condiciones ser� asignada al operador
            Llamada call = Dispatcher.dispatchCall();
            if (call != null) {
                ClaseUtil.log(Constantes.Mensajes.RESPONDIENDO
                        + call.getNumero(), Operador.class.getSimpleName()
                        + " " + id + "");
                finllamada = ClaseUtil.terminacion(call.getDuracion());
                status = OperadorEstado.EN_LLAMADA;
                asignarOperador();

            }
        } else //Se verifica si la llamada ha terminado
        if (System.currentTimeMillis() > finllamada) {
            ClaseUtil.log(Constantes.Mensajes.COLGANDO, Operador.class.getSimpleName()
                    + " " + id + "");
            status = OperadorEstado.LIBRE;
            asignarOperador();
            finllamada = 0;
        }
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
