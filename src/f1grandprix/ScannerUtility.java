/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

import java.util.Scanner;

/**
 *
 * @author milan
 */
public class ScannerUtility {
    //Scanner za ocitavanje unosa korisnika
    static Scanner sc = new Scanner(System.in);
    
    /**
     * Metoda koja poziva hasNextInt() klase Scanner dok
     * se ne ocita ispravan celobrojni unos sa tastature
     * @return ceo broj skeniran sa korisnickog unosa
     */
    public static int scanInt() {
        boolean finishedScan = false;
        int scannedInt = 0;
        do {
            if(sc.hasNextInt()) {
                scannedInt = sc.nextInt();
                finishedScan = true;
            } else {
                System.out.println("Invalid input, expected integer!");
            }
            sc.nextLine();
        } while(!finishedScan);
        
        return scannedInt;
    }
}
