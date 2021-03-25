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
public class Driver {
    //atributi
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedTime;
    private int accumulatedPoints;
    
    /**
     * Konstruktor klase Driver
     * 
     * @param driverString String koji sadrzi informacije o vozacu u sledecoj formi:
     * <br>
     * ime prezime, ranking, specialskill
     */
    public Driver(String driverString) {
        String [] tokens = driverString.split(",");
        
        if(tokens.length == 3) {
            try {
                name = tokens[0];
                ranking = Integer.parseInt(tokens[1]);
                specialSkill = tokens[2];
            } catch(Exception e) {
                //ako parsiranje tokena za rank ne uspe
                System.out.println("Unable to read driver data!");
                System.exit(0);
            }
        } else {
            System.out.println("Driver info is not in the valid format!");
            System.exit(0);
        }
        
        //default vrednosti za pojedine atribute
        eligibleToRace = true;
        accumulatedPoints = 0;
        accumulatedTime = 0;
    }
    
    /**
     * Funkcija koja pomocu generatora pseudoslucajnih brojeva
     * odredjuje da li ce vozac iskoristiti svoju specijalnu vestinu
     * u datom krugu
     */
    public void useSpecialSkill() {
        
    }
    
    //**********GETTER FUNKCIJE***********
    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public boolean isEligibleToRace() {
        return eligibleToRace;
    }

    public int getAccumulatedTime() {
        return accumulatedTime;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }
    //************************************
    
    //**********SETTER FUNKCIJE***********
    public void setName(String name) {
        this.name = name;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public void setEligibleToRace(boolean eligibleToRace) {
        this.eligibleToRace = eligibleToRace;
    }

    public void setAccumulatedTime(int accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }
    //************************************
}
