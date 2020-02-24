 
package mereni;

import doubleList.AbstrDoubleList;
import doubleList.DoubleList;
import doubleList.KolekceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author st55440
 *
 */
public class AbstrMereni implements SpravceMereni<Mereni> {

    private DoubleList<Mereni> spravceMereni; 
    
    public AbstrMereni() {
    spravceMereni = new AbstrDoubleList<>();
    }

    
    @Override
    public void vlozMereni(Mereni merni, EnumPozice pozice) throws KolekceException, NoSuchElementException {
        switch (pozice) {
            case PRVNI:
                spravceMereni.vlozPrvni(merni);
                break;
            case POSLEDNI:
                spravceMereni.vlozPosledni(merni);
                break;
            case NASLEDNIK:
                spravceMereni.vlozNaslednika(merni);
                break;
            case PREDCHUDCE:
                spravceMereni.vlozPredchudce(merni);
                break;
        }
    }

    @Override
    public Mereni zpristupniMereni(EnumPozice pozice) throws KolekceException, NoSuchElementException {
        switch (pozice) {
            case PRVNI:
                return spravceMereni.zpristupniPrvni();
            case POSLEDNI:
                return spravceMereni.zpristupniPosledni();
            case NASLEDNIK:
                return spravceMereni.zpristupniNaslednika();
            case PREDCHUDCE:
                return spravceMereni.zpristupniPredchudce();
            default:
                break;
        }
        return null;
    }

    @Override
    public Mereni odeberMereni(EnumPozice pozice) throws NoSuchElementException, 
            KolekceException {
        switch (pozice) {
            case PRVNI:
                return spravceMereni.odeberPrvni();
            case POSLEDNI:
                return spravceMereni.odeberPosledni();
            case NASLEDNIK:
                return spravceMereni.odeberNaslednika();
            case PREDCHUDCE:
                return spravceMereni.odeberPredchudce();
            default:break;
        }
        return null;
    }

    @Override
    public Iterator iterator() {
       return spravceMereni.iterator();
    }

    @Override
    public DoubleList MereniDen( LocalDate datum) {
      DoubleList<Mereni> current = new AbstrDoubleList<>();
        for (Mereni mereni : spravceMereni) {
            if (datum.getDayOfMonth() == mereni.getCasMereni().getDayOfMonth()){
                current.vlozPrvni(mereni);
            }
        }
    return current;
    }

    @Override
    public double MaxSpotreba(LocalDateTime datumOd, 
            LocalDateTime datumDo) {
       double maxSpotreba = 0;
        for (Mereni mereni : spravceMereni) {
            if (mereni.getCasMereni().isAfter(datumOd) 
                    && mereni.getCasMereni().isBefore(datumDo)){
                if (mereni instanceof MereniElektrika ) {
                    maxSpotreba += ((MereniElektrika) mereni).getSpotrebaNT() 
                            + ((MereniElektrika) mereni).getSpotrebaVT();
                }
                if (mereni instanceof MereniVoda) {
                    maxSpotreba += ((MereniVoda) mereni).getSpotrebaM3();
                }
            }
        }
        return maxSpotreba;
    }

    @Override
    public void zrus() {
        spravceMereni.zrus();
    }

}
