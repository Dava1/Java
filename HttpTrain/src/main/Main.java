package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("search.maven.org",80);
        InputStream response = socket.getInputStream();
        OutputStream request = socket.getOutputStream();
        byte[] data =("GET /watch?v=LOmcsf5IylI&list=PLU2ftbIeotGqSTOVNjT4L3Yfy8jatCdhm HTTP/1.1\n"
                +"Host: www.youtube.com\n\n").getBytes();
        request.write(data);
        int c;
        while((c=response.read())!=-1){
            System.out.print((char)c);
        }
        socket.close();
    }
}
