package Callcenter;

import java.io.Serializable;

/**
 *
 * @author Jotalvaro
 */
public class Llamada
    implements Serializable
{
// ------------------------------ FIELDS ------------------------------

    private final int duracion;
    private final int numero;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     *
     * @param numero
     * @param duracion
     */

    public Llamada( int numero, int duracion )
    {
        this.numero = numero;
        this.duracion = duracion;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    /**
     *
     * @return
     */

    public int getDuracion()
    {
        return duracion;
    }

    /**
     *
     * @return
     */
    public int getNumero()
    {
        return numero;
    }
}
