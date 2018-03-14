/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 * Clase Constantes
 * @author Jotalvaro
 */
public class Constantes {

    /**
     * Parámetros y mensajes
     */
    public static final class Mensajes {

        /**
         * Mensajes
         */
        public static final String RESPONDIENDO = "Respondiendo llamada ";
        public static final String COLGANDO = "Colgando ";
        public static final String LLAMADA = "Creando una llamada con una duracion de %s segundos.";
        public static final String ERROR = "Hubo un error encolando la llamada.";
        public static final String DISPATCH = "Encolando llamada %s con duración de %d segundos";

    }

    
    public static final class Parametros {

        /**
         * Parámetros
         */
        public static final int DESDE = 5;
        public static final int HASTA = 10;
        public static final String FORMATO_FECHA = "HH:mm:ss";
        public static final int SLEEP = 5000;
        public static final int SEGUNDOS = 1000;
    }

}
