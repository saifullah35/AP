
// going to be lazy about imports in these examples...
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The Triangle class, construct a triangle that can be drawn
 * on a Graphics area.
 */
class Triangle {

	// corner points
	private Point corners[];

	/**
	 * Construct a new Triangle.
	 * 
	 * @param corner1 first corner of the triangle
	 * @param corner2 second corner of the triangle
	 * @param corner3 third corner of the triangle
	 */
	public Triangle(Point corner1, Point corner2, Point corner3) {

		corners = new Point[3];
		corners[0] = corner1;
		corners[1] = corner2;
		corners[2] = corner3;
	}

	/**
	 * Draw the triangle on the given Graphics object.
	 * 
	 * @param g the Graphics object on which the triangle should be drawn
	 */
	public void paint(Graphics g) {

		g.drawLine(corners[0].x, corners[0].y, corners[1].x, corners[1].y);
		g.drawLine(corners[1].x, corners[1].y, corners[2].x, corners[2].y);
		g.drawLine(corners[2].x, corners[2].y, corners[0].x, corners[0].y);
	}
}

/**
 * A program to draw triangles on the screen.
 * 
 * @author Jim Teresco (starter)
 * @author 
 * @version Spring 2022
 */

public class DrawTriangles extends MouseAdapter implements Runnable {

	private JPanel panel;

	//partial triangle info
	private Point firstPress;
	private Point secondPress;
	private Point currentMouse;


	//list of completed triangles
	private ArrayList<Triangle> triangles = new ArrayList<>();
	

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
		JFrame frame = new JFrame("DrawTriangles");
		frame.setPreferredSize(new Dimension(800, 800));

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

				//draw any triangle in progress
				if (currentMouse != null){
					if (firstPress != null){
                        //we have pressed at least once to define a triangle
						if(secondPress == null){
							//draw rubber-banding line
							g.drawLine(firstPress.x, firstPress.y, currentMouse.x, currentMouse.y);
						} else{
							//draw rubber-banding triangle
							g.drawLine(firstPress.x, firstPress.y, secondPress.x, secondPress.y);
							g.drawLine(firstPress.x, firstPress.y, currentMouse.x, currentMouse.y);
							g.drawLine(currentMouse.x, currentMouse.y, secondPress.x, secondPress.y);
						}
					}
				}
				for (Triangle t : triangles)
				t.paint(g);
			}
		};
		frame.add(panel);

		firstPress = null;
		secondPress = null;
		currentMouse = null;

		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e){

		currentMouse = e.getPoint();
        if (firstPress == null){
            firstPress = e.getPoint();
		} else if(secondPress == null){
			secondPress = e.getPoint();
		} else{
			triangles.add(new Triangle(firstPress, secondPress, currentMouse));
			firstPress = null;
			secondPress = null;
			currentMouse = null;
		}

		panel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e){
		currentMouse = e.getPoint();
		panel.repaint();
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new DrawTriangles());
	}
}
