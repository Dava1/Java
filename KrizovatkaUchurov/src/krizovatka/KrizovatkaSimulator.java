package krizovatka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import kolekce.IFronta;
import kolekce.IMapa;
import kolekce.KolekceException;
import kolekce.Mapa;

/**
 * 
 * @author st55440
 */
public class KrizovatkaSimulator implements IKrizovatka {

    private IMapa<Smer, FrontaAut> fronty;
    private IMapa<Smer, RidicPrijezdu> prijezdy;
    private IMapa<SmerPrujezdu, RidicOdjezdu> odjezdy;
    private Semafor semafor;
    
    private long dobaPrujezdu = 900;

    public KrizovatkaSimulator() throws KolekceException {
            FrontaAut vychodAut = new FrontaAut("Vychod");
            FrontaAut severAut = new FrontaAut("Sever");
            FrontaAut jihAut = new FrontaAut("Jih");
            FrontaAut zapadAut = new FrontaAut("Zapad");
            fronty = new Mapa<>();
            fronty.vloz(Smer.VYCHOD, vychodAut);
            fronty.vloz(Smer.SEVER, severAut);
            fronty.vloz(Smer.JIH, jihAut);
            fronty.vloz(Smer.ZAPAD, zapadAut);
            prijezdy = new Mapa<>();
            prijezdy.vloz(Smer.VYCHOD, new RidicPrijezdu(2900, vychodAut));
            prijezdy.vloz(Smer.SEVER, new RidicPrijezdu(2000, severAut));
            prijezdy.vloz(Smer.JIH, new RidicPrijezdu(2500, jihAut));
            prijezdy.vloz(Smer.ZAPAD, new RidicPrijezdu(1400, zapadAut));
            
            RidicOdjezdu sevJih = new RidicOdjezdu(
                    dobaPrujezdu, SmerPrujezdu.SEVER_JIH, severAut,jihAut);
            
            RidicOdjezdu vychZap = new RidicOdjezdu(
                    dobaPrujezdu, SmerPrujezdu.VYCHOD_ZAPAD, vychodAut,zapadAut);
           
            odjezdy = new Mapa<>();
            odjezdy.vloz(SmerPrujezdu.SEVER_JIH, sevJih);
            odjezdy.vloz(SmerPrujezdu.VYCHOD_ZAPAD, vychZap);
            semafor = new Semafor(5000, 7500, odjezdy);
           
            try {
            String conSev = "Sever : " + severAut.getPocet();
            String conJih = "Jih : " + jihAut.getPocet();
            String conZapad = "Zapad : " + zapadAut.getPocet();
            String conVychod = "Vychod : " + vychodAut.getPocet();
            List<String> stav = new ArrayList<>();
            stav.add(conSev);
            stav.add(conJih);
            stav.add(conZapad);
            stav.add(conVychod);
            File file = new File("stav krizovatka.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(conSev +"\n");
            bw.write(conJih+"\n");
            bw.write(conVychod+"\n");
            bw.write(conZapad+"\n");
            bw.close();
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSemaforDobaZelena(SmerPrujezdu prujezd, long x) {
        semafor.setDobaZelena(prujezd, x);
    }

    @Override
    public long getSemaforDobaZelena(SmerPrujezdu prujezd) {
        return semafor.getDobaZelena(prujezd);
    }

    @Override
    public void setCetnostPrijezdu(Smer prijezd, long cetnostA) {
        prijezdy.dej(prijezd).setCetnost(cetnostA);
    }

    @Override
    public long getCetnostPrijezdu(Smer prijezd) {
        return prijezdy.dej(prijezd).getCetnost();
    }

    @Override
    public void setDobaPrujezdu(long s) {
      this.dobaPrujezdu = s;
    }

    @Override
    public long getDobaPrujezdu() {
        return dobaPrujezdu;
    }

    @Override
    public int getPocetCekajicichZeSmeru(Smer smer) {
        return fronty.dej(smer).getPocet();
    }
   
    @Override
    public IFronta<Auto> getFrontaSmeru(Smer smer) {
        return fronty.dej(smer);
    }

    @Override
    public void setHlaseniPrijezduZeSmeru(Smer smer, Consumer<Auto> hlaseni) {
        fronty.dej(smer).setHlaseniPrijezdu(hlaseni);
    }

    @Override
    public void setHlaseniOdjezduZeSmeru(Smer smer, Consumer<Auto> hlaseni) {
        fronty.dej(smer).setHlaseniOdjezdu(hlaseni);
    }

    @Override
    public void setHlaseniSemaforu(Consumer<SmerPrujezdu> hlaseni) {
        semafor.setHlaseniSemaforu(hlaseni);
    }

    @Override
    public void start() {
        semafor.start();
    }

    @Override
    public void stop() {
        semafor.stop();
    }

    @Override
    public void pause() {
        semafor.pause();
    }

}
