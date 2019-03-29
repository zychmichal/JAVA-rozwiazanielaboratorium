import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {


    public static void main(String[] args) throws IOException {
        File file = new File("wynik.txt");
        PrintStream out = new PrintStream(file);
        BookList list=new BookList();
        list.read("ibuk_wykaz_pozycji.csv");
        //list.list(out);
        list.selectByName("Wydawnictwo Naukowe PWN", false).list(out);




    }
}
