



public class Matrix {

    double[]data;
    int rows;
    int cols;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d){
        int m=0;
        this.rows=d.length;
        for(int i=0; i<d.length;  i++){
            if(m<d[i].length){
                m=d[i].length;
            }
        }
        this.cols=m;
        data = new double[rows*cols];
        for(int i=0; i<d.length;  i++){
            for(int j=0; j<d[i].length; j++){
                data[i*cols+j]=d[i][j];
            }
            /*for(int g=0; g<cols-d[i].length; g++){
                data[i*cols+d[i].length+g+1]=0;
            }*/
        }
    }

    double[][] asArray(){
        double[][] array2D = new double[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                array2D[i][j]=data[i*cols+j];
            }
        }
        return array2D;
    }

    double get(int r, int c){
        return data[cols*r+c];
    }

    void set(int r, int c,double value){
        data[cols*r+c]=value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0; j<cols; j++){
                buf.append(get(i,j));
                buf.append(" ");
            }
            buf.append("]\n");

        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
        rows=newRows;
        cols=newCols;
    }
    int[] shape(){
        int[] ksztalt=new int[2];
        ksztalt[0]=rows;
        ksztalt[1]=cols;
        return ksztalt;
    }
    Matrix add(Matrix m){
        if(rows!=m.rows ||  cols!= m.cols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be added", m.rows, m.cols));
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)+m.get(i,j));
            }
        }
        return this;
    }


    Matrix sub(Matrix m){
        if(rows!=m.rows ||  cols!= m.cols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be added", m.rows, m.cols));
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)-m.get(i,j));
            }
        }
        return this;
    }

    Matrix mul(Matrix m){
        if(rows!=m.rows ||  cols!= m.cols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be added", m.rows, m.cols));
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)*m.get(i,j));
            }
        }
        return this;
    }

    Matrix div(Matrix m){
        if(rows!=m.rows ||  cols!= m.cols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be added", m.rows, m.cols));
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)/m.get(i,j));
            }
        }
        return this;
    }

    Matrix add(double w){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)+w);
            }
        }
        return this;
    }

    Matrix sub(double w){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)-w);
            }
        }
        return this;
    }

    Matrix mul(double w){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)*w);
            }
        }
        return this;
    }

    Matrix div(double w){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                set(i,j,get(i,j)/w);
            }
        }
        return this;
    }

    double frobenius(){
        double suma=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                suma=suma+data[i*cols+j];
            }
        }
        return suma;
    }

    // METODA - KARTKOWKA
    Matrix getTransposition(){
        Matrix m= new Matrix(cols,rows);

        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; i++)
            m.data[j*rows+i]=data[i*cols+j];
        }
        return m;


    }

}
