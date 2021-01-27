package FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead  implements IDataHandling{
 private File inputFile;

 public FileRead(){
 }

    private String[] read(){
        StringBuffer sb = new StringBuffer();
        try {
        Scanner scan = new Scanner(this.inputFile);
        while(scan.hasNextLine()){
            sb.append(scan.nextLine());
            }
        scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count(sb.toString());
    }
    public String[]  count(String str) {
      String[] numbers = str.split(" ");
     return numbers;
 }

    @Override
    public Object dataManage() {
        System.out.println("Direction you file");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
     this.inputFile = new File(path);
     String[] numbers = read();
        System.out.println(new Counter(numbers).countAction());
     return new FileCreator().saveData(new Counter(numbers).countAction());
    }
}
