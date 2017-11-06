/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piggybank;

/**
 *
 * @author jotalvaro
 */
public class PiggyBank {


/**
 * Class that represent to PiggyBank 
 */

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

  
    private int amountCoin50;
    private int amountCoin100;
    private int amountCoin200;
    private int amountCoin500;
    private int amountCoin1000;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Piggy bank constructor. 
     * Initialized in 0.
     */
    public PiggyBank( )
    {
        amountCoin50 = 0;
        amountCoin100 = 0;
        amountCoin200 = 0;
        amountCoin500 = 0;
        amountCoin1000 = 0;
    }

    public int getNumberCoins50( )
    {
        return amountCoin50;
    }

 
    public int getNumberCoins100( )
    {
        return amountCoin100;
    }

    public int getNumberCoins200( )
    {
        return amountCoin200;
    }

    public int getNumberCoins500( )
    {
        return amountCoin500;
    }

    public int getNumberCoins1000( )
    {
        return amountCoin1000;
    }

    /**
     * .
     * @return return total amount of coins there are in Piggy bank.
     */
    public int calculateTotalAmount( )
    {
        return amountCoin50 + amountCoin100  + amountCoin200  + amountCoin500  + amountCoin1000;
    }

    public void setCoin50( )
    {
        amountCoin50 = amountCoin50+1;
    }

    public void setCoin100( )
    {
        amountCoin100 = amountCoin100+1;
    }

    public void setCoin200( )
    {
        amountCoin200 = amountCoin200+1;
    }

    public void setCoin500( )
    {
        amountCoin500 = amountCoin500+1;
    }

    public void setCoin1000( )
    {
        amountCoin1000 = amountCoin1000+1;
    }

    
}
