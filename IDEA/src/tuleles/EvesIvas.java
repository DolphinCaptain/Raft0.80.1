package tuleles;

import elolenyek.Bob;
import palya.Palya;
import palya.Tutaj;


/**
 * Ez az osztaly a karakterunk ivas illetve eves metodusait tartalmazza.
 * Az ivas automatikusan tortenik ha eltelt 25 kor es ralepunk egy viztisztitora
 * A fozeshez ra kell lepnunk a tuzhelyre ami levontolunk egy halat vagy egy burgonyat attol fuggoen hogy melyikbol van tobb
 * Ha enni szeretnenk 25 kort kell varnunk majd a tuzhelyre allva az x et megnyomva tudjuk elfogyasztani az etelt ami visszatolt 60 ehseget
 *
 */
public class EvesIvas {

    /**
     *amikor epitunk egy viztisztitot akkor elindul egy sztamnlalo a futtatas metodusban ami minden egyes helyes lepesnel hozzad egyet
     *  ha ez eleri a 25ot azaz eltelt 25 kor akkor a viztisztitora allva automatikusan 40-nel no a
     *  szomjusagunkat reprezentalo ertek
     *ha meg nem telt el 25 kor akkor hibauzenetet kapunk
     */
    public void ivas(Tutaj tutaj, Bob bob) {
        if (tutaj.getTutaj()[bob.getX()][bob.getY()] == 3) {
            if (Palya.vizszamolo >= 25) {
                Palya.vizszamolo -= 25;
                bob.setSzomj(bob.getSzomj() + 40);
                if (bob.getSzomj()>100){
                    bob.setSzomj(100);
                }
            } else {
                System.out.println("Meg varnod kell hogy vizet tudj inni!");
            }
        }
    }


    /**
     * A fozes metodus akkor hivodik meg ha ralepunk egy tuzhelyre, ha van nalunk hal vagy krumpli akkor
     * levon egyet attol fuggoen hogy melyikbol van tobb. Itt ugyan ugy elindul egy szamlalo
     */
    public boolean fozes(Tutaj tutaj, Bob bob, Kaja kaja) {
        if (tutaj.getTutaj()[bob.getX()][bob.getY()] == 4) {
            if (kaja.getBurgonya() > 0 || kaja.getHal() > 0) {
                if (kaja.getBurgonya() >= kaja.getHal()) {
                    kaja.setBurgonya(kaja.getBurgonya() - 1);
                    return true;
                } else {
                    kaja.setHal(kaja.getHal() - 1);
                    return true;
                }
            } else {
                System.err.println("nincs se halad se burgonyad amit meg tudnal fozni");
                return false;
            }
        }
        return false;
    }

    /**
     * Az eves metodus megvizsgalja hogy a karakterunk egy tuzhelyen all e, ha igen es a az evesszamlalo mar leszamolt
     * 25ot akkor visszatolt 60 ehseg pontot
     * ellenkezo esetekben hibauzeneteket kapsz
     */
    public boolean eves(Tutaj tutaj, Bob bob) {
        if (tutaj.getTutaj()[bob.getX()][bob.getY()] == 4) {
            if (Palya.etelszamolo >= 25) {
                Palya.etelszamolo -= 25;
                bob.setEhseg(bob.getEhseg() + 60);
                if (bob.getEhseg()>100){
                    bob.setEhseg(100);
            }
                return true;
            } else {
                System.err.println("Meg nem keszult el az etel!");
                return false;
            }
        }
        System.err.println("Hogy ehess, a tuzhelyre kell allnod a karaktereddel!");
        return false;
    }


}