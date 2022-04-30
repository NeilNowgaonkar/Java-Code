package DAA.Random_QuickSort;

import java.util.Random;
import java.util.Scanner;

public class Random_Quick_Sort
{
    public static int swaps=0;

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int n;
        System.out.println("Enter the total elements = ");
        n=sc.nextInt();

        int arr[]=new int[n];

        System.out.println("Enter the elements = ");
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }

        System.out.println("Sorted Array");
        random_quicksort(arr,0,n-1);

        for(int i=0;i<n;i++)
        {
            System.out.print(arr[i]+"\t");
        }


        System.out.println("\nNumber Of Swaps = "+swaps);
    }

    static void random_quicksort(int arr[],int low, int high)
    {
        if(low<high)
        {
            int pi=partition(arr,low,high);
            random_quicksort(arr,low,pi-1);
            random_quicksort(arr,pi+1,high);
        }
    }

    static int partition(int arr[],int low,int high)
    {
        random(arr,low,high);
        int pivot=arr[high];

        int i=low-1;
        for(int j=low;j<high;j++)
        {
            if(arr[j]<pivot)
            {
                i++;

                swaps++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }

        }

        swaps++;
        int temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;

        return i+1;
    }

    static void random(int arr[],int low,int high)
    {
        Random rand=new Random();
        int pivot=rand.nextInt(high-low)+low;

        int temp=arr[pivot];
        arr[pivot]=arr[high];
        arr[high]=temp;
    }
}


//10 7 8 9 1 5
//1 2 3 4 5  6 7 8 9 10
// 1 5 2 8 3 8 4 0