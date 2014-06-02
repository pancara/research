import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ExtractPageContent {

    public static final File source = new File(System.getProperty("user.dir"), "sample/ticket.pdf");
    public static final File result = new File(System.getProperty("user.dir"), "result/ticket.txt");

    public void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new ExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        out.flush();
        out.close();
    }

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new ExtractPageContent().parsePdf(source.getAbsolutePath(), result.getAbsolutePath());
    }


}

