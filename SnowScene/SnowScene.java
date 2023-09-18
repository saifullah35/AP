
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A program to demonstrate threads controlling graphical animations
 * by simulating a snowstorm.
 * 
 * IMPORTANT NOTE: Thread safety is not ensured! We will get to that
 * soon. Concurrency adds complications that we have not yet seen how
 * to handle!
 * 
 * Based on example from CSCI 134, Williams College
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class SnowScene extends MouseAdapter implements Runnable {

	// constants for the moon size and location
	private static final int MOON_INSET = 50;
	private static final int MOON_SIZE = 70;

	// sizes and locations for the bricks
	private static final int BRICK_LINE = 40; // from bottom
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_SPACING = 3;

	// colors of the sky, mortar, and brick
	private final Color NIGHTSKY = new Color(50, 50, 100);
	private final Color MORTAR = new Color(200, 200, 200);
	private final Color BRICKRED = new Color(150, 40, 40);

	// list of Cloud objects currently in existence
	private java.util.List<Cloud> list;

	private JPanel panel;

	/**
	 * Method to redraw our basic winter scene in the graphics panel.
	 * 
	 * @param g the Graphics object in which to paint
	 */
	protected void redrawScene(Graphics g) {
		int width = panel.getWidth();
		int height = panel.getHeight();

		// draw solid sky, mortar, and moon
		g.setColor(NIGHTSKY);
		g.fillRect(0, 0, width, height - BRICK_LINE);
		g.setColor(MORTAR);
		g.fillRect(0, height - BRICK_LINE, width, height);
		g.setColor(Color.WHITE);
		g.fillOval(MOON_INSET, MOON_INSET, MOON_SIZE, MOON_SIZE);

		// add the bricks
		int brickPosition = 0;
		g.setColor(BRICKRED);
		while (brickPosition < width) {
			g.fillRect(brickPosition, height - BRICK_LINE,
					BRICK_WIDTH, BRICK_HEIGHT);

			brickPosition = brickPosition + BRICK_WIDTH + BRICK_SPACING;
		}
	}

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
		JFrame frame = new JFrame("SnowScene");
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

				// redraw our main scene
				redrawScene(g);

				// for debugging purposes, draw the list size in the window
				g.setColor(Color.WHITE);
				g.drawString("" + list.size(), 10, 10);

				// redraw each cloud's contents, and along the
				// way, remove the ones that are done
				int i = 0;
				while (i < list.size()) {
					Cloud c = list.get(i);
					if (c.done()) {
						list.remove(i);
					} else {
						c.paint(g);
						i++;
					}
				}
			}
		};
		frame.add(panel);
		panel.addMouseListener(this);

		// construct the list
		list = new Vector<Cloud>();

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Mouse press event handler to create a new Cloud that will
	 * then start generating snowflakes for our scene.
	 * 
	 * @param e mouse event info
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		Cloud newCloud = new Cloud(panel);
		list.add(newCloud);

		// calling start on the object that extends Thread results in
		// its run method being called once the operating system and
		// JVM have set up the thread
		newCloud.start();
	}

	public static void main(String args[]) {

		// create the snowflake image that will be used by the
		// FallingSnow objects
		FallingSnow.loadSnowPic();

		// launch the main thread that will manage the GUI
		javax.swing.SwingUtilities.invokeLater(new SnowScene());
	}
}
