import java.io.PrintStream;

public class Paragraph {
    private String content;
    public Paragraph setContent(String content){

        this.content=content;
        return this;
    }
    public void writeHTML(PrintStream out){
        out.printf("<p> %s </p>",content);
    }                                                //powinna umieszczać treść pomiędzy znacznikam <p>…</p>

    public Paragraph(){
        content="";
    }
    public Paragraph(String content){
        this.content="content";
    }

}
