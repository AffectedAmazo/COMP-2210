import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Your Name (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-01-30
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
   
      Iterator<T> itr = coll.iterator(); 
   
      if (coll == null || coll.isEmpty()) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      T min = itr.next();
      
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, min) < 0)
            {
               min = element;
            }
         }
      
      }
      
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      Iterator<T> itr = coll.iterator(); 
   
      if (coll == null || coll.isEmpty()) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      T max = itr.next();
      
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, max) > 0)
            {
               max = element;
            }
         }
      
      }
      
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
   //creates iterator
      Iterator<T> itr = coll.iterator();
   
   //checks if collection is null or empty
      if (coll == null || coll.isEmpty()) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      //creates arraylist of collection and sorts it
      List<T> copyList = new ArrayList(coll);
      java.util.Collections.sort(copyList, comp);
      
      //if k < 0 or k > the array length
      if (k > copyList.size() || k <= 0) {
         throw new IllegalArgumentException("k - 1 must be"
            + " a value within the array set");
      }
      
      //counts number of unique numbers
      int duplicateCount = 0;
      int uniqueTotal = 0;
      
      T start = itr.next();
      
      for (T element: coll) {
         T current = itr.next();
         if (comp.compare(start, current) == 0) {
            duplicateCount++;
         }
      }
      
      uniqueTotal = copyList.size() - duplicateCount;
      
      if (k > uniqueTotal) {
         throw new IllegalArgumentException("k must be"
            + " less than the unique number count");
      }
      
      //creates copy array with only unique numbers of length uniqueTotal
      /*int[] c = Arrays.copyOf(b, b.length);
      int j = 0;
      int i = 1;
      
   
      while (i < c.length) {
         if (c[i] == c[j]) {
            i++;
         } 
         else {
            j++;
            c[j] = c[i];
            i++;
         }
      }
   
      int kmin = c[k - 1];*/
   
      return null;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      return null;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      return null;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      return null;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      return null;
   }

}
