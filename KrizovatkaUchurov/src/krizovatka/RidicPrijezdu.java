package krizovatka;

import casovani.Casovac;
import casovani.Posluchac;
import kolekce.KolekceException;

/**
 *
 * @author st55440
 */
public class RidicPrijezdu {

    private long intervalPrijezdu;
    private final FrontaAut prijezd;

    private long cas;

    public RidicPrijezdu(long cetnost, FrontaAut prijezd) throws KolekceException {
        intervalPrijezdu = vypocetIntervaluPrijezdu(cetnost);
        this.prijezd = prijezd;
        Casovac.instance().pridej(new Posluchac() {
            @Override
            public void aktualizuj() {
                cas += Casovac.PERIODA;
                if (cas >= intervalPrijezdu) {
                    try {
                        prijezd.vloz(new Auto());
                    } catch (Exception e) {
                    }
                    cas = 0;
                }
            }

        });
    }

    public FrontaAut getPrijezd() {
        return prijezd;
    }

    public void setCetnost(long cetnost) {
        intervalPrijezdu = vypocetIntervaluPrijezdu(cetnost);
    }

    public long getCetnost() {
        return (3600 * 1000) / intervalPrijezdu;
    }

    private static long vypocetIntervaluPrijezdu(long cetnost) {
        return (3600 * 1000 / cetnost);
    }

}
