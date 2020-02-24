package krizovatka;

import casovani.Casovac;
import casovani.Posluchac;
import kolekce.IMapa;
import kolekce.KolekceException;
import kolekce.Mapa;
import krizovatka.IKrizovatka.SmerPrujezdu;

/**
 *
 * @author st55440
 */
public class RidicOdjezdu {

    private long dobaPrujezduAuta;
    private final IMapa<SmerPrujezdu, FrontaAut> seznamFront;
    private long cas;
    private volatile boolean running;

    public RidicOdjezdu(long dobaPrujezduAuta, SmerPrujezdu smer, 
            FrontaAut... fronty) throws KolekceException {
        this.dobaPrujezduAuta = dobaPrujezduAuta;
        this.seznamFront = new Mapa<>();
        for (FrontaAut frontaAut : fronty) {
            seznamFront.vloz(smer, frontaAut);
        }
        this.running = false;
        Casovac.instance().pridej(new Posluchac() {
            @Override
            public void aktualizuj() {
                cas += Casovac.PERIODA;
                if (running && cas >= dobaPrujezduAuta) {
                    seznamFront.stream().forEach((FrontaAut f) -> {
                        f.odeber();
                    });
                    cas = 0;
                }
            }
        });

    }

    public synchronized void start() {
        cas = 0;
        running = true;
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized void setDobaPrujezdu(long dobaPrujezdu) {
        this.dobaPrujezduAuta = dobaPrujezdu;
    }

    public synchronized long getDobaPrujezdu() {
        return this.dobaPrujezduAuta;
    }
}
