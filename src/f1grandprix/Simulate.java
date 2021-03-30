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
        int chosenRaceIndex;
        int numOfRaces;
        int numOfLaps;
        int currentRace = 1;
        int currentLap = 1;
        boolean championshipFinished = false;
        boolean isRaining = false;
        Venue chosenVenue;
        
        //trazi od korisnika unos broja trka u sampionatu
        //pri cemu broj mora biti u opsegu 3-5
        System.out.print("Please enter the number of races in the championship ");
        System.out.println("(between 3 and 5 races)");
        numOfRaces = ScannerUtility.scanInt();
        
        while(numOfRaces < 3 || numOfRaces > 5) {
            System.out.println("Invalid input, please enter a valid number of races (3 to 5 races allowed)");
            numOfRaces = ScannerUtility.scanInt();
        }
        
        while(!championshipFinished) {
            //dopusti korisniku da izabere stazu za trku
            chosenRaceIndex = userInterface(grandPrix.getVenues());
            
            //izabranu stazu izbrisi iz liste staza i sacuvaj u promenljivoj
            //chosenVenue
            chosenVenue = grandPrix.getVenues().remove(chosenRaceIndex);
            System.out.println("\nChosen venue: " + chosenVenue.getVenueName());
            System.out.println("*****RACE STARTING*****\n");
            numOfLaps = chosenVenue.getNumberOfLaps();
            
            //dodeli vozacima kazneno vreme na osnovu ranking-a
            grandPrix.prepareForTheRace();
            //postavi trenutni krug na 1
            currentLap = 1;
            
            while(currentLap <= numOfLaps) {
                System.out.println("Lap no. " + currentLap);
                //svi vozaci odvoze srednje vreme kruga
                grandPrix.driveAverageTime(chosenVenue.getAverageLapTime());
                //primeni vestine vozaca
                grandPrix.applySpecialSkills(currentLap);
                //proveriti da li ce neki vozaci promeniti pneumatike
                grandPrix.changeTires();
                //proveri da li je pala kisa i kazni vozace bez pneumatika
                //za kisu
                grandPrix.checkRain(chosenVenue.getChanceOfRain());
                //na kraju, proveri mehanicke kvarove
                grandPrix.checkMechanicalProblems();
                System.out.println("\n");
                ++currentLap;
            }
            
            //na kraju ispisi tabelu sa vozacima
            grandPrix.printWinnersAfterRace(chosenVenue.getVenueName());
            
            ++currentRace;
            if(currentRace > numOfRaces) {
                championshipFinished = true;
                //na kraju sampionata, ispisi sampiona
                grandPrix.printChampion(numOfRaces);
            }
        }
    }
    
    /**
     * Metoda koja ispisuje korisnicki interfejs
     * i ocitavaju izabranu opciju
     * 
     * @param availableVenues Lista preostalih traka za dati sampionat
     * <br>
     * Ova lista se dobija sa getVenues() metodom klase Championship
     * @return Indeks izabrane staze za datu trku
     */
    static int userInterface(ArrayList<Venue> availableVenues) {
        int option;
        
        do {
            System.out.println("");
            System.out.println("Choose a venue:");
            for(int i = 1; i <= availableVenues.size(); ++i) 
                System.out.println(i + ") " + availableVenues.get(i-1).getVenueName());
            option = ScannerUtility.scanInt();
            
            if(option < 1 || option > availableVenues.size()) 
                System.out.println("\nInvalid choice! Please choose a valid option");
        } while (option < 1 || option > availableVenues.size());
        
        return option - 1;
    }
}
