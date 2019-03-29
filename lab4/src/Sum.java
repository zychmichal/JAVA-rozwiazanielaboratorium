import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    private List<Node> args = new ArrayList<>();

    public Sum(){}

    public Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    public Sum add(Node n){
        args.add(n);
        return this;
    }

    public Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    public Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    public double evaluate() {
        double result =0;
        for(Node x: args){
            result+=x.evaluate();
        }
        return result;
    }

    public int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        int count=0;
        for(Node x: args){
            count++;
            if(x.sign>0 && count>1)b.append("+");
            b.append(x.toString());

        }
        return b.toString();
    }


}