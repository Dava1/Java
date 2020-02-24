package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public class KeyToken extends Token {

    private String string;

    public KeyToken(String string) {
        super(string);
        this.string = string;
    }

    public boolean keyTokenSearch(String token) {
        boolean bool = false;
        for (KeyTokenEnum kt : KeyTokenEnum.values()) {
            if (kt.Begin.toString().contentEquals(token)) {
             bool = true;
            }
            if (kt.ELSE.toString().contentEquals(token)) {
                bool = true;
            }
            if (kt.END.toString().contentEquals(token)) {
                bool = true;
            }
            if (kt.FOR.toString().contentEquals(token)) {
               bool = true;
            }
            if (kt.IF.toString().contentEquals(token)) {
                bool = true;
            }
            if (kt.RETURN.toString().contentEquals(token)) {
               bool = true;
            }
            if (kt.THEN.toString().contentEquals(token)) {
              bool = true;
            }
            if (kt.WHILE.toString().contentEquals(token)) {
             bool = true;
            }
        }

        return bool;
    }

    public String keyToken(String token) {
        String str = "";
        for (KeyTokenEnum kt : KeyTokenEnum.values()) {

            if (kt.Begin.toString().contains(token)) {
                str = toString() + kt.Begin + "\n";

            }
            if (kt.ELSE.toString().contains(token)) {
                str = toString() + kt.ELSE + "\n";

            }
            if (kt.END.toString().contains(token)) {
                str = toString() + kt.END + "\n";

            }
            if (kt.FOR.toString().contains(token)) {
                str = toString() + kt.FOR + "\n";

            }
            if (kt.IF.toString().contains(token)) {
                str = toString() + kt.IF + "\n";
            }
            if (kt.RETURN.toString().contains(token)) {
                str = toString() + kt.RETURN + "\n";
            }
            if (kt.THEN.toString().contains(token)) {
                str = toString() + kt.THEN + "\n";
            }
            if (kt.WHILE.toString().contains(token)) {
                str = toString() + kt.WHILE + "\n";
            }
        }
        return str;
    }

    @Override
    public String toString() {
        return "Token " + "[" + string + "]" + " je klicove slovo ";
    }

}
