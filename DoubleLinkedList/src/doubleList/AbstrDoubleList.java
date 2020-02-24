package doubleList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author st55440
 * @param <E>
 */
public class AbstrDoubleList<E> implements DoubleList<E> {

    private Element prvni;
    private Element posledni;
    private Element aktualni;
    private int size = 0;

    @Override
    public void zrus() {
        if (!jePrazdny()) {
            while (prvni != null) {
                prvni = prvni.dalsi;
            }
        }
        size = 0;
    }

    @Override
    public boolean jePrazdny() {
        return size == 0;
    }

    @Override
    public int getMohutnost() throws NullPointerException {
        return size;
    }

    @Override
    public void vlozPrvni(E data) throws NullPointerException {
        Element element = new Element();
        element.setData(data);
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            posledni = element;
        } else {
            prvni.setPredchudce(element);
            element.setDalsi(prvni);
        }
        prvni = element;
        prvni.setPredchudce(null);
        size++;
    }

    @Override
    public void vlozPosledni(E data) throws NullPointerException {
        Element element = new Element();
        element.setData(data);
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            prvni = element;
        } else {
            element.setPredchudce(posledni);
            posledni.setDalsi(element);
        }
        posledni = element;
        posledni.setDalsi(null);
        size++;
    }

    @Override
    public void vlozNaslednika(E data) throws NullPointerException,
            KolekceException {
        Element element = new Element();
        element.setData(data);
        if (data == null) {
            throw new NullPointerException();
        } else if (aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni == posledni) {
            aktualni.setDalsi(element);
            element.setPredchudce(aktualni);
            posledni = element;
        } else {
            element.setDalsi(aktualni.dalsi);
            element.setPredchudce(aktualni);
            aktualni.dalsi = element;
        }
        size++;
    }

    @Override
    public void vlozPredchudce(E data) throws KolekceException,
            NullPointerException {
        Element element = new Element();
        element.setData(data);
        if (data == null) {
            throw new NullPointerException();
        }
        if (aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni == prvni) {
            aktualni.setPredchudce(element);
            element.setDalsi(aktualni);
            prvni = element;
        } else {
            element.setPredchudce(aktualni.predchudce);
            element.setDalsi(aktualni);
            aktualni.predchudce.setDalsi(element);
            aktualni.setPredchudce(element);
        }
        size++;
    }

    @Override
    public E zpristupniAktualni() throws NoSuchElementException,
            KolekceException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException();
        }
        return aktualni.getData();
    }

    @Override
    public E zpristupniPrvni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        aktualni = prvni;
        return aktualni.getData();
    }

    @Override
    public E zpristupniPosledni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        aktualni = posledni;
        return aktualni.getData();
    }

    @Override
    public E zpristupniNaslednika() throws NoSuchElementException,
            KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni.dalsi == null) {
            throw new NoSuchElementException();
        }
        aktualni = aktualni.dalsi;
        return aktualni.getData();
    }

    @Override
    public E zpristupniPredchudce() throws NoSuchElementException,
            KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        } else if (aktualni.predchudce == null) {
            throw new NoSuchElementException();
        }
        aktualni = aktualni.predchudce;
        return aktualni.getData();
    }

    @Override
    public E odeberAktualni() throws KolekceException,
            NoSuchElementException {
        Element result = new Element();
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException();
        }
        if (size == 1) {
            result = prvni;
            aktualni = prvni = posledni = null;
            return result.getData();
        } else if (aktualni == posledni) {
            result = aktualni;
            aktualni.predchudce.setDalsi(null);
            posledni = aktualni.predchudce;
            return result.getData();
        } else if (aktualni != prvni && aktualni != posledni) {
            result = aktualni;
            aktualni.dalsi.setPredchudce(aktualni.predchudce);
            aktualni.predchudce.setDalsi(aktualni.dalsi);
            aktualni = aktualni.dalsi;
        }
        aktualni = prvni;
        size--;
        return result.getData();
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        Element element = prvni;
        if (prvni.dalsi == null) {
            posledni = null;
            aktualni = null;
        } else {
            prvni.dalsi.setPredchudce(null);
        }
        if (prvni == aktualni) {
            aktualni = prvni.dalsi;
        }
        prvni = prvni.dalsi;
        size--;
        return element.getData();
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        Element result = new Element();
        Element element = posledni;
        if (jePrazdny()) {
            throw new KolekceException();
        }
        if (size == 1) {
            prvni = null;
            posledni = null;
            aktualni = null;
        } else if (posledni == aktualni) {
            posledni = posledni.predchudce;
//            aktualni = posledni;
        } else if (aktualni != posledni) {
            posledni.predchudce.setDalsi(null);
            posledni = posledni.predchudce;
        }
        size--;
        return element.getData();
    }

    @Override
    public E odeberNaslednika() throws KolekceException,
            NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni.dalsi == null) {
            throw new NoSuchElementException();
        }
        Element element = aktualni.dalsi;
        aktualni.setDalsi(aktualni.dalsi.dalsi);
        aktualni.dalsi.dalsi.setPredchudce(aktualni);
        size--;
        return element.getData();
    }

    @Override
    public E odeberPredchudce() throws KolekceException,
            NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni.predchudce == null) {
            throw new NoSuchElementException();
        }
        Element element = aktualni.predchudce;
        if (aktualni == prvni) {
            throw new KolekceException();
        } else if (size == 2) {
            aktualni.setPredchudce(null);
            prvni = aktualni;
        } else {
            aktualni.predchudce.predchudce.setDalsi(aktualni.predchudce.dalsi);
            aktualni.predchudce = aktualni.predchudce.predchudce;
        }
        aktualni = prvni;
        size--;
        return element.getData();
    }

    @Override
    public Stream stream() {
        return DoubleList.super.stream();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<E> implements Iterator<E> {

        private Element n = prvni;

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> cnsmr
        ) {
            Iterator.super.forEachRemaining(cnsmr); 
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public E next() {
            if (hasNext()) {
                E data = (E) n.data;
                n = n.dalsi;
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public void forEach(Consumer action) {
        DoubleList.super.forEach(action);
    }

    public class Element {

        private E data;
        private Element dalsi;
        private Element predchudce;

        public E getData() {
            return data;
        }

        public Element getDalsi() {
            return dalsi;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Element getPredchudce() {
            return predchudce;
        }

        public void setDalsi(Element dalsi) {
            this.dalsi = dalsi;
        }

        public void setPredchudce(Element predchudce) {
            this.predchudce = predchudce;
        }

    }
}
