package senzory;

import java.io.Serializable;
import java.time.LocalDateTime;
import mereni.Generator;
import mereni.Mereni;
import mereni.MereniElektrika;
import mereni.MereniVoda;
import mereni.TypSenzoru;

/**
 *
 * @author st55440
 */
public class Senzor implements Serializable {

    private int idSenzor;
    private TypSenzoru typSenzoru;

    public Mereni getMereni() {
        switch (typSenzoru) {
            case SENZOR_NA_ELEKTRIKA:
                return new MereniElektrika(LocalDateTime.MIN, idSenzor, 
                  Generator.generatorSpotreba(), Generator.generatorSpotreba());
            case SENZOR_NA_VODU:
                return new MereniVoda(LocalDateTime.MIN, idSenzor,
                                                 Generator.generatorSpotreba());
        }
        return null;
    }

    public Senzor(int idSenzor, TypSenzoru typSenzoru) {
        this.idSenzor = idSenzor;
        this.typSenzoru = typSenzoru;

    }

    public Senzor(TypSenzoru typSenzoru) {
        this.typSenzoru = typSenzoru;
        this.idSenzor = Generator.generatorID();
    }

    public int getIdSenzor() {
        return idSenzor;
    }

    public void setIdSenzor(int idSenzor) {
        this.idSenzor = idSenzor;
    }

    public TypSenzoru getTypSenzoru() {
        return typSenzoru;
    }

    public void setTypSenzoru(TypSenzoru typSenzoru) {
        this.typSenzoru = typSenzoru;
    }

    @Override
    public String toString() {
        return String.format("ID:%5d  Typ:%s", idSenzor, typSenzoru);
    }

}
