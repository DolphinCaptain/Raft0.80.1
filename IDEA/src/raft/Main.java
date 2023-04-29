package raft;

import palya.Palya;

/**
 * Itt fut a jatek!
 */
public class Main {
    public static void main(String[] args) {
        Palya palya = new Palya(0,0,0);
        System.out.println("Udvozollek a RAFT-ban!\nA jatek iranyitasarol a README.txt-ben tajekozodhatsz!\nA jatek celja hogy Bobbal azaz a karaktereddel tulelj 1000 cselekvest!\nNyomj egy Enter-t a palya betoltesehez");
        System.out.println();

        palya.futtatas();


    }
}