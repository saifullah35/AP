import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Bubble that floats around for given amount of time
 * before popping.
 * 
 */
public class Bubble extends Thread {

    // the Component where we'll drawn on
    private Component panel;
    private int bubbleSize;
    private Point currentLocation;
    private boolean popped;
    private boolean drawEmpty;
    private boolean growing; 
    private Point currentMouse;
    // delay between snow motions
    private static final int DELAY_TIME = 33;

    /**
     * Construct a new bubble object at the given position and speed.
     * 
     * @param panel the Component in which this FallingSnow will live
     *              objects
     */
    public Bubble(Component panel, Point currentMouse) {
        this.panel = panel;
        this.currentMouse = currentMouse;
        currentLocation = new Point(currentMouse.x - bubbleSize/2,currentMouse.y - bubbleSize/2);
        this.bubbleSize = 1;
        this.popped = false;
        this.drawEmpty = false;
        this.growing = true;
    }

    /**
     * Run method to define the life of this bubble, which consists of
     * falling down to the bottom of the component, then sitting there for
     * a little while before melting.
     */
    @Override
    public void run() {
      //Moves the bubble 
      while(growing == true){
        try {
            sleep(10);
        } catch (InterruptedException e) {
        }
        bubbleSize += 1;
        currentLocation = new Point(currentMouse.x - bubbleSize/2,currentMouse.y - bubbleSize/2);
       panel.repaint();
      }
        
      //Moves the bubble 
        java.util.Random r = new java.util.Random();
        int movementSpeedx = 0;
         movementSpeedx += r.nextInt(2) - 1;
         int movementSpeedy= 0;
            movementSpeedy += r.nextInt(2) - 1;
        int currentTime =  r.nextInt(100) + 50;
        for(int i = currentTime; i >= 0 ; i--){
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }
           movementSpeedx+= r.nextInt(3) - 1;
            movementSpeedy+= r.nextInt(3) - 1;
            currentLocation.translate(movementSpeedx ,movementSpeedy);
            panel.repaint();
         }
         //Bubble poppping
         try {
            sleep(200);
            } catch (InterruptedException e) {
            }
         drawEmpty = true;
         panel.repaint();
         try {
            sleep(200);
            } catch (InterruptedException e) {
            }
         popped = true;
         panel.repaint();
    }

    /**
     * Check if this bubble is popped or not
     * 
     * @return true if bubble is popped
     */
    public boolean isPopped() {
        return popped;
    }

       /**
     * Check if this bubble is popped or not
     * 
     * @return true if bubble is popped
     */
    public void stopGrowing() {
        growing = false;
    }

    /**
     * Draw the Square on the given Graphics object.
     * 
     * @param g the Graphics object on which the Bubble should be drawn
     */
    public void paint(Graphics g) {
        Color bright = new Color(173, 216, 245);
        if (!drawEmpty) {
            g.setColor(bright);
            g.fillOval(currentLocation.x, currentLocation.y, bubbleSize, bubbleSize);
            g.setColor(Color.BLACK);
            g.drawOval(currentLocation.x, currentLocation.y, bubbleSize, bubbleSize);
        } else  {
            g.setColor(Color.BLACK);
            g.drawOval(currentLocation.x, currentLocation.y, bubbleSize, bubbleSize);
        }

    }

}
