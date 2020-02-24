package kolekce;

/**
 * *****************************************************************************
 * Instance třídy {@code KolekceException} reprezentují výjimky ...
 *
 * @author
 * @version
 */
@SuppressWarnings("serial")
public class KolekceException extends Exception {

    public KolekceException() {
    }

    public KolekceException(String message) {
        super(message);
    }

    public KolekceException(Throwable cause) {
        super(cause);
    }

    public KolekceException(String message, Throwable cause) {
        super(message, cause);
    }

}
