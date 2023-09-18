import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A program to demonstrate a simple animation of A Bubble
 * Blowing Machine.
 * 
 * @author Jonathan Masih, Saif Ullah , Trevor Collins
 * @version Spring 2022
 */
public class BubbleBlower extends MouseAdapter implements Runnable {

    private JPanel panel;
    private int bubbleSize = 0;
    private ArrayList<Bubble> bubbles;
    private Point currentLocation;
    private Point currentMouse;
    private Bubble newBubble;

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
        JFrame frame = new JFrame("BubbleBlower");
        frame.setPreferredSize(new Dimension(800, 800));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel(new BorderLayout()) {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int i = 0;
                while (i < bubbles.size()) {
                    Bubble b = bubbles.get(i);
                    if (b.isPopped()) {
                        bubbles.remove(i);
                    } else {
                        b.paint(g);
                        i++;
                    }
                }
                if (bubbles.isEmpty()) {
                    int stringWidth = g.getFontMetrics().stringWidth("Press and hold the mouse to blow a bubble, release to let it free!") / 2;
                    //g.setFont(new Font("Yu Gothic Medium" , 10 , 15));
                    //int stringOffset = g.getFontMetrics().getAscent()/2;
                    g.drawString("Press and hold the mouse to blow a bubble, release to let it free!",  getWidth()/2 - stringWidth, getHeight()/2);
                } 
            }
        };

        bubbles = new ArrayList<Bubble>();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        frame.add(panel);
        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentMouse = e.getPoint();
        currentLocation = new Point(e.getPoint().x - bubbleSize / 2, e.getPoint().y - bubbleSize / 2);
        newBubble = new Bubble(panel, currentLocation);
        newBubble.start();
        bubbles.add(newBubble);
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        newBubble.stopGrowing();
    }

    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new BubbleBlower());
    }
}
