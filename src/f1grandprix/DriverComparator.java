/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

import java.util.Comparator;

/**
 *
 * @author milan
 */
public class DriverComparator implements Comparator<Driver>{
    //atribut koji oznacava polje po kom ce se objekti klase Driver
    //sortirati, moze biti "accumulatedTime" ili "accumulatedPoints"
    String fieldName;
    
    /**
     * Konstruktor DriverComparator klase
     * @param sortOption Parametar na osnovu kog se odlucuje
     * kako ce se objekti klase driver sortirati.
     * <br>
     * Dozvoljene opcije su "accumulatedPoints" ili "accumulatedTime"
     * <br>
     * Ukoliko korisnik ne unese jednu od ove dve opcije, bice izabrana
     * podrazumevana opcija, sortiranje po poenima.
     */
    public DriverComparator(String sortOption) {
        if(sortOption.equalsIgnoreCase("accumulatedTime") ||
           sortOption.equalsIgnoreCase("accumulatedPoints"))
            fieldName = sortOption;
        else
            fieldName = "accumulatedPoints";
    }
    
    public int compare(Driver drv1, Driver drv2) {
        if(fieldName.equalsIgnoreCase("accumulatedTime")) {
            //sortiranje po vremenu, uzeti u obzir da li je vozac
            //u stanju da se trka (eligibleToRace == true)
            if(drv1.isEligibleToRace() == false)
                return 1;
            
            if(drv2.isEligibleToRace() == false)
                return -1;
            
            int driverTime1 = drv1.getAccumulatedTime();
            int driverTime2 = drv2.getAccumulatedTime();
            
            if(driverTime1 < driverTime2)
                return -1;
            else if(driverTime1 == driverTime2)
                return 0;
            else
                return 1;
        } else {
            //sortiranje po poenima
            int driverPoints1 = drv1.getAccumulatedPoints();
            int driverPoints2 = drv2.getAccumulatedPoints();
            if(driverPoints1 > driverPoints2)
                return -1;
            else if(driverPoints1 == driverPoints2)
                return 0;
            else
                return 1;
        }
    }
}
