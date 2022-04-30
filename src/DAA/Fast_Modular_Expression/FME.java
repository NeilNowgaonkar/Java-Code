package DAA.Fast_Modular_Expression;
import java.util.Scanner;

public class FME
{
    public static int xpown(long x, long n, long p) {
        long res = 1;
        while (n > 0) {
            if (n % 2 != 0) {
                res = (res * x) % p;
                n--;
            } else {
                x = (x * x) % p;
                n = n / 2;
            }
        }
        return (int)res;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("To calculate x power n mod p :-");

        System.out.print("Enter value of x = ");
        int x = sc.nextInt();
        System.out.print("Enter value of n = ");
        int n = sc.nextInt();
        System.out.print("Enter value of p = ");
        long p=sc.nextLong();

        int ans = xpown(x,n,p);
        System.out.println("Answer = "+ans);
    }
}