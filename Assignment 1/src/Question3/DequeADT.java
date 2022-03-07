//Sukhman Singh 18041216

package Question3;

    import java.util.Iterator;

    interface DequeADT<E> 
    {
        public void enqueueRear(E element);

        public void enqueueFront(E element);

        public E dequeueRear();

        public E dequeueFront();

        public E first();

        public E last();

        public boolean isEmpty();

        public int size();

        public void clear();

        public Iterator<E> iterator();
    }
