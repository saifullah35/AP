import java.awt.*;
import javax.swing.*;
/**
 * Starter for a Vanishing Line.  CSIS-225 Lab 8.
 * 
 * @author Jonathan Masih and Saif Ullah
 * @version Spring 2022
 */

public class VanishingLine extends AnimatedLine{
    private Color currentColor = new Color(0,0,0);
	int rgb =  0;
	public VanishingLine (Point p1, Point p2, JComponent container){
		super(p1,  p2,  container);
	}

    @Override
    public void paint(Graphics g) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.setColor(currentColor);
    }

	@Override 
	public void run(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }
        while (rgb < 255) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }
            rgb += 2;
            if(rgb > 255){
                rgb = 255;
            }
            currentColor = new Color(rgb, rgb , rgb);
            // if we want to see the ball move to its new position, we
            // need to schedule a paint event on this container
            container.repaint();
        }
        done = true;

	}

}