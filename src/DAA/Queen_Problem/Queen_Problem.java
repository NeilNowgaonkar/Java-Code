package DAA.Queen_Problem;

import java.util.Arrays;
import java.util.Scanner;

public class Queen_Problem
{
    static int count=0;

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("N - Queen Problem");
        System.out.print("\nEnter the value of n = ");
        int n=sc.nextInt();

        String board[][]=new String [n][n];

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                board[i][j]=" ";
            }
        }

        nQueen(board,0);

        System.out.println("\nTotal Number of Solutions  = "+count);
    }

    static void nQueen(String board[][], int row)
    {
        if(row==board.length)
        {
            count++;

            System.out.println("\nSolution - "+count);
            display(board);
        }

        for(int col=0;col< board.length;col++)
        {
            if(isSafe(board,row,col))
            {
                String q="Q";
                board[row][col]=q;
                nQueen(board,row+1);
                board[row][col]=" ";
            }
        }

    }

    static boolean isSafe(String board[][], int r, int c)
    {
        //To check columns
        for(int i=0;i<r;i++)
        {
            if(board[i][c]=="Q")
                return false;
        }

        // Check if two queens share the same '' diagonal
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--)
        {
            if (board[i][j] == "Q")
            {
                return false;
            }
        }

        // return false if two queens share the same `/` diagonal
        for (int i = r, j = c; i >= 0 && j < board.length; i--, j++)
        {
            if (board[i][j] == "Q") {
                return false;
            }
        }

        return true;
    }

    public static void display(String board[][])
    {
        System.out.println("\n |---------------------------------------|");
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]==" ")
                {
                    System.out.print(" |  "+board[i][j]);

                }
                else
                {
                    System.out.print(" | "+board[i][j]+(i+1));

                }

            }
            System.out.print(" | ");
            System.out.println("\n |---------------------------------------|");
        }
    }
}
