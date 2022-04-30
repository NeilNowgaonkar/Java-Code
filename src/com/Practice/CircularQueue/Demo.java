package com.Practice.CircularQueue;

public class Demo
{
    public static void main(String args[])
    {
        MyCircularQueue cq=new MyCircularQueue();
        cq.define_size(5);

        cq.enqueue(14);
        cq.enqueue(22);
        cq.enqueue(13);
        cq.enqueue(-6);

        cq.display();
        cq.dequeue();
        cq.dequeue();
        cq.display();


        cq.enqueue(9);
        cq.enqueue(20);
        cq.enqueue(5);

        cq.display();
        cq.display();

        cq.enqueue(20);

    }
}
