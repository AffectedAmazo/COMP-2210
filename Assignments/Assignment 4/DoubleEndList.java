import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndList<T> implements DoubleEndedList<T> {

   private Node front;
   private Node last;
   private int size;
   
   public DoubleEndList() {
   
      front = null;
      size = 0;
   }

   public void addFirst(T element) {
   
      if (element == null) {
         throw new IllegalArgumentException();
      }
   
      Node n = new Node(element);
      
      if (size() == 0) {
      
         front = n;
         last = n;
      }
      
      else {
         n.next = front;
         front = n;
      }
      
      size++;  
   }

   public void addLast(T element) {
   
      if (element == null) {
         throw new IllegalArgumentException();
      }
   
      Node n = new Node(element);
      n.element = element;
      
      if (size == 0) {
         front = n;
         last = n;
      }
      
      else {
         last.next = n;
         last = n;
      }
      
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
      
      else if (size == 1) {
      
         T deleted = front.element;
         front = null;
         last = null;
         size--;
         return deleted;
      }
      
      else {
         Node n = front;
      
         while (n.next.next != null) {
            n = n.next;
         }
      
      
         T deleted = n.next.element;
         n.next = null;
         last = n;
         size--;
         return deleted;
      }
      
   }

   public int size() {
   
      return size;
   }

   public Iterator<T> iterator() {
   
      return new Iteration();
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
   
      private Node current = front;
   
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

