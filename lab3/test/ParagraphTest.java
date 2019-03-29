import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParagraphTest  {
    @Test
    public void writeHTML() throws Exception{
        String para = "lalala";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        Paragraph m = new Paragraph();
        m.setContent(para);
        m.writeHTML(ps);
        String result = null;
        try {
        result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        }

        //System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<p"));
        assertTrue(result.contains("</"));
        assertTrue(result.contains(para));
        }
}