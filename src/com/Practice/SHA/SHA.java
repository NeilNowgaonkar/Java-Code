package com.Practice.SHA;

import java.util.HashMap;
import java.util.Scanner;

public class SHA
{

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        //Initialize predefined values for round 1
        String hex_h0 = "67452301";
        String hex_h1 = "EFCDAB89";
        String hex_h2 = "98BADCFE";
        String hex_h3 = "10325476";
        String hex_h4 = "C3D2E1F0";

        int[] h0 =wordToInt(hexToBinary(hex_h0));
        int[] h1 =wordToInt(hexToBinary(hex_h1));
        int[] h2 =wordToInt(hexToBinary(hex_h2));
        int[] h3 =wordToInt(hexToBinary(hex_h3));
        int[] h4 =wordToInt(hexToBinary(hex_h4));


        //predefined values of k
        String hex_k0="5A827999";
        String hex_k1="6ED9EBA1";
        String hex_k2="8F1BBCDC";
        String hex_k3="CA62C1D6";

        int[] k0 =wordToInt(hexToBinary(hex_k0));
        int[] k1 =wordToInt(hexToBinary(hex_k1));
        int[] k2 =wordToInt(hexToBinary(hex_k2));
        int[] k3 =wordToInt(hexToBinary(hex_k3));



        //Declaring words
        String[] words = new String[80];


        int[] ml =new int[512];  //message length in bits

        System.out.print("Enter the message = ");
        String msg=sc.nextLine();

        String hex_msg=stringToHex(msg);
        System.out.println("Hex Value of msg ="+hex_msg);

        String bin_msg=hexToBinary(hex_msg);
        System.out.println("Binary Value of msg ="+bin_msg);

        String padded_msg = msg_padding(bin_msg);
        System.out.println("Padded message = "+padded_msg);

        System.out.println("==================================");

        //Initializing words
        words[0]=(padded_msg.substring(0,32));
        words[1]=(padded_msg.substring(32,64));
        words[2]=(padded_msg.substring(64,96));
        words[3]=(padded_msg.substring(96,128));
        words[4]=(padded_msg.substring(128,160));
        words[5]=(padded_msg.substring(160,192));
        words[6]=(padded_msg.substring(192,224));
        words[7]=(padded_msg.substring(224,256));
        words[8]=(padded_msg.substring(256,288));
        words[9]=(padded_msg.substring(288,320));
        words[10]=(padded_msg.substring(320,352));
        words[11]= padded_msg.substring(352,384);
        words[12]=(padded_msg.substring(384,416));
        words[13]=(padded_msg.substring(416,448));
        words[14]=(padded_msg.substring(448,480));
        words[15]=(padded_msg.substring(480,512));

        int[] a=h0;
        int[] b=h1;
        int[] c=h2;
        int[] d=h3;
        int[] e=h4;

        int[] ans =func0to19(b,c,d);
        printArray(ans);

        ans=func20to39(b,c,d);
        printArray(ans);

        ans=func40to59(b,c,d);
        printArray(ans);

        ans=func60to79(b,c,d);
        printArray(ans);

        int[] ans1 =leftrotate(a,5);
        System.out.println("Rotated array = ");
        printArray(ans1);

        for(int i=0;i<16;i++)
        {
            int[] word =wordToInt(words[i]);
            int[] f =func20to39(b,c,d);
            int[] temp =core(a,b,c,d,e,f,word,k0,i);

            //e=d;E = D; D = C; C = S30(B); B = A; A = TEMP;
              e=d;
              d=c;
              c=leftrotate(b,30);
              b=a;
              a=temp;

              h0=add(h0,a);
              h1=add(h1,b);
              h2=add(h2,c);
              h3=add(h3,d);
              h4=add(h4,e);
        }

        for(int i=16;i<80;i++)
        {
            int[] f =new int[32];
            int[] k =new int[32];
            int[] temp=new int[32];
            int[] word =new int[32];

            if(i<=0 && i>=19)
            {
                f=func0to19(b,c,d);
                k=k0;
            }
            else if(i<=20 && i>=39)
            {
                f=func20to39(b,c,d);
                k=k1;
            }
            else if(i<=40 && i>=59)
            {
                f=func40to59(b,c,d);
                k=k2;
            }
            else if(i<=60 && i>=79)
            {
                f=func60to79(b,c,d);
                k=k3;
            }

            //w[i] = (w[i-3] xor w[i-8] xor w[i-14] xor w[i-16]) leftrotate 1
            word=leftrotate((word_generator(words[i-3],words[i-8],words[i-14],words[i-16])),1);
            words[i]=IntToWord(word);

            temp =core(a,b,c,d,e,f,word,k,i);

            //e=d;E = D; D = C; C = S30(B); B = A; A = TEMP;
            e=d;
            d=c;
            c=leftrotate(b,30);
            b=a;
            a=temp;

            h0=add(h0,a);
            h1=add(h1,b);
            h2=add(h2,c);
            h3=add(h3,d);
            h4=add(h4,e);

        }

        System.out.println("Final Answer===============================================");


