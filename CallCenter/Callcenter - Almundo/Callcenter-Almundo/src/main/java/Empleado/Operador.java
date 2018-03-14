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
 * para verificar la asignación de llamadas a los operadores
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
     * Asignación estado al gestionador
     */
    public void asignarOperador() {
        Gestionador.getOperadores().put(id, status);
    }

    /**
     * Método encargado de verificar el estado, si existen operadores
     * disponibles será asignada la llamada al operador por el método
     * dispathCall, por lo cual si no cumple las condiciones no se asignará y la
     * llamada no será desencolada. el método también controla el estado y
     * duración de la llamada del Operador
     *
     */
    public void verificarLlamada() {
        if (status == OperadorEstado.LIBRE) {
            //Invocación al dispatchCall, si existen llamadas y cumplen las condiciones será asignada al operador
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
