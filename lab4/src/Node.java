abstract public class Node {
    protected int sign=1;
    public Node minus(){
        sign = -1;
        return this;
    }
    public Node plus(){
        sign = 1;
        return this;
    }
    public int getSign(){return sign;}

    /**
     * Oblicza wartość wyrażenia dla danych wartości zmiennych
     * występujących w wyrażeniu
     */
    public abstract double evaluate();

    /**
     *
     * zwraca tekstową reprezentację wyrażenia
     */
    public String toString(){return "";}

    /**
     *
     * Zwraca liczbę argumentów węzła
     */
    public int getArgumentsCount(){return 0;}




}
