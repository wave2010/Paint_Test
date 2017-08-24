import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shapes {
	protected Point startDrag;
	protected Point endDrag;
	protected Color color;
	protected User user;

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
	abstract void resize(double scale);
	
}