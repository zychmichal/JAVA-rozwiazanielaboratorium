import java.util.Scanner;

public class Problem610A {
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x;
        int licznik=0;
        if(n % 2 != 0){
            System.out.print(0);
            System.exit(0);
        }
        System.out.print(n/2-n/4-1);
    }
}
