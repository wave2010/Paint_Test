import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shapes {
	private int width;
	private int height;

	public Rectangle(Point startDrag, Point endDrag, Color color, User user) {
		super(startDrag, endDrag, color, user);
		width=Math.abs(startDrag.x - endDrag.x);
		height=Math.abs(startDrag.y - endDrag.y);
	}

	@Override
	public void draw(Graphics g,int w,int h) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		
		g2d.drawRect(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y)*1,
				width,height);
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

		return (((p.x == endDrag.x || p.x == startDrag.x || p.x == endDrag.x - 1 || p.x == startDrag.x - 1
				|| p.x == endDrag.x + 1 || p.x == startDrag.x + 1) && p.y > y2 && p.y < y1)
				|| ((p.y == endDrag.y || p.y == startDrag.y || p.y == endDrag.y - 1 || p.y == startDrag.y - 1
						|| p.y == endDrag.y + 1 || p.y == startDrag.y + 1) && p.x > x2 && p.x < x1));
	}

	@Override
	void resize(double scale) {
		width+=width* scale;
		height +=height*scale;

	}
}