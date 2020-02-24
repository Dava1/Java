package casovani;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.KolekceException;
import kolekce.Fronta;
import kolekce.IFronta;

/**
 *
 * @author karel@simerda.cz
 */
public final class Casovac {

    /**
     * Trvani jedne periody casocace v milisekundach
     */
    public static final long PERIODA = 100;

    private static volatile Casovac instance = null;

    private volatile long time;

    private Timer timer;
    private IFronta<Posluchac> posluchaci;
    private volatile boolean running;

    public static Casovac instance() {
        if (instance == null) {
            synchronized (Casovac.class) {
                if (instance == null) {
                    instance = new Casovac();
                }
            }
        }
        return instance;
    }

    private class Task extends TimerTask {

        @Override
        public void run() {
            if (running) {
               synchronized (Task.class) {
                    time += PERIODA;
                    for (Posluchac p : posluchaci) {
                        p.aktualizuj();
                    }
               }
            }
        }
    }

    private Casovac() {
        
            time = 0;
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new Task(), 0, PERIODA);
            posluchaci = new Fronta<>();
            running = false;
        
    }

    public synchronized void pridej(Posluchac posluchac) {
        try {
            posluchaci.vloz(posluchac);
        } catch (KolekceException ex) {
            Logger.getLogger(Casovac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getTime() {
        return time;
    }

    public void zrus() {
        posluchaci.zrus();
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
