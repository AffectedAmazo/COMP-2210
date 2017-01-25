import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   @Test (expected = IllegalArgumentException.class) public void minIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      
      Selector.min(a);
      Selector.min(b);
   }
   
   @Test public void minBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {2,1};
      int[] d = {1,2,3};
      int[] e = {3,2,1};
   
      Assert.assertEquals(1, Selector.min(a));
      Assert.assertEquals(1, Selector.min(b));
      Assert.assertEquals(1, Selector.min(c));
      Assert.assertEquals(1, Selector.min(d));
      Assert.assertEquals(1, Selector.min(e));
   }

   @Test public void minTypicalCases() {
   
      int[] a = {2,1,3};
      int[] b = {2,3,1,4};
      int[] c = {2,3,1,4,5};
   
      Assert.assertEquals(1, Selector.min(a));
      Assert.assertEquals(1, Selector.min(b));
      Assert.assertEquals(1, Selector.min(c));
   }
   
   @Test public void minSpecialCases() {
   
      int[] a = {1,1};
      int[] b = {1,1,2};
      int[] c = {2,1,1};
      int[] d = {2,1,3,4,1};
      
      Assert.assertEquals(1, Selector.min(a));
      Assert.assertEquals(1, Selector.min(b));
      Assert.assertEquals(1, Selector.min(c));
      Assert.assertEquals(1, Selector.min(d));
   }
   
   @Test (expected = IllegalArgumentException.class) public void maxIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      
      Selector.max(a);
      Selector.max(b);
   }
   
   @Test public void maxBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {2,1};
      int[] d = {1,2,3};
      int[] e = {3,2,1};
   
      Assert.assertEquals(1, Selector.max(a));
      Assert.assertEquals(2, Selector.max(b));
      Assert.assertEquals(2, Selector.max(c));
      Assert.assertEquals(3, Selector.max(d));
      Assert.assertEquals(3, Selector.max(e));
   }
   
   @Test public void maxTypicalCases() {
   
      int[] a = {2,3,1};
      int[] b = {2,1,4,3};
      int[] c = {2,3,5,1,4};
   
      Assert.assertEquals(3, Selector.max(a));
      Assert.assertEquals(4, Selector.max(b));
      Assert.assertEquals(5, Selector.max(c));
   }
   
   @Test public void maxSpecialCases() {
   
      int[] a = {1,1};
      int[] b = {2,2,1};
      int[] c = {1,2,2};
      int[] d = {2,4,3,1,4};
      
      Assert.assertEquals(1, Selector.max(a));
      Assert.assertEquals(2, Selector.max(b));
      Assert.assertEquals(2, Selector.max(c));
      Assert.assertEquals(4, Selector.max(d));
   }
   
   @Test (expected = IllegalArgumentException.class) public void kminIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      int[] c = {1,2,3};
      int[] d = {1,1,1,2};
      
      Selector.kmin(a, 1);
      Selector.kmin(b, 1);
      Selector.kmin(c, 4);
      Selector.kmin(c, 0);
      Selector.kmin(d, 3);
   }
   
   @Test public void kminBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {1,2,3};
   
      Assert.assertEquals(1, Selector.kmin(a, 1));
   
      Assert.assertEquals(1, Selector.kmin(b, 1));
      Assert.assertEquals(2, Selector.kmin(b, 2));
   
      Assert.assertEquals(1, Selector.kmin(c, 1));
      Assert.assertEquals(3, Selector.kmin(c, 3));
   }
   
   @Test public void kminTypicalCases() {
   
      int[] a = {1,2,3};
      int[] b = {1,2,3,4};
      int[] c = {1,2,3,4,5};
   
      Assert.assertEquals(2, Selector.kmin(a, 2));
      Assert.assertEquals(3, Selector.kmin(b, 3));
      Assert.assertEquals(4, Selector.kmin(c, 4));
   }
   
   @Test public void kminSpecialCases() {
      int[] a = {3,1,2};
      int[] b = {1,1,2};
      int[] c = {1,1,2,2};
      int[] d = {1,1,2,3,3};
   
      Assert.assertEquals(1, Selector.kmin(a, 1));
   
      Assert.assertEquals(1, Selector.kmin(b, 1));
      Assert.assertEquals(2, Selector.kmin(b, 2));
   
      Assert.assertEquals(1, Selector.kmin(c, 1));
      Assert.assertEquals(2, Selector.kmin(c, 2));
   
      Assert.assertEquals(2, Selector.kmin(d, 2));
      Assert.assertEquals(3, Selector.kmin(d, 3));
   }
   
   @Test (expected = IllegalArgumentException.class) public void kmaxIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      int[] c = {1,2,3};
      int[] d = {1,1,1,2};
      
      Selector.kmax(a, 1);
      Selector.kmax(b, 1);
      Selector.kmax(c, 4);
      Selector.kmax(d, 3);
   }
   
   @Test public void kmaxBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {1,2,3};
   
      Assert.assertEquals(1, Selector.kmax(a, 1));
   
      Assert.assertEquals(2, Selector.kmax(b, 1));
      Assert.assertEquals(1, Selector.kmax(b, 2));
   
      Assert.assertEquals(3, Selector.kmax(c, 1));
      Assert.assertEquals(1, Selector.kmax(c, 3));
   }
   
   @Test public void kmaxTypicalCases() {
   
      int[] a = {1,2,3};
      int[] b = {1,2,3,4};
      int[] c = {1,2,3,4,5};
   
      Assert.assertEquals(2, Selector.kmax(a, 2));
      Assert.assertEquals(3, Selector.kmax(b, 2));
      Assert.assertEquals(4, Selector.kmax(c, 2));
   }
   
   @Test public void kmaxSpecialCases() {
      int[] a = {3,1,2};
      int[] b = {1,1,2};
      int[] c = {1,1,2,2};
      int[] d = {1,1,2,3,3};
   
      Assert.assertEquals(3, Selector.kmax(a, 1));
   
      Assert.assertEquals(2, Selector.kmax(b, 1));
      Assert.assertEquals(1, Selector.kmax(b, 2));
   
      Assert.assertEquals(2, Selector.kmax(c, 1));
      Assert.assertEquals(1, Selector.kmax(c, 2));
   
      Assert.assertEquals(2, Selector.kmax(d, 2));
      Assert.assertEquals(1, Selector.kmax(d, 3));
   }
   
   @Test (expected = IllegalArgumentException.class) public void rangeIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      
      Selector.range(a, 1, 3);
      Selector.range(b, 1, 3);
   }
   
   @Test public void rangeBoundaryCases() {
      int[] a = {1};
      int[] aAnswer = {1};
      int[] b = {1,2,3};
      int[] bAnswer = {1,2,3};
   
      Assert.assertEquals(aAnswer[0], Selector.range(a, 0, 1)[0]);
      Assert.assertEquals(aAnswer.length, Selector.range(a, 0, 1).length);
   
      Assert.assertEquals(bAnswer[0], Selector.range(b, 1, 3)[0]);
      Assert.assertEquals(bAnswer[1], Selector.range(b, 1, 3)[1]);
      Assert.assertEquals(bAnswer[2], Selector.range(b, 1, 3)[2]);
      Assert.assertEquals(bAnswer.length, Selector.range(b, 1, 3).length);
   }
   
   @Test public void rangeTypicalCases() {
   
      int[] a = {1,2,4,5,6};
      int[] aAnswer = {4,5,6};
   
      Assert.assertEquals(aAnswer[0], Selector.range(a, 3, 7)[0]);
      Assert.assertEquals(aAnswer[1], Selector.range(a, 3, 7)[1]);
      Assert.assertEquals(aAnswer[2], Selector.range(a, 3, 7)[2]);
      Assert.assertEquals(aAnswer.length, Selector.range(a, 3, 7).length);
      
   }
   
   @Test public void rangeSpecialCases() {
   
      int[] a = {1,2,3};
      int[] aAnswer = {};
      
      int[] b = {1,2,2,3};
      int[] bAnswer = {2,2,3};
      
      int[] c = {1,2,3,4};
      int[] cAnswer = {3};
   
      Assert.assertEquals(aAnswer.length, Selector.range(a, 5, 8).length);
      
      Assert.assertEquals(bAnswer[0], Selector.range(b, 2, 4)[0]);
      Assert.assertEquals(bAnswer[1], Selector.range(b, 2, 4)[1]);
      Assert.assertEquals(bAnswer[2], Selector.range(b, 2, 4)[2]);
      Assert.assertEquals(bAnswer.length, Selector.range(b, 2, 4).length);
      
      Assert.assertEquals(cAnswer[0], Selector.range(c, 3, 3)[0]);
      Assert.assertEquals(cAnswer.length, Selector.range(c, 3, 3).length);
   }

   @Test (expected = IllegalArgumentException.class) public void ceilingIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      
      Selector.ceiling(a, 1);
      Selector.ceiling(b, 1);
   }

   @Test public void ceilingBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {1,2};
   
      Assert.assertEquals(1, Selector.ceiling(a, 1));
      Assert.assertEquals(2, Selector.ceiling(b, 2));
      Assert.assertEquals(1, Selector.ceiling(c, 0));
   }

   @Test public void ceilingTypicalCases() {
   
      int[] a = {1,2,3};
      int[] b = {1,2,3,4};
      int[] c = {1,2,3,4,5};
   
      Assert.assertEquals(2, Selector.ceiling(a, 2));
      Assert.assertEquals(3, Selector.ceiling(b, 3));
      Assert.assertEquals(4, Selector.ceiling(c, 4));
   }

   @Test public void ceilingSpecialCases() {
   
      int[] a = {1,1,1};
      int[] b = {3,2,4,1};
      int[] c = {1,2,2,3};
   
      Assert.assertEquals(1, Selector.ceiling(a, 1));
      Assert.assertEquals(4, Selector.ceiling(b, 4));
      Assert.assertEquals(2, Selector.ceiling(c, 2));
   }
   
   @Test (expected = IllegalArgumentException.class) public void floorIllegalCases() {
   
      int[] a = null;
      int[] b = {};
      
      Selector.floor(a, 1);
      Selector.floor(b, 1);
   }

   @Test public void floorBoundaryCases() {
   
      int[] a = {1};
      int[] b = {1,2};
      int[] c = {1,2};
   
      Assert.assertEquals(1, Selector.floor(a, 1));
      Assert.assertEquals(2, Selector.floor(b, 2));
      Assert.assertEquals(2, Selector.floor(c, 3));
   }

   @Test public void floorTypicalCases() {
   
      int[] a = {1,2,3};
      int[] b = {1,2,3,4};
      int[] c = {1,2,3,4,5};
   
      Assert.assertEquals(2, Selector.floor(a, 2));
      Assert.assertEquals(3, Selector.floor(b, 3));
      Assert.assertEquals(5, Selector.floor(c, 8));
   }

   @Test public void floorSpecialCases() {
   
      int[] a = {1,1,1};
      int[] b = {3,2,4,1};
      int[] c = {1,2,2,3};
   
      Assert.assertEquals(1, Selector.floor(a, 1));
      Assert.assertEquals(4, Selector.floor(b, 4));
      Assert.assertEquals(2, Selector.floor(c, 2));
   }

}
