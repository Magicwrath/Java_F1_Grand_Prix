/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

/**
 *
 * @author milan
 */
public class Venue {
    //atributi
    private int averageLapTime;
    private double chanceOfRain;
    private int numberOfLaps;
    private String venueName;
    
    /**
     * Konstruktor klase Venue
     * @param venueString String linija koja se cita iz .txt fajla sa stazama
     */
    public Venue(String venueString) {
        //tokenizuj string na delove izmedju zareza
        String [] tokens = venueString.split(",");
        if(tokens.length == 4) {
            try {
                venueName = tokens[0];
                numberOfLaps = Integer.parseInt(tokens[1]);
                averageLapTime = Integer.parseInt(tokens[2]);
                chanceOfRain = Double.parseDouble(tokens[3]);
            } catch(Exception e) {
                System.out.println("Unable to read Venue data!");
                System.exit(0);
            }
        } else {
            System.out.println("Venue information doesn't follow required format");
            System.exit(0);
        }
    }
    
    //**********GETTER I SETTER FUNCKIJE**********
    public int getAverageLapTime() {
        return averageLapTime;
    }

    public double getChanceOfRain() {
        return chanceOfRain;
    }

    public int getNumberOfLaps() {
        return numberOfLaps;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setAverageLapTime(int averageLapTime) {
        this.averageLapTime = averageLapTime;
    }

    public void setChanceOfRain(double chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public void setNumberOfLaps(int numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    
}
