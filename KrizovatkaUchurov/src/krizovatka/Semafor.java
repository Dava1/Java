package krizovatka;

import casovani.Casovac;
import casovani.Posluchac;
import java.util.function.Consumer;
import kolekce.IMapa;
import kolekce.KolekceException;
import krizovatka.IKrizovatka.SmerPrujezdu;

/**
 *
 * @author st55440
 */
public class Semafor {

    private boolean isGreen;
    private IMapa<SmerPrujezdu, RidicOdjezdu> odjezdSmr;
    private Consumer<SmerPrujezdu> hlaseniSemaforu;
    private long x1;
    private long x2;
    private long cas;
    private SmerPrujezdu parallejnySmer;

    Semafor(long doba1, long doba2, IMapa<SmerPrujezdu, RidicOdjezdu> odjezdySmery) throws KolekceException {
        this.odjezdSmr = odjezdySmery;
        this.x1 = doba1;
        this.x2 = doba2;
        isGreen = true;
        parallejnySmer = SmerPrujezdu.VYCHOD_ZAPAD;
        Casovac.instance().pridej(new Posluchac() {
            @Override
            public void aktualizuj() {
           cas += Casovac.PERIODA;
           switch(parallejnySmer){
               case SEVER_JIH:
                   ovladejSemafor(parallejnySmer, SmerPrujezdu.VYCHOD_ZAPAD, x1);
           break;
               case VYCHOD_ZAPAD:
                   ovladejSemafor(parallejnySmer, SmerPrujezdu.SEVER_JIH, x2);
                   break;
           }
            }
        });
        /*Casovac.instance().pridej(() -> {
            cas += Casovac.PERIODA;
            switch (parallejnySmer) {
                case SEVER_JIH:
                    ovladejSemafor(parallejnySmer, SmerPrujezdu.VYCHOD_ZAPAD, x1);
                    break;
                case VYCHOD_ZAPAD:
                    ovladejSemafor(parallejnySmer, SmerPrujezdu.SEVER_JIH, x2);
                    break;
            }
        });*/
    }

    private void ovladejSemafor(SmerPrujezdu soucasny, SmerPrujezdu novy, long dobaZelena) {
        if (isGreen && cas >= dobaZelena) {
            odjezdSmr.dej(soucasny).stop();
            odjezdSmr.dej(novy).start();
            if (hlaseniSemaforu != null) {
                hlaseniSemaforu.accept(novy);
            }
            parallejnySmer = novy;
            cas = 0;
        }

    }

    void start() {
        isGreen = true;
    }

    void stop() {
        isGreen = false;
    }

    void pause() {
        isGreen = false;
    }

    void setHlaseniSemaforu(Consumer<SmerPrujezdu> hlaseni) {
        this.hlaseniSemaforu = hlaseni;
    }

    public long getDobaZelena(SmerPrujezdu smer) {
        switch (smer) {
            case SEVER_JIH:
                return x1;
            case VYCHOD_ZAPAD:
                return x2;
        }
        return -1;
    }

    public void setDobaZelena(SmerPrujezdu smer, long doba) {
        switch (smer) {
            case SEVER_JIH:
                this.x1 = doba;
                break;
            case VYCHOD_ZAPAD:
                this.x2 = doba;
                break;
        }
    }
}
