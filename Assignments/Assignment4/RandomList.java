import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * RandomList.java - This program implements the RandomizedList interface
 * and uses type variables to create a personalized collection.
 * 
 * @author   Grant Haislip (gzh0020@auburn.edu)
 * @version  3/15/2017
 * @param <T> is the type variable of this program.
 */
public class RandomList<T> implements RandomizedList<T> {
   private T[] elements;
   
   /**
   * Creates a RandomList object.
   */
   @SuppressWarnings("unchecked")
   public RandomList() {
   
      elements = (T[]) new Object[size()];
   }
   
   /**
   * Creates an iterator for the list.
   * @return Iteration() is the iterator.
   */
   public Iterator<T> iterator() {
      
      return new Iteration();
   }

   /**
   * Adds an element to the list.
   * @param element is the added element.
   */
   public void add(T element) {
   
      if (element == null) {
         throw new IllegalArgumentException("element can't be null");
      }
   
      elements[size()] = element;
   }

   /**
   * removes a random element from the list.
   * @return deleted is the removed element.
   */
   public T remove() {
        
      T deleted = sample();
      
      deleted = elements[size() - 1];
      elements[size() - 1] = null;
      
      return deleted;
   }

   /**
   * returns a random element from the list.
   * @return the random element.
   */
   public T sample() {
   
      if (elements.length == 0) {
         return null;
      }
      
      int r = new Random().nextInt(size());
      return elements[r];
   }
   
   /**
   * returns the size of the list.
   * @return size is the size.
   */
   public int size() {
      int size = 0;
   
      while (iterator().hasNext()) {
         size++;
      }
   
      return size;
   }
   
   /**
   * checks if the list is empty.
   * @return boolean for if list is empty.
   */
   public boolean isEmpty() {
   
      return iterator().hasNext();
   }
      
   /**
   * Defines the behavior of the iterator of RandomList.
   */
   private class Iteration implements Iterator<T> {
      private int current;
   
      /**
      * checks if the iterator has a next element.
      * @return if it does.
      */
      public boolean hasNext() {
      
         return (current < size());
      }
      
      /**
      * returns next element of the list.
      * @return the next element.
      */
      public T next() {
      
         if	(!hasNext()) {
            throw new NoSuchElementException();
         }
         return elements[current++];
      }
      
      /**
      * gets overrided :'(.
      */
      public void remove() {
      
         throw new UnsupportedOperationException();
      }
   
   }
}
