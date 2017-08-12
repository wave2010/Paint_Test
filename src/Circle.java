import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shapes {

	Point startDrag;
	Point endDrag;
	Color color;
	
	public Circle(Point startDrag, Point endDrag, Color color,User user) {
		super(startDrag, endDrag, color,user);
		
		this.startDrag=startDrag;
		this.endDrag=endDrag;
		this.color=color;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		int dx=(int) Math.pow(endDrag.x, startDrag.x);
		int dy=(int)Math.pow(endDrag.y, startDrag.y);
		int d=(int)Math.sqrt(dx+dy);
		g2d.drawOval(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x -endDrag.x)*2, Math.abs(startDrag.y - endDrag.y)*2);
		
	}

	
}
