package palya;

import elolenyek.Bob;
import tuleles.Nyersanyagok;


/**
 * A Tutaj osztalyban tortenik a szamokbol allo tomb letrehozasa amihez a legtobb metodus kotodik.
 * A palya osztalyban levo metodus ezt a szamokbol allo tombot fogja atirni a megfelelo szimbolumokra mait aztan majfdmegjelenit.
 * Ebben az osztalyban vannak letrehozva a tutaj bovitesere es fejlesztesere letrehozott metodusok.
 */
public class Tutaj {
    public Tutaj(int[][] tutaj) {
        this.tutaj = tutaj;
    }

    private int[][] tutaj;

    public int[][] getTutaj() {
        return tutaj;
    }

    public void setTutaj(int[][] tutaj) {
        this.tutaj = tutaj;
    }

    /**
     * letrehoz egy 27*37 nagysagu tombot amibol 25*25 lesz majd csak kiiratva.A plussz sorok es oszlopok a szelsoseges
     * esetekre lettek letrehozva peldaul amikor a karakterunk fel akar venni egy nyersanyagot a palya legszelen,
     * igy nem lesz alul vagy tulindexelt a tomb.
     * Ezt a tombot feltolti 0-kal majd 4 0-at jelento viz elemet kicserel 1-essel jelolt tutaj elemekre.
     */
    public Tutaj() {
        tutaj = new int[27][37];
        for (int i = 0; i < tutaj.length; i++) {
            for (int j = 0; j < tutaj[i].length; j++) {
                tutaj[i][j] = 0;
                if ((i == 12 || i == 13) && (j == 16 || j == 17)) {
                    tutaj[i][j] = 1;
                }
            }
        }
    }

