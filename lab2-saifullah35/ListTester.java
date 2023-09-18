import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.LinkedList;
class ListTester{

    public static int noMoreNegativity(List<Integer> myList){
        int counter = 0;
        for(int i = 0; i < myList.size(); i++){
            if(myList.get(i) < 0){
                myList.set(i, Math.abs(myList.get(i)));
                counter++;
            }
        }
        return counter;
    }
    
    public static void main(String args[]){
        Vector<Integer> myVec1 = new Vector<Integer>();
        myVec1.add(-3);
        myVec1.add(-5);
        myVec1.add(-4);
        System.out.println(noMoreNegativity(myVec1));
        System.out.println(myVec1.toString());
        Vector<Integer> myVec2 = new Vector<Integer>();
        myVec2.add(-6);
        myVec2.add(15);
        myVec2.add(0);
        System.out.println(noMoreNegativity(myVec2));
        System.out.println(myVec2.toString());
        ArrayList<Integer> myAL1 = new ArrayList<Integer>();
        myAL1.add(3);
        myAL1.add(5);
        myAL1.add(4);
        System.out.println(noMoreNegativity(myAL1));
        System.out.println(myAL1.toString());
        ArrayList<Integer> myAL2 = new ArrayList<Integer>();
        myAL2.add(-6);
        myAL2.add(15);
        myAL2.add(0);
        System.out.println(noMoreNegativity(myAL2));
        System.out.println(myAL2.toString());
        LinkedList<Integer> myLL1 = new LinkedList<Integer>();
        myLL1.add(3);
        myLL1.add(5);
        myLL1.add(-4);
        System.out.println(noMoreNegativity(myLL1));
        System.out.println(myLL1.toString());
        LinkedList<Integer> myLL2 = new LinkedList<Integer>();
        myLL2.add(-6);
        myLL2.add(-15);
        myLL2.add(0);
        System.out.println(noMoreNegativity(myLL2));
        System.out.println(myLL2.toString());
    }
}