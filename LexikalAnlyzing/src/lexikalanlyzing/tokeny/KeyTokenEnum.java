 
package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public enum KeyTokenEnum {
  Begin("begin"),END("end"),IF("if"),ELSE("else"),FOR("for"),WHILE("while"),
  THEN("then"),RETURN("return");

private String key;

    private KeyTokenEnum(String key) {
        this.key = key;
    }

    
    @Override
    public String toString() {
        return  key;
    }



}
