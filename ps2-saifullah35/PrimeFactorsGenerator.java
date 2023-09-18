import java.util.Iterator;
/**
   Generator to return the prime factors of a given integer.

   @author Jim Teresco
   @version Spring 2020
*/
public class PrimeFactorsGenerator implements Iterator<Integer> {

    /** the number to factor */
    private int n;

    /** the number after factors reported so far have been divided */
    
    private int remaining;

    /** next factor to return */
    private int nextFactor;

    /**
       Advance to the next factor not yet returned that divides the
       remaining part of the number.
    */
    private void advance() {

	while (remaining % nextFactor != 0 && nextFactor <= n) {
	    nextFactor++;
	}
    }

    /**
       Construct a generator for prime factors of the given number.

       @param n the number whose factors will be generated
    */
    public PrimeFactorsGenerator(int n) {

	this.n = n;
	remaining = n;
	nextFactor = 2;
	advance();
    }

    /**
       Return whether any factors remain to be returned.

       @return whether any factors remain to be returned
    */
    @Override
    public boolean hasNext() {

	return nextFactor <= n;
    }

    /**
       Return the next factor.

       @return the next factor
    */
    @Override
    public Integer next() {

	int retVal = nextFactor;
	remaining /= nextFactor;
	advance();
	return retVal; 
    }

    /**
       main method to test the generator.

       @param args[0] the number whose factors should be printed
    */
    public static void main(String args[]) {

	if (args.length != 1) {
	    System.err.println("Usage: java PrimeFactorsGenerator n");
	    System.exit(1);
	}

	// convert the command-line parameter to the number to factor
	int n = 0;
	try {
	    n = Integer.parseInt(args[0]);
	}
	catch (NumberFormatException e) {
	    System.err.println(e);
	    System.exit(1);
	}
	Iterator<Integer> iter = new PrimeFactorsGenerator(n);
	while (iter.hasNext()) {
	    System.out.println(iter.next());
	}
	
    }
}
