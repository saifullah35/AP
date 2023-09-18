
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A program to draw triangles on the screen.
 * 
 * @author Jim Teresco
 * @author (completed by)
 * @version Spring 2022
 */

public class DrawSierpinskis extends MouseAdapter implements Runnable {

    private JPanel panel;

    // number of clicks since last triangle outline completed
    private int clickNum;

    // parts of the outline triangle so far
    private Point click1, click2;
    private Point currPos;

    private ArrayList<Sierpinski> triangles = new ArrayList<>();

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("DrawSierpinskis");
        frame.setPreferredSize(new Dimension(800, 800));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                // first, we should call the paintComponent method we are
                // overriding in JPanel
                super.paintComponent(g);

                // draw my triangles
                for (Sierpinski t : triangles) {
                    t.paint(g);
                }

                // draw the "sling" lines
                if (clickNum == 1) {
                    g.drawLine(click1.x, click1.y, currPos.x, currPos.y);
                } else if (clickNum == 2) {
                    g.drawLine(click1.x, click1.y, click2.x, click2.y);
                    g.drawLine(click1.x, click1.y, currPos.x, currPos.y);
                    g.drawLine(currPos.x, currPos.y, click2.x, click2.y);

                }

            }
        };
        frame.add(panel);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (clickNum == 0) {
            clickNum = 1;
            click1 = e.getPoint();
        } else if (clickNum == 1) {
            clickNum = 2;
            click2 = e.getPoint();
        } else {
            clickNum = 0;
            triangles.add(new Sierpinski(click1, click2, e.getPoint()));
            panel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        currPos = e.getPoint();
        panel.repaint();
    }
    
    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new DrawSierpinskis());
    }
}

/**
 * The Sierpinski class.
 */
class Sierpinski {

    // corner points
    private Point corners[];
    private static final int MINIMUM = 10;
    /**
     * Construct a new Sierpinski
     * 
     * @param corner1 first corner of the triangle border of the Sierpinski
     * @param corner2 second corner of the triangle border of the Sierpinski
     * @param corner3 third corner of the triangle border of the Sierpinski
     */
    public Sierpinski(Point corner1, Point corner2, Point corner3) {

        corners = new Point[3];
        corners[0] = corner1;
        corners[1] = corner2;
        corners[2] = corner3;
    }

    protected static void drawSierpinski(Point corner1, Point corner2, Point corner3, Graphics g) {
        if(corner1.distance(corner2) < MINIMUM && corner1.distance(corner3) < MINIMUM && corner2.distance(corner3) < MINIMUM) {
            int[] xs = {corner1.x, corner2.x, corner3.x};
            int[] ys = {corner1.y, corner2.y, corner3.y};
            g.drawPolygon(xs, ys, 3);
        }
        else {
            drawSierpinski(corner1, 
            new Point((corner1.x+corner2.x)/2, (corner1.y+corner2.y)/2), 
            new Point((corner1.x+corner3.x)/2, (corner1.y+corner3.y)/2), g);

            drawSierpinski(corner2, 
            new Point((corner2.x+corner1.x)/2, (corner2.y+corner1.y)/2), 
            new Point((corner2.x+corner3.x)/2, (corner2.y+corner3.y)/2), g);

            drawSierpinski(corner3, 
            new Point((corner3.x+corner1.x)/2, (corner3.y+corner1.y)/2), 
            new Point((corner3.x+corner2.x)/2, (corner3.y+corner2.y)/2), g);
        }
    }


    /**
     * Draw the Sierpinski on the given Graphics object.
     * 
     * @param g the Graphics object on which the Sierpinski should be drawn
     */
    public void paint(Graphics g) {
        drawSierpinski(corners[0], corners[1], corners[2], g);
    }
}