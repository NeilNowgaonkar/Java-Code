package DAA.Josephus_Problem;

import java.util.Scanner;

public class Josephus
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter the number of people = ");
        int n=sc.nextInt();

        System.out.print("Enter the k value = ");
        int k=sc.nextInt();

        //Special Case - When k=2
        if(k==2)
        {
            System.out.print("Safe Position = ");
            long startTime = System.nanoTime();
            System.out.println(special(n));
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Special Case - Execution time in nanoseconds: " + timeElapsed);
        }

        System.out.println();

        System.out.print("Safe Position = ");
            long startTime = System.nanoTime();
            System.out.println(josephus(n,k));
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("General Case - Execution time in nanoseconds: " + timeElapsed );


       // System.out.println(josephus(n,k));
    }

    public static int josephus(int n,int k)
    {
        if(n==1)
            return 1;

        int x=josephus(n-1,k);
        int y=(x+k-1)%n+1;
        return y;
    }

    public static int special(int n)
    {
        // Find value of 2 ^ (1 + floor(Log n))
        // which is a power of 2 whose value
        // is just above n.
        int p = 1;
        while (p <= n)
            p *= 2;

        // Return 2n - 2^(1+floor(Logn)) + 1
        return (2 * n) - p + 1;


    }
}
