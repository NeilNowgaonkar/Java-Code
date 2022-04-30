package DAA.Lab_2;

import java.util.Scanner;

public class Deterministic_QuickSort
{
    static int swaps=0;

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the total number of elements =");
        int n=sc.nextInt();
        System.out.println("\nEnter the elements :");
        int nums[]=new int[n];

        for(int i=0;i<n;i++)
        {
            nums[i]=sc.nextInt();
        }


        quickSort(nums,0,nums.length-1);

        System.out.println("Sorted Array");
        for(int i=0;i<n;i++)
        {
            System.out.print(nums[i]+" ");
        }

        System.out.println("\nNumber of Swaps = "+swaps);
    }

    public static void swap(int nums[], int a, int b)
    {
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
        swaps++;
    }

    static void quickSort(int nums[], int left, int right)
    {
        if(left>=right)
            return;

        int pi=partiton(nums,left,right);
        quickSort(nums,left,pi-1);
        quickSort(nums,pi+1,right);

    }

    static int partiton(int nums[],int left,int right)
    {

        int pivot=nums[right];
        int i=left-1;

        for(int j=left;j<=right-1;j++)
        {
            if(nums[j]<pivot)
            {
                i++;
                swap(nums,i,j);
            }
        }

        swap(nums,i+1,right);
        return i+1;


    }
}

//Sample 1 = 8 7 2 1 0 9 6
//Sample 2 = 10 7 8 9 1 5
//Sample 3 = 15 12 10 9 8 4
//Sample 4 = 10 9 8 7 6 5 4 3 2 1