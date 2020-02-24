package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public abstract class Token {

    private String string;

    public Token( String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
