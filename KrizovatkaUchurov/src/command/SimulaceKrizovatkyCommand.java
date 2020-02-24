package command;

import casovani.Casovac;
import kolekce.KolekceException;
import krizovatka.IKrizovatka;
import krizovatka.KrizovatkaSimulator;
    

public class SimulaceKrizovatkyCommand  {

    private static int i = 0;

    public static void main(String[] args)
        
        throws KolekceException, InterruptedException {
         
        IKrizovatka krizovatka = new KrizovatkaSimulator();
        
        Casovac.instance().start();

        krizovatka.setHlaseniSemaforu(
                s->System.err.printf("T= %04d: Zmena smeru na %s\n",i,s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> System.out.printf("T= %04d: Prijezd ze severu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> System.out.printf("T= %04d: Odjezd ze severu %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> System.out.printf("T= %04d: Prijezd ze smeru jihu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> System.out.printf("T= %04d: Odejzd ze smeru jihu %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s ->System.out.printf("T= %04d: Prijezd ze smeru vychod %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s ->System.out.printf("T= %04d: Odjezd ze smeru vychod %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s ->System.out.printf("T= %04d: Prijezd ze smeru zapad %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s ->System.out.printf("T= %04d: Odjezd ze smeru zapad %s\n", i, s));
        while (true) {
            Thread.sleep(1000);
            i++;
            
        }
    }
}
