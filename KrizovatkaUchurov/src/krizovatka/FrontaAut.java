package krizovatka;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.Fronta;
import kolekce.IFronta;
import kolekce.KolekceException;

public class FrontaAut implements IFronta<Auto> {

    private static Consumer<String> logging;

    private final String name;
    private final IFronta<Auto> fronta;
    private Consumer<Auto> hlaseniPrijezdu;
    private Consumer<Auto> hlaseniOdjezdu;

    public FrontaAut(String name) {
        this.name = name;
        fronta = new Fronta<>();
    }

    @Override
    public void vloz(Auto auto) {
        try {
            fronta.vloz(auto);
            if (logging != null) {
                logging.accept("Prijezd ze smeru " + name + " " + auto.toString());
            }
            if (hlaseniPrijezdu != null) {
                hlaseniPrijezdu.accept(auto);
            }
        } catch (KolekceException ex) {
            Logger.getLogger(FrontaAut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getPocet() {
        return fronta.getPocet();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean jePrazdny() {
        return fronta.jePrazdny();
    }

    @Override
    public Auto odeber() {
        try {
            Auto auto = fronta.odeber();
            if (logging != null) {
                logging.accept("Odjezd ze smeru " + name + " " + auto.toString());
            }
            if (hlaseniOdjezdu != null) {
                hlaseniOdjezdu.accept(auto);
            }
            return auto;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void zrus() {
        fronta.zrus();
    }

    @Override
    public Iterator<Auto> iterator() {
        return fronta.iterator();
    }

    void setHlaseniPrijezdu(Consumer<Auto> hlaseni) {
        this.hlaseniPrijezdu = hlaseni;
    }

    void setHlaseniOdjezdu(Consumer<Auto> hlaseni) {
        this.hlaseniOdjezdu = hlaseni;
    }
}
