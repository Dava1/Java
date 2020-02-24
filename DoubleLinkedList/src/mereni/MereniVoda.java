
package mereni;

import java.time.LocalDateTime;

/**
 *
 * @author st55440
 */
public class MereniVoda extends Mereni{
    private TypSenzoru senzoru;
    private double spotrebaM3;

    public MereniVoda(LocalDateTime casMereni, int idSenzory,double spotrebaM3){
        super(casMereni, idSenzory);
        this.senzoru = senzoru.SENZOR_NA_VODU;
        this.spotrebaM3 = spotrebaM3;
    }

     

    public double getSpotrebaM3() {
        return spotrebaM3;
    }

    public void setSpotrebaM3(double spotrebaM3) {
        this.spotrebaM3 = spotrebaM3;
    }
    
    @Override
    public String toString() {
        return String.format("%s Typ: %s M3:%4.2f", super.toString(),TypSenzoru.SENZOR_NA_VODU,spotrebaM3);
    }
    
}
