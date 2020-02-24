package lexikalanlyzing.anlizator;

import com.sun.javafx.font.CompositeGlyphMapper;
import lexikalanlyzing.tokeny.SeparatorTokenEnum;
import lexikalanlyzing.tokeny.NumberToken;
import lexikalanlyzing.tokeny.KeyTokenEnum;
import lexikalanlyzing.tokeny.IdentifikatorToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lexikalanlyzing.tokeny.KeyToken;
import lexikalanlyzing.tokeny.Token;

/**
 *
 * @author st55440
 */
public class LexikalAnlyzing {

    private final String path;
    private StringBuilder input;
    private String str;
    private List<String> seznam = new ArrayList<>();

    public LexikalAnlyzing(String path) throws FileNotFoundException {//nacteni s souboru
        this.path = path;
        this.input = new StringBuilder();
        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNext()) {
                input.append(sc.nextLine());
            }
        }
        deliveringToken();
        System.out.println(seznam);
    }

    public void deliveringToken() {

        str = input.toString();
        boolean cont = true;
        String tok = "";
        NumberToken nt = new NumberToken(tok);
        for (int i = 0; i < str.length();) {

            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') { //indefikatory

                while (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                    tok += str.charAt(i);
                    i++;
                }
                KeyToken kT = new KeyToken(tok);
                if (kT.keyTokenSearch(tok)) {
                    seznam.add(kT.keyToken(tok));
                    tok = "";
                    continue;
                } else {
                    IdentifikatorToken iD = new IdentifikatorToken(tok);
                    seznam.add(iD.toString());
                    tok = "";
                    continue;
                }
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9' && str.charAt(i + 1) != 'x') { //cele cislo 

                while (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    tok += str.charAt(i);
                    i++;
                }
                NumberToken numTok = new NumberToken(tok);
                seznam.add(numTok.toString());
                tok = "";
                continue;
            }
            if (str.charAt(i) == '0' && str.charAt(i + 1) == 'x') {
                i += 2;
                while (str.charAt(i) >= '0' && str.charAt(i) <= '9'
                        || str.charAt(i) >= 'a' && str.charAt(i) <= 'f') {
                    tok += str.charAt(i);
                    i++;
                }
                seznam.add(nt.hexNumber(tok));
                tok = "";
                continue;
            }
            if (str.charAt(i) == (char) 59) {
                seznam.add("Indeficator [" + SeparatorTokenEnum.strednik.toString()
                        + "] je separator \n");
                i++;
                continue;
            }
            if (str.charAt(i) == (char) 44) {
                seznam.add("Indeficator [" + SeparatorTokenEnum.carka.toString()
                        + "] je separator \n");
                i++;
                continue;
            }
            if (str.charAt(i) == (char) 46) {
                seznam.add("Indeficator [" + SeparatorTokenEnum.dot.toString()
                        + "] je separator \n");
                i++;
                continue;
            }
            if (str.charAt(i) == (char) 61) {
                seznam.add("Indeficator [" + SeparatorTokenEnum.equals.toString()
                        + "] je separator \n");
                i++;
                continue;
            }
            if (str.charAt(i) == (char) 32) {
                if (cont) {
                    seznam.add("Indeficator [Bily znak ] je separator \n");
                    cont = false;
                    i++;
                    continue;
                } else {
                    i++;
                }
                continue;
            }
            if (str.charAt(i) == (char) 58) {
                seznam.add("Indeficator [" + SeparatorTokenEnum.dvojetecka.toString()
                        + "] je separator \n");
                i++;
                continue;
            }else {
                System.out.println("Neznamy znak "+str.charAt(i));
                i++;
                continue;
            }

        }

    }

}
