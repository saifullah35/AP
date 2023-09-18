import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.Point2D;

/**
 * @author Jonathan Masih, Trevor Collins, Saif Ullah
 * @version 3/12/22
 * 
 *          This class is going to create the game of Skee Ball
 */

public class SkiBall extends MouseAdapter implements Runnable, ActionListener {

    // Panels for the game
    private JPanel gamePanel;
    private JPanel scoreboardPanel;
    private Point currentMouse;
    private Point initialMousePress;

    // Variable to draw the slingshot line from the foul line.
    private Point slingShotStart;
    private boolean mouseReleased;

    // Current ball being shot to the targets
    private Ball currentBall;

    // All the target circles
    private TargetCircle tenPointTarget;
    private TargetCircle twentyPointTarget;
    private TargetCircle thirtyPointTarget;
    private TargetCircle fiftyPointTarget;

    // Instance variable to keep track of which target the cicrle landed
    private TargetCircle currentTargetCircle;

    // Instance variables for the socreboard
    private int currnetShotPoints;
    private int totalPoints;
    private int shots = 10;

    //JLabels for the game in the scoreboard panel
    JLabel gameMessagesLabel = new JLabel( "Press Below The line to shoot");
    JLabel currentShotPointsLabel = new JLabel();
    JLabel shotsLeftLabel = new JLabel("Shots left: 10");
    JLabel totalPointLabel = new JLabel("Score: 0");

    
    //JButons for starting the game , restarting the game and Play again 
    private JButton currentButton = new JButton("Start Game");

    //instance variable that checks if the game has started or not.
    private boolean gameStarted = false;

    // ArrayList to paint the frame of the game
    private ArrayList<TargetCircle> TargetCircles = new ArrayList<TargetCircle>();

    //Constants for the game
    private static final int LANE_WIDTH = 400;
    private static final int LANE_HEIGHT = 800;
    private static final int POWER_OF_BALL = 4;

    @Override
    public void actionPerformed(ActionEvent e) {
     JButton temp = (JButton) e.getSource();
     if(temp.getText().equals("Start Game")){
        gameStarted = true;
        currentButton.setText("Restart Game");
     }else if(temp.getText().equals("Restart Game")){
        totalPointLabel.setText("Score: 0");
        shotsLeftLabel.setText("Shots left: 10");
         totalPoints = 0;
        shots = 10;
     }else if(temp.getText().equals("Play Again")){
        currentButton.setText("Restart Game");
        shotsLeftLabel.setText("Shots left: 10");
        totalPointLabel.setText("Score: 0");
        currentShotPointsLabel.setText("");
        totalPoints = 0;
        shots = 10;
        gameStarted = true;
     }
   
    }

