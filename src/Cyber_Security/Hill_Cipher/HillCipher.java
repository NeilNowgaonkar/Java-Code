package Cyber_Security.Hill_Cipher;

import java.util.Scanner;

public class HillCipher
{
    static void encrypt()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text to be encrypted = ");
        String text=sc.nextLine();
        text=text.toUpperCase();

        //If text length not even adding Q to make it even
        if(text.length()%2==1)
        {
            text=text+"Q";
        }

        //Generate 2X2 key matrix
        System.out.print("Enter key = ");
        String key=sc.nextLine();
        double sq=Math.sqrt(key.length());

        if(sq!=(long)sq)
        {
            System.out.println("Cannot Form a square matrix");
        }

        int len=(int)sq;

        int key_matrix[][]=new int[len][len];
        generate_key(key,key_matrix);

        //Check if the matrix is valid i.e if det!=0
        isValidMatrix(key_matrix);

        //Convert text to matrix
        int text_matrix[][]=new int [text.length()][1];
        generate_msg_matrix(text,text_matrix);

        int cipher_matrix[][]=new int [key_matrix.length][1];
        encrypt_text(text_matrix,key_matrix,cipher_matrix);

        String cipher_text="";
        for(int i=0;i<key_matrix.length;i++)
        {
            cipher_text=cipher_text+(char)(cipher_matrix[i][0]+65);
            //System.out.println("Checkpoint = "+cipher_matrix[i][0]+65);
        }

        System.out.println("Cipher Text = "+cipher_text);

    }

    static void generate_key(String key,int key_matrix[][])
    {
        int x=0;
        for(int i=0;i<key_matrix.length;i++)
        {
            for(int j=0;j<key_matrix[0].length;j++)
            {
                //Mod 65 because ASCII of A = 65
                key_matrix[i][j]=(key.charAt(x))%65;
                x++;
                //System.out.println("Checkpoint 1");
            }
        }
    }

    static void isValidMatrix(int key_matrix[][])
    {
        int det=key_matrix[0][0]*key_matrix[1][1] - key_matrix[0][1]*key_matrix[1][0];
        if(det==0)
        {
            System.out.println("Determinant is equal to zero, invalid key matrix!!");
        }
    }

    static void generate_msg_matrix(String text,int text_matrix[][])
    {
        int x=0;
        for(int i=0;i<text_matrix.length;i++)
        {
            //Mod 65 because ASCII of A = 65
            text_matrix[i][0]=(text.charAt(x))%65;
            x++;
        }
    }

    static void encrypt_text(int msg_matrix[][],int key_matrix[][],int cipher_matrix[][])
    {
        for(int i=0;i<msg_matrix.length;i++)
        {
            System.out.println("Checkpoint reached 1");

            for(int j=0;j<1;j++)
            {
                System.out.println("Checkpoint reached - 2");

                cipher_matrix[i][j]=0;

                for(int x=0;x<key_matrix.length;x++)
                {
                    System.out.println("Checkpoint reached - 3");

                    cipher_matrix[i][j]=cipher_matrix[i][j]+(key_matrix[i][x]*msg_matrix[x][j]);
                }

                cipher_matrix[i][j]=cipher_matrix[i][j]%26;
            }
        }
    }

    static void decrypt(String text)
    {

    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int choice=0;

        while(choice!=3)
        {
            System.out.println("Hill Cipher Program for (2X2)");
            System.out.println("==================================");
            System.out.println("1.) Encrypt Text");
            System.out.println("2.) Decrypt Text");
            System.out.println("3.) Exit");
            System.out.print("\n\nEnter your choice = ");
            choice = sc.nextInt();

            String text = "";

            switch (choice) {
                case 1:
                    encrypt();
                    break;
                case 2:
                    System.out.print("Enter text to be decrypted = ");
                    text = sc.nextLine();
                    decrypt(text);
                    break;
                case 3:
                    System.out.print("Program Exited");
                    System.exit(0);
                default:
                    System.out.println("Incorrect Choice");
            }
        }
    }
}
