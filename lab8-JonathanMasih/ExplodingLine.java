import java.awt.*;
import javax.swing.*;

/**
 * Starter for a Exploding Line.  CSIS-225 Lab 8.
 * 
 * @author Jonathan Masih and Saif Ullah
 * @version Spring 2022
 */

public class ExplodingLine extends AnimatedLine{
    private Point midpoint;
    private int distance;
    private boolean explode = false;
	public ExplodingLine (Point p1, Point p2, JComponent container){
		super(p1,  p2,  container);
        distance = (int) Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        this.midpoint = new Point (((p2.x + p1.x) / 2 - (distance/2)) ,((p2.y + p1.y) / 2 - (distance/2)));
    }

    @Override
    public void paint(Graphics g) {
        if(explode == false){
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }else{
            g.setColor(Color.red);
            g.fillOval(midpoint.x, midpoint.y, distance, distance);
    
        }
        g.setColor(Color.black);
    }

	@Override 
	public void run(){
       
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }
        explode = true;
        container.repaint();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }

        done = true;

	}

}