    @Override
    public void mousePressed(MouseEvent e) {
     if(gameStarted == true){
        Point pressedLocation = e.getPoint();
        if (pressedLocation.y > 550 && pressedLocation.x < 400 ) {
            initialMousePress = pressedLocation;
            mouseReleased = false;
            currentTargetCircle = null;
            slingShotStart = new Point((int) pressedLocation.getX(), 550);
            currentMouse = new Point(pressedLocation.x - 10, pressedLocation.y - 10);
            currentBall = new Ball(15, currentMouse, Color.BLACK);
            currentMouse = e.getPoint();

            //Changes the top game message
           gameMessagesLabel.setText("Drag to Aim");
            gamePanel.repaint();
        }

      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
     if(gameStarted == true){
        if (currentMouse != null &&  mouseReleased == false) {
            int dx = e.getPoint().x - currentMouse.x;
            int dy = e.getPoint().y - currentMouse.y;
            currentBall.translate(dx, dy);
            currentMouse = e.getPoint();
            gamePanel.repaint();
        }
     }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentMouse != null && initialMousePress != null) {
            mouseReleased = true;
            int dx = -1 * (POWER_OF_BALL * (e.getPoint().x - initialMousePress.x));
            int dy = -1 * (POWER_OF_BALL * (e.getPoint().y - 550));
            currentBall.translate(dx, dy);
            currentMouse = e.getPoint();
            shots--;
            shotsLeftLabel.setText("Shots left: " + shots);
            gamePanel.repaint();
             // Checks where the ball landed
        if (currentBall != null && gameStarted == true) {
            if (fiftyPointTarget.contains(currentBall.getCurrentCenter())) {
                currentTargetCircle = fiftyPointTarget;
               gameMessagesLabel.setText("Great shot");
               currentShotPointsLabel.setText(fiftyPointTarget.getPointAmount() +" points");
                totalPoints += fiftyPointTarget.getPointAmount();

            } else if (thirtyPointTarget.contains(currentBall.getCurrentCenter())) {
                currentTargetCircle = thirtyPointTarget;
               gameMessagesLabel.setText("Nice one.");
               currentShotPointsLabel.setText(thirtyPointTarget.getPointAmount() +" points");
                totalPoints += thirtyPointTarget.getPointAmount();

            } else if (twentyPointTarget.contains(currentBall.getCurrentCenter())) {
                currentTargetCircle = twentyPointTarget;
               gameMessagesLabel.setText("Not Bad..");
                currentShotPointsLabel.setText(twentyPointTarget.getPointAmount() +" points");
                totalPoints += twentyPointTarget.getPointAmount();

            } else if (tenPointTarget.contains(currentBall.getCurrentCenter())) {
                currentTargetCircle = tenPointTarget;
               gameMessagesLabel.setText("On the board.");
               currentShotPointsLabel.setText(tenPointTarget.getPointAmount() +" points");
                totalPoints += tenPointTarget.getPointAmount();

            } else {
               gameMessagesLabel.setText("Missed.");
               currentShotPointsLabel.setText("0 points");
                currentTargetCircle = null;
            }
          //Upadates the total points label
          totalPointLabel.setText("Total: " + totalPoints);   
     
        initialMousePress = null;
        gamePanel.repaint();
        }
    
        }
    
    }

