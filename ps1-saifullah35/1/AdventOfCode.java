import java.util.Scanner;
import java.util.ArrayList;
/**
 * Use the binary numbers in the diagnostic report to generate two new binary numbers 
 * (called the gamma rate and the epsilon rate).
 *
 * @author Saif Ullah
 * @version Feb 1, 2022
 */
public class AdventOfCode
{
    public static void main(String agrs[]){
        //READ IN STRING
        Scanner input = new Scanner(System.in);
        String words = input.nextLine();
        
        int gammaRate = 0;
        int epsilonRate = 0;
        int count1 = 0;
        int count2 = 0;
        
        while(input.hasNext()){
            for(int i = 0; i < words.length(); i++){
                if((word.charAt(i) == "0")) {
                    count1++;
                } else{
                    count2--;
                } 
                
                if((word.charAt(i) == "1")) {
                    count1--;
                } else{
                    count2++;
                } 
            }
        }
        
        if(count1 > count2){
            gammaRate = 1 * 16;
        } else{
            gammaRate = (1 * 8) + 16;
        }
        
    }
}
