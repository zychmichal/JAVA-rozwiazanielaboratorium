import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    private List<ListItem> elements= new ArrayList<>();



    public void addItem(String x){
        ListItem d = new ListItem(x);
        elements.add(d);
    }
    public void writeHTML(PrintStream out){
        out.print("<ul>");
        for(ListItem x: elements)
        {
            x.writeHTML(out);
        }
        out.print("</ul>");

    }
}