        printArray(h0);
        printArray(h1);
        printArray(h2);




    }

    static int[] add(int []temp1, int []temp2)
    {
        for(int i=0;i<32;i++)
        {
            temp1[i]=temp1[i]+temp2[i];
        }
        return temp1;
    }

    static int[] core(int[] a, int[] b, int[] c, int[] d, int[] e, int[] f, int[] word, int[] k, int x)
    {
        int[] temp =new int[32];
        a=leftrotate(a,5);
        for(int i=0;i<32;i++)
        {
            temp[i]=a[i]+f[i]+e[i]+k[i]+word[i];
        }

        return temp;

    }

    //f = (b and c) or ((not b) and d)
    static int[] func0to19(int[] b, int[] c, int[] d)
    {
        int[] ans =new int[32];

        for(int i=0;i<32;i++)
        {
            ans[i]=(b[i]&c[i])|((~b[i])&d[i]);
        }
        return ans;
    }

    //f = b xor c xor d
    static int[] func20to39(int[] b, int[] c, int[] d)
    {
        int[] ans =new int[32];

        for(int i=0;i<32;i++)
        {
            ans[i]=b[i]^c[i]^d[i];
        }
        return ans;
    }

    //f = (b and c) or (b and d) or (c and d)
    static int[] func40to59(int[] b, int[] c, int[] d)
    {
        int[] ans =new int[32];

        for(int i=0;i<32;i++)
        {
            ans[i]=(b[i]&c[i])|(b[i]&d[i])|(c[i]&d[i]);
        }
        return ans;
    }

    //f = b xor c xor d
    static int[] func60to79(int[] b, int[] c, int[] d)
    {
        int[] ans =new int[32];

        for(int i=0;i<32;i++)
        {
            ans[i]=b[i]^c[i]^d[i];
        }
        return ans;
    }

    static void printArray(int[] arr)
    {

        System.out.println("Printing Array");
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println("Length = "+arr.length);
    }

    static String stringToHex(String str)
    {
        StringBuffer sb = new StringBuffer();
        //Converting string to character array
        char[] ch = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }



    static String hexToBinary(String hex)
    {
        String binary="";

        hex = hex.toUpperCase();


        //Hashmap to convert hex to binary
        HashMap<Character,String> map=new HashMap<Character,String>();
        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "0101");
        map.put('6', "0110");
        map.put('7', "0111");
        map.put('8', "1000");
        map.put('9', "1001");
        map.put('A', "1010");
        map.put('B', "1011");
        map.put('C', "1100");
        map.put('D', "1101");
        map.put('E', "1110");
        map.put('F', "1111");

        int i;
        char ch;

        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < hex.length(); i++) {
            // extracting each character
            ch = hex.charAt(i);

            // checking if the character is
            // present in the keys
            if (map.containsKey(ch)) {
                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += map.get(ch);
            }
                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
    }

    static String msg_padding(String bin_msg)
    {
        String ans=bin_msg;
        int original_length=bin_msg.length();
        System.out.println("Length of original msg="+original_length);

        //Appending '1' to the end of binary message
        ans=ans+"1";

        int l=ans.length();

        for(int i=0;i<448-l;i++)
        {
            ans=ans+"0";
        }

        l=ans.length();
        System.out.println("Length of padded msg="+l);

        //calculating the binary of length of orignal string to concatenate to 448 bit string
        String len_orig_string=DecimalToBinary(original_length);
        System.out.println("Binary of decimal = "+len_orig_string);

        l=len_orig_string.length();

        for(int i=0;i<64-l;i++)
        {
            len_orig_string="0"+len_orig_string;
        }


        ans=ans+len_orig_string;
        l=ans.length();

        System.out.println("Length of padded msg="+l);

        return ans;
    }

    static String DecimalToBinary(int decimal)
    {
        StringBuilder ans= new StringBuilder();
        int[] binary = new int[40];
        int index = 0;
        while(decimal > 0){
            binary[index++] = decimal%2;
            decimal = decimal/2;
        }
        for(int i = index-1;i >= 0;i--)
        {
            ans.append(binary[i]);
        }
        return ans.toString();
    }

    static int[] wordToInt(String s)
    {
        int[] ans =new int[32];
        char ch;
        for(int i=0;i<32;i++)
        {
            ch=s.charAt(i);
            if(ch=='1')
            {
                ans[i]=1;
            }
            else
            {
                ans[i]=0;
            }
        }

        return ans;
    }

    static String IntToWord(int[] arr)
    {
        StringBuilder ans= new StringBuilder();
        for (int j : arr) {
            if (j == 1) {
                ans.append("1");
            } else {
                ans.append("0");
            }
        }

        return ans.toString();
    }

    static int[] leftrotate(int[] arr, int shift)
    {
        for(int i=0;i<shift;i++)
        {
            int temp=arr[0];
            for(int j=0;j<arr.length-1;j++)
            {
                arr[j]=arr[j+1];
            }
            arr[arr.length-1]=temp;
        }

        return arr;
    }

    static int[] word_generator(String w3, String w8, String w14, String w16)
    {
        int[] ans =new int[32];

        int[] word3 =wordToInt(w3);
        int[] word8 =wordToInt(w8);
        int[] word14 =wordToInt(w14);
        int[] word16 =wordToInt(w16);

        for(int i=0;i<32;i++)
        {
            ans[i]=word3[i]^word8[i]^word14[i]^word16[i];
        }
        return ans ;
    }
}

