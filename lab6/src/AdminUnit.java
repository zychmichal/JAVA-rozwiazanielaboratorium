import java.io.PrintStream;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children;
    BoundingBox bbox;

    public String toString(PrintStream out){
        return name + "|"  + Double.toString(population)+ "|" + Double.toString(area)+ "|" + Double.toString(density) + "|" + bbox.toString(out);
    }
}
