/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mereni;

import java.time.LocalDateTime;

/**
 *
 * @author st55440
 */
public class MereniElektrika extends Mereni{
    private TypSenzoru senzoru;  
    private double spotrebaVT;
    private double spotrebaNT;

    public MereniElektrika(LocalDateTime casMereni, int idSenzory,
            double spotrebaNT, double spotrebaVT) {
        super(casMereni, idSenzory);
        this.senzoru = senzoru.SENZOR_NA_ELEKTRIKA;
        this.spotrebaNT=spotrebaNT;
        this.spotrebaVT=spotrebaVT;
    }



    public double getSpotrebaVT() {
        return spotrebaVT;
    }

    public void setSpotrebaVT(double spotrebaVT) {
        this.spotrebaVT = spotrebaVT;
    }

    public double getSpotrebaNT() {
        return spotrebaNT;
    }

    public void setSpotrebaNT(double spotrebaNT) {
        this.spotrebaNT = spotrebaNT;
    }
    @Override
    public String toString() {
        return String.format("%s Typ: %s VT:%4.2f NT:%4.2f", super.toString(),TypSenzoru.SENZOR_NA_ELEKTRIKA,spotrebaVT,spotrebaNT);
    }
    
}
