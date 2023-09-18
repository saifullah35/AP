import java.util.Iterator;
/**
   An application to generate all sequences from 1 to the maximum n value
   and gets the Longest sequence started at n has largest length. Also find
   Largest value  at position in sequence beginning with  in n sequence;

   @author Saif Ullah and Jonathan Masih
   @version Spring 2022
*/
public class SyracuseSearch  {
     
   
    /**
       main method to test the application.

       @param args[0] The n max sequence
    */    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SyracuseGenerator n");
            System.exit(1);
        }
    
        // convert the command-line parameter to the number to a long
      long n = 0;
        try {
            n = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            System.err.println(e);
            System.exit(1);
        }
        
        //Varibles to hold largest value and LongestSequence
        long LongestSequenceStarted = 0;
        int lengthLongest = 0;
        
        //Varibles largest Value throughout all Sequences, largest Value position 
        //in the Sequence and  largest Value Sequence
        long largestValue = 0;
        int largestValuePos = 0;
        long largestValueSeq = 0;

        // pos counter for all sequences
        int pos = 0;
        
        //current value in the Sequence
        Long currentValue;

        int lengthCounter = 0;
        for(long i = 1 ; i <= n; i++){
            Iterator<Long> a = new SyracuseGenerator(i);
             while (a.hasNext()) {
                    currentValue = a.next();
                    lengthCounter++;
                    pos++;
                if(currentValue > largestValue ){
                        largestValue = currentValue;
                        largestValuePos = pos;
                        largestValueSeq = i;
                }
                }
                if(lengthCounter >= lengthLongest ){
                    lengthLongest = lengthCounter;
                    LongestSequenceStarted  = i;
                }
                lengthCounter = 0;
                pos = 0;
        }
       System.out.println("Longest sequence started at " + LongestSequenceStarted + " has length " + lengthLongest);
       System.out.println("Largest value " + largestValue + " at position " + largestValuePos +" in sequence beginning with "+ largestValueSeq);
        

     }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  