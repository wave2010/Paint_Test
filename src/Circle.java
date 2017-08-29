import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;

public class Circle extends Shapes {


	int radius;

	public Circle(Point startDrag, Point endDrag, Color color, User user) {
		super(startDrag, endDrag, color, user);
		int dx = (int) Math.pow((endDrag.x - startDrag.x), 2);
		int dy = (int) Math.pow((endDrag.y - startDrag.y), 2);
		radius = (int) Math.sqrt(dx + dy);
	}

	@Override
	public void draw(Graphics g,int w,int h) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawOval(startDrag.x - radius, startDrag.y - radius, 2 * radius, 2 * radius);
	}

	@Override
	boolean contains(Point p) {
		int cx = (int) Math.pow((p.x - startDrag.x), 2);
		int cy = (int) Math.pow((p.y - startDrag.y), 2);
		int d = (int) Math.sqrt(cx + cy);
		return (d == radius || d == radius - 2 || d == radius - 1);
	}

	@Override
	void resize(double scale) {
		// TODO Auto-generated method stub	
		radius +=radius * scale;
	}
	
}