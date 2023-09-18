import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A program to draw triangles on the screen.
 * 
 * @author (Jonathan Masih , Trevor Collins, Saif Ullah)
 * @version Spring 2022
 */
public class Playground extends MouseAdapter implements Runnable, ActionListener,ChangeListener {

    private static final Runnable Playground = null;
    private JPanel panel;
    private int minimum;
    private Point initialPress;
    private Point currentMouse;
    private int currentSize;
    private JButton clear;
    private JComboBox colorSelect;
    private boolean colorChanged;
    private JSpinner sizeSpinner;
    private JButton recolor;
    private Color currentColor;
    private JCheckBox applyAll;
    private boolean recolorAll = false;

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
        JFrame frame = new JFrame("Playground");
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
                    if (applyAll.isSelected()) {
                        s.setMinimumSquare(minimum);
                    }
                    if(recolorAll == true){
                        s.recolor(currentColor);
                        s.paint(g);
                        //recolorAll = false;
                    }else{
                        s.paint(g); 
                    }
                }
                if (initialPress != null && mouseReleased == false &&
                        currentMouse.x > initialPress.x && currentMouse.y > initialPress.y) {
                        g.setColor(currentColor);
                    g.fillRect(initialPress.x, initialPress.y, currentSize, currentSize);
                }
                recolorAll = false;
            }
        };
        recolor = new JButton("Recolor All");
        colorSelect = new JComboBox<>();
        colorSelect.addItem("Black");
        colorSelect.addItem("Blue");
        colorSelect.addItem("Green");
        colorSelect.addItem("Yellow");
        colorSelect.addItem("Orange");
        colorSelect.addItem("Red");
        clear = new JButton("Clear");
        clear.addActionListener(this);
        colorSelect.addActionListener(this);
        recolor.addActionListener(this);
        JLabel recursionsize = new JLabel("Recursion Size");

        sizeSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 200, 1));
        sizeSpinner.addChangeListener(this);
        minimum = (int)sizeSpinner.getValue();
        
        JLabel recursionsizeLabel = new JLabel("Apply All");
        applyAll = new JCheckBox();
        applyAll.setSelected(false);
        applyAll.addChangeListener(this);

        panel.add(clear);
        panel.add(colorSelect); 
        panel.add(recolor);
        panel.add(recursionsize);
        panel.add(sizeSpinner);
        panel.add(recursionsizeLabel);
        panel.add(applyAll);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        frame.add(panel);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialPress = e.getPoint();
        currentMouse = e.getPoint();
        currentSize = currentMouse.x - initialPress.x;
        mouseReleased = false;
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
        if (currentMouse.x > initialPress.x && currentMouse.y > initialPress.y) {
            squares.add(new Square(initialPress, currentSize, minimum, currentColor));
            mouseReleased = true;
            panel.repaint();
        }
    }

    public void setMinimum(int min) {
        this.minimum = min;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        minimum = (int)sizeSpinner.getValue();
        panel.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            squares.clear();
            panel.repaint();
        }
        if (e.getSource() == recolor) {
            recolorAll = true;
            panel.repaint();
        }

        if (e.getSource() == colorSelect) {
            if (colorSelect.getSelectedItem().equals("Black")) {
                currentColor = Color.BLACK;

            } else if (colorSelect.getSelectedItem().equals("Blue")) {
                currentColor = Color.BLUE;
            }
            else if (colorSelect.getSelectedItem().equals("Green")) {
                currentColor = Color.GREEN;
            }
            else if (colorSelect.getSelectedItem().equals("Yellow")) {
                currentColor = Color.YELLOW;
            }
            else if (colorSelect.getSelectedItem().equals("Orange")) {
                currentColor = Color.ORANGE;
            }
            else if (colorSelect.getSelectedItem().equals("Red")) {
                currentColor = Color.RED;
            }
            panel.repaint();
            return;
        }
        minimum = (int)sizeSpinner.getValue();
    }

    public static void main(String args[]) {
        
        javax.swing.SwingUtilities.invokeLater(new Playground());
    }

}
/**
 * The square class.
 */
class Square {
    private Point upperLeft;
    private int minimum;
    private int size;
    private Color color;
    

    /**
     * Construct a new FractalSquare
     * 
     * @param UpperLeft upper Left coordiante of the square
     */
    public Square(Point upperLeft, int size, int minimum, Color color) {
        this.upperLeft = upperLeft;
        this.size = size;
        this.minimum = minimum;
        this.color = color;
    }

    protected void drawSquare(Point upperLeft, int size, Graphics g) {
        g.fillRect(upperLeft.x, upperLeft.y, size, size);
        if (size < minimum) {
            return;
        } else {
            drawSquare(new Point(upperLeft.x, upperLeft.y - (size / 3)), size / 3, g);
            drawSquare(new Point(upperLeft.x + (int) (size / 1.5), upperLeft.y - (size / 3)), size / 3, g);
            drawSquare(new Point(upperLeft.x + size, upperLeft.y), size / 3, g);
            drawSquare(new Point(upperLeft.x + size, upperLeft.y + (int) (size / 1.5)), size / 3, g);
            drawSquare(new Point(upperLeft.x + (int) (size / 1.5), upperLeft.y + size), size / 3, g);
            drawSquare(new Point(upperLeft.x, upperLeft.y + size), size / 3, g);
            drawSquare(new Point(upperLeft.x - (size / 3), upperLeft.y + (int) (size / 1.5)), size / 3, g);
            drawSquare(new Point(upperLeft.x - (size / 3), upperLeft.y), size / 3, g);
        }
    }

    /**
     * Draw the Square on the given Graphics object.
     * 
     * @param g the Graphics object on which the Squareshould be drawn
     */
    public void paint(Graphics g) {
        g.setColor(color);
        drawSquare(upperLeft, size, g);
    }
   /**
     * Changes the color of the square
     * 
     * @param color recolor the sqaure
     */
    public void recolor(Color color) {
      this.color = color;
    }
    /**
     * Changes the minimum size of the square
     * 
     * @param int minimum size of the square
     */
    public void setMinimumSquare(int minimum){
     this.minimum = minimum;
    }
}
