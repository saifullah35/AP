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

public class SwingGrid implements Runnable {

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
        JFrame frame = new JFrame("SwingGrid");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        // creates the dimensions for the window size
        frame.setPreferredSize(new Dimension(500, 500));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a JLabel containing our message, and add
        // it to our JFrame
        //adding 5 JLabels to the JPanel
        JLabel swingGrid = new JLabel("North");
        swingGrid.setForeground(Color.RED);
        JLabel swingGrid2 = new JLabel("South");
        swingGrid2.setForeground(Color.BLACK);
        JLabel swingGrid3 = new JLabel("East");
        swingGrid3.setForeground(Color.BLUE);
        JLabel swingGrid4 = new JLabel("West");
        swingGrid4.setForeground(Color.BLACK);
        JLabel swingGrid5 = new JLabel("Center");
        swingGrid5.setForeground(Color.BLUE);
        JLabel swingGrid6 = new JLabel("Lab");
        swingGrid5.setForeground(Color.BLUE); 
        panel.add(swingGrid, new GridLayout(1,1)) ;
        panel.add(swingGrid2,new GridLayout(1,2)) ;
        panel.add(swingGrid3, new GridLayout(2,1)) ;
        panel.add(swingGrid4, new GridLayout(2,2));
        panel.add(swingGrid5, new GridLayout(3,1)) ;
        panel.add(swingGrid6,new GridLayout(3,2));
        
        frame.add(panel);
        

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {

        // The main method is responsible for creating a thread (more
        // about those later) that will construct and show the graphical
        // user interface.
        javax.swing.SwingUtilities.invokeLater(new SwingGrid());
    }
}
