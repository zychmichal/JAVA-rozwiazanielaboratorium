import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;



public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("wynik.txt");
        PrintStream out = new PrintStream(file);
        AdminUnitList list=new AdminUnitList();
        list.read("admin-units.csv");
        //list.list(System.out);
        for(AdminUnit x : list.units)
        {
            list.fixMissingValues(x);
        }
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(1000);
        query.execute().list(out);


    }
}
