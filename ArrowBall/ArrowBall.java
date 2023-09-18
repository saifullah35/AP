import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A program to demonstrate keyboard event handlers.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class ArrowBall implements Runnable, KeyListener {

	// ball size
	public static final int SIZE = 50;

	// amount to move on each arrow key press
	public static final int MOVE_BY = 2;

	// current coordinates of the upper left corner of the circle
	private Point upperLeft = new Point(50, 50);

	private JPanel panel;

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
		JFrame frame = new JFrame("ArrowBall");
		frame.setPreferredSize(new Dimension(500, 500));

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

				// redraw our circle at its current position
				g.fillOval(upperLeft.x, upperLeft.y, SIZE, SIZE);
			}
		};
		frame.add(panel);
		frame.addKeyListener(this);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Handle key typed, not used here.
	 * 
	 * @param e KeyEvent for this key typed
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Handle key released, not used here.
	 * 
	 * @param e KeyEvent for this key released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Handle the arrow key press, which results in motion of the ball.
	 *
	 * @param e the KeyEvent to determine direction
	 */
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upperLeft.translate(0, -MOVE_BY);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			upperLeft.translate(0, MOVE_BY);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			upperLeft.translate(-MOVE_BY, 0);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			upperLeft.translate(MOVE_BY, 0);
		}

		// trigger paint so we can see the ball in its new location
		panel.repaint();
	}

	public static void main(String args[]) {

		// The main method is responsible for creating a thread (more
		// about those later) that will construct and show the graphical
		// user interface.
		javax.swing.SwingUtilities.invokeLater(new ArrowBall());
	}
}
