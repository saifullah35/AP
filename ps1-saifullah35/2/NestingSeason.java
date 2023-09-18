 import java.util.Scanner;
import java.util.Deque;
import java.util.*;
/**
 * Your program should read a single string guaranteed to consist only of the characters {, }, (, ),
 * <, >, [, and ]. It should output a single word: YES if the string represents a valid nesting, NO if it
 * does not.
 *
 * @author Saif Ullah
 * @version Feb 1, 2022
 */
public class NestingSeason
{
    public static void main(String args[]){
        validNesting();
    }
    
    public static boolean validNesting(){
        //INPUT FOR THE CHARACTER NESTING
        Scanner input = new Scanner(System.in);
        String word = input.next();
        
        //DEQUE INTERFACE INITILIZED SO WHEN USING PUSH, IT PUSHES TO THE BEGINNING AND POP MOVES THE MOST RECENT CHARACTER ADDED  
        Deque<Character> stack = new ArrayDeque<>();
        
        //ITERATING THROUGH THE PROGRAM
        int i = 0;
        char lastChar;
        while(i < word.length()){
            if((word.charAt(i) == '{') || (word.charAt(i) == '(') || (word.charAt(i) == '<') || (word.charAt(i) == '[')){
                stack.push(word.charAt(i));

            } 
            //CHECKING TO SEE IS THE FIRST CHARACTER IS A CLOSING ONE
            else if(stack.isEmpty()){
               return false;
            }
            
            if(word.charAt(i) == '}'){
               //FIRST CHECK
               lastChar = stack.pop();
               if(lastChar == '(' || lastChar == '<' || lastChar == '['){
                   return false;
               }
               
            }else if(word.charAt(i) == ')'){
               //SECOND CHECK
               lastChar = stack.pop();
               if(lastChar == '{' || lastChar == '<' || lastChar == '['){
                   return false;
               }
            }else if(word.charAt(i) == '>'){
               //THIRD CHECK
               lastChar = stack.pop();
               if(lastChar == '{' || lastChar == '(' || lastChar == '['){
                   return false;
               }
            }else if(word.charAt(i) == ']'){
               //FOURTH CHECK
               lastChar = stack.pop();
               if(lastChar == '(' || lastChar == '<' || lastChar == '{'){
                   return false;
               }
            } 
            i++;
        } 
        
        //AT THE END, MAKE SURE THE STACK IS EMPTY
        return stack.isEmpty();
    }
}
