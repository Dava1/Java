package FileHandling;

import java.util.Scanner;

public class UserRead implements IDataHandling {
    private String data;

    public String[] getData(){
       Scanner sc = new Scanner(System.in);
       System.out.print("how many numbers your need?: ");
       int length = sc.nextInt();
       String[] tmp = new String[length];
       for(int i = 0; i< tmp.length; i++){
           System.out.print("next:");
           tmp[i] = sc.nextLine();
            if(tmp[i].isEmpty())tmp[i] = sc.nextLine();
       }
        return tmp;
    }
    @Override
    public Object dataManage() {
      String [] data = getData();
      FileCreator fileCreator = new FileCreator();
      ICounter counter = new Counter(data);
      System.out.println(counter.countAction());
       return fileCreator.saveData(counter.countAction());
    }
}
