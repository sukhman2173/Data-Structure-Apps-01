//Sukhman Singh 1804126

package Question3;

import java.util.Iterator;

public class TestMain 
{
    public static void main(String[] args) 
    {
        ArrayDeque<String> ad = new ArrayDeque<>();
        
        System.out.println("=============ARRAY DEQUE TEST=============");
        System.out.println("Enqueue to FRONT (F) and REAR (R)");
        ad.enqueueFront("(F)");
        ad.enqueueRear("(R)");
        System.out.println(ad);
        
        System.out.println("CLEARING the Deque:");
        ad.clear();
        System.out.println("Deque is: "+ad);
        
        System.out.println("Enqueue to REAR: A, B, C, D");
        ad.enqueueRear("A");
        ad.enqueueRear("B");
        ad.enqueueRear("C");
        ad.enqueueRear("D");
        System.out.println("Deque is: "+ad);
        System.out.println("FRONT element is: "+ad.first()+", REAR element is: "+ad.last());
        
        System.out.println("Enqueue to FRONT: E, F, G, H");
        ad.enqueueFront("E");
        ad.enqueueFront("F");
        ad.enqueueFront("G");
        ad.enqueueFront("H");
        System.out.println("Deque is: "+ad);
        System.out.println("Number of elements in Deque: "+ad.size());
        System.out.println("FRONT element is: "+ad.first()+", REAR element is: "+ad.last());
        
        System.out.println("Dequeue TWO elements from FRONT: H, G");
        ad.dequeueFront();
        ad.dequeueFront();
        System.out.println("Dequeue TWO elements from REAR: D, C");
        ad.dequeueRear();
        ad.dequeueRear();
        System.out.println("Number of elements in Deque: "+ad.size());
        System.out.println("Deque is: "+ad);
        System.out.println("FRONT element is: "+ad.first()+", REAR element is: "+ad.last());
        
        Iterator<String> adIter = ad.iterator();
        System.out.print("Testing ITERATOR: ");
        while(adIter.hasNext())
        {
            System.out.print(adIter.next()+" ");
        }
        System.out.println();
        
        System.out.println("LOOP and Dequeue from REAR until empty:");
        while(!ad.isEmpty())
        {
            System.out.print(ad.dequeueRear()+" ");
        }
        System.out.println();
        System.out.println("Deque is: "+ad);
        
        System.out.println("Enqueue at REAR: 1,2,3 and at FRONT: 4");
        ad.enqueueRear("1");
        ad.enqueueRear("2");
        ad.enqueueRear("3");
        ad.enqueueFront("4");
        System.out.println("Deque is: "+ad);
        
        System.out.println("LOOP and Dequeue from FRONT until empty:");
        while(!ad.isEmpty())
        {
            System.out.print(ad.dequeueFront()+" ");
        }
        System.out.println();
        System.out.println("Deque is: "+ad);
        
        //To check exception, REMOVE COMMENT HEADERS BELOW
        //System.out.println("This should cause an Exception (ON PURPOSE)!! Dequeue from REAR");
        //System.out.println("EXCEPTION!!: "+ad.dequeueRear());
        
        System.out.println("-----------ARRAY DEQUE TESTING COMPLETE-----------");
        
        DoublyLinkedDeque<String> dld = new DoublyLinkedDeque<>();
        
        System.out.println("\n=============DOUBLY-LINKED DEQUE TEST=============");
        System.out.println("Enqueue to FRONT (F) and REAR (R)");
        dld.enqueueFront("(F)");
        dld.enqueueRear("(R)");
        System.out.println(dld);
        
        System.out.println("CLEARING the Deque:");
        dld.clear();
        System.out.println("Deque is: "+dld);
        
        System.out.println("Enqueue to REAR: A, B, C, D");
        dld.enqueueRear("A");
        dld.enqueueRear("B");
        dld.enqueueRear("C");
        dld.enqueueRear("D");
        System.out.println("Deque is: "+dld);
        System.out.println("FRONT element is: "+dld.first()+", REAR element is: "+dld.last());
        
        System.out.println("Enqueue to FRONT: E, F, G, H");
        dld.enqueueFront("E");
        dld.enqueueFront("F");
        dld.enqueueFront("G");
        dld.enqueueFront("H");
        System.out.println("Deque is: "+dld);
        System.out.println("Number of elements in Deque: "+dld.size());
        System.out.println("FRONT element is: "+dld.first()+", REAR element is: "+dld.last());
        
        System.out.println("Dequeue TWO elements from FRONT: H, G");
        dld.dequeueFront();
        dld.dequeueFront();
        System.out.println("Dequeue TWO elements from REAR: D, C");
        dld.dequeueRear();
        dld.dequeueRear();
        System.out.println("Number of elements in Deque: "+dld.size());
        System.out.println("Deque is: "+dld);
        System.out.println("FRONT element is: "+dld.first()+", REAR element is: "+dld.last());
        
        Iterator<String> dldIter = dld.iterator();
        System.out.print("Testing ITERATOR: ");
        while(dldIter.hasNext())
        {
            System.out.print(dldIter.next()+" ");
        }
        System.out.println();
        
        System.out.println("LOOP and Dequeue from REAR until empty:");
        while(!dld.isEmpty())
        {
            System.out.print(dld.dequeueRear()+" ");
        }
        System.out.println();
        System.out.println("Deque is: "+dld);
        
        System.out.println("Enqueue at REAR: 1,2,3 and at FRONT: 4");
        dld.enqueueRear("1");
        dld.enqueueRear("2");
        dld.enqueueRear("3");
        dld.enqueueFront("4");
        System.out.println("Deque is: "+dld);
        
        System.out.println("LOOP and Dequeue from FRONT until empty:");
        while(!dld.isEmpty())
        {
            System.out.print(dld.dequeueFront()+" ");
        }
        System.out.println();
        System.out.println("Deque is: "+dld);
        
        //To check exception, REMOVE COMMENT HEADERS BELOW
        //System.out.println("This should cause an Exception (ON PURPOSE)!! Dequeue from REAR");
        //System.out.println("EXCEPTION!!: "+dld.dequeueRear());
        
        System.out.println("-----------DOUBLY-LINKED DEQUE TESTING COMPLETE-----------"); 
    }
}
