import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class DrawPanel extends JPanel {

    private List<XmasShape> shapes = new ArrayList<XmasShape>();

    DrawPanel(){
        setBackground(new Color(0,0,50));
        setOpaque(true);
        //addBubble(10,10,203040033, 203040033);
        addRoot(440,550,  77,25,0);
        addBranch(100,550,0,300,600,0,-150,0, 0, 100,0);
        addBranch(150,450,0,250,500,0,-150,0, 0, 100,0);
        addBranch(200,350,0,200,400,0,-150,0, 0, 100,0);
        addBranch(250,250,0,150,300,0,-150,0, 0, 100,0);
        addLight(300,500,700,200,0,50);
        addLight(330,400,670,200,200,50);
        addLight(365,300,635,200,0,50);
        addLight(400,200,600,200,200,50);
        addBubble(455,140,30, 30,221,160,210);
        addBubble(520,160,30, 30,255,165,0);
        addBubble(400,207,30, 30,0,255,255);
        addBubble(550,209,30, 30,210,105,30);
        addBubble(470,225,30, 30,188,143,143);
        addBubble(410,265,30, 30,128,0,0);
        addBubble(520,263,30, 30,123,104,238);
        addBubble(450,308,30, 30,200,200,50);
        addBubble(600,310,30, 30,0,206,209);
        addBubble(340,315,30, 30,75,0,130);
        addBubble(530,333,30, 30,244,164,96);
        addBubble(420,350,30, 30,255,248,220);
        addBubble(480,365,30, 30,255,69,10);
        addBubble(660,410,30, 30,200,0,0);
        addBubble(303,415,30, 30,255,105,180);
        addBubble(550,420,30, 30,128,0,128);
        addBubble(430,425,30, 30,150,250,80);
        addBubble(480,450,30, 30,200,0,50);
        addBubble(380,460,30, 30,230,230,250);
        addBubble(580,460,30, 30,255,0,255);
        addBubble(680,510,30, 30,75,0,130);
        addBubble(410,510,30, 30,255,20,147);
        addBubble(540,510,30, 30,138,43,226);
        addBubble(280,510,30, 30,250,0,0);
        addBranch(380,110,0,20,40,0,-30,0, 200, 200,50);
        addBranch(380,90,0,20,40,0,30,0, 200, 200,50);

    }

    void addBubble(int x1,int y1,int x2, int y2, int r,int g, int b){
        Bubble m= new Bubble(x1,y1,x2,y2,r,g,b);
        shapes.add(m);
    }
    void addRoot(int x1,int y1,int r,int g, int b){
        Root m= new Root(x1,y1,r,g,b);
        shapes.add(m);
    }

    void addBranch(int x1,int y1,int x11, int x12, int x13, int y11, int y12, int y13, int r,int g, int b){
        Branch m= new Branch(x1,y1,x11,x12,x13,y11,y12,y13,r,g,b);
        shapes.add(m);
    }

    void addLight(int x1,int y1,int x11,int r,int g, int b){
        Light m= new Light(x1,y1,x11,r,g,b);
        shapes.add(m);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }

    }
}