import java.awt.*;
import java.util.*;

/**
 * Class responsible for creating and managing a bunch of snowflakes
 * that will fall down the component.
 * 
 * Based on example from CSCI 134, Williams College
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class Cloud extends Thread {

    // total number of snowflakes
    private static final int SNOWFLAKES = 50;
    // time between flakes
    private static final int FLAKE_INTERVAL = 900;

    // the Component where we'll be creating Snowflakes
    private Component panel;

    // list of FallingSnow objects that are the responsibility
    // of this Cloud
    private java.util.List<FallingSnow> flakes;

    // are all of this Cloud's snowflakes started?
    private boolean allStarted;

    // are all of this Cloud's snowflakes done?
    private boolean done;

    /**
     * Construct a new Cloud, using the given component to pass along
     * to its FallingSnow objects that it will create.
     * 
     * @param panel the Component in which this Cloud will generate FallingSnow
     *              objects
     */
    public Cloud(Component panel) {

        this.panel = panel;
        flakes = new Vector<FallingSnow>(SNOWFLAKES);
        done = false;
        allStarted = false;
    }

    /**
     * Run method to define the life of this Cloud, which consists of
     * generating FallingSnow objects for a while.
     */
    @Override
    public void run() {

        Random r = new Random();

        for (int snowCount = 0; snowCount < SNOWFLAKES; snowCount++) {

            // wait a bit before creating the next snowflake
            try {
                sleep(FLAKE_INTERVAL);
            } catch (InterruptedException e) {
            }

            // random x coordinate
            int x = r.nextInt(panel.getWidth());

            // random y Speed, 2, 3, or 4
            int ySpeed = r.nextInt(2) + 2;

            FallingSnow newFlake = new FallingSnow(panel, x, ySpeed);
            flakes.add(newFlake);
            newFlake.start();
        }
        allStarted = true;
    }

    /**
     * Pass along a paint to all FallingSnow objects managed by this Cloud.
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) {

        if (done)
            return;

        for (FallingSnow flake : flakes) {
            flake.paint(g);
        }
    }

    /**
     * Check if this Cloud's work is done (all of its snowflakes have
     * fallen and later melted.)
     * 
     * @return true if this Cloud's work is done
     */
    public boolean done() {

        if (!allStarted)
            return false;

        if (done)
            return true;

        // this Cloud is done if all of its flakes have melted
        for (FallingSnow flake : flakes) {
            if (!flake.melted())
                return false;
        }
        done = true;
        return true;
    }
}
