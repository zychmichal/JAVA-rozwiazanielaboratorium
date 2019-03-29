import java.awt.*;

public class Root implements XmasShape {
    private int x;
    private int y;

    private double scale=1;
    private Color lineColor;
    private Color fillColor;
    Root(int x, int y,  int r, int g, int b){
        this.x=x;
        this.y=y;

        lineColor= new Color(r,g,b);
        fillColor= new Color(r,g,b);
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillRect( 0,0 ,120, 70 );
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
