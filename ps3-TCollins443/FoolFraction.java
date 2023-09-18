import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/**
 * 
 * Finding percentage of false news stories
 * 
 * @author Trevor Collins, Saif Ullah, Jonathan Masih
 * @version 2/20/22 
 */

public class FoolFraction{

/**
 * Method calculates percentage of fake stories by counting 
 * the total number of stories and dividing the number of true stories
 * by the total number of stories.
 * 
 * @param iter
 * @return
 */    
public static double foolFraction(Iterator<Boolean> iter){
    double countT = 0.0;
    double countF = 0.0;
    double sumStories = 0.0;
    while(iter.hasNext()){
        if(iter.next() == true){
            countT++;
        }else{
            countF++;
        }
    }
    sumStories = countF + countT;

    return countT / sumStories;
}

public static void main(String[] args) {
    List<Boolean> iterList = new LinkedList<Boolean>(); 
    List<Boolean> iterList2 = new LinkedList<Boolean>();
    List<Boolean> iterList3 = new LinkedList<Boolean>();

    /**
     * Adding list to test
     */


    iterList.add(true);
    iterList.add(true);
    iterList.add(true);
    iterList.add(false);
    iterList.add(false);

    Iterator<Boolean> testIter = iterList.iterator();

    System.out.println("Testing with 3 true's and 2 false: ");
    System.out.println("Result: " + foolFraction(testIter));

    iterList2.add(true);
    iterList2.add(true);
    iterList2.add(false);
    iterList2.add(false);
    iterList2.add(false);

    Iterator<Boolean> testIter2 = iterList2.iterator();

    System.out.println("Testing with 3 false and 2 true: ");
    System.out.println("Result: " + foolFraction(testIter2));

    iterList3.add(true);
    iterList3.add(true);
    iterList3.add(true);
    iterList3.add(false);
    iterList3.add(false);
    iterList3.add(false);

    Iterator<Boolean> testIter3 = iterList3.iterator();

    System.out.println("Testing with 3 false and 3 true: ");
    System.out.println("Result: " + foolFraction(testIter3));

}
}
