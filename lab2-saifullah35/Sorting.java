import java.util.Arrays;
import java.util.Comparator;
public class Sorting {
    public static void main(String[] args) {
    String[] stringArray = {"These", "Words", "Are", "Out", "Of", "Order"};
    Integer[] intArray = {3, 4, 7, 2, 1, 6};
    Arrays.sort(stringArray);
    Arrays.sort(intArray);
    System.out.println(Arrays.toString(intArray));
    System.out.println(Arrays.toString(stringArray));
    String[] stringArray2 = {"These", "Words", "Are", "Out", "Of", "Order"};
    Integer[] intArray2 = {3, -4, 7, -2, 1, 0};
    Arrays.sort(stringArray2, new StringLengthComparator());
    Arrays.sort(intArray2, new IntegerAbsComparator());
    System.out.println();
    System.out.println(Arrays.toString(intArray2));
    System.out.println(Arrays.toString(stringArray2));
}

}
