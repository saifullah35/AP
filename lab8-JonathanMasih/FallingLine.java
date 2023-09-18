import java.awt.*;
import javax.swing.*;
/**
 * Starter for a Falling Line.  CSIS-225 Lab 8.
 * 
 * @author Jim Teresco (modified by Jonathan Masih and Saif Ullah)
 * @version Spring 2022
 */
public class FallingLine extends AnimatedLine{
	
	private double ySpeed;
    private int bottom;
	public FallingLine(Point p1, Point p2, JComponent container){
		super(p1,  p2,  container);
		ySpeed = 1;
		bottom = container.getHeight();
	}
    
    @Override
    public void paint(Graphics g) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

	@Override
    public void run() {
        int higherY = 0;
        if (p1.y > p2.y){
            higherY = p1.y;
        }else{
            higherY = p2.y;
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }
        while (higherY < bottom) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }
            p1.translate(0, (int)ySpeed);
            p2.translate(0, (int)ySpeed);
            ySpeed += 0.3;

            // if we want to see the ball move to its new position, we
            // need to schedule a paint event on this container
            container.repaint();
        }

        done = true;
    }
}
