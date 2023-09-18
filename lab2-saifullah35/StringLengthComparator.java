import java.util.Comparator;

/**
   A Comparator that orders String values by length.

   @author Jim Teresco
   @version Spring 2020
*/

public class StringLengthComparator implements Comparator<String> {

    /**
       Compare two String values by length.  Returns a negative value
       if the first is shorter than the second, 0 if they are the same
       lengths, and a positive value if the first is longer than the
       second.

       @param a the first String
       @param b the second String

       @return a negative value if a is shorter than b, 0 if they are
       the same lengths, and a positive value if a is longer than b
    */
    public int compare(String a, String b) {

	return a.length() - b.length();
    }

    /**
       A main method to compare some Strings by this Comparator

       @param args not used
    */
    public static void main(String args[]) {

	StringLengthComparator c = new StringLengthComparator();
	System.out.println("compare(dog,cat): " + c.compare("dog", "cat"));
	System.out.println("compare(short,lengthy): " + c.compare("short", "lengthy"));
	System.out.println("compare(,something): " + c.compare("", "something"));
	System.out.println("compare(something,): " + c.compare("something", ""));
	System.out.println("compare(lengthy,short): " + c.compare("lengthy", "short"));
    }
}
