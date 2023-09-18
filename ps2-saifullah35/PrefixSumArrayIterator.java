/**
   An iterator to computes and returns prefix sums over an array
   of int values.

   @author Jim Teresco
   @version Spring 2020
*/

public class PrefixSumArrayIterator implements java.util.Iterator<Integer> {

    /** the array over which we are iterating */
    private int[] array;

    /** the index of the next value to include in the prefix sum */
    private int current;

    /** the sum of elements before array[current] */
    private int prefixSum;

    /**
       Construct a new prefix sum iterator over the given array.

       @param a the array containing the values
    */
    public PrefixSumArrayIterator(int[] a) {

	array = a;
	current = 0;
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

	return current < array.length;
    }

    /**
       Return the next value in the prefix sum sequence.

       @return the next value in the prefix sum sequence
    */
    @Override
    public Integer next() {

	prefixSum += array[current];
	current++;
	return prefixSum;
    }

    /**
       main method to test the PrefixSumArrayIterator.

       @param args[0] size of array to generate
       @param args[1] range of random values
    */
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

	// create and populate the array
	java.util.Random r = new java.util.Random();
	int a[] = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = r.nextInt(range);
	}

	// print the array
	System.out.println("Generated array: " + java.util.Arrays.toString(a));

	// print the prefix sums
	System.out.println("Prefix sums:");
	java.util.Iterator<Integer> iter = new PrefixSumArrayIterator(a);
	while (iter.hasNext()) {
	    System.out.println(iter.next());
	}
    }
}
