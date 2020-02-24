import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        PDDocument document = PDDocument.load( new File("/home/davinci/Downloads/premetOS.pdf"));
        document.getClass();
        if (!document.isEncrypted()){
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            PDFTextStripper tStripper = new PDFTextStripper();
            String pdfFileInText = tStripper.getText(document);
            System.out.println(pdfFileInText);
        }
    }
}
