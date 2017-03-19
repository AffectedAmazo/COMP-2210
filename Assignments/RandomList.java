import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * RandomList.java - This program implements the RandomizedList interface
 * and uses type variables to create a personalized collection.
 * 
 * @author   Grant Haislip (gzh0020@auburn.edu)
 * @version  3/18/2017
 * @param <T> is the type variable of this program.
 */
public class RandomList<T> implements RandomizedList<T> {
   private T[] elements;
   private int size;
   private static final int DEFAULT_LENGTH = 1;
   
   /**
   * Creates a RandomList object.
   */
   @SuppressWarnings("unchecked")
   public RandomList() {
   
      size = 0;
      elements = (T[]) new Object[DEFAULT_LENGTH];
   }
   
   /**
   * Creates an iterator for the list.
   * @return itr is the iterator.
   */
   public Iterator<T> iterator() {
      
      Iteration itr = new Iteration(elements, size());
      return itr;
   }

   /**
   * Adds an element to the list.
   * @param element is the added element.
   */
   public void add(T element) {
   
      if (element == null) {
         throw new IllegalArgumentException("element can't be null");
      }
      
      if (size == elements.length) {
         resize(elements.length * 2);
      }
   
      elements[size()] = element;
      size++;
   }

   /**
   * removes a random element from the list.
   * @return deleted is the removed element.
   */
   public T remove() {
   
      if (size() == 0) {
         return null;
      }
        
      int r = new Random().nextInt(size());
      
      T deleted = elements[r];
      elements[r] = elements[size() - 1];
      elements[size() - 1] = null;
      size--;
      
      if (size() > 0 && size() < elements.length / 4) {
         resize(elements.length / 2);
      }
      
      return deleted;
   }

   /**
   * returns a random element from the list.
   * @return the random element.
   */
   public T sample() {
   
      if (size == 0) {
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
   
      return size;
   }
   
   /**
   * checks if the list is empty.
   * @return boolean for if list is empty.
   */
   public boolean isEmpty() {
   
      return size == 0;
   }
   
   /**
   * resizes elements[] for dynamic memory usage.
   * @param capacity is the new capacity of the array.
   */
   @SuppressWarnings("unchecked")
   private void resize(int capacity) {
   
      T[] array = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i++) {
         array[i] = elements[i];
      }
      elements = array;
   }
      
   /**
   * Defines the behavior of the iterator of RandomList.
   */
   private class Iteration implements Iterator<T> {
      private T[] items;
      private int count;
      private int current;
      
      /**
      * creates special iterator, "Iteration," for this program.
      */
      Iteration(T[] array, int amount) {
      
         items = array;
         count = amount;
         current = 0;
      }
   
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
         return items[current++];
      }
      
      /**
      * gets overrided :'(.
      */
      public void remove() {
      
         throw new UnsupportedOperationException();
      }
   
   }
}