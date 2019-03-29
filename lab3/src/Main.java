import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Document cv = new Document("Jana Kowalski - CV");
        cv.addPhoto("http://fotofestiwal.com/2017/wp-content/uploads/2017/07/Dominika-G%C4%99sicka-This-is-not-real-life-Grand-Prix-Fotofestiwal-2017-slider-1.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                 );

        PrintStream out = new PrintStream(new File("lab.html"));
        cv.writeHTML(out);
    }

}
