import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    private String content;
    private List<UnorderedList> lista = new ArrayList<>();

    public ParagraphWithList() {
        super();
    }

    public ParagraphWithList setContent(String content){

        this.content=content;
        return this;
    }
    public ParagraphWithList addListItem(String x){
        UnorderedList d = new UnorderedList();
        d.addItem(x);
        lista.add(d);
        return this;
    }

    @Override
    public void writeHTML(PrintStream out) {
        out.print("<p>");
        for(UnorderedList x: lista)
        {
            x.writeHTML(out);
        }
        out.print("</p>");
    }
}
