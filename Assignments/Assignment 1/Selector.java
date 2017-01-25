import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Grant Haislip (gzh0020@tigermail.auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-01-24
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      int min = a[0];
   
      for(int i = 1; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      int max = a[0];
   
      for(int i = 1; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
   
      //if a is null or has a length of 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      
      //if k < 0 or k > the array length
      if (k > a.length || k <= 0) {
         throw new IllegalArgumentException("k - 1 must be a value within the array set");
      }
   
      Arrays.sort(a);
      
      //counts number of unique numbers
      int duplicateCount = 0;
      int uniqueTotal = 0;
      for (int i = 0; i < a.length - 1; i++) {
         if (a[i] == a[i + 1]) {
            duplicateCount++;
         }
      }
      
      uniqueTotal = a.length - duplicateCount;
      
      if (k > uniqueTotal) {
         throw new IllegalArgumentException("k must be less than the unique number count");
      }
      
      //creates copy array with only unique numbers of length uniqueTotal
      int j = 0;
      int i = 1;
      int[] b = Arrays.copyOf(a, a.length);
   
      while (i < b.length) {
         if (b[i] == b[j]) {
            i++;
         } 
         else {
            j++;
            b[j] = b[i];
            i++;
         }
      }
   
      int kmin = b[k - 1];
   
      return kmin;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
   
      //if a is null or has a length of 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      
      //if k < 0 or k > the array length
      if (k > a.length || k <= 0) {
         throw new IllegalArgumentException("k - 1 must be a value within the array set");
      }
   
      Arrays.sort(a);
      
      //count number of unique numbers
      int duplicateCount = 0;
      int uniqueTotal = 0;
      for (int i = 0; i < a.length - 1; i++) {
         if (a[i] == a[i + 1]) {
            duplicateCount++;
         }
      }
      
      uniqueTotal = a.length - duplicateCount;
      
      //if k is greater than the number of unique values
      if (k > uniqueTotal) {
         throw new IllegalArgumentException("k must be less than the unique number count");
      }
      
      //creates array with only unique numbers of length uniqueTotal
      int j = 0;
      int i = 1;
      int[] b = Arrays.copyOf(a, a.length);
      while (i < b.length) {
         if (b[i] == b[j]) {
            i++;
         } 
         else {
            j++;
            b[j] = b[i];
            i++;
         }
      }
   
      int kmax = b[uniqueTotal - k];
   
      return kmax;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
   
      //if a is null or has a length of 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
   
      //make copy of the original
      int[] b = Arrays.copyOf(a, a.length);
      int j = 0;
      
      //rewrite copy so that the values within the range are listed first in the array
      for(int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high)
         {
            b[j] = b[i];
            j++;
         }
      }
      
      //if no values in the array fall within the range
      if (j == 0) {
         int[] c = {};
         return c;
      }
      //if values fall within the range
      else {
         int[] d = Arrays.copyOf(b, j);
         return d;
      }
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
   
      int ceiling = 0;
      int change = 0;
   
      //if a is null or has a length of 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      
      //loop used to initiate the ceiling value as above the max of the array
      for (int i = 0; i < a.length; i++) {
         ceiling += Math.abs(a[i]);
      }
      
      //finds the lowest value equal to or above the key
      for (int i = 0; i < a.length; i++){
         if (a[i] >= key && a[i] <= ceiling) {
            ceiling = a[i];
            change++;
         }
      }
      
      //if no qualifying value for ceiling
      if (change == 0) {
         throw new IllegalArgumentException("there is no qualifying value in the array");
      }
      
      return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      
      int floor = 0;
      int change = 0;
   
      //if a is null or has a length of 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an array of at least 1 integer");
      }
      
      //loop used to initiate the ceiling value as above the max of the array
      for (int i = 0; i < a.length; i++) {
         floor -= Math.abs(a[i]);
      }
      
      //finds the highest value equal to or above the key
      for (int i = 0; i < a.length; i++){
         if (a[i] <= key && a[i] >= floor) {
            floor = a[i];
            change++;
         }
      }
      
      //if no qualifying value for floor
      if (change == 0) {
         throw new IllegalArgumentException("there is no qualifying value in the array");
      }
      
      return floor;
   
   }

}
