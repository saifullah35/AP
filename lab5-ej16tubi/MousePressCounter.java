import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Lab 5 demo of mouse events.
 * 
 * @author Saif Ullah and Ethan Tubia
 * @version Spring 2022
 */
public class MousePressCounter extends MouseAdapter implements Runnable, ActionListener{
	protected int clicks;
	protected JButton reset;
	protected JLabel counter;
	/**
	 * The run method to set up the graphical user interface
	 */
	@Override
	public void run() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("MouseDemo");
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reset = new JButton("reset");
		reset.addActionListener(this);
		counter = new JLabel("Mouse press Count: 0");

		// construct an anonymous class that extends JPanel,
		// for which we override the paintComponent method
		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				super.paintComponent(g);

				FontMetrics fm = g.getFontMetrics();

				String toDisplay = "Mouse Around and See!";
				int stringWidth = fm.stringWidth(toDisplay);
				int stringAscent = fm.getAscent();

				int xStart = getWidth() / 2 - stringWidth / 2;
				int yStart = getHeight() / 2 + stringAscent / 2;

				g.drawString(toDisplay, xStart, yStart);
			}
		};
		frame.add(panel);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.addMouseWheelListener(this);
		panel.add(counter);
		panel.add(reset);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

    @Override
	public void mouseClicked(MouseEvent e) {
		clicks ++;
		counter.setText("Mouse Press Counter: " + clicks);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		clicks = 0;
		counter.setText("Mouse Press Counter: 0");
	}

    public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new MousePressCounter());
	}
}
