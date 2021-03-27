/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1grandprix;

import java.util.Random;

/**
 *
 * @author milan
 */
public class RNG {
    //atributi
    private int minimumValue;
    private int maximumValue;
    private Random rnd;
    
    /**
     * Konstruktor klase RNG koja generise pseudoslucajan broj u odredjenom opsegu
     * @param minimumValue Minimalna vrednost opsega u kome ce se generisati pseudoslucajan broj
     * @param maximumValue Maksimalna vrednost opsega u kome ce se generisati pseudoslucajan broj
     */
    public RNG(int minimumValue, int maximumValue) {
        this.maximumValue = maximumValue;
        this.minimumValue = minimumValue;
        rnd = new Random();
    }
    
    /**
     * 
     * @return Vraca broj u opsegu [minimumValue, maximumValue] u zavisnosti od atributa klase RNG
     * minimumValue i maximumValue
     */
    int getRandomValue() {
        int rndNumber;
        
        //nextInt(maximumValue - minimumValue
        //vraca broj u opsegu [0, maximumValue - minimumValue + 1)
        //zatim se taj broj pomera za minimumValue i zavrsava u opsegu
        //[minimumValue, maximumValue + 1)
        rndNumber = rnd.nextInt(maximumValue - minimumValue + 1) + minimumValue;
        return rndNumber;
    }
    
    //**********GETTER I SETTER FUNKCIJE**********
    public int getMinimumValue() {
        return minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }
    
}
