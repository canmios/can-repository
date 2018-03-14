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

// ------------------------ M�TODOS INTERFACE ------------------------
// --------------------- Interface Runnable ---------------------
    @Override
    public void run() {
        while (running) {

            Integer duracion = ClaseUtil.duracionRandom();

            ClaseUtil.log(Constantes.Mensajes.LLAMADA.replace("%s", duracion.toString()),
                    Generador.class.getSimpleName());
            /**
             * Invocaci�n al dispatchCall,se crea una llamada y se encola para
             * la respectiva evaluaci�n asignaci�n de acuerdo a la
             * disponibilidad del Empleado
             */
            Dispatcher.queueCall(duracion);
            sleep();

        }
    }

// -------------------------- OTROS M�TODOS --------------------------
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
     * M�todo encargado de dormir el hilo con el fin de esperar un intervalo
     * para la asignaci�n de la siguiente llamada
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
