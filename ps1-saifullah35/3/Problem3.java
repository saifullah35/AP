import java.util.Scanner;
/**
 * The program will read two lines of text, each between 1 and 1000 characters in length. The
 * characters will consist of letters, spaces, and punctuation marks. It should output “YES” if the two
 * lines are anagrams of each other and “NO” if they are not.
 *
 * @author Saif Ullah
 * @version (a version number or a date)
 */
public class Problem3
{
    public static boolean Anagram(){
        Scanner input = new Scanner(System.in);
        //READ IN STRING
        String word1 = input.nextLine().replaceAll("[^a-zA-Z ]", "").replaceAll("\\s", "").toLowerCase(); 
        String word2 = input.nextLine().replaceAll("[^a-zA-Z ]", "").replaceAll("\\s", "").toLowerCase();
        
        
        if(word1.length() != word2.length()){
            return false;
        }
         
        //ARRAY OF 256 ASCII CHARACTERS
        int[] arr = new int[256];
        
        //TAKES THE FIRST STRING 
        for(int i = 0; i < word1.length(); i++){
            arr[word1.charAt(i)]++;  
        }
        
        for(int j = 0; j < word2.length(); j++){
            arr[word2.charAt(j)]--;  
        }
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                return false;
            }
        }
        
        return true;
    }   
}
