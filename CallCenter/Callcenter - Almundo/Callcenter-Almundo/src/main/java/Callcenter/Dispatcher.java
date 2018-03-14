package Callcenter;

import Util.ClaseUtil;
import Util.Constantes;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Clase encargada de la gestión, almacenamiento y distribución de las llamadas
 * @author Jotalvaro
 */
public class Dispatcher {
// ------------------------------ FIELDS ------------------------------

    private static Dispatcher instance;
    private int counter;
    private final LinkedBlockingQueue<Llamada> queue;

// -------------------------- STATIC METHODS --------------------------
    /**
     * Método encargado de encolar la llamada para su respectiva asignación en
     * caso de que ningún empleado se encuentre disponible para posteriormente
     * asignarsela
     */
    public static void queueCall(int duration) {
        try {
            Llamada call = new Llamada(getInstance().counter++, duration);
            ClaseUtil.log(Constantes.Mensajes.DISPATCH.
                    replace("%s", String.valueOf(call.getNumero())).
                    replace("%d", String.valueOf(call.getDuracion())),
                    Dispatcher.class.getSimpleName());
            getInstance().queue.put(call);
        } catch (InterruptedException e) {
            ClaseUtil.log(Constantes.Mensajes.ERROR, Dispatcher.class.getSimpleName());
        }
    }

    /**
     * Método encargado de desencolar y retornar la llamada de la cola para la
     * respectiva asignación al empleado
     *
     * @return la llamada
     */
    public static Llamada dispatchCall() {
        Llamada call = getInstance().queue.poll();

        return call;
    }

    /**
     * Método encargado de retornar la instancia de la clase Dispatcher
     *
     * @return instancia Dispatcher
     */
    private static Dispatcher getInstance() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

// --------------------------- CONSTRUCOTOR ---------------------------
    private Dispatcher() {
        this.queue = new LinkedBlockingQueue<Llamada>();
        this.counter = 1;
    }
}
