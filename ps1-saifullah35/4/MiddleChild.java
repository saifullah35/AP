import java.util.Scanner;
import java.util.*;
/**
 * On a given line, there will be 0 or more space-separated “L”, “M”, and “R”
 * characters, which indicate, starting at the root, that the new value should go down the left, middle,
 * or right branch, respectively, followed by an integer value to be added at that new node.
 *
 * @author Saif Ullah
 * @version Feb 1, 2022
 */
public class MiddleChild
{
    private Node root;
    
    /**
     * 
     */
    private class Node 
    {
        private int data;
        private Node left;
        private Node right;
        private Node middle;

        /**
         * 
         * @param data The data value for the node.
         * @param left A reference to the child of the node or
         * null if this node has no left child.
         * @param right A reference to the right child of the
         * node or null if this node has no right child.
         */
        private Node(int data, Node left, Node right, Node middle) 
        {
            this.data = data;
            this.left = left;
            this.right = right;
            this.middle = middle;
        }
        
        private boolean insert(int value, Node n){
           if(n.data == value){
                return true;
           } else if(n.data < value){
                return insert(n.right , value);
           } else if(n.data > value){
               return insert(n.left , value);
           } else{
               return insert(n.middle , value);
           }
        }
         
        private void printAll(Node n){
            if(n != null){
                System.out.print(n.data + " "); 
                printAll(n.left);
                printAll(n.middle);
                printAll(n.right);
            }
        }
    }
    
    
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        //READ IN STRING
        String words = input.nextLine();
        int data = 0;
        
        if(words.length() != 0){
                
        }
         
        while(!input.nextLine().equalsIgnoreCase("DONE")){
            input.nextInt();
            
            for(int i = 0; i < words.length();i++){
                if(words.charAt(i) < (words.charAt(i+1))){
                    System.out.println("L" + words.charAt(i));
                } else if(words.charAt(i) > (words.charAt(i+1))){
                    System.out.println("R" + words.charAt(i));
                } else if(words.charAt(i) == (words.charAt(i+1))){
                    System.out.println("L" + words.charAt(i));
                }
            }
        }
        
        
    }
    
}
