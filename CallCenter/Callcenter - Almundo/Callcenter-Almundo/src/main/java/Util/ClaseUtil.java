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
 * Clase Util con m�todos c�munes de la aplicaci�n
 * @author Jotalvaro
 */
public final class ClaseUtil{
    
    private static SimpleDateFormat formatter;

    /**
     * M�todo encargado de generar un valor aleatorio entre 5 y 10 segundos y el cu�l 
     * es es el valor de la duraci�n de la llamada
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
     * M�todo encargado de imprimir la secuencia de datos de cada una de las clases, est� m�todo
     * se encargar� de mostrar por consola cu�ndo la llamada se encola, cuando se genera
     * , cuando se asigna y cu�ndo se libera la llamada
     * 
     * @param mensaje
     * @param clase
     */
    public static void log( String mensaje, String clase)
    {
        System.out.println( "[" + date() + "]["+clase+"] " + mensaje );
    }
    
    /**
     * M�todo encargado de darle formato a la hora
     * @return Fecha con formato
     */
    public static String date(){
        formatter = new SimpleDateFormat(Constantes.Parametros.FORMATO_FECHA);
        return formatter.format( new Date() );
    }
      
    /**
     * M�todo encargado de asignar el tiempo en que la llamada finalizar�
     * @param duracion
     * @return Tiempo duraci�n de la llamada
     */
    public static long terminacion(int duracion){
        return System.currentTimeMillis() + ( duracion * Constantes.Parametros.SEGUNDOS );
    }

    
   
    
}
