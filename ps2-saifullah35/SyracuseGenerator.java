import java.util.Iterator;
/**
   An iterator to get the Syracuse Sequence

   @author Saif Ullah and Jonathan Masih
   @version Spring 2022
*/
public class SyracuseGenerator implements Iterator<Long> {
     
    /** positive integer from where to start the sequence */
    private long n;

    /** variable to keep track of the next number in sequence */
    private long nextNumInSequence;

    /**
       Construct a generator for Syracuse of the given number.

       @param n positive integer from where to start the sequence
    */
    public SyracuseGenerator(Long n) {

        this.n = n;
        nextNumInSequence = n;
        }  
        
    /**
       iterates through until the value of n equals 1 

       @return iterates through until the value of n equals 1 
    */
    @Override
    public boolean hasNext() {   
        return nextNumInSequence != 0 ;
    }

    /**
       Return the next number in the sequence.

       @return the next number
    */
    @Override
    public Long next() {
      long temp = nextNumInSequence;
      advance();
      return temp;
     }
      
     /**
       Calculates the next number in the sequence.
    */
    public void advance(){
         if(nextNumInSequence == 1){
            nextNumInSequence = 0;
         }else if (nextNumInSequence %2 == 0){
            nextNumInSequence /= 2; 
          }else {
            nextNumInSequence = (nextNumInSequence * 3) + 1;
          }
    }

    /**
       main method to test the generator.

       @param args[0] the numbers that should be printed
    */
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Usage: java SyracuseGenerator n");
            System.exit(1);
        }
    
        // convert the command-line parameter to the number to a long
        long n = 0;
        try {
            n = Long.parseLong(args[0]);
        }
        catch (NumberFormatException e) {
            System.err.println(e);
            System.exit(1);
        }
       

        Iterator<Long> iter = new SyracuseGenerator(n);
	    while (iter.hasNext()) {
	        System.out.println(iter.next());
	}
    }
}
