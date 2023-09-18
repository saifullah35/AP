import java.util.Comparator;

class IntegerAbsComparator implements Comparator<Integer>{
    public int compare(Integer value1, Integer value2){
       return Math.abs(value1) - Math.abs(value2);
    }
    public static void main(String[] args) {
        System.out.println("These are our test cases");
        IntegerAbsComparator myComp = new IntegerAbsComparator(); 
        System.out.println(myComp.compare(2, 3));
        System.out.println(myComp.compare(2, 1));
        System.out.println(myComp.compare(-2, -3));
        System.out.println(myComp.compare(-2, -1));
    }
}