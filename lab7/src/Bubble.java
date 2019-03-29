import java.awt.*;

public class Bubble implements XmasShape {
    private int x;
    private int y;
    private int x1;
    private int y1;
    private double scale=1;
    private Color lineColor;
    private Color fillColor;
    Bubble(int x1, int y1, int x2, int y2, int r, int g, int b){
        x=x1;
        y=y1;
        this.x1=x2;
        this.y1=y2;
        lineColor= new Color(r,g,b);
        fillColor= new Color(r,g,b);
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(0,0,x1,y1);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0,x1,y1);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}