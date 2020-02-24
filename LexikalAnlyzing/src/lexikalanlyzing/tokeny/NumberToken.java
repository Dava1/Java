package lexikalanlyzing.tokeny;

/**
 *
 * @author st55440
 */
public class NumberToken extends Token {

    private String num;

    public NumberToken(String num) {
        super(num);
        
    }
 
    public String hexNumber(String token){
    String value;
        return  value = "Indeficator " + "["+"0x"+token+"]"+" je hexidalne cislo \n";
    } 
            
    @Override
    public String toString() {
        return "Ideficator " + "[" + num + "]" + " je cele cislo \n";
    }

}
