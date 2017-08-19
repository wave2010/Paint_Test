import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shapes {
	private Point startDrag;
	private Point endDrag;
	private Color color;
	User user;

	public Shapes(Point startDrag, Point endDrag, Color color, User user) {
		this.startDrag = startDrag;
		this.endDrag = endDrag;
		this.color = color;
		this.user = user;
	}

	protected User getUser() {
		return user;
	}

	protected void setUser(User user) {
		this.user = user;
	}

	protected Point getStartDrag() {
		return startDrag;
	}

	protected void setStartDrag(Point startDrag) {
		this.startDrag = startDrag;
	}

	protected Point getEndDrag() {
		return endDrag;
	}

	protected void setEndDrag(Point endDrag) {
		this.endDrag = endDrag;
	}

	protected Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(Graphics g);

	abstract boolean contains(Point point);

	double distance(Point a, Point b) {
		return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
	}
}