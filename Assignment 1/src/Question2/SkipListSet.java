//Sukhman Singh 18041216

package Question2;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipListSet<E extends Comparable> extends AbstractSet<E>
{
    private int numElements;
    private int numLevels;
    private Node<E> firstNode;
    
    public SkipListSet()
    {
        super();
        E element = null;
        numElements = 0;
        numLevels = 0;
        firstNode = new Node<>(element);
    }
    
    public SkipListSet(Collection<? extends E> c)
    {   
        this();
        for (E element : c)
        {
            add(element);
        }
    }
    
    @Override
    public boolean add(E o)
    {
        Random rand = new Random();
        
        E nullElement = null;
        Node<E> topCurrent = firstNode;
        Node<E> current = firstNode;
        Node<E> lastAdded = firstNode;
        Node<E> newNode = new Node<>(o);
        int lvlCount = 0;
        boolean coinFlip = rand.nextBoolean();
        int numLvl = 0;
        
        if(numElements == 0)
        {
            while(coinFlip)
            {
                numLvl++;
                coinFlip = rand.nextBoolean();
            }
            
            numLevels = numLvl;
            if(numLevels == 0)
            {
                current.next = newNode;
                current = current.next; 
                current.next = new Node<>(nullElement);
                
                numElements++;
                return true;
            }
            else
            {
                for(int i = 0; i < numLevels; i++)
                {
                    if(i == 0)
                    {
                        current.next = new Node<>(nullElement);
                        current.down = new Node<>(nullElement);

                        current = current.next;
                        current.down = new Node<>(nullElement);

                        current = firstNode;
                        current = current.down;
                    }
                    else
                    {
                        topCurrent = topCurrent.next;
                        current.down = new Node<>(nullElement);
                        current.next = topCurrent.down;

                        current = current.next;
                        current.down = new Node<>(nullElement);

                        topCurrent = firstNode;
                        current = firstNode.down;

                        for(int j = 0; j < i; j++)
                        {
                            topCurrent = topCurrent.down;
                            current = current.down;
                        }
                    }
                }

                topCurrent = topCurrent.next;
                current.next = topCurrent.down;

                current = firstNode;
                
                while(current.down != null)
                {
                    if(lvlCount == 0)
                    {
                        newNode.next = current.next;
                        current.next = newNode;

                        newNode.down = new Node<>(o);
                        lastAdded = newNode;
                    }
                    else
                    {
                        lastAdded.down.next = current.next;
                        current.next = lastAdded.down;

                        current = current.next;
                        current.down = new Node<>(o);

                        lastAdded = current;
                    }
                    lvlCount++;
                    current = firstNode;
                    for(int i = 0; i < lvlCount; i++)
                    {
                        current = current.down;
                    }
                }
            
                lastAdded.down.next = current.next;
                current.next = lastAdded.down;

                numElements++;
                return true;
            }
        }
        else
        {
            while(current.down != null)
            {
                current = current.down;
            }
            
            while(current.next != null)
            {
                if(current.next.element != null)
                {
                    if(o.compareTo(current.next.element) == 0)
                    {
                        System.out.println("Element "+o+" Already Exists!!!");
                        return false;
                    }
                    else
                    {
                        current = current.next;
                    }
                }
                else
                {
                    current = firstNode;
                    break;
                }
            }
            
            while(coinFlip)
            {
                numLvl++;
                coinFlip = rand.nextBoolean();
            }
            
            if(numLvl > numLevels)
            {
                int expand = numLvl - numLevels;
                Node<E> tempFirst = new Node<>(nullElement);
                current = tempFirst;
                Node<E> oldcurrent = firstNode;
                
                if(expand == 1)
                {
                    current.next = new Node<>(nullElement);
                    current.down = oldcurrent;
                    
                    current = current.next;
                    while(oldcurrent.next != null)
                    {
                        oldcurrent = oldcurrent.next;
                    }
                    
                    current.down = oldcurrent;
                    
                    current = tempFirst;
                    firstNode = current;
                    numLevels = numLvl;
                }
                else
                {
                    for(int i = 0; i < expand; i++)
                    {
                        if(i < expand-1)
                        {
                            current.down = new Node<>(nullElement);
                            current.next = new Node<>(nullElement);

                            current = current.next;
                            current.down = new Node<>(nullElement);
                            
                            topCurrent = current;
                            current = tempFirst;
                            
                            for(int j = 0; j < i+1; j++)
                            {
                                current = current.down;
                            }
                        }
                        else
                        {
                            current.next = topCurrent.down;
                            current.down = oldcurrent;
                            
                            current = current.next;
                            while(oldcurrent.next != null)
                            {
                                oldcurrent = oldcurrent.next;
                            }
                            
                            current.down = oldcurrent;
                            
                            current = tempFirst;
                            firstNode = current;
                            numLevels = numLvl;
                        }
                    }
                }
            }
            
            for(int i = 0; i < numLevels-numLvl; i++)
            {
                current = current.down;
            }
            
            while(current.down != null)
            {
                while(current.next != null)
                {
                    if(current.next.element == null)
                    {
                        if(lvlCount == 0)
                        {
                            newNode.next = current.next;
                            current.next = newNode;
                            
                            current = current.next;
                            current.down = new Node<>(o);
                            lastAdded = current;
                            break;
                        }
                        else
                        {
                            lastAdded.down.next = current.next;
                            current.next = lastAdded.down;
                            
                            current = current.next;
                            current.down = new Node<>(o);
                            lastAdded = current;
                            break;
                        }
                    }
                    if(o.compareTo(current.next.element) == 0)
                    {
                        System.out.println("Element already exists");
                        return false;
                    }
                    else if(o.compareTo(current.next.element) < 0)
                    {
                        if(lvlCount == 0)
                        {
                            newNode.next = current.next;
                            current.next = newNode;

                            current = current.next;
                            current.down = new Node<>(o);
                            lastAdded = current;
                            break;
                        }
                        else
                        {
                            lastAdded.down.next = current.next;
                            current.next = lastAdded.down;
                            
                            current = current.next;
                            current.down = new Node<>(o);
                            lastAdded = current;
                            break;
                        }
                    }
                    else if(o.compareTo(current.next.element) > 0)
                    {
                        current = current.next;
                    }
                }
                lvlCount++;
                
                current = firstNode;
                
                for(int i = 0; i < numLevels-numLvl+lvlCount; i++)
                {
                    current = current.down;
                }
            }
            if(numLevels == 0)
            {
                current = firstNode;
                while(current.next != null)
                {
                    if(current.next.element != null)
                    {
                        if(o.compareTo(current.next.element) == 0)
                        {
                            System.out.println("Element already exsists");
                            return false;
                        }
                        else if(o.compareTo(current.next.element) < 0)
                        {
                            newNode.next = current.next;
                            current.next = newNode;
                            numElements++;
                            return true;
                        }
                        else if(o.compareTo(current.next.element) > 0)
                        {
                            current = current.next;
                        }
                    }
                    else
                    {               
                        newNode.next = current.next;
                        current.next = newNode;
                        numElements++;
                        return true; 
                    }
                }
                return false;
            }
            else
            {
                while(current.next != null)
                {
                    if(current.next.element == null)
                    {
                        if(lastAdded != firstNode)
                        {
                            lastAdded.down.next = current.next;
                            current.next = lastAdded.down;
                            numElements++;
                            return true;
                        }
                        else
                        {
                            newNode.next = current.next;
                            current.next = newNode;
                            numElements++;
                            return true;
                        }
                    }
                    
                    if(lastAdded != firstNode)
                    {
                        if(o.compareTo(current.next.element) == 0)
                        {
                            System.out.println("Element already exsists");
                            return false;
                        }
                        else if(o.compareTo(current.next.element) < 0)
                        {
                            lastAdded.down.next = current.next;
                            current.next = lastAdded.down;
                            numElements++;
                            return true;
                        }
                        else if(o.compareTo(current.next.element) > 0)
                        {
                            current = current.next;
                        }
                    }
                    else
                    {
                        if(o.compareTo(current.next.element) == 0)
                        {
                            System.out.println("Element already exsists");
                            return false;
                        }
                        else if(o.compareTo(current.next.element) < 0)
                        {
                            newNode.next = current.next;
                            current.next = newNode;
                            numElements++;
                            return true;
                        }
                        else if(o.compareTo(current.next.element) > 0)
                        {
                            current = current.next;
                        }
                    }
                }
            }
            return false;
        }
    }
    
    @Override
    public boolean remove(Object o)
    {
        Node<E> current = firstNode;
        int count = 0;
        boolean removed = false;
        
        while(current.down != null)
        {
            while(current.next != null)
            {
                if(current.next.element != null)
                {
                    if(o == current.next.element)
                    {
                        current.next = current.next.next;
                        removed = true;
                        break;
                    }
                    else
                    {
                        current = current.next;
                    }
                }
                else
                {
                    break;
                }
            }
            count++;
            current = firstNode;
            for(int i = 0; i < count; i++)
            {
                current = current.down;
            } 
        }
        
        while(current.next != null)
        {
            if(current.next.element != null)
            {
                if(o == current.next.element)
                {
                    current.next = current.next.next;
                    removed = true;
                    break;
                }
                else
                {
                    current = current.next;
                }
            }
            else
            {
                break;
            }
        }
        
        return removed;
    }
    
    @Override
    public Iterator<E> iterator()
    {
        return new SkipListIterator<>(firstNode);
    }
    
    private class SkipListIterator<E> implements Iterator<E>
    {
        private Node<E> current;
        
        public SkipListIterator(Node<E> firstNode)
        {
            current = firstNode;
            
            while(current.down != null)
            {
                current = current.down;
            }
            
            current = current.next;
        }
        
        @Override
        public boolean hasNext()
        {  
            return (current.element != null);
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
    }
    
    @Override
    public int size()
    {
        return numElements;
    }
    
    @Override
    public void clear()
    {
        firstNode = null;
    }
    
    @Override
    public boolean contains(Object o)
    {
        Node<E> current = firstNode;
        
        while(current.down != null)
        {
            current = current.down;
        }
        
        while(current.next != null)
        {
            if(current.next.element != null)
            {
                if(o == current.next.element)
                {
                    System.out.println(o+" found!");
                    return true;
                }
                else
                {
                    current = current.next;
                }
            }
            else
            {
                System.out.println(o+" not found!");
                return false;
            }
            
        }
        return false;
    }
    
    protected class Node<E>
    {  
        public E element;
        public Node<E> next;
        public Node<E> down;

        public Node(E element)
        {  
            this.element = element;
            next = null;
            down = null;
        }
    }
    
    @Override
    public String toString()
    {
        if(firstNode == null)
        {
            return "[EMPTY]";
        }
        String output = "";
        int lvlCount = 0;
        Node<E> current = firstNode;
        
        if(numLevels == 0)
        {
            output += "Level "+(numLevels-lvlCount+1)+"\n";
            output += "[";
            output += current.element;
            
            if(current.next != null)
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
            
            output += "]\n\n";
            
            return output;
        }
        while(current.down != null)
        {
            if(lvlCount == 0)
            {
                output += "Level "+(numLevels-lvlCount+1)+"\n";
                output += "[";
                output += current.element;

                if(current.next != null)
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
                lvlCount++;
                output += "]\n\n";
            }
            
            current = firstNode;
            for(int i = 0; i < lvlCount; i++)
            {
                current = current.down;
            }          
            output += "Level "+(numLevels-lvlCount+1)+"\n";
            output += "[";
            output += current.element;

            if(current.next != null)
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
            lvlCount++;
            output += "]\n\n";
        }
        
        return output;
    }
    
    public static void main(String[] args)
    {
        System.out.println("===========SKIP LIST SET===========");
        SkipListSet<Integer> set = new SkipListSet();
        for(int i = 1; i <= 20; i++)
        {
            set.add(i);
        }
        
        System.out.println("Size of Skip list is "+set.size());
        System.out.println(set);
        
        System.out.println("ITERATE THROUGH ELEMENTS");
        Iterator<Integer> itr = set.iterator();
        while(itr.hasNext())
        {
            System.out.print(itr.next()+" ");
        }
        System.out.println("\n");
        
        System.out.println("REMOVE 12, 2, 0");
        System.out.println("REMOVING 12");
        set.remove(12);
        System.out.println(set);
        
        System.out.println("REMOVING 2");
        set.remove(2);
        System.out.println(set);
        
        if(!set.remove(0))
        {
            System.out.println("Didnt Remove 0, because not in the list");
        }
        
        System.out.println("REMOVE 19, 29, 12");
        System.out.println("REMOVING 19");
        set.remove(19);
        System.out.println(set);
        
        if(!set.remove(29))
        {
            System.out.println("Didnt Remove 29, because not in the list");
        }
        if(!set.remove(12))
        {
            System.out.println("Didnt Remove 12, because not in the list");
        }
        
        System.out.println("ADDING 2");
        set.add(2);
        System.out.println(set);
    } 
}
