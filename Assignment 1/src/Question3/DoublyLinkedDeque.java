//Sukhman Singh 18041216

package Question3;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedDeque<E> implements DequeADT<E>
{ 
    private Node<E> lastNode;
    private Node<E> firstNode;
    private int numElements;
    
    public DoublyLinkedDeque()
    {
        lastNode = null;
        firstNode = null;
        numElements = 0;
    }
    
    @Override
    public void enqueueRear(E element)
    {
        Node<E> newNode = new Node<>(element);
        
        if(firstNode == null)
        {
            firstNode = newNode;
            lastNode = firstNode;
            numElements++;
        }
        else
        {
            Node<E> current = firstNode;
            
            while(current.next != null)
            {
                current = current.next;
            }
            
            current.next = newNode;
            newNode.prev = current;
            lastNode = current.next;
            
            numElements++;
        }
    }

    @Override
    public void enqueueFront(E element)
    {
        Node<E> newNode = new Node<>(element);
        
        if(firstNode == null)
        {
            firstNode = newNode;
            lastNode = firstNode;
            numElements++;
        }
        else
        {
            newNode.next = firstNode;
            firstNode.prev = newNode;
            firstNode = newNode;
            numElements++;
        }
    }

    @Override
    public E dequeueRear()
    {
        if(numElements != 0)
        {
            E element = lastNode.element;
            
            if(numElements > 1)
            {
                lastNode = lastNode.prev;
                lastNode.next = null;
            }
            else
            {
                firstNode = null;
                lastNode = null;
            }

            numElements--;
            return element;
        }
        else
        {
            throw new NoSuchElementException("No elements in Queue");
        }
    }

    @Override
    public E dequeueFront()
    {
        if(numElements != 0)
        {
            E element = firstNode.element;
            
            if(numElements > 1)
            {
                firstNode = firstNode.next;
                firstNode.prev = null;
            }
            else
            {
                firstNode = null;
                lastNode = null;
            }
            
            numElements--;
            return element; 
        }
        else
        {
            throw new NoSuchElementException("No elements in Queue");
        }
    }

    @Override
    public E first()
    {
        return firstNode.element;
    }

    @Override
    public E last()
    {
        return lastNode.element;
    }

    @Override
    public boolean isEmpty()
    {
        return (numElements == 0);
    }

    @Override
    public int size()
    {
        return numElements;
    }

    @Override
    public void clear()
    {
        Node<E> current = firstNode;
        
        while(current.next != null)
        {
            current = current.next;
            current.prev = null;
        }
        
        firstNode = null;
        lastNode = null;
        
        numElements = 0;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new DoublyLinkedIterator<>(firstNode);
    }
    
    private class DoublyLinkedIterator<E> implements Iterator<E>
    {
        private Node<E> current;

        public DoublyLinkedIterator(Node<E> firstNode)
        {  
            current = firstNode;
        }

        @Override
        public boolean hasNext()
        {  
            return (current != null);
        }

        public boolean hasPrev()
        {
            return (current.prev != null);
        }

        @Override
        public E next() throws NoSuchElementException
        {  
          if (!hasNext())
          {
              throw new NoSuchElementException();
          }

          E element = current.element;
          current = current.next;
          return element;
        }

        public E prev() throws NoSuchElementException
        {
            if(!hasPrev())
            {
                throw new NoSuchElementException();
            }

            E element = current.element;
            current = current.prev;
            return element;
        }
    }
    
    protected class Node<E>
    {  
       public E element;
       public Node<E> next;
       public Node<E> prev;

       public Node(E element)
       {  this.element = element;
          next = null;
          prev = null;
       }
    }

    @Override
    public String toString()
    {
        String output = "";
        Node<E> current = firstNode;
        
        output += "[";
        
        if(numElements > 0)
        {
            output += current.element;
            
            if(firstNode.next != null)
            {
                output += ",";
            }
            
            while(current.next != null)
            {
                output += current.next.element;
                current = current.next;

                if(current.next != null)
                {
                    output += ",";
                }
            }
        }
       
        output += "]";
        return output;
    }
}
