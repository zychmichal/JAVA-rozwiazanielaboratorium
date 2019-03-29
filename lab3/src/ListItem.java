import java.io.PrintStream;

public class ListItem {
    private String tekst;
    public ListItem(String tekst)
    {
        this.tekst=tekst;
    }
    public void writeHTML(PrintStream out){
        out.printf("<li> %s </li>", tekst);

    }
}
