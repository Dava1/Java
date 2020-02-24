 
package doubleList;

import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Rozhraní DoubleList je určeno k implementaci dvousměrného spojového seznamu.
 * <p>
 * Nezáleží, zda se bude implementovat necyklická nebo cyklická varianta.
 * <p>
 * Chování implementace tohoto rozhraní musí plně vyhovět učitelskému testu a
 * zadání semestrální práce. Při vzniku rozporů mezi zadáním semstrální práce a
 * kontraktem tohoto rozhraní, platí to co v koontraktu.
 * <p>
 * <p>
 * <b>Poznámka:</b>
 * Význam použití výjimek není konzistentní v jednotlivých metodách. Proto je
 * nutné u každé metody použít výjimky tak, jak specifikováno.
 *
 * @author kasi
 * @param <E>
 */
public interface DoubleList<E> extends Iterable<E> {
    /**
     * Metoda zruší obsah celého seznamu.
     * <p>
     */
    void zrus();

    /**
     * Test naplněnosti seznamu.
     *
     * @return Vrací true, když seznam je prázdný.
     */
    boolean jePrazdny();

    /**
     * Metoda zjistí počet prvků v seznamu.
     *
     * @return počet prvku v seznamu
     */
    int getMohutnost();

    /**
     * Metoda vlozi datový element z parametru seznamu jako první prvek seznamu.
     * <p>
     * Pokud bude seznam prázdný, nastaví se tento vkládaný prvek zároveň jako
     * poslední. Aktuální pozice ukazatele do seznamu se touto metodou nemění.
     *
     * @param data Reference na datový element, který se vloží do seznamu.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     *
     */
    void vlozPrvni(E data) throws NullPointerException;

    /**
     * Metoda vloží datový element odkazovaný parametru konec seznamu.
     * <p>
     * Když bude seznam prázdný, tak se tento nový prvek zároveň nastaví jako
     * první. Aktuální prvek se touto metodou nemění.
     *
     * @param data Reference na datový element, která se má vložit do seznamu.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     */
    void vlozPosledni(E data) throws NullPointerException;

    /**
     * Metoda vytvoří nový prvek seznamu s datovým elementem z parametru a
     * zařadí tento prvek jako následníka aktuálního prvku. Pokud aktuální prvek
     * bude zároveň posledním prvkem seznamu dojde ke změně posledního prvku,
     * kterým bude nový prvek, přičemž aktuální prvek se nezmění.
     *
     *
     * @param data Reference na datový element, která se má vložit do seznamu
     * jako následník.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     * @throws KolekceException
     *
     */
    void vlozNaslednika(E data) throws NullPointerException, KolekceException;

    /**
     * Metoda vytvoří nový prvek seznamu s datovým elementem z parametru a
     * zařadí tento prvek jako předchůdce aktuálního prvku. Pokud bude aktuální
     * prvek zároveň prvním prvkem dojde k změně prvního prvku, kterým bude nový
     * prvek, přičemž aktuální prvek se nezmění.
     *
     *
     * @param data Datový element, který se má vložit do seznamu jako
     * předchůdce.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     *
     * @throws KolekceException Vystavení výjimky při nenastaveném aktuálním
     * prvku.
     *
     */
    void vlozPredchudce(E data) throws KolekceException, NullPointerException;

    /**
     * Metoda zpřístupňuje data aktuálního prvku seznamu.
     *
     * @return Reference na zpřístupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu. *
     *
     * @throws KolekceException Výjjimka se vystaví, když není nastaven aktuální
     * prvek seznamu
     */
    E zpristupniAktualni() throws NoSuchElementException, KolekceException;

    /**
     * Metoda zpřístupňuje data prvního prvku seznamu. Zároveň nastaví aktuální
     * prvek na první prvek seznamu.
     *
     * @return Reference na zpřístupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu. *
     *
     */
    E zpristupniPrvni() throws NoSuchElementException;

    /**
     * Metoda zpřístupňuje data posledního prvku seznamu. Zároveň nastaví
     * aktuální prvek na poslední prvek seznamu.
     *
     * @return Reference na zpřístupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     *
     */
    E zpristupniPosledni() throws NoSuchElementException;

    /**
     * Metoda zpřístupňuje data nasledníka aktuálního prvku seznamu. Zároveň
     * nastaví aktuální prvek na následníka aktuálního prvku seznamu.
     *
     * @return Reference na zpřístupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví, když není následník
     * aktuálního prvku.
     *
     * @throws KolekceException Výjimka se vystaví v případě, když není nastaven
     * aktuální prvek.
     *
     */
    E zpristupniNaslednika() throws NoSuchElementException, KolekceException;

    /**
     * Metoda zpřístupňuje data predchůdce aktuálního prvku seznamu. Zároveň
     * nastaví aktuální prvek na předchůdce aktuálního prvku seznamu. *
     *
     * @return Reference na zpřístupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví, když není předchůdce
     * akuálního prvku.
     *
     * @throws KolekceException Výjimka se vystaví v případě, když není nastaven
     * aktuální prvek.
     */
    E zpristupniPredchudce() throws NoSuchElementException, KolekceException;

    /**
     * Metoda odebere (vyjme) aktuální prvek ze seznamu. Potom bude za aktuální
     * prvek nastaven na první prvek, pokud ovšem je takový prvek v seznamu.
     *
     * @return Reference na odebraný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     *
     * @throws KolekceException Výjimka se vystaví v případě, když není nastaven
     * aktuální prvek.
     */
    E odeberAktualni() throws KolekceException, NoSuchElementException;

    /**
     * Metoda odebere první prvek ze seznamu. Pokud byl první prvek nastaven
     * současně jako aktuální, bude za aktuální prvek nastaven nasledný prvek,
     * pokud ovšem takový prvek bude v seznamu.
     *
     * @return Reference na odebraný datový element v seznamu.
     *
     * @throws KolekceException výjimka se vystaví při prázdném seznamu
     */
    E odeberPrvni() throws KolekceException;

    /**
     * Metoda odebere poslední prvek ze seznamu. Pokud byl poslední prvek
     * nastaven současně jako aktuální, bude za aktuální prvek nastaven
     * předchozí prvek, pokud ovšem takový prvek bude v seznamu.
     *
     * @return Reference na odebraný datový element v seznamu.
     *
     * @throws KolekceException Výjimka se vystaví při prázdném seznamu.
     */
    E odeberPosledni() throws KolekceException;

    /**
     * Metoda odebere následníka aktuálního prvku ze seznamu.
     *
     * @return Reference na odebraný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu
     * nebo v takovém případě, když není následník.
     *
     * @throws KolekceException Výjimka se vystaví v případě, když není nastaven
     * aktuální prvek.
     */
    E odeberNaslednika() throws KolekceException, NoSuchElementException;

    /**
     * Metoda odebere předchůdce aktuálního prvku seznamu
     *
     * @return Reference na odebraný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu
     * nebo v takovém případě, když není předchůdce.
     *
     * @throws KolekceException Výjimka se vystaví v případě, když není
     * zpřistupněn aktuální prvek.
     */
    E odeberPredchudce() throws KolekceException, NoSuchElementException;

    /**
     * Metoda vytvoří instanci datového proudu {@code Stream}. Tuto metodu není
     * zapotřebí překrývat v implementaci.
     *
     * @return reference na datový proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
