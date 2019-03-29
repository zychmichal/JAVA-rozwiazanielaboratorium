import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }


    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter,true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",",true);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        reader= new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }


    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line  = reader.readLine();
        if(line==null){
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);

        // przetwarzaj dane w wierszu
        for(int i=0;i<header.length;i++){
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
        }
    }


    boolean next() throws IOException {
        String line  = reader.readLine();
        if(line==null){
            return false;
        }

        current = line.split(delimiter);
        System.out.print(current);
        if(current.length<columnLabels.size()){
            String [] currenttmp= new String[current.length];
            System.arraycopy(current, 0, currenttmp, 0, current.length);
            current= new String[columnLabels.size()+2];
            //System.out.print("jestem tutaj!");
            //System.out.print(columnLabels.size() + "\n");
            System.arraycopy(currenttmp, 0, current, 0, currenttmp.length);
            //System.out.print(current.length);
            for(int i=0; i<current.length; i++){
                if(current[i]==null){
                    current[i]="";
                }
            }
            //System.out.print(current.length);
        }
        return true;
    }


    public List<String> getColumnLabels(){                  //zwraca etykiety kolumn
        return columnLabels;
    }

    public int getRecordLength(){                          //zwraca długość bieżącego wczytanego rekordu
        return current.length;

    }

    public boolean isMissing(int columnIndex){              //czy wartość istnieje w bieżącym rekordzie
        if(columnIndex>current.length) return true;
        return current[columnIndex].equals("");
    }

    public boolean isMissing(String columnLabel){//analogiczny dostęp przez etykietę kolumny
        return current[columnLabelsToInt.get(columnLabel)].equals("");
    }

    public String get(int columnIndex){                    //zwraca wartość jako String, raczej pusty tekst, a nie null.
        return current[columnIndex];
    }

    public String get(String columnLabel){
        return current[columnLabelsToInt.get(columnLabel)];
    }

    public int getInt(int columnIndex){
        return Integer.parseInt(current[columnIndex]);
    }

    public int getInt(String columnLabel){                 //funkcja konwertuje wartość do int. Użyj Integer.parseInt(). Funkcja wygeneruje wyjątek, jeśli pole było puste.
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    public long getLong(int columnIndex){
        return Long.parseLong(current[columnIndex]);
    }

    public long getLong(String columnLabel){
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    public double getDouble(int columnIndex){
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel){
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }


}

