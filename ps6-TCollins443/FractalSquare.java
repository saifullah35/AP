import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A program to draw triangles on the screen.
 * 
 * @author (Jonathan Masih , Trevor Collins, Saif Ullah)
 * @version Spring 2022
 */
public class FractalSquare extends MouseAdapter implements Runnable {

    private JPanel panel;
    private int minimum;
    private Point initialPress;
    private Point currentMouse;
    private int currentSize;

    private boolean mouseReleased = false;

    // ArrayList for all the squares
    private ArrayList<Square> squares = new ArrayList<>();

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
        JFrame frame = new JFrame("FractalSquare");
        frame.setPreferredSize(new Dimension(800, 800));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Square s : squares) {
                    s.paint(g);
                }
                if (initialPress != null && mouseReleased == false &&
                        currentMouse.x > initialPress.x && currentMouse.y > initialPress.y) {
                    g.fillRect(initialPress.x, initialPress.y, currentSize, currentSize);
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
        initialPress = e.getPoint();
        currentMouse = e.getPoint();
        currentSize = currentMouse.x - initialPress.x;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // update the square here
        currentMouse = e.getPoint();
        currentSize = currentMouse.x - initialPress.x;
        panel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // draw the square and with the recursive calls
        if(currentMouse.x > initialPress.x && currentMouse.y > initialPress.y){
        squares.add(new Square(initialPress, currentSize, minimum));
        panel.repaint();
        }
    }

    public void setMinimum(int min) {
        this.minimum = min;
    }

    public static void main(String args[]) {
        int commandline;
        if (args.length == 1) {
            commandline = Integer.parseInt(args[0]);
            FractalSquare newFractalSquare = new FractalSquare();
            newFractalSquare.setMinimum(commandline);
            javax.swing.SwingUtilities.invokeLater(newFractalSquare);
        } else {
            System.err.println("Usage: java FractalSqaure size range");
            System.exit(1);
        }

    }

}

/**
 * The square class.
 */
class Square {
    private Point upperLeft;
    private int minimum;
    private int size;

    /**
     * Construct a new FractalSquare
     * 
     * @param UpperLeft  upper Left coordiante of the square
     */
    public Square(Point upperLeft, int size, int minimum) {
        this.upperLeft = upperLeft;
        this.size = size;
        this.minimum =  minimum;
    }

    protected void drawSquare(Point upperLeft,int size ,Graphics g) {
        g.fillRect(upperLeft.x,upperLeft.y,size,size );
        if (size <  minimum) {
          return;
        } else {
            drawSquare(new Point(upperLeft.x,upperLeft.y - (size/3)), size/3 , g);
            drawSquare(new Point(upperLeft.x + (int)(size /1.5),upperLeft.y -(size/3 )), size/3 , g);
            drawSquare(new Point(upperLeft.x + size ,upperLeft.y) ,size/3, g);
            drawSquare(new Point(upperLeft.x + size,upperLeft.y + (int)(size/1.5)),size/3 , g);
            drawSquare(new Point(upperLeft.x + (int)(size /1.5),upperLeft.y + size ),size/3 , g);
            drawSquare(new Point(upperLeft.x,upperLeft.y + size),size/3  , g);
            drawSquare(new Point(upperLeft.x - (size / 3),upperLeft.y + (int)(size /1.5 )),size/3, g);
            drawSquare(new Point(upperLeft.x - (size / 3),upperLeft.y) ,size/3 , g);  
    }

    }

    /**
     * Draw the Square on the given Graphics object.
     * 
     * @param g the Graphics object on which the Sierpinski should be drawn
     */
    public void paint(Graphics g) {
        drawSquare(upperLeft, size, g);
    }
}