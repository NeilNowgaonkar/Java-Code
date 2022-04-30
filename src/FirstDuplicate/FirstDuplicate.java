package FirstDuplicate;

import java.util.HashSet;
import java.util.Scanner;

public class FirstDuplicate
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter size of the array");
        int n=sc.nextInt();

        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter element = ");
            arr[i]=sc.nextInt();
        }

        System.out.println("Answer = "+duplicate(arr));
    }

    static int duplicate(int arr[])
    {
        if(arr.length==0)
        {
            return -1;
        }

        HashSet<Integer> set = new HashSet<Integer>();

        int dup=-1;
        for(int i=0;i<arr.length;i++)
        {
            if(set.contains(arr[i]))
            {
                dup=arr[i];
                break;
            }
            else
            {
                set.add(arr[i]);
            }
        }
        return dup;
    }
}
