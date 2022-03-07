//Sukhman Singh 18041216

package Question4;

import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements QueueADT<E>
{
   private int numElements;
   private Node<E> frontNode;
   private Node<E> rearNode; 
   
   public LinkedQueue()
   {  super();
      numElements = 0;
      frontNode = null;
      rearNode = null;
   }
   
   public LinkedQueue(Collection<? extends E> c)
   {  this();
      for (E element : c)
         enqueue(element);
   }
   

   
   public void enqueue(E element)
   {  Node<E> newNode = new Node<E>(element);

      if (rearNode==null)
      {  frontNode = newNode;
         rearNode = newNode;
      }
      else
      {  rearNode.next = newNode;
         rearNode = newNode;
      }
      numElements++;
   }
   
   
   public E dequeue() throws NoSuchElementException
   {  if (frontNode != null)
      {  E frontElement = frontNode.element;
         frontNode = frontNode.next;
         numElements--;
         if (numElements == 0)
            rearNode = null;
         return frontElement;
      }
      else
         throw new NoSuchElementException();
   }
   
   
   public E first() throws NoSuchElementException
   {  if (numElements > 0)
         return frontNode.element;
      else
         throw new NoSuchElementException();
   }
   
   
   public boolean isEmpty()
   {  return (numElements==0);
   }
   
   
   public int size()
   {  return numElements;
   }
   
   
   public String toString()
   {  String output = "[";
      Node currentNode = frontNode;
      while (currentNode != null)
      {  output += currentNode.element;
         if (currentNode.next != null)
            output += ",";
         currentNode = currentNode.next;
      }
      output += "]";
      return output;
   }
      
   
   private class Node<E>
   {  
      public E element;
      public Node<E> next;
      
      public Node(E element)
      {  this.element = element;
         next = null;
      }
   }
}
