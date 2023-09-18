import java.util.Scanner;

public class ListMatches {
    
    public static void main(String[] args) {
        String phraseToFind, phraseToMatch;
        if (args.length != 2) {
            System.out.println("Need two parameters");
        }
        else {
            phraseToFind = phraseToMatch = "";
            phraseToFind = (args[0]);
            phraseToMatch = (args[1]);       
        
            doesNotMatch(phraseToFind, phraseToMatch);    
        }
    }

    public static void doesNotMatch(String x, String y) {
        int pos = 0;
        if (x.indexOf(y) < 0) {
            System.out.println("No matches");
        }
        else {
            while (x.indexOf(y, pos) > -1) {
                System.out.print(x.indexOf(y, pos) + " ");
                pos = x.indexOf(y, pos) + 1;
            }    
        }
    }
}
