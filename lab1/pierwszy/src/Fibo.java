import java.util.Scanner;


public class Fibo {
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if( n<1 || n>45){
            System.exit(1);
        }
        int[] tab = new int[n];
        if(n==1){
            System.out.printf("1");
        }
        else {
            tab[0] = 1;
            tab[1] = 1;
            for (int i = 2; i < n; i++) {
                tab[i] = tab[i - 1] + tab[i - 2];
            }
            for (int i = 0; i < n; i++) {
                System.out.printf(" %d \n", tab[i]);
            }
        }
    }
}
