package kolekce;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Rozhrani {@code IKolekce} predepisuje jednoduche rozhrani pro ruzne
 * implementace kolekci.
 * <p>
 * Rozhrani rozsiruje rozhrani Iterable, ktere ma jednu abstraktni metodu
 * {@code Iterator<T> iterator()} a dalsi dve metody, a to
 * {@code void forEach(Consumer<? super T> action)} a
 * {@code Spliterator<T> spliterator()}, ktere jsou oznaceny jako
 * {@code default}. Tyto defaultni metody zajistuji implicitni chovani vsem
 * implementacnim tridam pro praci s prvky seznamu.
 * <p>
 * <p>
 * Protoze se jedna o univerzalni rozhrani, jsou nektere metody navrzeny jako
 * {@code default}, aby je nektere implementacni tridy nemusely implementovat.
 * <p>
 * <p>
 * Metody tohoto rozhrani byly zvoleny tak, aby se procvicila latka z prednasek
 * a cviceni predmetu IOOP. Dalsim duvodem je to, ze implementace podobnych
 * rozhrani bude vyzadovana v pristim semestru v predmetu IDATS datove
 * struktury. Pricemz je dana moznost, ze nektere metody nebude nutne
 * implementovat, protoze jsou v tomto rozhrani navrzeny jako {@code default}.
 * To umozni zdatnejsim studentum si vyzkouset sve dovednosti a tim se lepe
 * pripravit na zvladnuti tri semestralnich praci v ramci predmetu datove
 * struktury. Mene zdatnejsim studentum, to zase umozni implementovat jenom ty
 * metody, ktere jsou potrebne pro druhou semestralni praci z predmetu objektove
 * programovani.
 *
 * @author karel@simerda.cz
 *
 * @param <E> typovy parametr datove entity
 */
public interface IKolekce<E> extends Iterable<E> {

    /* =========================================================================
       Zjistovaci metody
       -----------------
     */
    /**
     * Metoda vrátí maximální velikost kolekce (kapacitu), pokud to bude mít
     * smysl. To znamena, ze defaultni metoda bude prekryta v implementacni
     * tride.
     *
     * @return maximalni pocet mist v kolekci nebo -1, kdyz to nebude mozne
     * implementovat
     */
    default public int getVelikost() {
        return -1;
    }

    /**
     * Metoda vrati aktualni pocet vlozenych datovych entit {@code E} do kolekce
     *
     * @return pocet datovych entit v kolekci
     */
    int getPocet();

    /**
     * Metoda zjisti, zda je kolekce prazdna.
     *
     * @return vraci {@code true}, kdy je kolekce prazdna, jinal {@code false}
     */
    boolean jePrazdny();

    /**
     * Metoda zjisti, zda je kolekce plna.
     * <p>
     * <p>
     * Plnost kolekce zavisi na tom, jake jsou pozadavky na implementacni tridy
     * podle tohto rozhrani. Omezeni muze byt dano maximalnim poctem prvku
     * kolekce v parametru konstruktoru implementacini tridy, pricemz to nemusi
     * zalezet na tom, zda implementace je na poli nebo zda je realizovana
     * spojovym seznamem.
     * <p>
     * I tato metoda je zde uvedena jako defaultni, takze neni zapotrebi ji
     * realizovat v implementacich se spojovym seznamem. Pokud bude implementace
     * polem, tak samozrejmne se tato metoda musi prekryt nebo zajistit
     * automaticke rozsirovani interniho pole.
     *
     * @return vraci {@code true}, kdyz je kolekce plna, jinak vraci
     * {@code false}
     */
    default boolean jePlny() {
        return false;
    }

    /* =========================================================================
       Metody pridavani a odebirani datovych entit typu{@code E} z kolekce.    
       --------------------------------------------------------------------
     */
    /**
     * Prida datovou entitu do kolekce na konec seznamu.
     * <p>
     * <p>
     * U implementace spojovym seznamem to bude vzdy na konec seznamu. U
     * implementace pomoci pole to bude se musi provest kontrola na volne misto
     * a kdyz se volne misto zjisti, tak se musi prvky pole presunout.
     *
     * @param data vkladana datova entita do kolekce
     *
     * @throws KolekceException pokud se vyskytla chyba pri vkladani
     */
    void pridej(E data) throws KolekceException;

