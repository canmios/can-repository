package Empleado;

import Callcenter.Dispatcher;
import Callcenter.Gestionador;
import Callcenter.Llamada;
import Entidades.DirectorEstado;
import Interface.EmpleadoInterface;
import Util.ClaseUtil;
import Util.Constantes;

/**
 * Clase director que se encarga de ejecutar un hilo en intervalos de tiempo
 * para verificar la asignación de llamadas al director
 *
 * @author Jotalvaro
 */
public class Director
        implements Runnable, EmpleadoInterface {
// ------------------------------ CAMPOS ------------------------------

    private long finllamada;
    private boolean running;
    private DirectorEstado status;

// --------------------------- CONSTRUCTORES ---------------------------
    /**
     *
     */
    public Director() {
        super();
        this.status = DirectorEstado.LIBRE;
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
     * gestionadora,si existen operadores y supervisores ocupados y si existen
     * directores disponibles será asignada la llamada por el método
     * dispathCall, por lo cual si no cumple las condiciones no se asignará y la
     * llamada no será desencolada. el método también controla el estado y
     * duración de la llamada del director
     */
    public void verificarLlamada() {
        if (Gestionador.operadoresOcupados() 
                && Gestionador.supervisoresOcupados() 
                     && status == DirectorEstado.LIBRE) {
            //Invocación al dispatchCall, si existen llamadas y cumplen las condiciones será asignada al director
            Llamada call = Dispatcher.dispatchCall();
            if (call != null) {
                ClaseUtil.log(Constantes.Mensajes.RESPONDIENDO + call.getNumero(), Director.class.getSimpleName());
                finllamada = ClaseUtil.terminacion(call.getDuracion());
                status = DirectorEstado.EN_LLAMADA;

            }
        } else //Se verifica si la llamada ha terminado
        if (System.currentTimeMillis() > finllamada && finllamada != 0) {
            ClaseUtil.log(Constantes.Mensajes.COLGANDO, Director.class.getSimpleName());
            status = DirectorEstado.LIBRE;
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
