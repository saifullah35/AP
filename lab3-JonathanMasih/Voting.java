import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

/**
 * A program for Java Voting run as a Java application,
 * modified to have the class implement the Runnable interface rather
 * than creating the Runnable as an anonymous class.
 * 
 * @author Aaron McGuirk, Saif Ullah, Jonathan Masih
 * @version Spring 2022
 */

public class Voting implements Runnable, ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JButton label3;
    private JButton label4;
    private JButton resetButton;
    private int countForA = 0;
    private int countForB = 0;

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
        JFrame frame = new JFrame("VotingGrid");
        JPanel panel = new JPanel(new BorderLayout());
        // creates the dimensions for the window size
        frame.setPreferredSize(new Dimension(500, 500));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adds configured panels to the border panel
        JPanel gridCenter = new JPanel(new GridLayout(2, 1));
        JPanel flowSouth = new JPanel(new FlowLayout());

        panel.add(gridCenter, BorderLayout.CENTER);
        panel.add(flowSouth, BorderLayout.SOUTH);

        label1 = new JLabel("A: 0");
        label2 = new JLabel("B: 0");
        label3 = new JButton("Vote for A");
        label4 = new JButton("Vote for B");
        resetButton = new JButton("Reset");

        Font currentFont = label1.getFont();
        Font newFont = new Font(currentFont.getFontName(),
                currentFont.getStyle(), 30);
        label1.setFont(newFont);
        label2.setFont(newFont);

        gridCenter.add(label1, new GridLayout(1, 1));
        gridCenter.add(label2, new GridLayout(2, 1));
        flowSouth.add(label3);
        flowSouth.add(resetButton);
        flowSouth.add(label4);

        frame.add(panel);

        label3.addActionListener(this);
        label4.addActionListener(this);
        resetButton.addActionListener(this);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton)e.getSource();

        if (temp == resetButton){
            System.out.println("Reset");
            countForA = 0;
            countForB = 0;
            label1.setText("A: " + countForA);
            label2.setText("B: " + countForB);
            label1.setForeground(Color.BLACK);
            label2.setForeground(Color.BLACK);
        }
        if (temp == label3) {
            System.out.println("Got A");
            countForA++;
            label1.setText("A: " + countForA);
        } else if (temp == label4) {
            System.out.println("Got B");
            countForB++;
            label2.setText("B: " + countForB);
        }

        if(countForA > countForB){
            System.out.println("A is winning");
            label1.setForeground(Color.GREEN);
            label2.setForeground(Color.RED);

        } else if (countForA < countForB) {
            System.out.println("B is winning");
            label2.setForeground(Color.GREEN);
            label1.setForeground(Color.RED);
        } else if (countForA == countForB && countForA != 0 && countForB != 0) {
            System.out.println("A & B are tied");
            label1.setForeground(Color.BLUE);
            label2.setForeground(Color.BLUE);
        }
    }

    public static void main(String args[]) {

        // The main method is responsible for creating a thread (more
        // about those later) that will construct and show the graphical
        // user interface.
        javax.swing.SwingUtilities.invokeLater(new Voting());
    }
}
