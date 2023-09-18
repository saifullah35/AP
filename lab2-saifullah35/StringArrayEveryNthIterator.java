import java.util.Iterator;

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
    
    public static void main(String args[]){
        String nthValue[] = { "This", "can", "not", "be", "an", "array", "of", "Strings" };

        System.out.println("Array n:");
        Iterator<String> nIter = new StringArrayEveryNthIterator(nthValue, 3);
        while (nIter.hasNext()) {
            System.out.println(nIter.next());
        }
    }
}