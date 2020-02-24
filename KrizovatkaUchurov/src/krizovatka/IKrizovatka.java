package krizovatka;

import java.util.function.Consumer;
import kolekce.IFronta;

/**
 * Toto rozhraní {@code IKrizovatka} je určeno k implementaci jádra simulátoru
 * křižovatky.
 * <p>
 * Simulátor křižovatky má mít tyto prvky: Je to klasická křižovatka 4 silnic
 * (SeverJih-Východ-Západ). V každém příchozím směru je jen jeden jednoduchý
 * semafor. Pro zjednodušení předpokládáme, že mají jen zelené nebo červené
 * světla. Světla se mění takto: Určitou dobu X1 svítí semafory Sever a Jih
 * zeleně, Západ a Východ červeně. Potom se vymění a po dobu X2 svítí naopak. A
 * tak stále dokola. Auta přijíždějí ke křižovatce s určitými frekvencemi: Ze
 * severu A1 aut/minutu, z jihu A2 aut/minutu, ze západu A3 aut/minutu, z
 * východu A4 aut/minutu. Automobily opouští křižovatku v tom pořadí, v jakém
 * přijely. Opuštění křižovatky trvá každému automobilu S1 sekund, teprve potom
 * může jet další. Pro zjednodušení též předpokládáme, že žádná auta nebudou
 * odbočovat. Žádné jiné prvky v simulaci hrát roli nebudou, např. neřešte
 * kolize automobilů, přednosti v jízdě apod. Bude to velmi zjednodušený
 * simulátor.
 * <p>
 * <p>
 * Poznámky k implementaci:
 * <ol>
 * <li>Požaduje se, aby časování simulace křižovatky bylo po 0.1 sekundě </li>
 * <li>V implementačních třídách simulace křižovatky je vyžadováno použití pouze
 * vlastních datových struktur, které vyhoví rozhraním v balíčku
 * {@code kolekce}. Jinak řečeno: Není dovoleno použití jakýchkoliv instančních
 * tříd z knihoven Java nebo z jiných zdrojů.</li>
 * </ol>
 *
 * @author karel@simerda.cz
 *
 */
public interface IKrizovatka {

    /**
     * Výčet směrů příjezdových silnic křižovatky
     */
    public enum Smer {
        SEVER, VYCHOD, JIH, ZAPAD;
    }

    /**
     * Výčet směrů průjezdu křižovatkou
     */
    public enum SmerPrujezdu {
        SEVER_JIH, VYCHOD_ZAPAD;
    }

    /**
     * Metoda nastavi podle směru průjezdu křižovatkou dobu průjezdu na zelenou-
     *
     * @param prujezd Směr průjezdu křižovatkou
     * @param x Doba svícení zeleného světla semaforu v milisekundách
     */
    void setSemaforDobaZelena(SmerPrujezdu prujezd, long x);

    /**
     * Metoda dodá dobu svícení zeleného světla semaforu ve směru průjezdu
     *
     * @param prujezd Směr průjezdu křižovatkou
     *
     * @return Doba svícení zeleného světla semaforu v milisekundách
     */
    long getSemaforDobaZelena(SmerPrujezdu prujezd);

    /**
     * Metoda nastavi četnost příjezdu v zadaném směru příjezdu
     *
     * @param prijezd Směr příjezdu do křižovatky
     *
     * @param cetnostA Četnost/frekvence příjezdu aut do křižovatky v počtu aut
     * za minutu
     *
     */
    void setCetnostPrijezdu(Smer prijezd, long cetnostA);

    /**
     * Metoda dodá četnost-frekvenci příjezdu aut do křižovatky v zadaném směru
     *
     * @param prijezd Směr příjezdu do křižovatky
     *
     * @return Četnost/frekvence příjezdu aut za jednu minutu
     */
    long getCetnostPrijezdu(Smer prijezd);

    /**
     * Metoda nastaví dobu průjezdu jednoho auta křižovatkou
     *
     * @param s Doba průjezdu auta křižovatkou v milisekundách
     */
    void setDobaPrujezdu(long s);

    /**
     * Metoda dodá dobu průjezdu jednoho auta křižovatkou
     *
     * @return Doba průjezdu křižovatkou v milisekundách
     */
    long getDobaPrujezdu();

    /**
     * Metoda dodá počet čekajících aut v zadaném směru přijezdu do křižovatky
     *
     * @param smer Směr příjezdu do křižovatky
     *
     * @return počet čekajících aut
     */
    int getPocetCekajicichZeSmeru(Smer smer);

    /**
     * Metoda dodá seznam čekajících aut ve zadanem směru.
     *
     * @param smer Směr příjezdu aut do křižovatky
     *
     * @return Seznam čekajích aut v datové struktuře fronta
     */
    IFronta<Auto> getFrontaSmeru(Smer smer);

    /**
     * Metoda nastaví zpětné hlášení o příjezdu auta v zadaném směru do
     * křižovatky.
     * <p>
     * Hlášení se vystaví v okamžiku příjezdu nového auta do křižovatky v daném
     * směru
     *
     * @param smer Směr v kterém budou přijíždět auta do křižovatky ¨
     * @param hlaseni Hlášení jaké auto přijelo v zadaném směru do křižovatky
     */
    void setHlaseniPrijezduZeSmeru(Smer smer, Consumer<Auto> hlaseni);

    /**
     * Metoda nastaví zpětné hlášení o odjezdu auta v zadaném směru z
     * křižovatky.
     * <p>
     * Hlášení se vystaví v okamžiku odjezdu auta z křižovatky v daném směru
     *
     * @param smer Směr z kterého směrum budou auta odjíždět
     * @param hlaseni Hlášení jaké auto odjelo z křižovatky v zadaném směru
     */
    void setHlaseniOdjezduZeSmeru(Smer smer, Consumer<Auto> hlaseni);

    /**
     * Metoda nastaví zpětné hlášení o změně směru průjezdu aut křižovatkou.
     * <p>
     * Ze směru lze potom určit jak svíti světla semaforu
     *
     * @param hlaseni Hlášení o změně směru průjezdu aut křižovatkou
     */
    void setHlaseniSemaforu(Consumer<SmerPrujezdu> hlaseni);

    /**
     * Metoda zahájí simulaci průjezdu automobilů křižovatkou.
     */
    void start();

    /**
     * Metoda ukončí simulaci průjezdu automobilů křižovatkou.
     */
    void stop();

    /**
     * Metoda pozastaví simulaci průjezdu automobilů
     */
    void pause();
}