    /**
     * Metoda vlozi serii datovych entit v parametrech metody do kolekce.
     * <p>
     * Tato metoda slouzi k usnadneni vkladani serie datovych entit do kolekce v
     * ramci volani jedne metody. Touto {@code default} metodou budou
     * automaticky "vybaveny" vsechny implemetnace tohoto rozhrani.
     *
     * @param data seznam vkladanych datovych entit
     *
     * @throws KolekceException pokud se vyskytla chyba pri vkladani
     */
    default void pridej(E... data) throws KolekceException {
        for (E prvek : data) {
            pridej(prvek);
        }
    }

    /**
     * Metoda odebere prvni prvek z kolekce a vrati odkaz na datovou entitu
     * prvku, ktery byl pripojen k odebiranemu prvnimu prvku kolekce.
     * <p>
     * Po odebrani prvku bude seznam stale spojity. Kdyz se odebere prvni, tak
     * nasledujici prvek se automaticky stane prvnim. Pokud kolekce po odebrani
     * prvku bude obsahovat pouze jeden prvek, tak tento provek bude prvni i
     * posledni.
     *
     * @return pokud kolekce obsahuje alespon jeden prvek, tak se vraci odkaz na
     * datovou entitu, jinak se vystavuje vyjimka
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna.
     */
    E odeberPrvni() throws KolekceException;

    /**
     * Metoda odebere prvek z kolekce, ktery byl vlozen do kolekce jako posledni
     * a vrati odkaz na datovou entitu odebiraneho prvku.
     * <p>
     * Po odebrani prvku musi zustat seznam stale spojity. Kdyz se odebere
     * posledni, tak predchazejici se automaticky stane poslednim, i kdyby byl
     * zaroven prvni.
     *
     * @return pokud kolekce obsahuje alespon jeden prvek, tak se vraci odkaz na
     * datovou entitu z odebiraneho prvku, jinak vystavuje vyjimku
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna.
     *
     */
    E odeberPosledni() throws KolekceException;

    /**
     * Metoda zrusi obsah seznamu.
     */
    void zrus();

    /* =========================================================================
       Metody prevodu obsahu kolekce na pole datovych entit.
       -----------------------------------------------------
       Metody vraceji pole s typem prvku Object nebo  s typem, ktery je dan
       typovym parametrem   tridy. 
       Tyto metody jsou urceny k procviceni ruznych pristupu, jak objektu sdelit 
       jakeho typu maji byt prvky vystupniho pole. 
    
       Upozorneni: Metody vraceji pole s odkazy na datove entity, ktere byly 
                   vlozeny do kolekce. V zadnem pripade metoda nesmi vracet pole
                   s odkazy na pomocne vnitrni objekty, ktere byly nutne pro 
                   vytvoreni linearniho seznamu.    
     */
    /**
     * Metoda vrati pole s kopiemi datovych entit kolekce o delce presne
     * odpovidajici poctu vlozenych datovych entit. Typ prvku pole bude Object,
     * protoze typ prvku pole po jeho vytvoreni nelze zmenit.
     *
     * @return pole objektu
     */
    E[] toArray();

    /**
     * Metoda vrati pole s kopiemi datovych entit o delce odpovidajici delce
     * pole v parametru metody. Pole bude mit prvky stejneho typu jako pole v
     * parametru.
     *
     * Pred volanim teto metody je vhodne si zjistit pocet prvku v kolekci a
     * pole vytvorit nejen s prvky pozadovaného typu ale i delky.
     *
     * @param array vzorove pole s ocekavanym typem prvku pole a delky
     *
     * @return pole s odkazy na vlozene entity kolekce
     *
     * @throws IllegalArgumentException vystavi vyjimku, kdyz pole je mensi nez
     * pocet prvku v seznamu
     */
    E[] toArray(E[] array) throws IllegalArgumentException;

