import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class BookList {
    List<Book> units = new ArrayList<>();
    List<Integer> years = new ArrayList<>();
    Map<String, List<Book>> kategory = new HashMap<>();
    BookList(){}
    BookList(BookList x){
        this.units=x.units;

    }

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);
        int i = 0;
        while(reader.next() && i<450){
        //while (reader.next()) {
            Book m = new Book();
            Long parent;

            double x1, x2, x3, x4, y1, y2, y3, y4;


            if (reader.isMissing(0)) {
                m.id = 0;
            } else {
                m.id = reader.getInt(0);
            }



            if (reader.isMissing(1)) {
                m.title = "";
            } else {
                m.title = reader.get(1);
            }


            if (reader.isMissing(2)) {
                m.author = "";
            } else {
                m.author = reader.get(2);
            }


            if (reader.isMissing(3)) {
                m.isbn = "";
            } else {
                m.isbn = reader.get(3);
            }


            if (reader.isMissing(4)) {
                m.wydawnictwo = "";
            } else {
                m.wydawnictwo = reader.get(4);
            }

            if (reader.isMissing(5)) {
                m.year = 0;
            } else {
                m.year = reader.getInt(5);
            }


            if (reader.isMissing(6)) {
                m.kateg = "";
            } else {
                m.kateg = reader.get(6);
            }

            if (reader.isMissing(7)) {
                m.podkat = "";
            } else {
                m.podkat = reader.get(7);
            }


            if (reader.isMissing(8)) {
                m.url = "";
            } else {
                m.url = reader.get(8);
            }

            if(!kategory.containsKey(m.kateg)){
                List<Book> d = new ArrayList<>();
                d.add(m);
                kategory.put(m.kateg,d);
            }else {
                kategory.get(m.kateg).add(m);
            }



        }



    }

    void list(PrintStream out){
        for(Book x: units){
            out.println(x.toString(out));
        }
    }


    BookList selectByName(String pattern, boolean regex){
        BookList ret = new BookList();
        for(Book x: units){
            if(regex){
                if(x.wydawnictwo.matches(pattern)) ret.units.add(x);
            }
            else{
                if(x.wydawnictwo.contains(pattern)) ret.units.add(x);
            }
        }
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        return ret;
    }

    Map<String,Integer> CounKat(BookList m){
        Map<String, Integer> d = new HashMap<>();
        Set<String> mm= new HashSet<>();
        mm=m.kategory.keySet();
        for(String item: mm){
            d.put(item,m.kategory.get(item).size());
        }
        return d;


    }

    /*void countYear(BookList x){
        for(Integer m: x.years){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }*/
}