    /**
     * A bovites metodus a tutaj bovitesere lett letrehozva.
     * Akkor hivodik meg ha a jatekos egy megfelelo b betuvel kezdodo bemenetet adott meg, ez a futtatas metodus
     * ciklusaban van ellenorizve.
     * Eloszor azt ellenorzi hogy van e megfelelo mennyisegu nyersanyagunk a boviteshez, ha nem akkor hibauzenettel ter vissza.
     * Ha van elegendo nyersanyagunk akkor megvizsgalja bob jelenlegi helyzetet hogy megfelelo elemen all e.
     * Ha ez is rendben volt akkor megvizsgalja hogy melyik iranynak megfelelo bemenetet adott meg a felhasznalo.
     * Ha ez is rendben volt akkor megvizsgalja hogy lehetseges e odaraknunk a tutaj elemet ahova szeretnenk, itt pl csak vizre rakhatunk.
     * Ha igen akkor levonja a fejlesztes koltseget es a megfelelo helyen kicsereli az elemet a tutaj tombben.
     * Minden nem megfelelo eset le van kezelve es hibaval ter vissza.
     */
    public boolean bovites(String input, Bob bob, Nyersanyagok nyersanyagok) {
        if (!(nyersanyagok.getDeszka() < 2 || nyersanyagok.getLevel() < 2)) {
            if (tutaj[bob.getX()][bob.getY()] == 1 || tutaj[bob.getX()][bob.getY()] == 3 || tutaj[bob.getX()][bob.getY()] == 4) {
                if (input.equals("ba")) {
                    if (tutaj[bob.getX()][bob.getY() - 1] == 0) {
                        tutaj[bob.getX()][bob.getY() - 1] = 1;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 2);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz tutaj bovitest alkalmazni, meglevo tutajra vagy halora nem epithetsz!");
                        return false;
                    }
                }
                if (input.equals("bd")) {
                    if (tutaj[bob.getX()][bob.getY() + 1] == 0) {
                        tutaj[bob.getX()][bob.getY() + 1] = 1;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 2);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz tutaj bovitest alkalmazni, meglevo tutajra vagy halora nem epithetsz!");
                        return false;
                    }
                }
                if (input.equals("bw")) {
                    if (tutaj[bob.getX() - 1][bob.getY()] == 0) {
                        tutaj[bob.getX() - 1][bob.getY()] = 1;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 2);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz tutaj bovitest alkalmazni, meglevo tutajra vagy halora nem epithetsz!");
                        return false;
                    }
                }
                if (input.equals("bs")) {
                    if (tutaj[bob.getX() + 1][bob.getY()] == 0) {
                        tutaj[bob.getX() + 1][bob.getY()] = 1;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 2);
                        return true;
                    } else {

                        System.err.println("Csak vizre tudsz tutaj bovitest alkalmazni, meglevo tutajra vagy halora nem epithetsz!");
                        return false;
                    }
                } else {
                    System.err.println("Rossz bemenetet adtal meg!");
                    return false;
                }
            } else {
                System.err.println("Csak ugy tudod boviteni a tutajt ha az kapcsolodik a mar meglevo tutajodhoz es egy meglevo tutaj elemen allsz!");
                return false;
            }
        } else {
            System.err.println("Nincs eleg nyersanyagod a tutaj bovitesehez! Legalabb 2 deszkara es 2 levelre van szukseged a bovitesehez!");
            return false;
        }
    }

    /**
     * Ugyan az tortenik itt is mint a bovites metodusnal csak nyilvan mas bemenetet var
     */
    public boolean halo(String input, Bob bob, Nyersanyagok nyersanyagok) {
        if (!(nyersanyagok.getDeszka() < 2 || nyersanyagok.getLevel() < 6)) {
            if (tutaj[bob.getX()][bob.getY()] == 1 || tutaj[bob.getX()][bob.getY()] == 3 || tutaj[bob.getX()][bob.getY()] == 4) {
                if (input.equals("ha")) {
                    if (tutaj[bob.getX()][bob.getY() - 1] == 0) {
                        tutaj[bob.getX()][bob.getY() - 1] = 2;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 6);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz halot epiteni, meglevo halora vagy tutajra nem epithetsz!");
                        return false;
                    }
                } else if (input.equals("hd")) {
                    if (tutaj[bob.getX()][bob.getY() + 1] == 0) {
                        tutaj[bob.getX()][bob.getY() + 1] = 2;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 6);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz halot epiteni, meglevo halora vagy tutajra nem epithetsz!");
                        return false;
                    }
                } else if (input.equals("hw")) {
                    if (tutaj[bob.getX() - 1][bob.getY()] == 0) {
                        tutaj[bob.getX() - 1][bob.getY()] = 2;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 6);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz halot epiteni, meglevo halora vagy tutajra nem epithetsz!");
                        return false;
                    }
                } else if (input.equals("hs")) {
                    if (tutaj[bob.getX() + 1][bob.getY()] == 0) {
                        tutaj[bob.getX() + 1][bob.getY()] = 2;
                        nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                        nyersanyagok.setLevel(nyersanyagok.getLevel() - 6);
                        return true;
                    } else {
                        System.err.println("Csak vizre tudsz halot epiteni, meglevo halora vagy tutajra nem epithetsz!");
                        return false;
                    }
                } else {
                    System.err.println("Rossz bemenetet adtal meg!");
                    return false;
                }
            } else {
                System.err.println("Csak ugy tudsz halot rakni ha az kapcsolodik a mar meglevo tutajodhoz es egy meglevo tutaj elemen allsz!");
                return false;
            }
        } else {
            System.err.println("Nincs eleg nyersanyagod a halohoz! Legalabb 2 deszka es 6 level kell hozza");
            return false;
        }
    }


    /**
     * Hasonloan az elozo ket metodushoz ez is megvizsgalja a bemenetet viszont a viztisztitot csak magunk ala tehetjuk tehat
     * nem kell minden iranyt megvizsgalnunk, eleg csak a karakterunk alatti elemet megnezni ha megfelel minden feltetelnek akkor levonja a
     * fejlesztesi koltseget es a tombbe beilleszti a megfelelo helyre a tisztitot.
     */
    public boolean viztisztito(Bob bob, Nyersanyagok nyersanyagok) {
        if (!(nyersanyagok.getLevel() < 2 || nyersanyagok.getHulladek() < 4)) {
            if (tutaj[bob.getX()][bob.getY()] == 1) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() - 2);
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() - 4);
                tutaj[bob.getX()][bob.getY()] = 3;
                return true;
            } else {
                System.err.println("Ide nem epithetsz viztisztitot, csak a tutaj deszkajara lehet epiteni! ");
                return false;
            }
        } else {
            System.err.println("Nincs eleg nyersanyagod a viztisztitiohoz! Legalabb 2 levelre es 4 hulladekra van szukseged a megepitesehez!");
            return false;
        }
    }


    /**
     * ugyan ugy mukodik mint a viztisztito, ezt is csak magunk ala tudjuk epiteni,
     */
    public boolean tuzhely(Bob bob, Nyersanyagok nyersanyagok) {
        if (!(nyersanyagok.getLevel() < 4 || nyersanyagok.getHulladek() < 3 || nyersanyagok.getDeszka() < 2)) {
            if (tutaj[bob.getX()][bob.getY()] == 1) {
                nyersanyagok.setLevel(nyersanyagok.getLevel() - 4);
                nyersanyagok.setHulladek(nyersanyagok.getHulladek() - 3);
                nyersanyagok.setDeszka(nyersanyagok.getDeszka() - 2);
                tutaj[bob.getX()][bob.getY()] = 4;
                return true;
            } else {
                System.err.println("Ide nem epithetsz tuzhelyet, csak a tutaj deszkajara lehet epiteni! ");
                return false;
            }
        } else {
            System.err.println("Nincs eleg nyersanyagod a tuzhelyhez! Legalabb 2 deszkara, 3 hulladekra es 4 levelre van szukseged a megepitesehez!");
            return false;
        }
    }


}
