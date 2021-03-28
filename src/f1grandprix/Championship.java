/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author milan
 */
public class Championship {
    //atributi
    private ArrayList<Driver> drivers;
    private ArrayList<Venue> venues;
    
    //konstante
    final int MINOR_MECHANICAL_FAULT = 5;
    final int MAJOR_MECHANICAL_FAUlT = 3;
    final int UNRECOVERABLE_MECHANICAL_FAULT = 1;
    
    /**
     * Konstruktor za klasu Championship
     * @param driverFileName String koji predstavlja ime tekstualnog fajla koji sadrzi
     * informacije o vozacima, npr. "vozaci.txt"
     * @param venueFileName String koji predstavlja ime tekstualnog fajla koji sadrzi 
     * informacije o stazama, npr. "staze.txt"
     */
    public Championship(String driverFileName, String venueFileName) {
        //kreiraj ArrayList za vozace i staze
        drivers = new ArrayList<>();
        venues = new ArrayList<>();
        String sp = System.getProperty("file.separator");
        File venueFile, driverFile;
        
        //otvori fajlove u extra folderu, sa imenima venueFileName i driverFileName
        venueFile = new File("." + sp + "extra" + sp + venueFileName);
        driverFile = new File("." + sp + "extra" + sp + driverFileName);
        
        BufferedReader rd;
        
        //bufferovani citac, koji se spregnut sa FileInputStream, jer ne treba
        //voditi racuna o UTF-8 enkodiranju
        try {
            //probaj da otvoris fajl sa stazama i hvataj exception u slucaju
            //da se fajl nije mogao otvoriti
            rd = new BufferedReader(new InputStreamReader(
                new FileInputStream(venueFile)));
            
            String lineString;
            while((lineString = rd.readLine()) != null) {
                //kreiraj stazu sa informacijama iz procitane linije
                venues.add(new Venue(lineString));
            }
            rd.close(); //zatvori fajl
        } catch(FileNotFoundException fnfe) {
            System.out.println("Venue file not found!");
            System.exit(0);
        } catch(IOException ioe) {
            System.out.println("Can't read venue file contents!");
            System.exit(0);
        }
        
        //uradi isto za driver fajl
        try {
            //probaj da otvoris fajl sa vozacima i hvataj exception u slucaju
            //da se fajl nije mogao otvoriti
            rd = new BufferedReader(new InputStreamReader(
                new FileInputStream(driverFile)));
            
            String lineString;
            while((lineString = rd.readLine()) != null) {
                //kreiraj stazu sa informacijama iz procitane linije
                drivers.add(new Driver(lineString));
            }
            rd.close(); //zatvori fajl
        } catch(FileNotFoundException fnfe) {
            System.out.println("Driver file not found!");
            System.exit(0);
        } catch(IOException ioe) {
            System.out.println("Can't read driver file contents!");
            System.exit(0);
        }
    }
    
    /**
     * Metoda koja dodeljuje rank-ove vozacima na osnovu
     * njihovih akumuliranih poena
     */
    void awardRanks() {
        //sortiraj vozace na osnovu akumuliranih poena
        Collections.sort(drivers, new DriverComparator("accumulatedPoints"));
        //prva 4 vozaca dobijaju rank-ove 1-4
        //ostali vozaci dobijaju rank 5
        int rank = 1;
        for(Driver drv : drivers) {
            drv.setRanking(rank);
            if(rank < 5)
                ++rank;
        }
    }
    
    /**
     * Metoda koja 
     * inicijalizuje atribute vozaca (npr. resetuje mogucnost
     * vozaca da se trka, eligibleToRace) i dodeljuje vr. kasnjenja
     * na osnovu pozicije vozaca u sampionatu.
     */
    void prepareForTheRace() {
        //na pocetku svake trke, svakom vozacu daj mogucnost da se trka
        //takodje, akumulirano vreme mu postavi na nulu i kasnjenja na
        //osnovu ranking-a vozaca
        for(Driver drv : drivers) {
            drv.setEligibleToRace(true);
            drv.setAccumulatedTime(0);
            int rank = drv.getRanking();

            switch(rank) {
                case 1:
                    //prvo mesto nema vr. kaznu
                    break;
                case 2:
                    //drugo mesto dobija 3s kazne
                    drv.setAccumulatedTime(3);
                    break;
                case 3:
                    //trece mesto 5s kazne
                    drv.setAccumulatedTime(5);
                    break;
                case 4:
                    //cetvrto mesto dobija 7s kazne
                    drv.setAccumulatedTime(7);
                    break;
                default:
                    //ostala mesta dobijaju 10s kazne
                    drv.setAccumulatedTime(10);
                    break;
            }
        }
    }
    
    /**
     * Metoda koja svim vozacima dodeljuje prosecno vreme voznje
     * kruga staze. S obzirom da se u Simulate klasi bira staza, njeno prosecno
     * vreme kruga ce se proslediti kao parametar.
     * @param avgTime Prosecno vreme voznje kruga
     */
    void driveAverageTime(int avgTime) {
        int accTime;
        for(Driver drv : drivers) {
           accTime = drv.getAccumulatedTime();
           accTime += avgTime;
           drv.setAccumulatedTime(accTime);
        }
    }
    
