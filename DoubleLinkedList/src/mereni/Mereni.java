 
package mereni;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author st55440
 */
public abstract class Mereni implements Serializable{
    private int idSenzory;
    private TypSenzoru typSenzoru;
    private LocalDateTime casMereni;

    public Mereni( LocalDateTime casMereni, int idSenzory) {
        this.casMereni = casMereni;
        this.idSenzory = idSenzory;
    }

    
    public void setIdSenzory(int idSenzory) {
        this.idSenzory = idSenzory;
    }

    public void setTypSenzoru(TypSenzoru typSenzoru) {
        this.typSenzoru = typSenzoru;
    }

    public void setCasMereni(LocalDateTime casMereni) {
        this.casMereni = casMereni;
    }

    public int getIdSenzory() {
        return idSenzory;
    }

    public TypSenzoru getTypSenzoru() {
        return typSenzoru;
    }

    public LocalDateTime getCasMereni() {
        return casMereni;
    }
    @Override
    public String toString(){
     return String.format("ID:%5d ", idSenzory);
    }
            
    
}
