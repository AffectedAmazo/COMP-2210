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
   
      /*Node n = new Node(element);
   
      while(iterator().hasNext()) {
         T end = iterator().next();
      }
   
      Node last = new Node(end);
   
      end.next = n;
      n = end;
   
      size++;*/   
   }

   public T removeFirst() {
   
   
   
      return null;
   }

   public T removeLast() {
   
      return null;
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
