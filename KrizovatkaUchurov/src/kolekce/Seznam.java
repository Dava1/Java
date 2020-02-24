package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author st55440
 * @param <E>
 */
class Seznam<E> implements IKolekce<E> {

    private Prvek prvni;
    private Prvek aktualni;
    private int pocet;

    public Prvek getPrvni() {
        return prvni;
    }

    @Override
    public E odeber() throws KolekceException {

        Prvek n = prvni;
        Prvek n1 = null;

        if (jePrazdny()) {
            throw new KolekceException();

        }

        for (int i = 0; i < getPocet() - 2; i++) {
            n = n.dalsi;

        }
        n1 = n.dalsi;
        n.dalsi = null;

        return n1.data;

    }

    @Override
    public int getPocet() {
        Prvek prvek = prvni;

        int length = 0;
        while (prvek != null) {
            prvek = prvek.dalsi;
            length++;
        }
        return length;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        aktualni = prvni;
    }

    @Override
    public boolean jeDalsi() throws KolekceException {
        return aktualni.dalsi != null;
    }

    @Override
    public void prejdiNaDalsi() throws KolekceException {
        if (jeDalsi()) {
            aktualni = aktualni.dalsi;
        } else {
            throw new KolekceException();
        }
    }

    @Override
    public boolean jePrazdny() {
        return getPocet() == 0;
    }

    @Override
    public void pridej(E data) throws KolekceException {
        Prvek prvek = new Prvek();
        prvek.data = data;
        if (prvni == null) {
            prvni = prvek;
        } else {
            Prvek nPrvek = prvni;
            while (nPrvek.dalsi != null) {
                nPrvek = nPrvek.dalsi;
            }
            nPrvek.dalsi = prvek;
        }

    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {

            throw new KolekceException("Seznam je prazdny");
        }
        Prvek prvek = prvni;
        prvni = prvek.dalsi;
        return prvek.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Prvek n = prvni;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = n.data;
                    n = n.dalsi;
                    return data;
                }
                else{
                    throw new NoSuchElementException();
                }
            }
        };
    }

    @Override
    public E zpristupni() throws KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        }
        return aktualni.getData();
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[getPocet()];
        array = toArray(array);
        return array;
    }

    @Override
    public E[] toArray(E[] array) throws IllegalArgumentException {
        array = (E[]) new Object[getPocet()];
        try {
            nastavPrvni();
        } catch (KolekceException ex) {
            Logger.getLogger(Seznam.class.getName());
        }
        for (int i = 0; i < getPocet(); i++) {
            array[i] = aktualni.data;
            aktualni = aktualni.dalsi;
        }
        return array;
    }

    @Override
    public E[] toArray(Function<Integer, E[]> createFunction) {
        E[] pole = createFunction.apply(getPocet());
        try {
            nastavPrvni();
        } catch (KolekceException ex) {
            Logger.getLogger(Seznam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pole;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (jePrazdny()) {

            throw new KolekceException();
        }
        Prvek prvek = prvni;
        Prvek n = null;
        for (int i = 0; i < getPocet() - 2; i++) {
            prvek = prvek.dalsi;

        }
        System.out.println("Data :" + prvek.data);
        n = prvek.dalsi;
        prvek.dalsi = null;
        return n.data;
    }

    @Override
    public void zrus() {
        if (!jePrazdny()) {
            while (prvni != null) {
                prvni = prvni.dalsi;
            }
        }
    }

    class Prvek {

        private E data;
        private Prvek dalsi;

        public E getData() {
            return data;
        }

        public Prvek getDalsi() {
            return dalsi;
        }

        public void setDalsi(Prvek dalsi) {
            this.dalsi = dalsi;
        }

    }

}
