import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/**
 * A program for Java Swing run as a Java application,
 * modified to have the class implement the Runnable interface rather
 * than creating the Runnable as an anonymous class.
 * 
 * @author Aaron McGuirk, Saif Ullah, Jonathan Masih
 * @version Spring 2022
 */

public class SwingBasics implements Runnable {

    /**
     * The run method to set up the graphical user interface
     * 
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("SwingBasics");
        JPanel panel = new JPanel();

        // creates the dimensions for the window size
        frame.setPreferredSize(new Dimension(200, 500));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a JLabel containing our message, and add
        // it to our JFrame
        JLabel swingBasics = new JLabel("Swing is Fun");
        swingBasics.setForeground(Color.RED);
        JLabel swingBasics2 = new JLabel("Swing is Awful");
        swingBasics2.setForeground(Color.WHITE);
        JLabel swingBasics3 = new JLabel("Swing is John");
        swingBasics3.setForeground(Color.BLUE);
        panel.add(swingBasics);
        panel.add(swingBasics2);
        panel.add(swingBasics3);
        frame.add(panel);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {

        // The main method is responsible for creating a thread (more
        // about those later) that will construct and show the graphical
        // user interface.
        javax.swing.SwingUtilities.invokeLater(new SwingBasics());
    }
}
