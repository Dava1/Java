package casovani;

/**
 * {@code Posluchac} je specialni funkcni rozhrani pro registraci posluchacu
 * casovace.
 * <p>
 * Je ekvivalentem knihovniho rozhrani {@code Runnable}
 *
 * @author karel@simerda.cz
 *
 * @see java.lang.Runnable
 */
@FunctionalInterface
public interface Posluchac {

    void aktualizuj(); // update
}
