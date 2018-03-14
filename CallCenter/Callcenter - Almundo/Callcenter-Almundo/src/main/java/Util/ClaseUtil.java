/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Clase Util con métodos cómunes de la aplicación
 * @author Jotalvaro
 */
public final class ClaseUtil{
    
    private static SimpleDateFormat formatter;

    /**
     * Método encargado de generar un valor aleatorio entre 5 y 10 segundos y el cuál 
     * es es el valor de la duración de la llamada
     * @return duracion de la llamada
     */
    public static int duracionRandom(){
        Random r = new Random();
        int desde = Constantes.Parametros.DESDE;
        int hasta = Constantes.Parametros.HASTA;
        int duracion = r.nextInt(hasta-desde) + desde;
        
        return duracion;
    }
    
    /**
     * Método encargado de imprimir la secuencia de datos de cada una de las clases, esté método
     * se encargará de mostrar por consola cuándo la llamada se encola, cuando se genera
     * , cuando se asigna y cuándo se libera la llamada
     * 
     * @param mensaje
     * @param clase
     */
    public static void log( String mensaje, String clase)
    {
        System.out.println( "[" + date() + "]["+clase+"] " + mensaje );
    }
    
    /**
     * Método encargado de darle formato a la hora
     * @return Fecha con formato
     */
    public static String date(){
        formatter = new SimpleDateFormat(Constantes.Parametros.FORMATO_FECHA);
        return formatter.format( new Date() );
    }
      
    /**
     * Método encargado de asignar el tiempo en que la llamada finalizará
     * @param duracion
     * @return Tiempo duración de la llamada
     */
    public static long terminacion(int duracion){
        return System.currentTimeMillis() + ( duracion * Constantes.Parametros.SEGUNDOS );
    }

    
   
    
}
