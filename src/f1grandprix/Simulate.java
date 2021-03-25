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
    }
}
