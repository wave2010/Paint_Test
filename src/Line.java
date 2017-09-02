import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends Shapes {


	public Line(Point startDrag, Point endDrag, Color color, User user) {
		super(startDrag, endDrag, color, user);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
	}

	@Override
	boolean contains(Point point) {
		 return new Line2D.Double(startDrag, endDrag).ptLineDist(point) <=.8;
	}

	@Override
	void resize(double scale) {
		
		
	}
}
