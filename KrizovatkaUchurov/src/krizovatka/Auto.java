package krizovatka;


import java.util.Random;

/**
 *
 * @author karel@simerda.cz
 */
public class Auto implements Comparable<Auto> {

    static int pocetInstanci = 0;
    private final int cislo;
    private final Barva barva;
    private final Random rnd = new Random();

    @Override
    public int compareTo(Auto o) {
        return this.cislo - o.cislo;
    }

    private enum Color {
        WHITE, BLACK, RED, YELLOW, BLUE;
    }

    public Auto() {
        pocetInstanci++;
        cislo = pocetInstanci;
        int i = rnd.nextInt(Color.values().length);
        barva = Barva.values()[i];

    }

    public static int getPocetInstanci() {
        return pocetInstanci;
    }

    public int getCislo() {
        return cislo;
    }

    public Barva getBarva() {
        return barva;
    }

    @Override
    public String toString() {
        return "Auto{" + cislo + " " + barva.name() + '}';
    }

}
