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
    //konstanta koja oznacava sansu da se promene pneumatici
    //tokom kruga
    final int LAP_TIRE_CHANGE = 50;
    //atributi
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedTime;
    private int accumulatedPoints;
    //ovaj atribut sam ja dodao, da bi
    //svaki vozac imao svoju RNG klasu
    //sa atributima u zavisnosti od njegove vestine
    private RNG skillRNG;
    //moji dodati atributi
    //promenljiva koja oznacava da li vozac ima pneumatike za kisu
    private boolean usingRainTires;
    
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
            
            //konstruisi RNG objekat sa atributima u zavisnosti od vestine vozaca
            if(specialSkill.equalsIgnoreCase("overtaking")) {
                //preticanje moze skratiti vreme za 10-20s
                skillRNG = new RNG(10, 20);
            } else {
                //ostale vestine mogu skratiti vreme za 1-8s
                skillRNG = new RNG(1, 8);
            }
        } else {
            System.out.println("Driver info is not in the valid format!");
            System.exit(0);
        }
        
        //default vrednosti za pojedine atribute
        eligibleToRace = true;
        accumulatedPoints = 0;
        accumulatedTime = 0;
        //vozac pocinje trku sa pneumaticima za suvo vreme
        usingRainTires = false;
    }
    
    /**
     * Funkcija koja pomocu generatora pseudoslucajnih brojeva
     * odredjuje da li ce vozac iskoristiti svoju specijalnu vestinu
     * u datom krugu.
     * <br>
     * @param currentLap Trenutni krug u voznji staze, na osnovu ovog
     * parametra se odredjuje da li ce se vestina iskoristiti
     */
    public void useSpecialSkill(int currentLap) {
        int savedTime;
        if(specialSkill.equalsIgnoreCase("overtaking")) {
            //ova vestina moze svaki 3. krug
            if(currentLap % 3 == 0) {
                savedTime = skillRNG.getRandomValue();
                accumulatedTime -= savedTime;
                //System.out.println("Driver " + name + " used overtaking for " + savedTime +"s!");
            } 
        } else {
            //ostale vestine mogu svaki krug
            savedTime = skillRNG.getRandomValue();
            accumulatedTime -= savedTime;
            //System.out.println("Driver " + name + " used braking/cornering for " + savedTime +"s!");
        }
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
    
    public boolean isUsingRainTires() {
        return usingRainTires;
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
    
    public void setUsingRainTires(boolean usingRainTires) {
        this.usingRainTires = usingRainTires;
    }
    //************************************
}
