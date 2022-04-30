package DAA.Majority_Element;

import java.util.Scanner;

public class Majority_Element
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter the number of elements = ");
        int n=sc.nextInt();

        System.out.println("Enter the elements = ");
        int nums[]=new int [n];
        for(int i=0;i<n;i++)
        {
            nums[i]=sc.nextInt();
        }

        System.out.println("Majority element  = "+ findMajorityElement(nums,0,nums.length-1));
    }

    public static int findMajorityElement(int nums[], int low, int high)
    {
        if(low==high)
            return nums[low];

        int mid= low+(high-low)/2;
        int left= findMajorityElement(nums,low,mid);
        int right = findMajorityElement(nums,mid+1,high);

        if(left==right)
        {
            return left;
        }

        int left_count = count(nums,left,low,high);
        int right_count = count(nums,right,low,high);

        return left_count>right_count?left:right;
    }

    public static int count(int[] nums, int num, int low, int high)
    {
        int count = 0;
        for (int i = low; i <= high; i++)
        {
            if (nums[i] == num)
            {
                count++;
            }
        }
        return count;
    }
}


//Sample Inputs
//7
//2 2 1 1 1 2 2