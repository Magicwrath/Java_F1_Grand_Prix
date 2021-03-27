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
        // TODO code application logic here
        Driver drv;
        drv = new Driver("Juan Manuel Fangio,3,Overtaking");
        System.out.println(drv.getName());
        System.out.println(drv.getRanking());
        System.out.println(drv.getSpecialSkill());
        
        RNG generator = new RNG(10, 20);
        System.out.println(generator.getRandomValue());
        
        Venue ven;
        ven = new Venue("Albert Park Raceway Australia,3,79,0.05");
        System.out.println(ven.getVenueName());
        System.out.println(ven.getNumberOfLaps());
        System.out.println(ven.getAverageLapTime());
        System.out.println(ven.getChanceOfRain());
        
        Championship chmp;
        chmp = new Championship("vozaci.txt", "staze.txt");
        ArrayList<Driver> drivers = chmp.getDrivers();
        //pokusaj da sortiras vozace
        Collections.sort(drivers);
    }
}
