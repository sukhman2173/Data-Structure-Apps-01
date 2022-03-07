//Sukhman Singh 18041216

package Question3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements DequeADT<E>
{
    private int numElements;
    private E[] elements;
    private int head;
    private int tail;
    
    public ArrayDeque()
    {
        numElements = 0;
        elements = (E[]) (new Object[1]);
        head = 0;
        tail = 0;
    }
    
    @Override
    public void enqueueRear(E element)
    {
        if(numElements >= elements.length)
        {
            expandCapacity();
        }
        
        if(tail >= elements.length)
        {
            E[] tempArray =(E[])(new Object[elements.length]);
        
            int position = head;
            for(int i = 0; i < elements.length; ++i)
            {
                if(position >= elements.length)
                {
                    tempArray[i] = element;
                    break;
                }
                
                tempArray[i] = elements[position];
                position++;
            }
            
            elements = tempArray;
            head = 0;
            numElements++;
            tail = numElements;
        }
        else
        {
            elements[tail] = element;
            numElements++;
            tail++;
        }
    }

    @Override
    public void enqueueFront(E element)
    {
        if(numElements >= elements.length)
        {
            expandCapacity();
        }
        
        if(head == 0)
        {
            E[] tempArray =(E[])(new Object[elements.length]);
            tempArray[0] = element;
            
            int position = head;
            
            for(int i = 1; i < elements.length; i++)
            {
                tempArray[i] = elements[position];
                
                position++;
            }
            
            elements = tempArray;
            numElements++;
            head = 0;
            tail = numElements;
        }
        else
        {
            elements[head-1] = element;
            
            head--;
            numElements++;
        }
    }

    @Override
    public E dequeueRear()
    {
        if(numElements != 0)
        {
            E tailElement = elements[tail-1];
            elements[tail-1] = null;
            numElements--;
            tail--;
            
            return tailElement;
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
            E headElement = elements[head];
            elements[head] = null;
            numElements--;
            head++; 
        
            return headElement;
        }
        else
        {
            throw new NoSuchElementException("No elements in Queue");
        }
    }

    @Override
    public E first()
    {
        return elements[head];
    }

    @Override
    public E last()
    {
        return elements[tail-1];
    }

    @Override
    public boolean isEmpty()
    {
        return numElements == 0;
    }

    @Override
    public int size()
    {
        int size = 0;
        for(int i = 0; i < elements.length; i++)
        {
            if(elements[i] != null)
            {
                size++;
            }
        }
        
        return size;
    }

    @Override
    public void clear()
    {
        elements = (E[]) (new Object[1]);
        head = 0;
        tail = 0;
        numElements = 0;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new ArrayIterator<>(elements);
    }
    
    private class ArrayIterator<E> implements Iterator<E>
    {
        private int nextIndex;
        private E[] elements;
        
        public ArrayIterator(E[] elements)
        {  
            nextIndex = head;
            this.elements = elements;
        }
        
        @Override
        public boolean hasNext() 
        {
            return (nextIndex<tail); 
        }

        @Override
        public E next() 
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return elements[nextIndex++];
        }
        
    }
    
    public void expandCapacity()
    {
        E[] largerArray =(E[])(new Object[elements.length*2]);
        
        int position = head;
        for(int i = 0; i < elements.length; ++i)
        {
            if(position >= elements.length)
            {
                position = 0;
            }
            largerArray[i] = elements[position];
            position++;
        }
        elements = largerArray;
        head = 0;
        tail = numElements;
    }
    
    @Override
    public String toString()
    {  
        String output = "[";
        for (int i = 0; i < elements.length; i++)
        {  
            if(elements[i] != null)
            {
                output += elements[i];
                
                if(i < elements.length-1)
                {
                    if (elements[i+1] != null)
                    {
                        output += ",";
                    }
                }
            }
        }
        output += "]";
        
        return output;
    }
}
