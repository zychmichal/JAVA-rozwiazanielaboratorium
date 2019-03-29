import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    private String title;
    private List<Paragraph> paragraps = new ArrayList<>() ;
    public Section()
    {
        title="";
    }

    public Section(String title)
    {
        this.title="title";
    }



    public Section setTitle(String title){
        this.title=title;
        return this;
    }
    public Section addParagraph(String paragraphText){
        Paragraph x = new Paragraph();
        x.setContent(paragraphText);
        paragraps.add(x);
        return this;
    }


    public Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    public void writeHTML(PrintStream out){
        out.printf("<section> <h1> %s </h1>", title);
        for(Paragraph x: paragraps)
        {
            x.writeHTML(out);
        }
        out.print("</section>");
    }

}
