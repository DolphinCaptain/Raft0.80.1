package tuleles;

import palya.Tutaj;

import java.util.Random;


/**
 * Ebben az osztalyban vannak implementalva a nyersanyagopk illetve megvalositva a
 * nyersanyagok sodrodas es valoszinuseg metodusai amik a futtatas metodusban hivodnak meg
 */
public class Nyersanyagok {
    private int deszka = 1000;
    private int level = 1000;
    private int hulladek =1000;
    Random rand = new Random();
    int darab;

    public int getDeszka() {
        return deszka;
    }

    public void setDeszka(int deszka) {
        this.deszka = deszka;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHulladek() {
        return hulladek;
    }

    public void setHulladek(int hulladek) {
        this.hulladek = hulladek;
    }

    public Nyersanyagok() {
    }

    public Nyersanyagok(int deszka, int level, int hulladek) {
        this.deszka = deszka;
        this.level = level;
        this.hulladek = hulladek;
    }

    /**
     * Ez a metodus a feladatnak megfeleloen 0,1,2 vagy 3 darab nyersanyagot szur be az elso sorba
     * A for ciklus annyiszor fut le amennyire a darab ertek lett allitva random 0 es 3 kozott.
     * Majd a feladatnak megfelelo szazalekos eloszlasban 32 32 32 illetve 4 valoszinuseggel
     * johetnek ki a kulonbozo anyagok, majd ezek szerint beallitja az elso sorba az erteket egy random helyre.
     */
    public void valoszinuseg(Tutaj tutaj) {
        darab = rand.nextInt(4);

        for (int i = 0; i < darab; i++) {
            int esely = rand.nextInt(101);

            if (esely < 32) {
                tutaj.getTutaj()[0][rand.nextInt(35)] = 5;
            }
            if (esely < 64 && esely > 31) {
                tutaj.getTutaj()[0][rand.nextInt(35)] = 6;
            }
            if (esely < 96 && esely > 63) {
                tutaj.getTutaj()[0][rand.nextInt(35)] = 7;
            }
            if (esely < 100 && esely > 95) {
                tutaj.getTutaj()[0][rand.nextInt(35)] = 8;
            }
        }
    }


    /**
     * Ez a metodus a hordoban levo anyagok random eloszlasat valositja meg.
     * Itt is az elozo metodusban hasznalt modon csak itt fixen 5szor fut le a for ciklus
     * es minden nyersanyagra 25 % esely van. Az adott nyersanyagot hozzadajuk az inventorynkhoz
     */
    public void hordoban(Kaja kaja) {                                            //hordoban levo dolgok valoszinusege
        for (int k = 0; k < 5; k++) {
            int esely = rand.nextInt(101);

            if (esely < 25) {
                setDeszka(getDeszka() + 1);
            }
            if (esely > 24 && esely < 50) {
                setLevel(getLevel() + 1);
            }
            if (esely > 49 && esely < 75) {
                setHulladek(getHulladek() + 1);
            }
            if (esely > 74 && esely < 100) {
                kaja.setBurgonya(kaja.getBurgonya() + 1);
            }
        }
    }

    /**
     * Ez a metodus felelos azert hogy a nyersanyagok minden egyes cselekvesnel egy sornyit csusszanak lefele
     * A metodus vegigmegy a tutaj tombon es mindenhol ahol nyersanyagot talal annak a poziciojahoz hozzad egyet
     * ha eleri az utolso sort akkor kitorli az elemet
     * ha a nyersanyag alatt egy halo van akkor az kitorli a nyersanyagot es hozzadja az inventorynkhoz
     */
    public void sodrodas(Tutaj tutaj, Kaja kaja) {
        for (int i = tutaj.getTutaj().length - 1; i >= 0; i--) {
            for (int j = tutaj.getTutaj()[i].length - 1; j >= 0; j--) {
                if (i == 25) {                                                             //utolso sor nyersanyag torles
                    tutaj.getTutaj()[i][j] = 0;
                }
                int nyersa = tutaj.getTutaj()[i][j];
                if (nyersa == 5 || nyersa == 6 || nyersa == 7 || nyersa == 8) {

                    if (tutaj.getTutaj()[i + 1][j] == 2) {
                        if (tutaj.getTutaj()[i][j] == 5) {
                            setLevel(getLevel() + 1);
                            tutaj.getTutaj()[i][j] = 0;
                        } else if (tutaj.getTutaj()[i][j] == 6) {
                            setDeszka(getDeszka() + 1);
                            tutaj.getTutaj()[i][j] = 0;
                        } else if (tutaj.getTutaj()[i][j] == 7) {
                            setHulladek(getHulladek() + 1);
                            tutaj.getTutaj()[i][j] = 0;
                        } else if (tutaj.getTutaj()[i][j] == 8) {
                            tutaj.getTutaj()[i][j] = 0;
                            hordoban(kaja);
                        }
                    } else if (tutaj.getTutaj()[i + 1][j] == 1 || tutaj.getTutaj()[i + 1][j] == 3 || tutaj.getTutaj()[i + 1][j] == 4) {
                        tutaj.getTutaj()[i][j] = 0;
                    } else {
                        tutaj.getTutaj()[i + 1][j] = nyersa;
                        tutaj.getTutaj()[i][j] = 0;
                    }
                }
            }
        }
    }
}
