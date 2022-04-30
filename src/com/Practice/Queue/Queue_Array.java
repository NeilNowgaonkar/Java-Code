package com.Practice.Queue;

class Queue
{
    private int arr[];
    int front=-1;
    int rear=-1;
    int n;
    void define_size(int N)
    {
        arr=new int [N];
        n=N;
    }

    void enqueue(int x)
    {
        if(isEmpty())
        {
            front++;
            rear++;
            arr[front]=x;
        }
        if(isFull())
        {
            System.out.println("Overflow");
        }
        else
        {
            rear++;
            arr[rear]=x;
        }
    }

    int dequeue()
    {
        if(!isEmpty())
        {
            int val=arr[front];
            front++;
            System.out.println(val);
            return val;
        }
        else
        {
            System.out.println("Underflow");
            return -1;
        }
    }

    boolean isFull()
    {
        if(rear==n-1)
        {
            return true;
        }
        return false;
    }

    boolean isEmpty()
    {
        if(front==-1 || front>rear)
        {
            return true;
        }
        return false;
    }

}
