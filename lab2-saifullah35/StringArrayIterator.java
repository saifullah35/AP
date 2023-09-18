import java.util.Iterator;

/**
   Iterator over an array of String.

   @author Jim Teresco
   @version Spring 2020

*/

public class StringArrayIterator implements Iterator<String> {

    /* instance variables to remember the array and the next index to be 
       returned */
    private String[] array;
    private int current;

    /**
       Construct a new iterator over the given array of Strings.

       @param a the array of Strings over which this iterator will operate
    */
    public StringArrayIterator(String a[]) {

	array = a;
	current = 0;
    }

    /**
       Check if the iterator has more Strings to return.

       @return whether the iterator has more Strings to return
    */
    public boolean hasNext() {

	return current < array.length;
    }

    /**
       Return the next String from the array not yet returned.

       @return the next String from the array not yet returned
    */
    public String next() {

	return array[current++];
    }

    /**
       main method implementing a few test cases for this iterator.

       @param args not used
    */
    public static void main(String args[]) {

	String a[] = { "This", "is", "an", "array", "of", "Strings" };
	String b[] = {};
	String c[] = new String[100];

	// fill c with String representations of the numbers 0-99
	for (int i = 0; i < c.length; i++) {
	    c[i] = ""+i;
	}

	// Iterate over each with our iterators
	System.out.println("Array a:");
	Iterator<String> aIter = new StringArrayIterator(a);
	while (aIter.hasNext()) {
	    System.out.println(aIter.next());
	}
	
	System.out.println("Array b:");
	Iterator<String> bIter = new StringArrayIterator(b);
	while (bIter.hasNext()) {
	    System.out.println(bIter.next());
	}
	
	System.out.println("Array c:");
	Iterator<String> cIter = new StringArrayIterator(c);
	while (cIter.hasNext()) {
	    System.out.println(cIter.next());
	}
	
    }
}
