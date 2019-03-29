import java.io.PrintStream;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty;


    BoundingBox(){
        xmin=0;
        xmax=0;
        ymin=0;
        ymax=0;
        empty=true;
    }

    BoundingBox(double x1, double x2, double y1, double y2){
        xmin=x1;
        xmax=x2;
        ymin=y1;
        ymax=y2;
        empty=false;

    }

    String toString(PrintStream out){
        return Double.toString(xmin) + "|" + Double.toString(xmax)+ "|" + Double.toString(ymin) + "|" + Double.toString(ymax);
    }

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     *
     */

    void addPoint(double x, double y){
        if(x<xmin) xmin=x;
        if(x>xmax) xmax=x;
        if(y<ymin) ymin=y;
        if(y>ymax) ymax=y;
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return xmax>=x && xmin<=x && ymax>=y && ymin<=y;

    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return xmax>=bb.xmax && xmin<=bb.xmin && ymax>=bb.ymax && ymin<=bb.ymin;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        return bb.xmax>xmin && xmax>bb.xmin && bb.ymax>ymin && ymax>bb.ymin;
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if(this.contains(bb)){
            return this;
        }
        else{
            ymin=Math.min(ymin,bb.ymin);
            xmin=Math.min(xmin,bb.xmin);
            ymax=Math.max(ymax,bb.ymax);
            xmax=Math.max(xmax,bb.xmax);
            return this;
        }


    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return empty;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        if(empty){
            throw new RuntimeException("Not implemented");
        }
        else{
            return (xmin+xmax)/2;
        }
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        if(empty){
            throw new RuntimeException("Not implemented");
        }
        else{
            return (ymin+ymax)/2;
        }
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx){
        double x=Math.abs(getCenterX()-bbx.getCenterX());
        double y=Math.abs(getCenterY()-bbx.getCenterY());
        return Math.sqrt(x*x+y*y);
    }



}