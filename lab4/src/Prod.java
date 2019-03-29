import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    private List<Node> args = new ArrayList<>();

    public Prod(){}

    public Prod(Node n1){
        args.add(n1);
    }
    public Prod(double c){
        this(new Constant(c));
    }

    public Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    public Prod(double c, Node n){
        this(new Constant(c), n);
    }



    public Prod mul(Node n){
        args.add(n);
        return this;
    }

    public Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    public double evaluate() {
        double result =1;
        for(Node x: args){
            result*=x.evaluate();
        }
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        return result;
    }
    public int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();

        for(Node x: args){
            if(x.sign<0) b.append("(");
            b.append(x.toString());
            if(x.sign<0) b.append(")");
            b.append("*");
        }
        b.deleteCharAt(b.length()-1);
        return b.toString();
    }


}