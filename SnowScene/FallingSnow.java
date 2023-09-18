import java.awt.*;

/**
 * Class responsible for creating and animating a single snowflake
 * that will fall down the component.
 * 
 * Based on example from CSCI 134, Williams College
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class FallingSnow extends Thread {

    // all FallingSnow objects draw copies of the same image, so
    // we need only store it once, hence a static variable, which
    // is set by a call to setSnowPic, also a static method
    private static Image snowPic;

    // the filename that will be loaded into snowPic
    private static final String snowPicFilename = "snow.gif";

    // its height should really be queried, but we will ignore that
    // complication for now
    private static final int snowPicHeight = 24;

    // delay between snow motions
    private static final int DELAY_TIME = 33;

    // delay for melting time
    private static final int MELTING_TIME = 2000;

    // speed of fall
    private int ySpeed;

    // the Component where we'll be falling (so we know when to stop)
    private Component panel;

    // latest location of the snowflake
    private Point upperLeft;

    // have we melted?
    private boolean melted;

    /**
     * Construct a new FallingSnow object at the given position and speed.
     * 
     * @param panel the Component in which this FallingSnow will live
     *              objects
     */
    public FallingSnow(Component panel, int x, int ySpeed) {

        this.panel = panel;
        this.ySpeed = ySpeed;

        // fix y
        upperLeft = new Point(x, -snowPicHeight);

        melted = false;
    }

    /**
     * Run method to define the life of this FallingSnow, which consists of
     * falling down to the bottom of the component, then sitting there for
     * a little while before melting.
     */
    @Override
    public void run() {

        while (upperLeft.y < panel.getHeight() - snowPicHeight) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }

            // every 30 ms or so, we move the coordinates of the flake down
            // according to its falling speed
            upperLeft.translate(0, ySpeed);

            // if we want to see the flake move to its new position, we
            // need to schedule a paint event on this container
            panel.repaint();
        }

        try {
            sleep(MELTING_TIME);
        } catch (InterruptedException e) {
        }

        melted = true;
        panel.repaint();
    }

    /**
     * Paint this FallingSnow object at its current position, if not melted
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) {

        if (!melted) {
            g.drawImage(snowPic, upperLeft.x, upperLeft.y, null);
        }
    }

    /**
     * Check if this FallingSnow has melted.
     * 
     * @return true if this FallingSnow has melted.
     */
    public boolean melted() {

        return melted;
    }

    /**
     * Set the Image to be used by all FallingSnow objects, to be
     * called by the main method before the GUI gets set up
     */
    public static void loadSnowPic() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        FallingSnow.snowPic = toolkit.getImage(snowPicFilename);
    }
}
