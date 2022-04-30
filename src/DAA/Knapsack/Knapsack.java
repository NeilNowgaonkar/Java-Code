package DAA.Knapsack;


import java.util.Scanner;
class Knapsack
{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int object,m;
        System.out.println("Enter the Total Objects");
        object=sc.nextInt();
        int weight[]=new int[object];
        int profit[]=new int[object];
        System.out.print("\nEnter the weights for all ");
        for(int i=0;i<object;i++)
        {
            weight[i]=sc.nextInt();
        }


        System.out.print("\nEnter the profit for all ");
        for(int i=0;i<object;i++)
        {
            profit[i]=sc.nextInt();
        }

        System.out.print("\nEnter the Capacity");
        m=sc.nextInt();
        double p_w[]=new double[object];
        for(int i=0;i<object;i++)
        {
            p_w[i]=(double)profit[i]/(double)weight[i];
        }
        System.out.println("");
        System.out.print("Objects \t");
        for(int i=1;i<=object;i++)
        {
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("Profit \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(profit[i]+"\t");
        }
        System.out.println();
        System.out.print("Weight \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(weight[i]+"\t");
        }
        System.out.println();
        System.out.print("P/W \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(p_w[i]+"\t");
        }

        quicksort(p_w,0,object-1,profit,weight);

        System.out.println("\n\n=====================================");
        System.out.println("\nAfter Sorting : ");
        System.out.print("Objects \t");
        for(int i=1;i<=object;i++)
        {
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("Profit \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(profit[i]+"\t");
        }
        System.out.println();
        System.out.print("Weight \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(weight[i]+"\t");
        }
        System.out.println();
        System.out.print("P/W \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(p_w[i]+"\t");
        }

        double final_ratio[]=new double[object];
        int k=0;
        double sum=0;
        System.out.println();
        while(m>0)
        {
            if(weight[k]<m)
            {
                sum+= profit[k];
                m=m-weight[k];
                final_ratio[k]=1.0;
            }
            else
            {
                double x4=m*profit[k];
                double x5=weight[k];
                final_ratio[k]=1.0;
                double x6=x4/x5;
                sum=sum+x6;
                m=0;
            }
            k++;
        }

        System.out.print("\nPick = \t\t");
        for(int i=0;i<object;i++)
        {
            System.out.print(final_ratio[i]+"\t");
        }
        System.out.println("\nFinal Profit is = "+sum);
    }

    static void swap(double[] arr, int i, int j,int []profit, int [] weight)
    {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        int temp2=profit[i];
        profit[i]=profit[j];
        profit[j]=temp2;

        int temp3=weight[i];
        weight[i]=weight[j];
        weight[j]=temp3;

    }

    static int partition(double[] arr, int low, int high,int[] profit,int weight[])
    {
        double pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            if (arr[j] > pivot)
            {

                i++;
                swap(arr, i, j,profit,weight);
            }
        }
        swap(arr, i + 1, high,profit,weight);
        return (i + 1);
    }

    static void quicksort(double[] arr, int low, int high,int []profit, int [] weight)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high,profit,weight);

            quicksort(arr, low, pi - 1,profit,weight);
            quicksort(arr, pi + 1, high,profit,weight);
        }
    }
}