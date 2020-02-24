package senzory;

import doubleList.AbstrDoubleList;
import doubleList.DoubleList;
import doubleList.KolekceException;
import java.util.Iterator;
import mereni.EnumPozice;

/**
 *
 * @author st55440
 */
public class SenzoryList implements Senzory {

    private DoubleList<Senzor> listSenzory;

    public SenzoryList() {
        listSenzory = new AbstrDoubleList<>();
    }

@Override
    public void vlozSenzor(Senzor senzor, EnumPozice pozice) throws
            NullPointerException, KolekceException {
        switch (pozice) {
            case PRVNI:
                listSenzory.vlozPrvni(senzor);
                break;
            case POSLEDNI:
                listSenzory.vlozPosledni(senzor);
                break;
            case NASLEDNIK:
                listSenzory.vlozNaslednika(senzor);
                break;
            case PREDCHUDCE:
                listSenzory.vlozPredchudce(senzor);
                break;
        }
    }

    @Override
    public Senzor odeberSenzor(EnumPozice pozice) throws KolekceException {
        switch (pozice) {
            case PRVNI:
                listSenzory.odeberPrvni();
                break;
            case POSLEDNI:
                listSenzory.odeberPosledni();
                break;
            case NASLEDNIK:
                listSenzory.odeberNaslednika();
                break;
            case PREDCHUDCE:
                listSenzory.odeberPredchudce();
                break;
        }
        return null;
    }

    @Override
    public void zrus() {
        listSenzory.zrus();
    }

    @Override
    public Iterator iterator() {
        return listSenzory.iterator();
    }

    @Override
    public Senzor zpristupniAktualni(EnumPozice pozice) throws KolekceException {
        switch (pozice) {
            case PRVNI:
                return listSenzory.zpristupniPrvni();
            case POSLEDNI:
                return listSenzory.zpristupniPosledni();
            case NASLEDNIK:
                return listSenzory.zpristupniNaslednika();
            case PREDCHUDCE:
                return listSenzory.zpristupniPredchudce();
        }
        return null;
    }

}
