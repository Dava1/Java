package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public enum SeparatorTokenEnum {
    white_key(" "), equals("="), carka(","), dot("."), strednik(";"),dvojetecka(":");

    String str;

    private SeparatorTokenEnum(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
