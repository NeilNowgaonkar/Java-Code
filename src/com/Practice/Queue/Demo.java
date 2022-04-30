package com.Practice.Queue;

public class Demo
{
    public static void main(String args[])
    {
        Queue q= new Queue();
        q.define_size(4);
        q.enqueue(5);
        q.dequeue();
        q.dequeue();
        q.enqueue(10);
        q.enqueue(15);
        q.enqueue(20);
        q.enqueue(25);

    }
}
