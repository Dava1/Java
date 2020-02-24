package mereni;

import java.time.LocalDateTime;
import java.util.Random;
import senzory.Senzor;

/**
 *
 * @author st55440
 */
public class Generator {

    public static Mereni generatorMereni(TypSenzoru typ) {
        switch (typ) {
            case SENZOR_NA_ELEKTRIKA:
                return new MereniElektrika(LocalDateTime.MIN, generatorID(),
                        generatorSpotreba(), generatorSpotreba());
            case SENZOR_NA_VODU:
                return new MereniVoda(LocalDateTime.MIN, generatorID(),
                        generatorSpotreba());
            default:
                break;
        }
        return null;
    }

    public static Senzor generatorSenzor(TypSenzoru typ) {
        switch (typ) {
            case SENZOR_NA_ELEKTRIKA:
                return new Senzor(generatorID(), TypSenzoru.SENZOR_NA_ELEKTRIKA);
            case SENZOR_NA_VODU:
                return new Senzor(generatorID(), TypSenzoru.SENZOR_NA_VODU);
            default:
                break;
        }
        return null;
    }

    public static int generatorID() {
        return new Random().nextInt(10);
    }

    public static double generatorSpotreba() {
        return new Random().nextDouble();
    }
}
