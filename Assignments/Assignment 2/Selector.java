import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Grant Haislip (gzh0020@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-02-05
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
    * @param <T>     the general data type used
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
   
      //checks if collection is null or empty, comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException("must be a"
            + " collection that is not empty");
      }
     
      Iterator<T> itr = coll.iterator(); 
      T min = itr.next();
      
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, min) < 0) {
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
    * @param <T>     the general data type used
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      
      //checks if collection is null or empty, comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException("must be a"
            + " collection that is not empty");
      }
      Iterator<T> itr = coll.iterator();
      T max = itr.next();
      
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, max) > 0) {
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
    * @param <T>     the general data type used
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
     //checks if collection is null or empty, comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      //checks if collection is empty
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a"
            + " collection that is not empty");
      }
      
      //creates arraylist of collection and sorts it
      List<T> copyList = new ArrayList(coll);
      java.util.Collections.sort(copyList, comp);
      
      //checks if k < 0 or k > the array length
      if (k > copyList.size() || k <= 0) {
         throw new NoSuchElementException("k - 1 must be"
            + " a value within the array set");
      }
           
      //counts number of unique and duplicate numbers, removes duplicate
      int duplicateCount = 0;
      int uniqueTotal = 0;
      int originalTotal = copyList.size();
      for (int i = 0; i < copyList.size() - 1; i++) {
         while (copyList.size() > 1 && i < copyList.size() - 1
            && copyList.get(i) == copyList.get(i + 1)) {
            copyList.remove(i);
            duplicateCount++;
         }
      }
      
      uniqueTotal = originalTotal - duplicateCount;
      
      //if k is greater than the number of unique values
      if (k > uniqueTotal) {
         throw new NoSuchElementException("k must be"
               + " less than the unique number count");
      }
      
      return copyList.get(k - 1);
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
    * @param <T>     the general data type used
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
   
     //checks if collection is null or empty, comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      //checks if collection is empty
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a"
            + " collection that is not empty");
      }
      
      //creates arraylist of collection and sorts it
      List<T> copyList = new ArrayList(coll);
      java.util.Collections.sort(copyList, comp);
      
      //checks if k < 0 or k > the array length
      if (k > copyList.size() || k <= 0) {
         throw new NoSuchElementException("k - 1 must be"
            + " a value within the array set");
      }
      
      //counts number of unique and duplicate numbers, removes duplicates
      int duplicateCount = 0;
      int uniqueTotal = 0;
      int originalTotal = copyList.size();
      for (int i = 0; i < copyList.size() - 1; i++) {
         while (copyList.size() > 1 && i < copyList.size() - 1
            && copyList.get(i) == copyList.get(i + 1)) {
            copyList.remove(i);
            duplicateCount++;
         }
      }
      
      uniqueTotal = originalTotal - duplicateCount;
      
      //if k is greater than the total number of unique values
      if (k > uniqueTotal) {
         throw new NoSuchElementException("k must be"
               + " less than the unique number count");
      }
            
      return copyList.get(uniqueTotal - k);
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
    * @param <T>     the general data type used
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an"
            + " array of at least 1 integer");
      }
      
      //checks if collection is empty
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a"
            + " collection that is not empty");
      }
      
      //makes arraylist of the original and arraylist for the range
      //set j for number of qualifying values
      List<T> copyList = new ArrayList(coll);
      List<T> range = new ArrayList(coll);
      int j = 0;
      
      /*rewrite copy so that the values within
      the range are listed first in the array*/
      for (int i = 0; i < copyList.size(); i++) {
         if ((comp.compare(copyList.get(i), low) >= 0)
            && (comp.compare(copyList.get(i), high) <= 0)) {
            range.set(j, copyList.get(i));
            j++;
         }
      }
      
      //if no values in the arraylist fall within the range
      if (j == 0) {
         throw new NoSuchElementException("the collection must"
            + " have values within the range");
      }
      
      //delete extra values on end of range
      for (int i = range.size() - 1; i > j - 1; i--) {
         range.remove(i);
      }
      
      return range;
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
    * @param <T>     the general data type used
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
   
      //if a is null or has a length of 0
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be"
            + " an array of at least 1 integer"); 
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException("can't be an empty coll");
      }
      
      //finds maximum value
      Iterator<T> itr = coll.iterator();
      T ceiling = itr.next();
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, ceiling) > 0) {
               ceiling = element;
            }
         }
      
      }
      
      int change = 0;
      
      //finds the lowest value equal to or above the key
      for (T element: coll) {
         if ((comp.compare(element, key) >= 0)
            && (comp.compare(element, ceiling) <= 0)) {
            ceiling = element;
            change++;
         }
      }
      
      
      //if no qualifying value for ceiling
      if (change == 0) {
         throw new NoSuchElementException("there is"
            + " no qualifying value in the coll");
      }
      
      return ceiling;
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
    * @param <T>     the general data type used
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      //if a is null or has a length of 0
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be"
            + " an coll of at least 1 integer");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("can't be an exmpy coll");
      }
      
      
      //finds minimum value
      Iterator<T> itr = coll.iterator();
      T floor = itr.next();
      if (itr.hasNext()) {
      
         for (T element: coll) {
            if (comp.compare(element, floor) < 0) {
               floor = element;
            }
         }
      
      }
      
      
      int change = 0;
      
      //finds the lowest value equal to or above the key
      for (T element: coll) {
         if ((comp.compare(element, key) <= 0)
            && (comp.compare(element, floor) >= 0)) {
            floor = element;
            change++;
         }
      }
      
      
      //if no qualifying value for ceiling
      if (change == 0) {
         throw new NoSuchElementException("there is"
            + " no qualifying value in the coll");
      }
      
      return floor;
   }

}


