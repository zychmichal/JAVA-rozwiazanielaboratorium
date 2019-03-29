public class Variable extends Node {
    private String name;
    private Double value;
    public Variable(String name){
        this.name = name;
    }
    public void setValue(double d){
        value = d;
    }


    @Override
    public double evaluate() {
        return sign*value;
    }


    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }

}