    /**
     * Metoda vrati pole s kopiemi datovych entit o delce presne odpovidajici
     * poctu vlozenych entit. Pole bude mit prvky pozadovaneho typu.
     *
     * @param createFunction funkce na vytvoreni pole skutecnym typem prvku
     *
     * @return pole s kopiemi datovych prvku seznamu
     */
    E[] toArray(Function<Integer, E[]> createFunction);

    /* =========================================================================
       Metody primeho ovladani seznamu - Jsou nepovinne!!!
       To jsou metody nastavPrvni, prejdiNaDalsi, zpristupni, vlozPred, vlozZa a
       odeber.
    
        Upozorneni: Tyto metody nebo velmi podobne jsou velmi casto vyzadovany
                    v zadanich semestralnich praci na datovych strukturach. 
                    Metody slouzi k procviceni primeho ovladani seznamu. Tim se
                    mysli to, ze lze pomoci techto metod menit strukturu seznamu.
                    Prakticky lze jakoukoliv datovou entitu ulozit na libovolne 
                    misto seznamu a nebo ji z tohoto mista odebrat.
    
     */
    /**
     * Metoda nastavi aktualni vnitrni ukazatel na prvni prvek kolekce.
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna nebo
     * kdyz metoda neni implemenovana ve tride
     */
    default void nastavPrvni() throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /**
     * Metoda presune ukazatel na aktualni prvek na dalsi prvek v kolekci.
     * <p>
     * Pred prvnim volanim teto metody musi byt vzdy nastaven ukazatel na
     * aktualni prvek seznamu. Pote lze jiz tuto metodu volat tolikrat kolik je
     * potreba. Jeste jinak, pred volanim teto metody musi byt vnitrni aktualni
     * prvek nejakym zpusobem nastaven.
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna,
     * neni nastaven aktualni prvek nebo kdyz metoda neni implemenovana ve tride
     */
    default void prejdiNaDalsi() throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /**
     * Metoda vraci referenci na datovy objekt prvku kolekce na ktery ukazuje
     * vnitrni aktualni ukazatel.
     *
     * @return datovy prvek kolekce z aktualni pozice
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna,
     * neni nastaven aktualni prvek nebo kdyz metoda neni implemenovana ve tride
     */
    default E zpristupni() throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /**
     * Metoda vraci informaci, zda je k dispozici dalsi aktualni prvek kolekce.
     *
     * @return true, kdyz aktualni prvek je napojen na dalsi prvek v kolekce
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je kolekce prazdna,
     * neni nastaven aktualni prvek nebo kdyz metoda neni implemenovana ve tride
     */
    default boolean jeDalsi() throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /**
     * Metoda odebere aktualni prvek ze seznamu.
     * <p>
     * Po odebrani prvku je seznam stale spojity. Kdyz se odebere prvni, tak
     * nasledujici se automaticky stane prvnim. Kdyz se odebere prvek uprostred
     * seznamu, tak dojde ke spojeni predchoziho s nasledujicim prvkem. Ukaztel
     * aktualniho prvku je po odebrani nedefinovan. Musi se znovu nastavit
     * ukazatel na prvni polozku a prejit na nove pozadované misto.
     *
     * @return pokud je prvek nalezen vraci se reference na odebirany datovy
     * prvek, jinak se vraci null
     *
     * @throws KolekceException vyjimka se vystavi, kdyz je seznam prazdny, neni
     * nastaven aktualni prvek nebo kdyz metoda neni implemenovana ve tride.
     */
    default E odeber() throws KolekceException {
        throw new KolekceException("Metoda neni implementovana");
    }

    /* =========================================================================
       Metoda prevodu obsahu seznamu na datovy proud datovych entit.
    
       Upozorneni: Tuto metodu jiz neni zapotrebi prekryvat v implementacnich 
                   tridach
    
     */
    /**
     * Metoda prevede obsah seznamu na proud datovych entit, ktery se preda pri
     * navratu.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
