/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piggybank;

import java.util.Scanner;

/**
 *
 * @author jotalvaro
 */
public class PiggyMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);

        //Create a piggy bank object called 'bank'
        PiggyBank bank = new PiggyBank();

        System.out.println("Welcome to Gap's Piggy Bank!");

        //Displays the menu options to the user before they make a choice
        displayMenu();      

        //Read in the reader's selection into 'choice'
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        
        //Program can run continuosly until the user enters 0 for choice.
         while (choice != 0){

            //Show total in the piggy bank
            switch (choice) {
                case 1:
                    separator();
                    System.out.println("Total coins in Piggy Bank: "+bank.calculateTotalAmount());
                    separator();
                    break;
                case 2:
                    bank.setCoin50();
                    break;
                case 3:
                    bank.setCoin100();
                    break;
                case 4:
                    bank.setCoin200();
                    break;
                case 5:
                    bank.setCoin500();
                    break;
                case 6:
                    bank.setCoin1000();
                    break;
                case 7:
                    separator();
                    displaySubMenu();
                    subMenu(bank);
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid input.\n");
                    break;
            }
            separator();
            //Start next round of options.
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();   
        }
    }
    
        //----------------------------------------------------------------
    //displayMenu(): Displays the menu on the screen
    //----------------------------------------------------------------
    public static void displayMenu(){
        System.out.println("Select one of the following choices or 0 to exit:");
        System.out.println("1. Show total coins in bank.");
        System.out.println("2. Add a coin of 50");
        System.out.println("3. Add a coin of 100");
        System.out.println("4. Add a coin of 200");
        System.out.println("5. Add a coin of 500");
        System.out.println("6. Add a coin of 1000");
        System.out.println("7. Show specific number of coins by name.");

    }
    
    public static void displaySubMenu(){
        System.out.println("Select one of the following choices:");
        System.out.println("1. Show total coins coin of 50");
        System.out.println("2. Show total coins coin of 100");
        System.out.println("3. Show total coins coin of 200");
        System.out.println("4. Show total coins coin of 500");
        System.out.println("5. Show total coins coin of 1000");

    }
    
    public static void subMenu(PiggyBank bank){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choiceSubmenu = input.nextInt();
        separator();
        separator();
        switch (choiceSubmenu) {
            case 1:
                System.out.println("Total coins of 50: "+bank.getNumberCoins50());
                break;
            case 2:
                System.out.println("Total coins of 100: "+bank.getNumberCoins100());
                break;
            case 3:
                System.out.println("Total coins of 200: "+bank.getNumberCoins200());
                break;
            case 4:
                System.out.println("Total coins of 500: "+bank.getNumberCoins500());
                break;
            case 5:
                System.out.println("Total coins of 1000: "+bank.getNumberCoins500());
                break;    
            default:
                System.out.println();
                System.out.println("Invalid input.\n");
                break;
        }
        separator();
    }
    
    public static void separator(){
        System.out.println("----------------------------------------------------");
    }
    
    
}
