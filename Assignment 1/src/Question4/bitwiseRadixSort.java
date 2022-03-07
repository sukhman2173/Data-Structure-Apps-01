//Sukhman Singh 18041216

package Question4;

import java.util.Random;

public class bitwiseRadixSort 
{
    public static void bitwiseRadixSort(char[] list)
    {
        //int list for storing unicode values for each char from char list
        int[] numList = new int[list.length];
        
        //Assign char list values into int list
        for(int i = 0; i < numList.length; i++)
        {
            numList[i] = list[i];
        }

        //output unsorted char and int lists
        System.out.println("Sorting These");
        System.out.print("[");
        for (int i=0; i<list.length; i++)
        {
            System.out.print(list[i]);
            
            if(i < list.length-1)
            {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        System.out.print("[");
        for (int i=0; i<numList.length; i++)
        {
            System.out.print(numList[i]);
            
            if(i < numList.length-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
        
        //creating the 2 queues for 0 and 1
        LinkedQueue<Character>[] radixQueues = new LinkedQueue[2];
        for (int queueNum=0; queueNum<2; queueNum++)
        {
            radixQueues[queueNum] = new LinkedQueue<>();
        }
        
        //number which refers to which queue (0 or 1) the char should be enqueued to
        int queueNum;
        
        //start from last bit and end at first bit
        //Loop 16 times for each bit for char (0 - 15)
        for (int digit=0; digit < 16; digit++)
        {  
           //iterate through char list
           for (int i=0; i < list.length; i++)
           {   
               //finding bit (0 or 1) for each binary number (for char) in char list and enqueue char to approriate queue (0 or 1)
               queueNum = (list[i] >> digit) & 1;
               radixQueues[queueNum].enqueue(list[i]);
           }
           
           //dequeue each char from 0 and then 1 radixQueues and add back into char list
           int nextIndex = 0;
           for (queueNum = 0; queueNum < 2; queueNum++)
           {  
               while (!radixQueues[queueNum].isEmpty())
               {
                    list[nextIndex] = radixQueues[queueNum].dequeue();
                    nextIndex++;
               }
           }
        }
        
        System.out.println("\nSorted");
        //get sorted char list and insert into int list
        for(int i = 0; i < list.length; i++)
        {
            numList[i] = list[i];
        }
        
        // output the sorted int and char lists
        System.out.print("[");
        for (int i=0; i<list.length; i++)
        {
            System.out.print(list[i]);
            
            if(i < list.length-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
        
        System.out.print("[");
        for (int i=0; i<numList.length; i++)
        {
            System.out.print(numList[i]);
            
            if(i < numList.length-1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
    
    public static void main(String[] args) 
    {
        Random rand = new Random();
        char[] randList = new char[20];
        
        for(int i = 0; i < randList.length; i++)
        {
            randList[i] = (char)rand.nextInt();
        }
        
        bitwiseRadixSort(randList);
    }
}
