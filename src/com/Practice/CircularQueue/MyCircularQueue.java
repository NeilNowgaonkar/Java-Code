package com.Practice.CircularQueue;

class MyCircularQueue
{
    int arr[];
    int front=-1,rear=-1;
    int n=0;

    void define_size(int x)
    {
        arr=new int [x];
        n=x;
    }

    void enqueue(int x)
    {
        if(front==-1 && rear==-1)
        {
            front=rear=0;
            arr[rear]=x;
        }
        else if((rear+1)%n==front)
        {
            System.out.println("\nCircular Queue is Full!");
        }
        else
        {
            rear=(rear+1)%n;
            arr[rear]=x;
        }
    }

    int dequeue()
    {
        if(front==-1 && rear==-1)
        {
            System.out.println("Circular Queue is Empty! Therefore returned = -1");
            return -1;
        }
        else if(front==rear)
        {
            int val=arr[front];
            front=rear=-1;
            System.out.println("Dequed :");
            return val;
        }
        else
        {
            int val=arr[front];
            System.out.println("\nDequed : "+val);
            front=(front+1)%n;
            return val;
        }
    }

    int peek()
    {
        if(front==-1)
        {
            System.out.println("\nCircular Queue is Empty! Therefore returned = -1");
            return -1;
        }
        return arr[front];
    }

    void display()
    {
        if(front==-1 && rear==-1)
        {
            System.out.println("Queue is Empty!");

        }
        else
        {
            int i=front;

            System.out.print("\nElements in circular queue are :");
            while(i!=rear)
            {
                System.out.print(arr[i]+" ");
                i = (i + 1) % n;
            }
            System.out.print(arr[rear]);
        }

    }
}
