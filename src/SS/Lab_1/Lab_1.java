package SS.Lab_1;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Lab_1
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int choice=0;

        while(true)
        {
            System.out.println("Choose the Disk Scheduling Algorithm to be performed:-");
            System.out.println("1. FCFS");
            System.out.println("2. SSTF");
            System.out.println("3. SCAN");
            System.out.println("4. C-SCAN");
            System.out.println("5. Exit");
            System.out.print("\n\nEnter your choice:-");
            choice=sc.nextInt();

            if(choice==5)
            {
                System.exit(0);
            }

            int n,head;

            System.out.print("\nEnter total number of requests =");
            n=sc.nextInt();

            int req[]=new int[n];

            System.out.println("Enter the requests:-");
            for(int i=0;i<n;i++)
            {
                req[i]=sc.nextInt();
            }

            System.out.println("Enter the current position of the head");
            head=sc.nextInt();


            switch(choice)
            {
                case 1:
                    FCFS(req,head);
                    break;

                case 2:
                    SSTF(req,head);
                    break;

                case 3:
                    SCAN(req,head);
                    break;

                case 4:
                    CSCAN(req,head);
                    break;

                default:
                    System.out.println("Invalid Choice!!");
            }
            System.out.println("==============================================");

        }
    }

    static void FCFS(int req[], int head)
    {
        int n= req.length,tst=0,dist=0;

        System.out.println("==============================================");
        System.out.println("Curr Head \t Distance \t Total Seek Time");
        System.out.println("==============================================");

        for(int i=0;i<n;i++)
        {
            dist=Math.abs(head-req[i]);
            tst=tst+dist;
            System.out.println(head+"\t\t\t\t" + dist + "\t\t\t\t" + tst);
            head=req[i];
        }

        System.out.println("\nTotal Seek Time = "+tst);
        System.out.println("Average Seek Time = "+(tst/n));

    }

    static class node
    {
        int dist=0;
        boolean serviced=false;
    }

    static void SSTF(int req[], int head)
    {
        int tst=0;

       node diff[]=new node[req.length];

       for(int i=0;i< diff.length;i++ )
       {
           diff[i]=new node();
       }

       int seek_seq[]=new int[req.length+1];

        System.out.println("==========================================================");
        System.out.println("Curr Head \t Distance \t Total Seek Time");
        System.out.println("==========================================================");

       for(int i=0;i<req.length;i++)
       {
           seek_seq[i]=head;
           difference(req,head,diff);

           int index=minimum(diff);

           diff[index].serviced=true;


           tst=tst+diff[index].dist;
           System.out.println(head+ "\t\t\t\t" + diff[index].dist+ "\t\t\t\t" + tst);
           head=req[index];
       }
       System.out.println(head+"\t\t\t\t" + 0 + "\t\t\t\t" + tst);

       seek_seq[seek_seq.length-1]=head;
        System.out.println("Total Seek Time = "+tst);
        System.out.println("Average Seek Time = "+(double)tst/12);

    }

    static int minimum(node[] diff)
    {

        int index=-1, min=Integer.MAX_VALUE;

        for(int i=0;i<diff.length;i++)
        {
            if(!diff[i].serviced && min>diff[i].dist)
            {
                min=diff[i].dist;
                index=i;
            }
        }
        return index;
    }

    static void difference(int req[], int head, node diff[])
    {
        for(int i=0;i<diff.length;i++)
        {
            diff[i].dist=Math.abs(req[i]-head);
        }
    }

    static void SCAN(int req[],int head)
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the maximum disk size");
        int disk_size= sc.nextInt();

        System.out.println("Choose the head direction between left and right :-");
        String direction=sc.next();

        int tst = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>(),
                right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // appending end values which has to be visited before reversing the direction
        if (direction == "left")
            left.add(0);
        else if (direction == "right")
            right.add(disk_size - 1);

        for (int i = 0; i < req.length; i++)
        {
            if (req[i] < head)
                left.add(req[i]);
            if (req[i] > head)
                right.add(req[i]);
        }

        // sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        System.out.println("==========================================================");
        System.out.println("Curr Head \t Distance \t Total Seek Time");
        System.out.println("==========================================================");


        // run the while loop two times.
        // one by one scanning right
        // and left of the head
        int run = 2;
        while (run-- >0)
        {

            if (direction.equalsIgnoreCase("left"))
            {
                for (int i = left.size() - 1; i >= 0; i--)
                {
                    cur_track = left.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    tst += distance;

                    System.out.println(head+ "\t\t\t\t" + distance+ "\t\t\t\t" + tst);

                    // accessed track is now the new head
                    head = cur_track;
                }
                direction = "right";

            }
            else if (direction.equalsIgnoreCase("right"))
            {
                for (int i = 0; i < right.size(); i++)
                {
                    cur_track = right.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    tst += distance;

                    System.out.println(head+ "\t\t\t\t" + distance+ "\t\t\t\t" + tst);


                    // accessed track is now new head
                    head = cur_track;
                }
                direction = "left";
            }

        }
        System.out.println(head+ "\t\t\t\t" + 0+ "\t\t\t\t" + tst);

        System.out.print("Total Seek Time = " + tst + "\n");
        System.out.print("Average Seek Time = " + (double)tst/12 + "\n");

    }

    static void CSCAN(int req[],int head)
    {
        Scanner sc = new Scanner(System.in);
        int tst = 0;
        int distance, cur_track;

        System.out.println("Enter the maximum disk size");
        int disk_size= sc.nextInt();

        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence
                = new Vector<Integer>();

        // Appending end values which has
        // to be visited before reversing
        // the direction
        left.add(0);
        right.add(disk_size - 1);

        // Tracks on the left of the
        // head will be serviced when
        // once the head comes back
        // to the beggining (left end).
        for (int i = 0; i < req.length; i++) {
            if (req[i] < head)
                left.add(req[i]);
            if (req[i] > head)
                right.add(req[i]);
        }

        // Sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        System.out.println("==========================================================");
        System.out.println("Curr Head \t Distance \t Total Seek Time");
        System.out.println("==========================================================");

        // First service the requests
        // on the right side of the
        // head.
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);

            // Appending current track to seek sequence
            seek_sequence.add(cur_track);

            // Calculate absolute distance
            distance = Math.abs(cur_track - head);

            // Increase the total count
            tst += distance;

            System.out.println(head+ "\t\t\t\t" + distance+ "\t\t\t\t" + tst);

            // Accessed track is now new head
            head = cur_track;
        }

        // Once reached the right end
        // jump to the beggining.
        head = 0;

        // adding seek count for head returning from 199 to
        // 0
        tst += (disk_size - 1);

        // Now service the requests again
        // which are left.
        for (int i = 0; i < left.size(); i++) {
            cur_track = left.get(i);

            // Appending current track to
            // seek sequence
            seek_sequence.add(cur_track);

            // Calculate absolute distance
            distance = Math.abs(cur_track - head);

            // Increase the total count
            tst += distance;

            System.out.println(head+ "\t\t\t\t" + distance+ "\t\t\t\t" + tst);


            // Accessed track is now the new head
            head = cur_track;
        }
        System.out.println(head+ "\t\t\t\t" + 0+ "\t\t\t\t" + tst);

        System.out.println("Total seek time = " + tst);
        System.out.println("Average seek time = " + (double)tst/12);

    }
}
