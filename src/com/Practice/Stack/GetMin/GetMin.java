package com.Practice.Stack.GetMin;

import java.util.Scanner;
import java.util.Stack;

 class MinStack
{
    Stack <Integer> main_stack;
    Stack <Integer> supp_stack;

    MinStack()
    {
        main_stack=new Stack<Integer>();
        supp_stack=new Stack<Integer>();
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of elements");
        int n=sc.nextInt();

        System.out.println("Enter "+n+" elements :");
        for (int i=0;i<n;i++)
        {
            //main_stack.push(sc.nextInt());
        }




    }
}
