package Callcenter;

import Util.ClaseUtil;
import Util.Constantes;

/**
 * Clase encargada de generar llamadas consecutivamente
 * @author Jotalvaro
 */
public class Generador
        implements Runnable {
// ------------------------------ CAMPOS ------------------------------

    private boolean running;

// --------------------------- CONSTRUCTOR ---------------------------
    /**
     *
     */
    public Generador() {

    }

// ------------------------ MÉTODOS INTERFACE ------------------------
// --------------------- Interface Runnable ---------------------
    @Override
    public void run() {
        while (running) {

            Integer duracion = ClaseUtil.duracionRandom();

            ClaseUtil.log(Constantes.Mensajes.LLAMADA.replace("%s", duracion.toString()),
                    Generador.class.getSimpleName());
            /**
             * Invocación al dispatchCall,se crea una llamada y se encola para
             * la respectiva evaluación asignación de acuerdo a la
             * disponibilidad del Empleado
             */
            Dispatcher.queueCall(duracion);
            sleep();

        }
    }

// -------------------------- OTROS MÉTODOS --------------------------
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
     * Método encargado de dormir el hilo con el fin de esperar un intervalo
     * para la asignación de la siguiente llamada
     *
     */
    private void sleep() {
        try {
            Thread.sleep(ClaseUtil.duracionRandom() * Constantes.Parametros.SEGUNDOS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
