package mereni;

import doubleList.DoubleList;
import doubleList.KolekceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *Tento
modul umožnuje správu měření z jednotlivých měření od senzorů a implementuje
následující rozhraní:
 * @author st55440
 */
public interface SpravceMereni<E> {

    /**
     *     vloží nové
 měření do seznamu na příslušnou pozici (první, poslední, předchůdce, následník
     * @param merni
     * @param pozice 
     */

    void vlozMereni(Mereni merni, EnumPozice pozice)throws KolekceException, 
            NoSuchElementException;

    /**
     * odebere z požadované pozice 
    (první, poslední, předchůdce, následník, aktuální) měření
     * @param pozice
     * @return 
     */
     
    public Mereni zpristupniMereni(EnumPozice pozice) throws KolekceException, 
            NoSuchElementException;

    /**
     *  odebere z požadované pozice 
    (první, poslední, předchůdce, následník, aktuální) měření
     * @param pozice
     * @return 
     * @throws doubleList.KolekceException 
     */
   
    public Mereni odeberMereni(EnumPozice pozice) throws KolekceException, 
            NoSuchElementException;

   /**
    * vrátí iterátor
    * @return 
    */
    public Iterator iterator();

    /**
     * pomocí iterátoru vyhledá všechna měření daného senzoru v rámci
     * požadovaného dne. (seznam je následně možné vypsat)
     *
     * @return
     */
    public DoubleList MereniDen(LocalDate datum);

    /**
     * pomocí iterátoru vyhledá maximální spotřebu daného senzoru v rámci
     * požadovaného intervalu. Pro elektrický senzor je hodnota dána součtem VT
     * a NT (hodnotu je následně možné vypsat)
     *
     * @return
     */
    public double MaxSpotreba(LocalDateTime datumOd, 
            LocalDateTime datumDo);

    /**
     * zruší všechny měření.
     */
    public void zrus();
}
