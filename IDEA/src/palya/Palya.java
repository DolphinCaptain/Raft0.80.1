package palya;

import elolenyek.Bob;
import elolenyek.Capa;
import tuleles.EvesIvas;
import tuleles.Nyersanyagok;

import java.util.Scanner;


/**
 * A Palya nevu classban van a jatekom fo ciklusa ami minden cselekves utan
 * az adott lepes szerint legeneralja a palyat es kiirja a jatekos adtatait
 */
public class Palya {
    public static int cselSzam;
    public static int vizszamolo;
    public static int etelszamolo;

    public Palya(int cselSzam, int vizszamolo, int etelszamolo) {
        Palya.cselSzam = cselSzam;
        Palya.vizszamolo = vizszamolo;
        Palya.etelszamolo = etelszamolo;

    }

    /**
     * A feltoltes nevu metodus vegigmegy a Tutaj osztalyban definialt tutaj szamokbol allo tomb elemein, miindegyiket
     * ellenorzi es a feltetelkenek megfeleloen kicsereli a szamokat a megfelelelo szimbolumokra, majd az igy
     * legenearalt palyara beilleszti a karakterunket Bobot es a capat a megfelelo helyre, ezutan tortenik a
     * palya kiiratasa a kepernyore a mar kicserelt es igy megfelelo karakterekkel.
     *
     * @param palya = a megfelelo szimbolumokkkal legeneralt tomb
     * @param tutaj = szamokbol allo tomb a Tutaj osztalybol
     * @param capa  = a capa
     * @param bob   = a karakterunk
     */
    public static void feltoltes(char[][] palya, Tutaj tutaj, Capa capa, Bob bob) {
        for (int i = 1; i < palya.length - 1; i++) {
            for (int j = 1; j < palya[i].length - 1; j++) {
                palya[i][j] = '~';

                if (tutaj.getTutaj()[i][j] == 1) {      //tutaj
                    palya[i][j] = '#';
                }
                if (tutaj.getTutaj()[i][j] == 2) {      //halo
                    palya[i][j] = '%';
                }
                if (tutaj.getTutaj()[i][j] == 3) {      //viztisztito
                    palya[i][j] = 'V';
                }
                if (tutaj.getTutaj()[i][j] == 4) {      //tuzhely
                    palya[i][j] = 'T';
                }
                if (tutaj.getTutaj()[i][j] == 5) {      //level
                    palya[i][j] = 'L';
                }
                if (tutaj.getTutaj()[i][j] == 6) {      //deszka
                    palya[i][j] = 'D';
                }
                if (tutaj.getTutaj()[i][j] == 7) {      //hulladek
                    palya[i][j] = '&';
                }
                if (tutaj.getTutaj()[i][j] == 8) {      //hordo
                    palya[i][j] = 'H';
                }

                palya[capa.getX()][capa.getY()] = 'S';      //a capa a megfelelo helyere
                palya[bob.getX()][bob.getY()] = 'B';        //a karakterunk Bob a megeleleo helyere
            }
        }

        for (char[] chars : palya) {
            for (char aChar : chars) {
                System.out.print(aChar);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    /**
     * A futtatas a jatek legfontosabb metodusa amely a mainben fog meghivodni. Ebben a metodusban egy ciklus talalhato
     * ami minden egyes cselekves utan kiirja a karakterunk tehat Bob adatait majd legeneralja a palyat attol fuggoen
     * hogy milyen cselekvest halytottunk vegre.
     * Itt van meghatarozva a karakterunk es a capa kezdeti helye is.
     * Beker a felhasznalotol egy bemenetet majd a megadott feltetelek szerint meghiv egy fuggvenyt. Ha rossz bemenetet
     * adunk meg akkor a palyan levo objektumok nem fognak valtozni csak egy hibauzenetet kapunk hogy valamit rosszul adtunk meg!
     * Ha helyes a bemenet amit megad a felhasznalo akkor a feltetleben meghivodik egy fuggveny ami egy masik
     * osztalyban lett megvalositva, ha a fuggveny visszaterese igaz azaz a hiba nelkul lefutott akkor igaz lesz az
     * a feltetel ami a palya dinamikus elemeit tartalmazza peldaul a nyersanyagok sodrodasat es valoszinuseget.
     * Ezutan meghivja a feltoltes fuggvenyt ami feljebb targyalvav van.
     * Vegul ellenorzi a lepesek szamat, ha 1000 akkor nyertunk, valamint ellenorzi a szomjusag es ehseg erteket
     * amelyekbol ha valaemyik 0-ra csokken akkor vesztettunk.
     */
    public void futtatas() {
        Scanner sc = new Scanner(System.in);
        char[][] palya = new char[26][35];
        Bob bob = new Bob(12, 17);
        Capa capa = new Capa(16, 27);
        Tutaj tutaj = new Tutaj();
        Nyersanyagok nyersanyagok = new Nyersanyagok();
        EvesIvas evesIvas = new EvesIvas();
        boolean szamolo = false;
        boolean masikszamolo = false;

        while (true) {
            boolean lepes = false;
            System.out.println("Lepesek: " + cselSzam + " | Etel: " + bob.getEhseg() + " | Ital: " + bob.getSzomj() + "\nHal: " + bob.kaja.getHal() + " | Burgonya: " + bob.kaja.getBurgonya() + "\nDeszka: " + nyersanyagok.getDeszka() + " | Level: " + nyersanyagok.getLevel() + " | Hulladek: " + nyersanyagok.getHulladek());
            System.out.println("---------------------------------------------------------------------------\nMuvelet: ");
            String input = sc.nextLine();

            if (input.equals("")) {
                feltoltes(palya, tutaj, capa, bob);
                continue;
            }
            if (input.charAt(0) == 'b') {                           //tutaj bovitese
                if (tutaj.bovites(input, bob, nyersanyagok)) {
                    lepes = true;
                }
            } else if (input.charAt(0) == 'h') {                    //halo lerakasa
                if (tutaj.halo(input, bob, nyersanyagok)) {
                    lepes = true;
                }
            } else if (input.equals("v")) {                         //viztisztito lerakasa
                if (tutaj.viztisztito(bob, nyersanyagok)) {
                    szamolo = true;
                    lepes = true;
                }
            } else if (input.equals("t")) {                         // tuzhely lereakasa
                if (tutaj.tuzhely(bob, nyersanyagok)) {
                    lepes = true;
                }
            } else if (input.equals("f")) {                         //horgaszas
                if (bob.horgaszas(tutaj)) {
                    lepes = true;
                }
            } else if (input.equals("l")) {                         //landzsakeszites
                if (bob.landzsa(nyersanyagok)) {
                    lepes = true;
                }
            } else if (input.equals("x")) {                         //nyersanyagfelvetel
                if (bob.nyersaFelvetel(tutaj, nyersanyagok)) {
                    lepes = true;
                }
            } else if (input.equals("r")) {                         //eves
                if (evesIvas.eves(tutaj, bob)) {
                    lepes = true;
                }
            } else {
                if (bob.mozgas(input) && bob.validLepes(bob.getX(), bob.getY())) {     //lepes a karakterunkkel
                    evesIvas.ivas(tutaj, bob);
                    if (evesIvas.fozes(tutaj, bob, bob.kaja)) {
                        masikszamolo = true;
                    }
                    lepes = true;
                }
            }
            if (masikszamolo) {
                etelszamolo++;
            }
            if (szamolo) {
                vizszamolo++;
            }
            if (lepes) {
                nyersanyagok.valoszinuseg(tutaj);
                nyersanyagok.sodrodas(tutaj, bob.kaja);
                bob.setEhseg(bob.getEhseg() - 1);
                bob.setSzomj(bob.getSzomj() - 1);
                capa.uszkalas();
                cselSzam++;
            }

            feltoltes(palya, tutaj, capa, bob);

            if (bob.getEhseg() <= 0) {
                System.out.println("VESZTETTEL! A karaktered ehen halt!");
                break;
            } else if (bob.getSzomj() <= 0) {
                System.out.println("VESZTETTEL! A karaktered szomjan halt!");
                break;
            }
            if (cselSzam >= 1000) {
                System.out.println("GYOZTEL! Sikerult tulelned 1000 cselekvest");
                break;
            }
        }
    }

}