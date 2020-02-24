package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public class IdentifikatorToken extends Token {

    private String indef;

    public IdentifikatorToken(String indef) {
        super(indef);
        this.indef = indef;
    }



    @Override
    public String toString() {
        return "Token "+"["+indef+"]"+" je indeficator \n";
    }
}
