package kolekce;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Toto rozhrani je urceno pro implementaci algoritmu FIFO – First In First Out
 * <p>
 * <p>
 Pozadavek na implementaci:
 * <ul>
 * <li>Implementnaci provest pomoci tridy {@code Seznam} a rozhrani
 * {@code IKolekce}. </li>
 * <li>Pouzit skladani. Pouziti dedicnosti neni povoleno.</li>
 * </ul>
 * @author karel@simerda.cz
 * @param <E> typovy parametr tridy {@code Fronta}
 */
public interface IFronta<E> extends Iterable<E> {

    /**
     * Metoda zjisti pocet prvku ve fronte
     *
     * @return pocet prvku ve fronte
     */
    int getPocet();

    /**
     * Metoda zjisti, zda je fronta prazdna.
     *
     * @return vraci {@code true}, kdy je kolekce prazdna, jinal {@code false}
     */
    boolean jePrazdny();

    /**
     * Metoda prida datovou entitu do fronty na jeji konec .
     * <p>
     * <p>
     *    *
     * @param data vkladana datova entita do kolekce
     *
     * @throws KolekceException pokud se vyskytla chyba pri pridavani
     */
    void vloz(E data) throws KolekceException;

    /**
     * Metoda odeber prvni prvek z fronty a vrati odkaz na datovou entitu,
     * kterou nesl odebirany prvek fronty..
     * <p>
     * <p>
     *
     * @return odkaz na odebirranou datovou entitu z fronty
     *
     * @throws KolekceException pokud se vyskytla chyba pri pridavani
     */
    E odeber() throws KolekceException;

    /**
     * Metoda zrusi cely obsah fronty. Fronta po vykonani metodu bude ve stavu
     * prazdna.
     */
    void zrus();

    /**
     * Metoda prevede obsah fronty na proud datovych entit, ktery se preda pri
     * navratu.
     * 
     * Poznamka: Tato metoda se jiz neimplentuje. Necha se v tomto stavu.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
