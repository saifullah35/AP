import java.awt.*;
import javax.swing.*;

/**
 * Lab 5 starter example
 * 
 * @author Saif Ullah and Ethan Tubia
 * @version Spring 2022
 */

// a class that extends JPanel to override the paintComponent method,
// allowing us to draw a picture
class GraphicsPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

        // first, we should call the paintComponent method we are
        // overriding in JPanel
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(30,30,20,20);
        g.drawRect(27,27,19,19);

        g.setColor(Color.RED);
        g.fillOval(15,15,15,15);
        g.drawOval(14,14,0,0);
        
        g.setColor(Color.GREEN);
        g.drawLine(16,16,0,0);
        

        // the Graphics object passed to this method has many methods
        // we can use to draw in the area of the panel, one of which
        // allows us to draw a String at a given x,y position
        g.setColor(Color.BLACK);
        g.drawString("car", 10, 15);
    }
}



public class ArtProject implements Runnable {

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // the usual JFrame setup steps
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("HelloGraphics");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // construct JPanel with a custom paintComponent method
        JPanel panel = new GraphicsPanel();
        frame.add(panel);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new HelloGraphics());
    }
}
