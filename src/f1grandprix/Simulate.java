/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author milan
 */
public class Simulate {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //kreiraj objekat klase Championship
        Championship grandPrix = new Championship("vozaci.txt", "staze.txt");
        
        //trazi od korisnika unos broja trka u sampionatu
        //pri cemu broj mora biti u opsegu 3-5
        System.out.print("Please enter the number of races in the championship ");
        System.out.println("(between 3 and 5 races)");
        int numOfRaces = ScannerUtility.scanInt();
        
        while(numOfRaces < 3 || numOfRaces > 5) {
            System.out.println("Invalid input, please enter a valid number of races (3 to 5 races allowed)");
            numOfRaces = ScannerUtility.scanInt();
        }
    }
}
