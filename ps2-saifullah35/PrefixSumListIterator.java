import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
   An iterator to computes and returns prefix sums over a List
   of int values using an Iterator

   @author Saif Ullah and Jonathan Masih
   @version Spring 2020
*/
public class PrefixSumListIterator implements java.util.Iterator<Integer> {

    /** the array over which we are iterating */
    private Iterator<Integer> nums;

    /** the sum of elements before array[current] */
    private int prefixSum;


   /**
      Construct a new prefix sum iterator over the given List

      @param a the array containing the values
   */
   public PrefixSumListIterator(List<Integer> a) {
    nums =  a.iterator();
    prefixSum = 0;

     
   }

 /**
       Return whether there are more elements that have not yet been
       part of the prefix sum sequence.

       @return whether there are more elements that have not yet been
       part of the prefix sum sequence
    */
    @Override
    public boolean hasNext() {

	return nums.hasNext();
    }

    /**
       Return the next value in the prefix sum sequence.

       @return the next value in the prefix sum sequence
    */
    @Override
    public Integer next() {

	prefixSum += nums.next();
	return prefixSum;
    }
    

   public static void main(String args[]) {

	if (args.length != 2) {
	    System.err.println("Usage: java PrefixSumArrayIterator size range");
	    System.exit(1);
	}
	// convert the command-line parameters to the numbers needed
	int n = 0;
	int range = 0;
	try {
	    n = Integer.parseInt(args[0]);
	    range = Integer.parseInt(args[1]);
	}
	catch (NumberFormatException e) {
	    System.err.println(e);
	    System.exit(1);
	}

	// create and populate The list
	java.util.Random r = new java.util.Random();
    List <Integer> alist =new LinkedList<Integer>();
    List <Integer> blist =new ArrayList<Integer>();
    
	for (int i = 0; i < n; i++) {
	    alist.add(r.nextInt(range));
        blist.add(r.nextInt(range));
	}

    
	// print the array
	System.out.println("Generated array for LinkedList: " + alist.toString());
    System.out.println("Generated array for ArrayList: " + blist.toString());

	// print the prefix sums
	System.out.println("Prefix sums:");
	java.util.Iterator<Integer> iter = new PrefixSumListIterator(alist);
	while (iter.hasNext()) {
	    System.out.println(iter.next());   
	}
    
    System.out.println("Prefix sums:");
    java.util.Iterator<Integer> iter2 = new PrefixSumListIterator(blist);
    while (iter2.hasNext()) {
        System.out.println(iter2.next());

    }
}
}
