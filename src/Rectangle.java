import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shapes{
    Point startDrag;
    Point endDrag;
    Color color;
	
	public Rectangle(Point startDrag, Point endDrag, Color color,User user) {
		super(startDrag, endDrag, color,user);
		// TODO Auto-generated constructor stub
		this.endDrag=endDrag;
		this.startDrag=startDrag;
		this.color=color;
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		
		g2d.drawRect(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x -endDrag.x), Math.abs(startDrag.y - endDrag.y));
			
	}

}
