import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void Matrix() {
        double [][] d= new double [3][4];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                d[i][j] = i + j;
            }
        }
        Matrix m= new Matrix(d);
        assertEquals(3,m.rows);
        assertEquals(4,m.cols);
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(d[i][j],m.get(i,j),0.1);
            }
        }


    }

    @org.junit.Test
    public void Matrixint() {
        Matrix m= new Matrix(5,6);
        assertEquals(5,m.rows);
        assertEquals(6,m.cols);

    }
    @org.junit.Test
    public void asArray() {
        double [][] d= new double [3][4];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                d[i][j] = i + j;
            }
        }
        Matrix m= new Matrix(d);
        double [][]g= m.asArray();
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(d[i][j],g[i][j],0.1);
            }
        }

    }

    @org.junit.Test
    public void get() {
        double [][] d= new double [3][4];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                d[i][j] = i + j;
            }
        }
        Matrix m= new Matrix(d);
        assertEquals(3,m.rows);
        assertEquals(4,m.cols);
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(d[i][j],m.get(i,j),0.1);
            }
        }
    }

    @org.junit.Test
    public void set() {
        double [][] d= new double [3][4];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                d[i][j] = i + j;
            }
        }
        double [][] g= new double [3][4];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                g[i][j] = 0;
            }
        }
        Matrix m= new Matrix(g);
        assertEquals(3,m.rows);
        assertEquals(4,m.cols);
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 4; j++) {
                m.set(i,j,d[i][j]);
                assertEquals(d[i][j],m.get(i,j),0.1);
            }
        }

    }

    @org.junit.Test
    public void testtoString() {
    }

    @org.junit.Test
    public void reshape() {
        boolean thrown=false;
        Matrix m= new Matrix(3,4);
        m.reshape(2,6);
        assertEquals(2,m.rows);
        assertEquals(6,m.cols);
        try{
            m.reshape(3,6);
        }catch (RuntimeException e) {
            thrown = true;
        }

        assertTrue(thrown);

    }

    @org.junit.Test
    public void shape() {
    }

    @org.junit.Test
    public void add() {
    }

    @org.junit.Test
    public void sub() {
    }

    @org.junit.Test
    public void mul() {
    }

    @org.junit.Test
    public void div() {
    }

    @org.junit.Test
    public void add1() {
    }

    @org.junit.Test
    public void sub1() {
    }

    @org.junit.Test
    public void mul1() {
    }

    @org.junit.Test
    public void div1() {
    }

    @org.junit.Test
    public void frobenius() {
    }
}