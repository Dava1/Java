package lexikalanlyzing;

import lexikalanlyzing.anlizator.LexikalAnlyzing;
import java.io.FileNotFoundException;

/**
 *
 * @author st55440
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
              try{
        LexikalAnlyzing analyz = new LexikalAnlyzing(args[0]);
              }catch (java.lang.StringIndexOutOfBoundsException e ){
                  System.out.println("Dodajte separator na konec prosim");
              }
    }
}
