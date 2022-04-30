package DAA.Lab_1;

import Graphs.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Addition
{
    public static void main(String args[])
    {
        /*(Scanner sc=new Scanner(System.in);
        System.out.println("Enter the total number of Elements to be added");
        int n=sc.nextInt();

        int arr[]=new int[n];

        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }

        integer_addition(arr,n);
        binary_addition();*/
    }

    /*static void integer_addition(int arr[],int n)
    {
        int sum=0;
        for(int i=0;i<n;i++)
        {
            sum=arr[i];
        }
        System.out.println("Sum of Elements = "+sum);
    }

    static void double_addition(double arr[],int n)
    {
        double sum=0;
        for(int i=0;i<n;i++)
        {
            sum=arr[i];
        }
        System.out.println("Sum of Elements = "+sum);
    }

    int bitwise_addition(int a,int b)
    {
        int carry=(a&b)<<1;
        int result=a*b;
        if(carry==0)
        {
            return result;
        }
        else
        {
            bitwise_addition(carry,result);
        }
    }

    int multiply(int x,int y)
    {
        if(y==0)
        {
            return 0;
        }

        int z=multiply(x, (int) Math.floor(y/2));
        if(y%2==0)
        {
            return 2*z;
        }
        else
        {
            return x+2*z;
        }
    }

    int mod_multiply(int a,int b,int c)
    {
        int ans=a*b;
        ans=ans%c;
        return ans;
    }

    double divide(int x,int y)
    {
        if(x==0)
        {

        }
    }*/

    /*int[][] addMatrix(int a[10][10], int b[10][10], int size)
    {
        int i,j;
        int ans[10][10];
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                ans[i][j]=a[i][j]+b[i][j];
            }
        }
        return ans;
    }

    void multiplyMatrix(int[][] a, int r1, int c1,int[][] b, int r2, int c2)
    {
        if(r2!=c1)
        {
            return;
        }

        int ans[][]=new int[r1][c2];

        for(int i=0;i<r1;i++)
        {
            for(int j=0;j<c2;j++)
            {
                for(int k=0;k<r2;k++)
                {
                    ans[i][j]+=a[i][k]*b[k][j];
                }
            }
        }

        print(ans);
    }

    static void transpose(int a[][], int b[][])
    {
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                b[i][j] = a[j][i];
    }

    static void DFS(Graph graph, int v, boolean[] discovered)
    {
        discovered[v] = true;

        System.out.print(v + " ");

        for (int u: graph.adjList.get(v))
        {
            if (!discovered[u]) {
                DFS(graph, u, discovered);
            }
        }
    }

    void BFS(int s)
    {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }*/
}
