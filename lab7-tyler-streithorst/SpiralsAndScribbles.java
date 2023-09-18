
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * This program draws a "Spiral of Lines". When the mouse is pressed
 * then dragged, a series of lines are drawn from the press point to
 * the current location.
 * 
 * This will be enhanced as part of a lab exercise with various other
 * functionality.
 * 
 * @author Jim Teresco (initial Spiral drawing)
 * @author Tyler Streithorst and Jonathan Masih
 * @version Spring 2022
 */

public class SpiralsAndScribbles extends MouseAdapter implements Runnable {

	// a list of pairs of points to keep track of the start and end coordinates
	// where we need to draw lines
	private ArrayList<Point[]> lines = new ArrayList<>();
	private ArrayList<Color> colors = new ArrayList<>();

	// press point for the current spiral
	private Point pressPoint;
	private Point lastPoint;

	private JPanel panel;
	private JPanel controlPanel;
	private JCheckBox screenClear;
	private JRadioButton[] drawType = new JRadioButton[2];
	private JComboBox<String> colorful;
	private Color colorfulColor;

	// this method is called by the paintComponent method of
	// the anonymous extension of JPanel, to keep that method
	// from getting too long
	protected void redraw(Graphics g) {

		// draw all of the lines in the list
		for (int i = 0; i < lines.size(); i++) {
			g.setColor(colors.get(i));
			g.drawLine(lines.get(i)[0].x, lines.get(i)[0].y, lines.get(i)[1].x, lines.get(i)[1].y);
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
		JFrame frame = new JFrame("SpiralsAndScribbles");
		frame.setPreferredSize(new Dimension(600, 600));

		// tell the JFrame that when someone closes the
		// window, the application should terminate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel basePanel = new JPanel(new BorderLayout());

		// JPanel with a paintComponent method
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				// first, we should call the paintComponent method we are
				// overriding in JPanel
				super.paintComponent(g);

				// redraw our graphics items
				redraw(g);
			}
		};
		controlPanel = new JPanel();
		screenClear = new JCheckBox("Clear when mouse leave window");
		controlPanel.add(screenClear);

		//Radio Buttons
		drawType[0] = new JRadioButton("Spiral");
		drawType[1] = new JRadioButton("Scribble");
		drawType[0].setSelected(true);
		ButtonGroup drawTypeGroup = new ButtonGroup();
		drawTypeGroup.add(drawType[0]);
		drawTypeGroup.add(drawType[1]);
		controlPanel.add(drawType[0]);
		controlPanel.add(drawType[1]);

		//Colorful Modes
		colorful = new JComboBox<String>();
		colorful.addItem("Default");
		colorful.addItem("Colorful");
		colorful.addItem("More Colorful");
		colorful.addItem("Crazy Colorful");
		colorful.setSelectedItem("Default");
		controlPanel.add(colorful);

		basePanel.add(panel);
		basePanel.add(controlPanel, BorderLayout.SOUTH);
		frame.add(basePanel);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		pressPoint = e.getPoint();
		lastPoint = e.getPoint();
		if(colorful.getSelectedItem().equals("Colorful")) {
			colorfulColor = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
			for(int i = 0; i < lines.size(); i ++) {
				if(pressPoint.distance(lines.get(i)[0]) < 25)
					colors.set(i, colorfulColor);
			}
		}
		if(colorful.getSelectedItem().equals("More Colorful")) {
			for(int i = 0; i < lines.size(); i ++) {
				if(pressPoint.distance(lines.get(i)[0]) < 25)
					colors.set(i, new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
			}
		}
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		Point p[] = new Point[2];
		if(drawType[0].isSelected()) //Spiral
			p[0] = pressPoint;
		else {
			p[0] = lastPoint;
			lastPoint = e.getPoint();
		}
		p[1] = e.getPoint();
		lines.add(p);
		if(colorful.getSelectedItem().equals("Default"))
			colors.add(Color.BLACK);
		if(colorful.getSelectedItem().equals("Colorful"))
			colors.add(colorfulColor);
		if(colorful.getSelectedItem().equals("More Colorful"))
			colors.add(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
		if(colorful.getSelectedItem().equals("Crazy Colorful")) {
			colors.add(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
			for (int i = 0; i < colors.size(); i++) {
				colors.set(i, new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
			}
		}

		panel.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(screenClear.isSelected()) {
			lines.clear();
			colors.clear();
		}

		panel.repaint();
	}

	public static void main(String args[]) {

		// The main method is responsible for creating a thread (more
		// about those later) that will construct and show the graphical
		// user interface.
		javax.swing.SwingUtilities.invokeLater(new SpiralsAndScribbles());
	}
}
