import java.util.Iterator;

/**
 * Iterator over an array of String that returns only those elements at
 * even-indexed positions.
 * 
 * @author Jim Teresco
 * @version Spring 2020
 * 
 */

public class StringArrayEvensIterator implements Iterator<String> {

    /*
     * instance variables to remember the array and the next index to be
     * returned
     */
    private String[] array;
    private int current;

    /**
     * Construct a new iterator over the given array of Strings.
     * 
     * @param a the array of Strings over which this iterator will operate
     */
    public StringArrayEvensIterator(String a[]) {

        array = a;
        current = 0;
    }

    /**
     * Check if the iterator has more Strings to return.
     * 
     * @return whether the iterator has more Strings to return
     */
    public boolean hasNext() {

        return current < array.length;
    }

    /**
     * Return the next even-indexed String from the array not yet returned.
     * 
     * @return the next even-indexed String from the array not yet returned
     */
    public String next() {

        String value = array[current];
        current += 2;
        return value;
    }

    public class StringArrayEveryNthIterator implements Iterator<String> {

        /*
         * instance variables to remember the array and the next index to be
         * returned
         */
        private String[] array;
        private int current;
        private int n;

        /**
         * Construct a new iterator over the given array of Strings.
         * 
         * @param a the array of Strings over which this iterator will operate
         */
        public StringArrayEveryNthIterator(String a[], int n) {

            array = a;
            current = 0;
            this.n = n;
        }

        /**
         * Check if the iterator has more Strings to return.
         * 
         * @return whether the iterator has more Strings to return
         */
        public boolean hasNext() {

            return current < array.length;
        }

        /**
         * Return the next nth-indexed String from the array not yet returned.
         * 
         * @return the next nth-indexed String from the array not yet returned
         */
        public String next() {

            String value = array[current];
            current += n;
            return value;
        }
    }

    /**
     * main method implementing a few test cases for this iterator.
     * 
     * @param args not used
     */
    public static void main(String args[]) {

        String a[] = { "This", "is", "an", "array", "of", "Strings" };
        String b[] = {};
        String c[] = new String[100];
        String nthValue[] = { "This", "can", "not", "be", "an", "array", "of", "Strings" };

        // fill c with String representations of the numbers 0-99
        for (int i = 0; i < c.length; i++) {
            c[i] = "" + i;
        }

        // Iterate over each with our iterators
        System.out.println("Array a:");
        Iterator<String> aIter = new StringArrayEvensIterator(a);
        while (aIter.hasNext()) {
            System.out.println(aIter.next());
        }

        System.out.println("Array b:");
        Iterator<String> bIter = new StringArrayEvensIterator(b);
        while (bIter.hasNext()) {
            System.out.println(bIter.next());
        }

        System.out.println("Array c:");
        Iterator<String> cIter = new StringArrayEvensIterator(c);
        while (cIter.hasNext()) {
            System.out.println(cIter.next());
        }

        System.out.println("Array n:");
        Iterator<String> nIter = new StringArrayEveryNthIterator(nthValue, 3);
        while (nIter.hasNext()) {
            System.out.println(nIter.next());
        }
    }

}
