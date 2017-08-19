import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;

public class Line extends Shapes {

	Point startDrag;
	Point endDrag;
	Color color;

	public Line(Point startDrag, Point endDrag, Color color, User user) {
		super(startDrag, endDrag, color, user);
		this.startDrag = startDrag;
		this.endDrag = endDrag;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
		List ls = new List();
		ls.add("Ali");
	}

	@Override
	boolean contains(Point point) {
		return (distance(startDrag, point) + distance(endDrag, point) == distance(startDrag, endDrag));
	}
}
