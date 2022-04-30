package ECC;

import java.util.Scanner;

public class Trying
{
    //Power function to return value a^b mod P
    public static int generate_key(int a, int b, int P)
    {
        if(b==1)
        {
            return a;
        }
        else
        {
            return ((int)Math.pow(a,b)%P);
        }
    }

    public static void main(String args[])
    {
        /*Scanner sc=new Scanner(System.in);
        int P,G; //Public keys
        int a,b; //Private Keys
        int x;   // generate key for P
        int y;   //generated key for G
        int ka;  //Secret key for a
        int kb;  //Secret key for b

        System.out.print("Enter two public keys (prime numbers) for :- ");
        System.out.print("\nPerson 1 = ");
        P=sc.nextInt();
        System.out.print("Person 2 = ");
        G=sc.nextInt();

        System.out.print("\nEnter two private keys for:-");
        System.out.print("\nPerson 1 = ");
        a=sc.nextInt();
        System.out.print("Person 2 = ");
        b=sc.nextInt();

        System.out.println("===================================================");

        x=generate_key(G,a,P);
        y=generate_key(G,b,P);

        //Generating the secret keys
        ka=generate_key(y,a,P);
        kb=generate_key(x,b,P);

        System.out.println("Secret key for Person 1 = "+ka);
        System.out.println("Secret key for Person 2 = "+kb);
*/
    }
}
