
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Quick demo that shows how a graphics image can be loaded and displayed.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class ShowSnowflake implements Runnable, ImageObserver {

	// we store the contents of the file in an Image object,
	// declared as static since we'd only ever need it once.
	private static Image snowflakeImage;

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
		JFrame frame = new JFrame("ShowSnowflake");
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

				// draw a big, blue background
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, 500, 500);

				// draw the snowflake
				g.drawImage(snowflakeImage, 100, 100, this);

			}
		};
		frame.add(panel);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);

	}

	// the method required by ImageObserver
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {

		if ((infoflags & ImageObserver.ALLBITS) > 0) {
			panel.repaint();
			return false;
		}
		return true;
	}

	public static void main(String args[]) {

		// create the snowflake image that will be drawn by the
		// paintComponent method
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		snowflakeImage = toolkit.getImage("snow.gif");

		// launch the main thread that will manage the GUI
		javax.swing.SwingUtilities.invokeLater(new ShowSnowflake());
	}
}
