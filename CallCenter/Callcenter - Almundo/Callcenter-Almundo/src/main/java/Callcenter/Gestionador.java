/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Callcenter;

import Entidades.OperadorEstado;
import Entidades.SupervisorEstado;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase gestionado para comunicar los hilos y conocer los estados de Operador,
 * Supervisor y Director
 *
 * @author Jotalvaro
 */
public class Gestionador {

    private static HashMap<Integer, OperadorEstado> operadores
            = new HashMap<Integer, OperadorEstado>();
    private static HashMap<Integer, SupervisorEstado> supervisores
            = new HashMap<Integer, SupervisorEstado>();

    /**
     *
     * @return
     */
    public static HashMap<Integer, OperadorEstado> getOperadores() {
        return operadores;
    }

    /**
     *
     * @param operadores
     */
    public static void setOperadores(HashMap<Integer, OperadorEstado> operadores) {
        Gestionador.operadores = operadores;
    }

    /**
     *
     * @return
     */
    public static HashMap<Integer, SupervisorEstado> getSupervisores() {
        return supervisores;
    }

    /**
     *
     * @param supervisores
     */
    public static void setSupervisores(HashMap<Integer, SupervisorEstado> supervisores) {
        Gestionador.supervisores = supervisores;
    }

    /**
     * Método encargado de validar los estados de todos los operadores, si
     * existe un operador libre la llamada no será asignada a los supervisores
     * ni al director, por el contrario si todos los operadores están ocupados
     * las llamadas podrán ser asignadas a los supervisores
     *
     * @return Verdadero o falso de acuerdo al estado de los operadores
     */
    public static Boolean operadoresOcupados() {
        Boolean ocup = true;
        for (Map.Entry<Integer, OperadorEstado> entry : operadores.entrySet()) {

            if (entry.getValue() == OperadorEstado.LIBRE) {
                ocup = false;
                return ocup;
            }
        }
        return ocup;
    }

    /**
     * Método encargado de validar los estados de todos los supervisores, si
     * existe un supervisor libre la llamada no será asignada a el director, por
     * el contrario si todos los supervisores están ocupados las llamadas podrán
     * ser asignadas al director
     *
     * @return Verdadero o falso de acuerdo al estado de los supervisores
     */
    public static Boolean supervisoresOcupados() {
        Boolean ocup = true;
        for (Map.Entry<Integer, SupervisorEstado> entry : supervisores.entrySet()) {

            if (entry.getValue() == SupervisorEstado.LIBRE) {
                ocup = false;
                return ocup;
            }

        }
        return ocup;
    }

}
