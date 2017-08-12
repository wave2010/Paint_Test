import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shapes {

	Point startDrag;
	Point endDrag;
	Color color;
	public Line(Point startDrag, Point endDrag, Color color,User user) {
		super(startDrag, endDrag, color,user);
		// TODO Auto-generated constructor stub
		this.startDrag=startDrag;
		this.endDrag=endDrag;
		this.color=color;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
	}
}
