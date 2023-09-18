
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Looking at sequences of proteins to determine the longest
 * uninterrupted sequence of H and E.
 * 
 * @author Jim Teresco (starter code)
 * @author Paul and Saif (modified)
 * @version Spring 2022
 */

public class Protein {

    /**
     * Reads in a protein file.
     * 
     * @param args command line parameters, unused as the file
     * is hardcoded in.
     */
    public static void main(String[] args) {
        File file = new File("2VAOA.txt");
        Pattern p;
        Matcher m;

        try (Scanner s = new Scanner(file)) {
            s.nextLine();
            s.nextLine();
            s.nextLine();
            String line = s.nextLine();

            System.out.print(getLongestOccurance(p = Pattern.compile("H+"), m = p.matcher(line)));
            System.out.print(" " + getLongestOccurance(p = Pattern.compile("E+"), m = p.matcher(line)));    

            s.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Finds the longest uninterrupted sequence of H and E.
     * @param p The pattern to match.
     * @param m The matcher to use.
     * @return The length of the longest uninterrupted sequence of H and E.
     */
    public static int getLongestOccurance(Pattern p, Matcher m) {
        int num = 0;
        String current = "";
        String longest = "";
        while (m.find()) {
            current = (m.group());
            if (current.length() > longest.length()) {
                num = current.length();
                longest = current;
            }
        }
        return num;
    }
}