import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("admin-units.csv",",",true);
        int i=0;
        while(reader.next() && i<1000){
            boolean y=reader.isMissing("name");
            //System.out.printf()
            int id = reader.getInt("id");
            String name = reader.get("name");
            int fare = reader.getInt("parent");
            System.out.printf("%d %s %d %d\n", id, name, fare, i);
            i++;


        }
    }
}
