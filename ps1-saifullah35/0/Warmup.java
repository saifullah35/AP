import java.util.Scanner;
/**
 * Write a description of class Warmup here.
 *
 * @author Saif Ullah
 * @version February 1, 2022
 */
public class Warmup
{
    public static void main(String[] args){
        numberOfTimes();
    }
    
    public static void numberOfTimes(){
        Scanner input = new Scanner(System.in);
        
        //READ IN INTEGER
        //CONVERT THE STRING TO AN INTEGER TO READ IN THE NUMBER OF CHARACTERS PRINTED 
        int num = Integer.parseInt(input.nextLine());
        
        //READ IN STRING
        String words = input.nextLine(); 

        for(int i = 0; i < num; i++){
           System.out.println(words);
        }
        
        System.out.println();
        input.close();   
    }
}
