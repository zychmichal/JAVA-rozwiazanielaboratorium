import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Light implements XmasShape {
    private List<Bubble> lights =new ArrayList<Bubble>();
    private int x_start;
    private int x_end;
    private int y_start;
    private double scale=1;
    private Color lineColor;
    private Color fillColor;
    Light(int x1, int y1, int x2, int r, int g, int b){
        x_start=x1;
        y_start=y1;
        x_end=x2;
        lineColor= new Color(r,g,b);
        fillColor= new Color(r,g,b);
        for(int i=x_start; i<x_end;i=i+5){
            Bubble m= new Bubble(i,y_start,5,5,r,g,b);
            lights.add(m);
        }
    }
    @Override
    public void render(Graphics2D g2d) {

    }

    @Override
    public void transform(Graphics2D g2d) {


    }
    @Override
    public void draw(Graphics2D g2d){
        for(Bubble x: lights){
            x.draw(g2d);
        }
    }
}
