import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomList<T> implements RandomizedList<T> {
   private T[] elements;

   public void add(T element) {
   
      if (element == null) {
         throw new IllegalArgumentException("element can't be null");
      }
   
      elements.add(element); 
   
   }

   public T remove() {
   
      if (elements.length == 0) {
         return null;
      }
   
      int r = new Random().nextInt(elements.length);
      
      
   
   }
   
   private static int sizeOf() {
      int size = 0;
   
      Iteration itr = new Iteration(elements);
      while (itr.hasNext()) {
         size++;
      }
   
      return size;
   }

   public T sample() {
   
   }
   
   //public Iterator<T> iterator() {
   
   //}
      
   
   private class Iteration<T> implements Iterator<T> {
     
      private Iterator<T> iterator() {
      
      }
   
      private boolean hasNext() {
      
      }
   
      private boolean isEmpty() {
      
      }
   
      private int size() {
      
      }
   }
}