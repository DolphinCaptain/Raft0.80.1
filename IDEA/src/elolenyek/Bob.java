package elolenyek;

import tuleles.Kaja;
import tuleles.Nyersanyagok;
import palya.Tutaj;

import java.util.Random;

/**
 * Ebben az osztalyban Bob azaz a karakterunk cselekvesei vannak megvalositva,
 * a mozgas , horgaszas, illetve a nyersanyag felvetel.
 * illetve az ehseg es a szomj valtozo ami minden korben csokken eggyel
 */
public class Bob {
    private int ehseg = 100;
    private int szomj = 100;
    private int landzsa = 0;
    private int x, y;
    Random rand = new Random();
    public Kaja kaja = new Kaja(0, 0);

    public Bob(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getLandzsa() {
        return landzsa;
    }

    public void setLandzsa(int landzsa) {
        this.landzsa = landzsa;
    }

    public int getEhseg() {
        return ehseg;
    }

    public void setEhseg(int ehseg) {
        this.ehseg = ehseg;
    }

    public int getSzomj() {
        return szomj;
    }

    public void setSzomj(int szomj) {
        this.szomj = szomj;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    /**
     * Ez a metodus van meghivva a mozgas metodusanal ugyanis ez ellenorzi hogy a karakterunk
     * ne lepjen ki a meghatarozott palyahatarokon
     */
    public boolean validLepes(int x, int y) {
        if (x < 1 || x > 24 || y < 1 || y > 35) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A mozgas metodusa, ellenorzi a felhasznalo altal bevitt erteket, ha megfelelo akkor az adott iranynak
     * megfelo bob jelenlegi helyzetet leiro x y koordinatakon valtoztat
     */
    public boolean mozgas(String input) {
        if (input.equals("a")) {
            if (validLepes(this.getX(), this.getY() - 1))
                this.setY(this.getY() - 1);
            return true;
        } else if (input.equals("d")) {
            if (validLepes(this.getX(), this.getY() + 1))
                this.setY(this.getY() + 1);
            return true;
        } else if (input.equals("w")) {
            if (validLepes(this.getX() - 1, this.getY()))
                this.setX(this.getX() - 1);
            return true;
        } else if (input.equals("s")) {
            if (validLepes(this.getX() + 1, this.getY()))
                this.setX(this.getX() + 1);
            return true;
        } else if (input.equals("q")) {
            if (validLepes(this.getX() - 1, this.getY() - 1)) {
                this.setY(this.getY() - 1);
                this.setX(this.getX() - 1);
                return true;
            }
        } else if (input.equals("e")) {
            if (validLepes(this.getX() - 1, this.getY() + 1)) {
                this.setY(this.getY() + 1);
                this.setX(this.getX() - 1);
                return true;
            }
        } else if (input.equals("c")) {
            if (validLepes(this.getX() + 1, this.getY() + 1)) {
                this.setY(this.getY() + 1);
                this.setX(this.getX() + 1);
                return true;
            }
        } else if (input.equals("y")) {
            if (validLepes(this.getX() + 1, this.getY() - 1)) {
                this.setY(this.getY() - 1);
                this.setX(this.getX() + 1);
                return true;
            }
        } else {
            System.err.println("Rossz lepest adatal meg!");
            return false;
        }
        return false;
    }

    /**
     * A horgaszas metodus azt vizsgalja hogy a karakteunk megfelelo helyen tartozkodik, tehat vizben all, ha igen
     * akkor a bevezetett esely nevu random valtozo ha 25 ala esik egy 100as listabol azaz 25% esely van ra akkor fogsz egy halat
     * egyebkent kiirja hogy nem fogtal semmit.
     */
    public boolean horgaszas(Tutaj tutaj) {
        if (tutaj.getTutaj()[this.getX()][this.getY()] == 0 || tutaj.getTutaj()[this.getX()][this.getY()] == 5 || tutaj.getTutaj()[this.getX()][this.getY()] == 6 || tutaj.getTutaj()[this.getX()][this.getY()] == 7 || tutaj.getTutaj()[this.getX()][this.getY()] == 8) {

            int esely = rand.nextInt(101);
            if (esely < 26) {
                kaja.setHal(kaja.getHal() + 1);
                System.out.println("Sikerult fognod egy halat!");
            } else {
                System.out.println("Nem fogtal semmit! :(");
            }
            return true;
        } else {
            System.err.println("Itt nem horgaszhatsz!");
            return false;
        }

    }

    /**
     * Ha a karakterunk korul 1 vagy tobb nyersanyag van akkor hozzaadja az inventorynkhoz es kitorli a palyarol.
     * Ha a bemenet megnyomasakor a hely korul ahol allunk nincs nyersanyag akkor hibauzenetet ir ki
     */
    public boolean nyersaFelvetel(Tutaj tutaj, Nyersanyagok nyersanyagok) {
        boolean vissza = false;
        if (tutaj.getTutaj()[this.getX()][this.getY() - 1] != 0 || tutaj.getTutaj()[this.getX()][this.getY() - 1] != 1 || tutaj.getTutaj()[this.getX()][this.getY() - 1] != 2 || tutaj.getTutaj()[this.getX()][this.getY() - 1] != 3 || tutaj.getTutaj()[this.getX()][this.getY() - 1] != 4) {
            if (tutaj.getTutaj()[this.getX()][this.getY() - 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() - 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() - 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() - 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX()][this.getY() - 1] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX() - 1][this.getY()] != 0 || tutaj.getTutaj()[this.getX() - 1][this.getY()] != 1 || tutaj.getTutaj()[this.getX() - 1][this.getY()] != 2 || tutaj.getTutaj()[this.getX() - 1][this.getY()] != 3 || tutaj.getTutaj()[this.getX() - 1][this.getY()] != 4) {
            if (tutaj.getTutaj()[this.getX() - 1][this.getY()] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY()] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY()] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY()] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY()] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY()] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY()] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() - 1][this.getY()] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX()][this.getY() + 1] != 0 || tutaj.getTutaj()[this.getX()][this.getY() + 1] != 1 || tutaj.getTutaj()[this.getX()][this.getY() + 1] != 2 || tutaj.getTutaj()[this.getX()][this.getY() + 1] != 3 || tutaj.getTutaj()[this.getX()][this.getY() + 1] != 4) {
            if (tutaj.getTutaj()[this.getX()][this.getY() + 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() + 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() + 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX()][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY() + 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX()][this.getY() + 1] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX() + 1][this.getY()] != 0 || tutaj.getTutaj()[this.getX() + 1][this.getY()] != 1 || tutaj.getTutaj()[this.getX() + 1][this.getY()] != 2 || tutaj.getTutaj()[this.getX() + 1][this.getY()] != 3 || tutaj.getTutaj()[this.getX() + 1][this.getY()] != 4) {
            if (tutaj.getTutaj()[this.getX() + 1][this.getY()] == 5) {
                tutaj.getTutaj()[this.getX() + 1][this.getY()] = 0;
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY()] == 6) {
                tutaj.getTutaj()[this.getX() + 1][this.getY()] = 0;
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY()] == 7) {
                tutaj.getTutaj()[this.getX() + 1][this.getY()] = 0;
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY()] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() + 1][this.getY()] = 0;
                vissza = true;
            }
        }



        if (!vissza){
            vissza=keresztnyersaFelvetel(tutaj,nyersanyagok);
        }

        if (!vissza && !alattadnyersaFelvetel(tutaj, nyersanyagok) && !keresztnyersaFelvetel(tutaj, nyersanyagok)) {
            System.err.println("Nincs a kozeledbe semmilyen nyersanyag");
        }
        keresztnyersaFelvetel(tutaj,nyersanyagok);
        alattadnyersaFelvetel(tutaj,nyersanyagok);

        return vissza;
    }

    /**
     *A karakterunktol atlosan levo nyersanyagokat veszi fel. A nyersaFelvetel metodus vegen kerul meghivasra.
     */
    public boolean keresztnyersaFelvetel(Tutaj tutaj, Nyersanyagok nyersanyagok) {
        boolean vissza = false;
        if (tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] != 0 || tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] != 1 || tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] != 2 || tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] != 3 || tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] != 4) {
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() - 1][this.getY() - 1] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] != 0 || tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] != 1 || tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] != 2 || tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] != 3 || tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] != 4) {
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() - 1][this.getY() + 1] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] != 0 || tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] != 1 || tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] != 2 || tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] != 3 || tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] != 4) {
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() + 1][this.getY() + 1] = 0;
                vissza = true;
            }
        }
        if (tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] != 0 || tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] != 1 || tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] != 2 || tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] != 3 || tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] != 4) {
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] = 0;
                vissza = true;
            }
            if (tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX() + 1][this.getY() - 1] = 0;
                vissza = true;
            }
        }
        return vissza;
    }


    /**
     * Ha a karakterunk nyersanyagon all es megnyomjuk a megfelelo bemenetet akkor felveszi az alattunk levo nyersanyagot.
     * A nyersaFelvetel metodus vegen van megivva
     */
    public boolean alattadnyersaFelvetel(Tutaj tutaj, Nyersanyagok nyersanyagok) {
        if (tutaj.getTutaj()[this.getX()][this.getY()] == 5 || tutaj.getTutaj()[this.getX()][this.getY()] == 6 || tutaj.getTutaj()[this.getX()][this.getY()] == 7 || tutaj.getTutaj()[this.getX()][this.getY()] == 8) {
            if (tutaj.getTutaj()[this.getX()][this.getY()] == 5) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() + 1);
                tutaj.getTutaj()[this.getX()][this.getY()] = 0;
                return true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY()] == 6) {
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() + 1);
                tutaj.getTutaj()[this.getX()][this.getY()] = 0;
                return true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY()] == 7) {
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() + 1);
                tutaj.getTutaj()[this.getX()][this.getY()] = 0;
                return true;
            }
            if (tutaj.getTutaj()[this.getX()][this.getY()] == 8) {
                nyersanyagok.hordoban(kaja);
                tutaj.getTutaj()[this.getX()][this.getY()] = 0;
                return true;
            }
        }
        return false;
    }


    /**
     * Ez a metodus a landzsakeszitest teszi lehetove. Ellenorzi hogy van e eleg nyersanyag a felhsznalonal,
     * ha igen akkor hozzaad egyet a landzsak szamahoz, egyebkent hibauzenetet dob
     */
    public boolean landzsa(Nyersanyagok nyersanyagok) {
        if (!(nyersanyagok.getLevel() < 4 || nyersanyagok.getHulladek() < 4 || nyersanyagok.getDeszka() < 4)) {
            nyersanyagok.setLevel(nyersanyagok.getLevel() - 4);
            nyersanyagok.setHulladek(nyersanyagok.getHulladek() - 4);
            nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 4);
            this.setLandzsa(this.getLandzsa() + 1);
            System.out.println("Keszitettel egy landzsat! Mostmar sikeresen tudsz vedekezni a capa ellen!");
            return true;
        } else {
            System.err.println("Nincs eleg nyersanyagod a landzsahoz! Legalabb 4 deszkara, 4 hulladekra es 4 levelre van szukseged a megepitesehez!");
            return false;
        }
    }

}