import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shapes {
	Point startDrag;
	Point endDrag;
	Color color;

	public Rectangle(Point startDrag, Point endDrag, Color color, User user) {
		super(startDrag, endDrag, color, user);
		this.endDrag = endDrag;
		this.startDrag = startDrag;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawRect(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y),
				Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
	}

	@Override
	boolean contains(Point p) {
		   int y1, y2, x1, x2;
	        if (startDrag.y >= endDrag.y) {
	            y1 = startDrag.y;
	            y2 = endDrag.y;
	        } else {
	            y1 = endDrag.y;
	            y2 = startDrag.y;
	        }
	        if (startDrag.x >= endDrag.x) {
	            x1 = startDrag.x;
	            x2 = endDrag.x;
	        } else {
	            x1 = endDrag.x;
	            x2 = startDrag.x;
	        }

	        return (((p.x == endDrag.x || p.x == startDrag.x || p.x == endDrag.x - 1 || p.x == startDrag.x - 1 || p.x == endDrag.x + 1 || p.x == startDrag.x + 1) && p.y > y2 && p.y < y1) || ((p.y == endDrag.y || p.y == startDrag.y || p.y == endDrag.y - 1 || p.y == startDrag.y - 1 || p.y == endDrag.y + 1 || p.y == startDrag.y + 1) && p.x > x2 && p.x < x1));
	}
}