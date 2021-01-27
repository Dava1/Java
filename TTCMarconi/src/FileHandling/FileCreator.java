package FileHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileCreator {
    private File file;

    public File saveData(String data){
       file = new File("data.txt");
        try {
            FileWriter fw = new FileWriter(file);
            for(char e: data.toCharArray()){
                fw.write(e);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public File createFile() {
        String path;
        Scanner sc = new Scanner(System.in);
        System.out.print("Vitejte v nase programmy, zadejte prosim cesty do soubory: ");
        path = sc.nextLine();
        file = new File(path);
        int max = 100, min =0;
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(int j = 0; j < 100;j++ ) {
                int i = (int) (Math.random() * ((max - min) + 1) - min);
                fileWriter.append(i + " ");
            }
            fileWriter.close();
            System.out.println("Soubor je vytvoren");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
