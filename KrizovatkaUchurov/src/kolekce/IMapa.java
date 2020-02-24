package kolekce;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Rozhrani {@code IMapa} predepisuje jednoduche rozhrani pro implementace
 * kolekci s vyhledavanim podle klice.
 * <p>
 * Pozadavek na implementaci:
 * <ul>
 * <li>Implementnaci provest pomoci tridy {@code Seznam} a rozhrani
 * {@code IKolekce}. </li>
 * <li>Pouzit skladani. Pouziti dedicnosti neni povoleno.</li>
 * </ul>
 *
 * @author karel@simerda.cz
 * @param <K> typovy parametr klice
 * @param <E> typovy parametr datove entity
 */
public interface IMapa<K, E> extends Iterable<E> {

    /**
     * Vlozi datovou entitu typu {@code E} s klicem typu {@code K} do kolekce
     * jako posledni prvek seznamu.
     * <p>
     *
     *
     * @param klic hodnota klice, ktery bude prirazen k datove entite
     * @param data vkladana datova entita do mapy
     *
     * @throws KolekceException pokud se vyskytla chyba pri vkladani
     */
    void vloz(K klic, E data) throws KolekceException;

    /**
     * Metoda vrati odkaz na datovou entitu z kolekce podle klice v parametru
     * metody.
     * <p>
     * Struktura seznamu prvku mapy zustane po vykonani metody nezmenena.
     *
     *
     * @param klic klic podle ktereho se vyhledá datova entita v kolekci
     *
     * @return odkaz na datovou entitu u ktere se shodovala hodnota klice, pokud
     * entita nebyla nalezenaje, tak se vrati null.
     */
    E dej(K klic);

    /**
     * Metoda odebere prvek z kolekce podle klice v parametru metody.
     * <p>
     * Vyhledani odebiraneho prvku se provede porovnanim klice v parametru s
     * ulozenym klicem v prvku kolekce. Po odebrani prvku musi zustat seznam
     * prvku kolekce stale spojity.
     * <p>
     * Poznamka k implementaci:
     * <ul>
     * <li>U jednosmerneho linearniho seznamu pokud nejsou implementovany metody
     * primeho ovladani seznamu z rozhrani {@code IKolekce} nelze tuto metodu
     * implementovat</li>
     * <li>Existuje vsak jeste jedna moznost a to, ze se implementuje metoda
     * {@code remove} v iteratoru jednosmerneho seznamu. potom lze iteratorem
     * dokracet k pazadovanemu prvky a ten vyjmout prave metodou {@code remove}.
     * </li>
     * </ul>
     *
     * @param klic klic, podle ktereho se odebere prvek z kolekce
     *
     * @return odkaz na odebiranou datovou entitu
     * @throws kolekce.KolekceException
     */
    default E odeber(K klic) throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /**
     * Metoda prevede obsah seznamu na proud datovych entit, ktery se preda pri
     * navratu.
     * <p>
     * Poznamka: Tato metoda se jiz neimplentuje. Necha se v tomto stavu.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
