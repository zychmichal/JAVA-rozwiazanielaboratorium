import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private String title;
    private Photo photo;
    private List<Section> sections = new ArrayList<>();

    public Document(String x){
       this.title=x;
       this.photo=null;
    }

    public void addPhoto(String url){
        photo = new Photo(url);
    }

    public Document setTitle(String title){
        this.title = title;
        return this;
    }

    public Document setPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }


    public Section addSection(String sectionTitle){
        Section s = new Section();
        sections.add(s.setTitle(sectionTitle));
        return s;
    }
    public Document addSection(Section s){
        sections.add(s);
        return this;
    }


    public void writeHTML(PrintStream out){
        out.print("<body> \n");
        out.printf("<h1> %s </h1> \n", title);
        photo.writeHTML(out);
        for(Section x: sections)
        {
            x.writeHTML(out);
        }
        out.print("</body> \n");
    }


}