    /**
     * Metoda koja na osnovu vestine vozaca skracuje akumulirano vreme
     * za odredjenu kolicinu.
     * <br>
     * Poziva se metoda useSpecialSkill() klase Driver koja na osnovu
     * vestine vozaca generise slucajan broj koji oduzima od akumuliranog
     * vremena.
     */
    void applySpecialSkills() {
        for(Driver drv : drivers) {
            drv.useSpecialSkill();
        }
    }
    
    /**
     * Metoda koja za svakog vozaca proverava da li se desio
     * kvar. U slucaju da se kvar desio, dodaje se adekvatno vreme
     */
    void checkMechanicalProblem() {
        //prvo se proverava verovatnoca za najmanje verovatan slucaj
        //odnosno, za neotklonjiv mehanicki kvar
        RNG faultRNG;
        //ako smatramo da je generator pseudosl. brojeva idealno
        //slucajan, svaki broj ima verovatnocu 1/100 da se generise,
        //odnosno 1% verovatnoce
        //to znaci da za verovatnocu 5% da ce se desiti neki kvar
        //dovoljno je da generator generise jedan od 5 brojeva
        //npr. 1-5...
        for(Driver drv : drivers) {
            faultRNG = new RNG(1, 100); //da bi dobili drugi seed
            int accTime = drv.getAccumulatedTime();
            int faultProbability = faultRNG.getRandomValue();
            
            if(faultProbability <= UNRECOVERABLE_MECHANICAL_FAULT) {
                //neotklonjiv kvar, vozac ne moze vise da se trka
                drv.setEligibleToRace(false);
                System.out.println("Driver " + drv.getName() + " suffered an unrecoverable fault!");
            } else if(faultProbability <= MAJOR_MECHANICAL_FAUlT) {
                //veliki kvar, dodatnih 120s na akumulirano vreme
                accTime += 120;
                System.out.println("Driver " + drv.getName() + " suffered a major fault!");
            } else if(faultProbability <= MINOR_MECHANICAL_FAULT) {
                //mali kvar, dodatnih 20s na akumulirano vreme
                accTime += 20;
                System.out.println("Driver " + drv.getName() + " suffered a minor fault");
            }
            
            drv.setAccumulatedTime(accTime);
        }
    }
    
    /**
     * Metoda koja se koristi nakon
     * svakog kruga da ispise vozaca sa najmanjim akumuliranim
     * vremenom
     * 
     * @param lap Parametar koji se koristi da bi se u ispisu
     * naznacilo o kom krugu je rec
     * 
     * PITATI PROFESORA DA LI JE POTREBAN PARAMETAR int lap
     */
    void printLeader(int lap) {
        Driver tmpDriver;
        Driver leader = drivers.get(0);
        int shortestTime = leader.getAccumulatedTime();
        
        for(int i = 1; i < drivers.size(); ++i) {
            tmpDriver = drivers.get(i);
            if(tmpDriver.getAccumulatedTime() < shortestTime) {
                leader = tmpDriver;
                shortestTime = tmpDriver.getAccumulatedTime();
            }
        }
        
        //ispisi informacije o vozacu sa najmanjim
        //akumuliranim vremenom
        System.out.println("Leader after lap" + lap + ":");
        System.out.println(leader.getName());
        System.out.println("Time: " + leader.getAccumulatedTime());
    }
    
    /**
     * Metoda koja ispisuje prva cetiri mesta nakon zavrsene trke.
     * <br>
     * Takodje, azuriraju se akumulirani poeni datih vozaca.
     * @param venueName Ime staze na kojoj se odrzavala trka
     */
    void printWinnersAfterRace(String venueName) {
        //sortiraj vozace na osnovu vremena
        Collections.sort(drivers, new DriverComparator("accumulatedTime"));
        int awardPoints[] = {8, 5, 3, 1}; //poeni za mesta
        
        //prva 4 vozaca u sortiranoj kolekciji dobijaju poene
        //ostali ne
        System.out.println("***************");
        System.out.println("Winners at " + venueName + ":");
        Driver drv;
        for(int i = 0; i < 4; ++i) {
            drv = drivers.get(i);
            System.out.println(i + ": " + drv.getName() + " " + drv.getAccumulatedTime());
            
            int accPoints = drv.getAccumulatedPoints();
            accPoints += awardPoints[i];
            drv.setAccumulatedPoints(accPoints);
        }
        System.out.println("***************");
    }
    
    /**
     * Metoda koja ispisuje ime vozaca koji je osvojio najvise poena
     * tokom sampionata sa numOfRaces trka
     * @param numOfRaces Parametar koji sluzi da se naznaci u ispisu koliko je
     * bilo trka
     */
    void printChampion(int numOfRaces) {
        //s obzirom da awardRanks() metoda sortira
        //na osnovu akumuliranih poena, dovoljno je samo
        //iscitati ime vozaca na prvom mestu u kolekciji
        //drivers
        Driver champion = drivers.get(0);
        System.out.println("**********CHAMPIONSHIP WINNER AFTER " + numOfRaces + "**********");
        System.out.println(champion.getName());
        System.out.println("************************************");
    }
    
    //**********GETTER I SETTER FUNKCIJE**********
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }
    
}
