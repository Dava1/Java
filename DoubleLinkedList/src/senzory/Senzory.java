 
package senzory;

import doubleList.KolekceException;
import java.util.Iterator;
import mereni.EnumPozice;

/**
 *
 * @author st55440
 * 
 */
public interface Senzory{
    /**
    * vlozi senzor do seznamy 
    */ 
   void vlozSenzor(Senzor data, EnumPozice pozice) throws NullPointerException, 
           KolekceException;
   /**
    * odebere senzor z seznamy
    */
   Senzor odeberSenzor(EnumPozice pozice) throws KolekceException;
   /**
    * ocisti seznam
    */
    void zrus();
    /**
     * nastavi aktualni element a zaroven vrati aktualni element
     */
    Senzor zpristupniAktualni(EnumPozice pozice) throws KolekceException;
    /**
     * @return iterator
     */
    Iterator iterator();
}
