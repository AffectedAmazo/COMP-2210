import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class DoubleEndList<T> implements DoubleEndedList<T> {

   private Node front;
   private int size;
   
   public DoubleEndList() {
   
      front = null;
      size = 0;
   }

   public void addFirst(T element) {
   
      Node n = new Node(element);
      n.next = front;
      front = n;
      size++;  
   }

   public void addLast(T element) {
   
      Node n = new Node(element);
      T end = null;
   
      while(iterator().hasNext()) {
         end = iterator().next();
      }
   
      Node last = new Node(end);
   
      last.next = n;
      n = last;
      size++;
   }

   public T removeFirst() {
   
      if (size == 0) {
         return null;
      }
      T deleted = front.element;
      front = front.next;
      size--;
   
      return deleted;
   }

   public T removeLast() {
   
      if (size == 0) {
         return null;
      }
      
      T deleted = null;
      
      while(iterator().hasNext()) {
         deleted = iterator().next();
      }
      
      Node last = new Node(deleted);
      last = null;
      size--;
         
      return deleted;
   }

   public int size() {
   
      return size;
   }

   public Iterator<T> iterator() {
   
      return new Iteration(front, size);
   }

   public boolean isEmpty() {
   
      return size == 0;
   }
   
   private class Node {
   
      private T element;
      private Node next;
   
      public Node(T t) {
      
         element = t;
      }
   
      public Node(T t, Node n) {
      
         element = t;
         next = n;
      }
   
   }
   
   private class Iteration implements Iterator<T> {
   
      private Node current;
      private int count;
      private Node start;
      
      Iteration(Node n, int amount) {
      
         current = front;
         count = amount;
         start = n;
      }
   
      public T next() {
      
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.next;
         return result;
      }
   
      public boolean hasNext() {
      
         return current != null;
      }
   
      public void remove() {
      
         throw new UnsupportedOperationException();
      }
   }

}
