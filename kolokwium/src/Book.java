import java.io.PrintStream;
import java.util.List;

public class Book {
    int id;
    String title;
    String author;
    String isbn;
    String wydawnictwo;
    int year;
    String kateg;
    String podkat;
    String url;



    public String toString(PrintStream out){
        return Integer.toString(id) + "|"  + title+ "|" + author+ "|" + isbn + "|" + wydawnictwo;
    }
}
