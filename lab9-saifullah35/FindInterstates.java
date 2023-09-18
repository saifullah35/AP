
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Look for interstate highway references in a METAL tmg graph file's
 * vertex labels, and report how many are found.
 * 
 * The only needed modifications are where you see "CODE HERE" below.
 * 
 * @author Jim Teresco (starter code)
 * @version Spring 2022
 */

public class FindInterstates {

    /**
     * main method to read in a METAL .tmg file as specified in args[0]
     * and print a count of how many vertices in the graph appear to have
     * a reference to an interstate highway
     * 
     * @param args command line parameters, where args[0] is expected
     *             to have a filename for a METAL tmg graph file
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java FindInterstates tmgfile");
            System.exit(1);
        }

        // create a Pattern that will match anything with I- followed
        // by 1 or more numbers, with special cases of A (for Alaska
        // interstates), H (for Hawaii interstates), or PR (for Puerto
        // Rico interstates) appearing between the - and the number.

        // CODE HERE
        Pattern p = Pattern.compile("(I-[0-9]+)|(I-A[0-9]+)|(I-H[0-9]+)|(I-PR[0-9]+)");

        int iCount = 0;
        int numV = 0;
        try (Scanner s = new Scanner(new File(args[0]))) {

            // first line is version and format info, which we assume is good
            s.nextLine();
            // second line is number of vertices and number of edges in file
            numV = s.nextInt();
            s.nextInt();

            for (int v = 0; v < numV; v++) {
                String label = s.next();
                Matcher m = p.matcher(label);

                if (m.find()) {
                    iCount++;
                }

                // here, check if it matches, increment iCount if it does

                // CODE HERE

                // skip over the lat/lng info that we don't care about here
                s.nextLine();
            }

            s.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println("Found " + iCount + " interstate references among " + numV +
                " vertex labels in " + args[0]);
    }
}