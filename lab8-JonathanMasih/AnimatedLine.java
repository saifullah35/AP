import java.awt.*;
import javax.swing.*;
/**
 * A class to encapsulate information about a animated line on the screen.
 * @author (completed by Jonathan Masih , Saih Ullah)
 * @version Spring 2022
 */
public abstract class AnimatedLine extends Thread  {

    // some named constants to define shapes
    public static final int DELAY_TIME = 33;

    // shape info
    protected boolean done;
    protected Point p1,p2;
    protected JComponent container;
    
    public AnimatedLine(Point p1 , Point p2 , JComponent container){
		this.p1 = p1;
        this.p2 = p2;
        this.container = container;
		done = false;
	}
	   /**
     * paint this object onto the given Graphics area
     * 
     * @param g the Graphics object where the shape should be drawn
     */
    public abstract void paint(Graphics g);

   /**
     * This object's run method, which manages the life of the animated line as it
     * moves down the screen.
     */
    public abstract void run();	
   /**
     * Return whether the ball has completed its fall to the bottom.
     * 
     * @return whether the ball has completed its fall to the bottom
     */
    public boolean done() {
        return done;
    }

}