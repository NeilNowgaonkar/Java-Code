package CompilerDesign;

import java.util.Scanner;
import java.io.*;


public class Lab_2
{
    int machince_code, length;
    String table_class;

    public static void main(String args[]) throws IOException
    {
        File file = new File("D://TY COLLEGE//COMPILER DESIGN (CD)//LAB//Lab_Program.txt");
        Scanner input = new Scanner(file);

        int count = 0;
        while (input.hasNext())
        {
            String word  = input.next();
            System.out.println(word);
            count = count + 1;
        }
        System.out.println("Word count: " + count);
    }

    public void interpret(String word)
    {
        switch(word)
        {
            case "START":
                table_class="IS";
                machince_code=00;
                length=1;
                break;

            case "ADD":
                table_class="IS";
                machince_code=01;
                length=1;
                break;

            case "SUB":
                table_class="IS";
                machince_code=02;
                length=1;
                break;

            case "MULTI":
                table_class="IS";
                machince_code=03;
                length=1;
                break;

            case "MOVER":


        }
    }


}
