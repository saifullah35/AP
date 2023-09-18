import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * Lab 9 testbed for regex practice.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class RegexPractice {

    public static void main(String[] args) {

        // this string is the regular expression we are looking for
        String regex = "0x[a-fA-F0-9]+";
        // this string is the text in which we are searching
        String text = "0x0 1231 0x1A 0xfa 0xAF9 0xAaaaaAaa5";

        // note that we don't construct a Pattern object directly, but instead
        // call the static method compile that constructs it for us
        Pattern p = Pattern.compile(regex);

        // the matcher method of Pattern constructs and returns a Matcher object
        // which, among other things, allows us to iterate over matching
        // substrings
        Matcher m = p.matcher(text);

        // Matcher's find method finds the next matching substring in the text
        // that matches the regular expression, returning true on success
        while (m.find()) {

            // Matcher's group method returns the substring of the most recent match
            System.out.println(m.group());
        }
    }
}
