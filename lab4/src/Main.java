import java.util.Locale;

public class Main {
    public static void main(String[] args){
        buildAndPrint();
        defineCircle();
        //buildAndEvaluate();
    }

    static void findAndPrint(Node node, Variable x, Variable y){

        int counter=0;
        zagniezdzonepetle:
        for(double i=-10; i<10; i+=0.1){
            for(double j=-10; j<10; j+=0.1){
                x.setValue(i);
                y.setValue(j);
                if(node.evaluate()<0){
                    counter++;
                    System.out.println(String.format("Punkt (%f,%f) leży wewnatrz koła %s",i,j,node.toString()));
                }
                if(counter==100){
                    System.out.println(counter);
                    break zagniezdzonepetle;
                }
            }
        }
    }

    static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        //System.out.println(circle.toString());
        findAndPrint(circle,x,y);
        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }

    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Prod (2.1,new Power(x,3)))
                .add(new Power(x,2))
                .add(new Prod(-2,x))
                .add(7);
        System.out.println(exp.toString());

    }


    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        System.out.println(exp.toString());
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

}
