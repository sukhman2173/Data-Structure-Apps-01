//Sukhman Singh 18041216

package Question4;

import java.util.NoSuchElementException;

public interface QueueADT<E>
{
   public void enqueue(E element);
   
   public E dequeue() throws NoSuchElementException;
   
   public E first() throws NoSuchElementException;
   
   public boolean isEmpty();
   
   public int size();
}
