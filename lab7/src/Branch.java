import java.awt.*;

public class Branch implements XmasShape{
    private int x;
    private int y;
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;
    private Color fillColor;
    private double scale=1;


    Branch(int x1, int y1, int x11, int x22,int x33,int y11,int y22,int y33, int r, int g, int b){
        x=x1;
        y=y1;
        x1=x11;
        x2=x22;
        x3=x33;
        y1=y11;
        y2=y22;
        y3=y33;

        fillColor= new Color(r,g,b);
    }



    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        int xs[]={x1,x2,x3};
        int ys[]={y1,y2,y3};
        g2d.fillPolygon(xs,ys,xs.length-1);
        g2d.translate(100,0);
        g2d.fillPolygon(xs,ys,xs.length);
        g2d.translate(-100,0);
        /*int tabX [ ] = { 40, 60, 80};
        int tabY [ ] = { 40, 20, 40};
        int n = tabX.length;
        g2d.fillPolygon( tabX, tabY, n );*/
        //g2d.translate(100,0);
        //g2d.drawPolyline(tabX, tabY, n);
        //g2d.translate(-100,0);

        //g2d.fillOval(100,100,100,100);
        //g2d.setColor(lineColor);
        //g2d.drawOval(100,100,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
