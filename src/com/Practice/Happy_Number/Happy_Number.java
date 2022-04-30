package com.Practice.Happy_Number;

import java.util.HashSet;
import java.util.Scanner;

public class Happy_Number
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the first number of the range = ");
        int start=sc.nextInt();
        int end= sc.nextInt();
        int arr[]=new int[end-start];
        int k=0;
        for(int i=start;i<end;i++)
        {
            if(isHappy(i))
            {
                arr[k++]=i;
            }
        }


        for(int i=0;i<(end-start);i++)
        {
            if(arr[i]!=0)
            {
                System.out.print(arr[i]+"\t");
            }
        }


    }

    public static boolean isHappy(int n)
    {
        if(n==1 || n==-1)
            return true;

        HashSet<Integer> set=new HashSet<Integer>();

        while(n!=1 && set.contains(n)==false)
        {
            set.add(n);
            n=getSum(n);
        }

        return n==1;

    }

    static int getSum(int n)
    {
        int sum=0;
        while(n>0)
        {
            int rem=n%10;
            sum=sum+(rem*rem);
            n=n/10;
        }
        return sum;
    }
}
