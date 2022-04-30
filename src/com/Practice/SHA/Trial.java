package com.Practice.SHA;

import java.util.Scanner;

public class Trial
{
    public static void main(String args[])
    {
        System.out.println("Insert a word or a phrase to be hashed");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println("Plain Text: " + word);

        //Converting the word to binary
        String binary = convertToBinary(word);


    }

    public static String convertToBinary(String word)
    {
        /*int l=word.length();
        StringBuilder binary=new StringBuilder();
        int k=1;
        for(int i=0;i<l;i++)
        {
            if(k%8==0)
            {
                binary.append(' ');
            }

            if(word.charAt(i)==0)
            {
                binary.append(0);
            }
            else
            {
                binary.append(1);
            }
            k++;
        }
        */

        //Bit masking technique
        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;//val contains the value of byte 'b' as integer
            //System.out.println(val);
            //System.out.println("======================");
            //Every byte has 8 bits that's why loop is running 8 times
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);     //Appending 1 to binary (StringBuilder) if right most bit is 1 otherwise appends 0.
                //System.out.println("binary = "+binary);
                //System.out.println("=====================");
                val <<= 1;
                //System.out.println("val = "+val);
            }
            binary.append(' ');
        }

        return binary.toString();


    }

    private static int leftrotate(int x, int shift)
    {

        return ((x << shift) | (x >>> (32 - shift)));  //>>> is an UNSIGNED shift compared >> which is not

    }

    //instance variables
    private static int h1 = 0x67452301;
    private static int h2 = 0xEFCDAB89;
    private static int h3 = 0x98BADCFE;
    private static int h4 = 0x10325476;
    private static int h5 = 0xC3D2E1F0;
    private static final int k1 = 0x5A827999;
    private static final int k2 = 0x6ED9EBA1;
    private static final int k3 = 0x8F1BBCDC;
    private static final int k4 = 0xCA62C1D6;



    private static String hash(int[] z) {

        //Extend the sixteen 32-bit words into eighty 32-bit words
        int integer_count = z.length;
        int[] intArray = new int[80];
        int j = 0;

        for(int i = 0; i < integer_count; i += 16) {
            for(j = 0; j <= 15; j++)
                intArray[j] = z[j+i];
            for ( j = 16; j <= 79; j++ ) {
                //w[i] = (w[i-3] xor w[i-8] xor w[i-14] xor w[i-16]) leftrotate 1
                intArray[j] = leftrotate(intArray[j - 3] ^ intArray[j - 8] ^ intArray[j - 14] ^ intArray[j - 16], 1);
                //System.out.printf("J: %d ARRAY: %x\n", j, intArray[j]);
            }

            //  calculate A,B,C,D,E:
            int A = h1;
            int B = h2;
            int C = h3;
            int D = h4;
            int E = h5;
            int t = 0; //temp

            for ( int x = 0; x <= 19; x++ ) {
                //temp = leftrotate(a leftrotate 5) + f(t) + e + w[i] + k
                t = leftrotate(A,5)+((B&C)|((~B)&D))+E+intArray[x]+k1;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int b = 20; b <= 39; b++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+intArray[b]+k2;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for (int c = 40; c <= 59; c++ ) {
                t = leftrotate(A,5)+((B&C)|(B&D)|(C&D))+E+intArray[c]+k3;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int d = 60; d <= 79; d++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+intArray[d]+k4;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }

            h1+=A; h2+=B; h3+=C; h4+=D; h5+=E;

        }

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);
        //System.out.println(h1Length.length());

        //Integer.toHexString does not include extra leading 0's
        if(h1Length.length() < 8) {
            StringBuilder h1L = new StringBuilder(h1Length);
            h1L.insert(0,0);
            h1Length = h1L.toString();
        } else if(h2Length.length() < 8) {
            StringBuilder h2L = new StringBuilder(h2Length);
            h2L.insert(0,0);
            h2Length = h2L.toString();
        } else if(h3Length.length() < 8) {
            StringBuilder h3L = new StringBuilder(h3Length);
            h3L.insert(0,0);
            h3Length = h3L.toString();
        } else if(h4Length.length() < 8) {
            StringBuilder h4L = new StringBuilder(h4Length);
            h4L.insert(0,0);
            h4Length = h4L.toString();
        } else if(h5Length.length() < 8) {
            StringBuilder h5L = new StringBuilder(h5Length);
            h5L.insert(0,0);
            h5Length = h5L.toString();
        }

        //result
        String hh = h1Length + h2Length + h3Length + h4Length + h5Length;
        System.out.println("Result: " + hh);


        return null;
    }

    public static String printMessage(String message) {

        StringBuilder sb = new StringBuilder(message);
        int num = message.length();

        while (num > 0) {
            if (num % 32 == 0) {
                sb.insert(num, " ");
            }
            num--;
        }

        return sb.toString();

    }
}
