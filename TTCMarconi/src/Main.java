import FileHandling.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int i = 0;
        IDataHandling dataHandling;
        while(i == 0){
            System.out.println("ČISLA PREČTETE Z SOUBORY ČI VSTUPU");
            System.out.println("SOUBOR: 1 VSTUP: 2");
            Scanner sc = new Scanner(System.in);
            int j;
            try{
                j = sc.nextInt();
                if(j == 1){
                    dataHandling = new FileRead();
                }else dataHandling = new UserRead();
            }catch (InputMismatchException e) {
                dataHandling = new UserRead();
            }
            dataHandling.dataManage();
            System.out.println("CHCETE DAL? ANO : 1 NE: JINE ČISLO");
            j = sc.nextInt();
            if(j != 1){
                i=-1;
            }
        }

    }
}