    @Override
    public void run() {
        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("Ski Ball");
        frame.setPreferredSize(new Dimension(LANE_WIDTH + 225, LANE_HEIGHT));
        frame.setResizable(false);

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // g.setColor(Color.BLUE);
                g.drawRect(0, 0, LANE_WIDTH - 1, LANE_HEIGHT - 250);
                g.drawRect(0, LANE_HEIGHT - 250, LANE_WIDTH - 1, LANE_HEIGHT);
                
                //Draws all the target circles
                for (TargetCircle s : TargetCircles) {
                    s.paint(g);
                }
                
                //draws the ball 
                if (currentMouse != null) {
                    currentBall.paint(g);
                }

                //Makes the slingshot line from the foul line to ball
                if (slingShotStart != null && mouseReleased == false) {
                    g.drawLine(slingShotStart.x, slingShotStart.y,
                            currentBall.getCurrentCenter().x, currentBall.getCurrentCenter().y);
                }
               
                 //Checks which target the ball landed on and changes that color of the ball
                 //depending on where it landed
                if (currentBall != null && mouseReleased == true) {
                    if (currentTargetCircle == tenPointTarget) {
                        currentBall = new Ball(currentBall.size, currentBall.upperLeft,
                                tenPointTarget.getColor().darker());
                    } else if (currentTargetCircle == twentyPointTarget) {
                        currentBall = new Ball(currentBall.size, currentBall.upperLeft,
                                twentyPointTarget.getColor().darker());
                    } else if (currentTargetCircle == thirtyPointTarget) {
                        currentBall = new Ball(currentBall.size, currentBall.upperLeft,
                                thirtyPointTarget.getColor().darker());
                    } else if (currentTargetCircle == fiftyPointTarget) {
                        currentBall = new Ball(currentBall.size, currentBall.upperLeft,
                                fiftyPointTarget.getColor().darker());
                    } else  {
                        currentBall = new Ball(currentBall.size, currentBall.upperLeft,
                                gamePanel.getBackground().darker());
                    }
                    currentBall.paint(g);

                    if(shots == 0){
                        gameStarted = false;
                     currentButton.setText("Play Again");
                    }
                }
            }

        };

        //Panel for the game scoreboard and other stats
        scoreboardPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(scoreboardPanel,BoxLayout.Y_AXIS);
        scoreboardPanel.setLayout(boxLayout);


        // All the points of the cicrles
        Point2D.Double tenPoints = new Point2D.Double(75, 40);
        tenPointTarget = new TargetCircle(250, tenPoints, new Color(20, 9, 232), 10);
        TargetCircles.add(tenPointTarget);
        Point2D.Double twentyPoints = new Point2D.Double(125, 90);
        twentyPointTarget = new TargetCircle(150, twentyPoints, new Color(13, 222, 177), 20);
        TargetCircles.add(twentyPointTarget);
        Point2D.Double thirtyPoints = new Point2D.Double(172.5, 137.5);
        thirtyPointTarget = new TargetCircle(55, thirtyPoints, new Color(247, 207, 2), 30);
        TargetCircles.add(thirtyPointTarget);
        Point2D.Double fiftyPoints = new Point2D.Double(174, 41);
        fiftyPointTarget = new TargetCircle(50, fiftyPoints, Color.RED, 50);
        TargetCircles.add(fiftyPointTarget);

        //Scoreboard panel 
        gamePanel.setPreferredSize(new Dimension(LANE_WIDTH, LANE_HEIGHT));
        scoreboardPanel.setPreferredSize(new Dimension(185, 400));
        JLabel skiBallLabel = new JLabel("Ski Ball!");
        skiBallLabel.setFont(new Font("Arial", Font.BOLD , 40));
        scoreboardPanel.add( skiBallLabel);
        scoreboardPanel.add( gameMessagesLabel);
        scoreboardPanel.add(currentShotPointsLabel);
        scoreboardPanel.add(shotsLeftLabel);
        scoreboardPanel.add(totalPointLabel);
        scoreboardPanel.add(currentButton);

        currentButton.addActionListener(this);
      

        frame.add(gamePanel, BorderLayout.WEST);
        frame.add(scoreboardPanel, BorderLayout.EAST);

        
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * A Class that makes a skee ball to throw.
     */
    class Ball {
        private Point currentCenter;
        protected int size;
        protected Point upperLeft;
        protected Color color;

        public Ball(int size, Point upperLeft, Color color) {
            this.size = size;
            this.color = color;
            // where to draw the circle
            this.upperLeft = upperLeft;
            this.currentCenter = new Point(upperLeft.x + size / 2, upperLeft.y + size / 2);
        }

        /**
         * paint this object onto the given Graphics area
         * 
         * @param g the Graphics object where the shape should be drawn
         */
        public void paint(Graphics g) {
            g.setColor(color);
            g.fillOval((int) upperLeft.x, (int) upperLeft.y, size, size);
            this.currentCenter = new Point(upperLeft.x + size / 2, upperLeft.y + size / 2);
        }

        /**
         * Returns the center of the circle at any given call.
         * 
         */
        public Point getCurrentCenter() {
            return currentCenter;
        }

        /**
         * A relative move of this object.
         * 
         * @param dx amount to translate in x
         * @param dy amount to translate in y
         */
        public void translate(int dx, int dy) {
            upperLeft.translate(dx, dy);
            currentCenter = new Point(upperLeft.x + size / 2, upperLeft.y + size / 2);
        }

    }

    /**
     * A class that makes the target circles with the amount points
     * it gives if the ball lands on this target.
     * 
     */
    class TargetCircle {
        private int pointAmount;
        protected int size;
        protected Point2D.Double upperLeft;
        protected Color color;

        public TargetCircle(int size, Point2D.Double upperLeft, Color color, int pointAmount) {
            this.size = size;
            this.color = color;
            this.pointAmount = pointAmount;
            // where to draw the circle
            this.upperLeft = upperLeft;
        }

        /**
         * Determine if the given point is within this target
         * 
         * @param p Point to check
         */
        public boolean contains(Point p) {
            Point2D.Double circleCenter = new Point2D.Double(upperLeft.x + size / 2, upperLeft.y + size / 2);
            return circleCenter.distance(p) <= size / 2;
        }

        /**
         * Determine if the given point is within this shape.
         * 
         * @return the color of the thr target circle.
         */
        public Color getColor() {
            return this.color;
        }

        /**
         * paint this object onto the given Graphics area
         * 
         * @param g the Graphics object where the shape should be drawn
         */
        public void paint(Graphics g) {
            g.setColor(color);
            g.fillOval((int) upperLeft.x, (int) upperLeft.y, size, size);
            g.setColor(Color.BLACK);
            g.drawOval((int) upperLeft.x, (int) upperLeft.y, size, size);
        }

        public int getPointAmount() {
            return this.pointAmount;
        }

    }

    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new SkiBall());
    }
}
