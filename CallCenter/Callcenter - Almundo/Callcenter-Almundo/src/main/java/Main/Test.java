package Main;

import Callcenter.Dispatcher;
import Empleado.Director;
import Callcenter.Generador;
import Empleado.Operador;
import Empleado.Supervisor;
import Util.ClaseUtil;

/**
 *
 * @author Jotalvaro
 */
public class Test
{
// --------------------------- main() method ---------------------------

    /**
     *
     * @param args
     */

    public static void main( String... args )
    {
        //Prueba unitaria con 3 operadores
        for ( int i = 0; i < 3; i++ )
        {
            new Operador( i + 1 ).start();
        }
        
        //Prueba unitaria con 10 operadores
//        for ( int i = 0; i < 10; i++ )
//        {
//            new Operador( i + 1 ).start();
//        }
        
        //Prueba unitaria con 2 supervisores
        for ( int i = 0; i < 2; i++ )
        {
            new Supervisor( i + 1 ).start();
        }
        
        //Prueba unitaria con 5 supervisores
//        for ( int i = 0; i < 5; i++ )
//        {
//            new Supervisor( i + 1 ).start();
//        }
        
        //Prueba unitaria con 1 director
        new Director().start();
        
        //Prueba unitaria parardo la ejecución
        //new Director(). stop();

        //Prueba unitaria de método que se encarga de generar/simularar llamadas automaticas
        //new Generador().start();
        
        //Prueba unitaria que consiste en inyectar 10 llamadas de modo concurrente
        for (int i = 0; i < 10; i++) {
            Dispatcher.queueCall(ClaseUtil.duracionRandom());
        }
        
        //Prueba unitaria que consiste en inyectar 100 llamadas de modo concurrente
//        for (int i = 0; i < 100; i++) {
//            Dispatcher.queueCall(ClaseUtil.duracionRandom());
//        }
    }
}
