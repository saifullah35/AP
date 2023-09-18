
/**
 * Computes values of Ackermann function using recursion
 *
 * @author Saif Ullah
 * @version Feb 1, 2022
 */
public class Ackermann
{
    public static int Ackermann(int m, int n){
        //ACKERMANN CLASS WITH RECURSION
        if(m == 0){
            return n + 1;
        } else if ((m > 0) && (n == 0)){
            return Ackermann(m - 1, 1);
        } else if ((m > 0) && (n > 0)){
            return Ackermann(m - 1, Ackermann(m, n-1));
        } else{
          return n + 1;
        }
    } 
}
