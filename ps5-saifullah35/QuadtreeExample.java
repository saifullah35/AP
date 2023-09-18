/**
 * A program that builds a Quadtree structure, adds some points,
 * and iterates over them. Assumes the implementation in the 
 * PointSet2D example.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
public class QuadtreeExample {

	public static final int REFINEMENT_THRESHOLD = 3;

	public static void main(String args[]) {

		Quadtree<Waypoint> q = new Quadtree<Waypoint>(-100, 100, -100, 100,
				REFINEMENT_THRESHOLD);
		q.add(new Waypoint("A", -32, 17));
		q.add(new Waypoint("B", 27, 13));
		q.add(new Waypoint("C", 14, 87));
		q.add(new Waypoint("D", 77, 12));
		q.add(new Waypoint("E", -10, -10));
		q.add(new Waypoint("F", 82, 10));
		q.add(new Waypoint("G", 58, 20));
		q.add(new Waypoint("H", 62, 8));
		q.add(new Waypoint("I", 71, 4));
		q.add(new Waypoint("J", -87, 75));

		java.util.Iterator<Waypoint> i = q.iterator();
		int count = 0;
		while (i.hasNext()) {
			count++;
			Waypoint w = i.next();
		}
		System.out.println(count);
	}
}
