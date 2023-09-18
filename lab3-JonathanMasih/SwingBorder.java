import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/**
 * A program for Java Border run as a Java application,
 * modified to have the class implement the Runnable interface rather
 * than creating the Runnable as an anonymous class.
 * 
 * @author Aaron McGuirk, Saif Ullah, Jonathan Masih
 * @version Spring 2022
 */

public class SwingBorder implements Runnable {

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
        JFrame frame = new JFrame("SwingBorder");
        JPanel panel = new JPanel(new BorderLayout());

        // creates the dimensions for the window size
        frame.setPreferredSize(new Dimension(500, 500));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a JLabel containing our message, and add
        // it to our JFrame
        //adding 5 JLabels to the JPanel
        JLabel swingBorder = new JLabel("North");
        swingBorder.setForeground(Color.RED);
        JLabel swingBorder2 = new JLabel("South");
        swingBorder2.setForeground(Color.BLACK);
        JLabel swingBorder3 = new JLabel("East");
        swingBorder3.setForeground(Color.BLUE);
        JLabel swingBorder4 = new JLabel("West");
        swingBorder4.setForeground(Color.BLACK);
        JLabel swingBorder5 = new JLabel("Center");
        swingBorder5.setForeground(Color.BLUE);
        panel.add(swingBorder, BorderLayout.NORTH) ;
        panel.add(swingBorder2, BorderLayout.SOUTH) ;
        panel.add(swingBorder3, BorderLayout.EAST) ;
        panel.add(swingBorder4, BorderLayout.WEST) ;
        panel.add(swingBorder5, BorderLayout.CENTER) ;
        frame.add(panel);
        

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {

        // The main method is responsible for creating a thread (more
        // about those later) that will construct and show the graphical
        // user interface.
        javax.swing.SwingUtilities.invokeLater(new SwingBorder());
    }
}
