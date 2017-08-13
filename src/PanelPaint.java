import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelPaint extends JPanel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public ArrayList<Shapes> shapes = new ArrayList<>();
	// protected Set<Shape> shapess = new HashSet<>();
	public Point startDrag, endDrag;
	Shapes shape;
	User userasli;
	UserEntityManager uem = new UserEntityManager();

	PanelPaint(User user) throws SQLException {
		shapes = uem.loadShapes();
		uem.loadShapes();
		userasli = user;
		setBounds(10, 11, 374, 293);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color!=null) {
					endDrag = new Point(e.getX(), e.getY());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color!=null) {
					startDrag = new Point(e.getX(), e.getY());
					endDrag = startDrag;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color!=null) {
					if (PaintMain.name == "Line") {
						shape = new Line(startDrag, endDrag, PaintMain.color, userasli);
						shapes.add(shape);
					} else if (PaintMain.name == "Rectangle") {
						shape = new Rectangle(startDrag, endDrag, PaintMain.color, userasli);
						shapes.add(shape);
					} else if (PaintMain.name == "Circle") {
						shape = new Circle(startDrag, endDrag, PaintMain.color, userasli);
						shapes.add(shape);
					}

					try {
						uem.saveShape(shape);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
					}
					startDrag = null;
					endDrag = null;
					repaint();
				}
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);
			for (Shapes s : shapes)
				s.draw(g);

			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				Shape shapejava = null;
				if (PaintMain.name == "Line") {
					shapejava = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				} else if (PaintMain.name == "Circle") {
					shapejava = makeOval(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				} else if (PaintMain.name == "Rectangle") {
					shapejava = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				}
				g2.draw(shapejava);
			}
		
	}

	private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
		return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
		return new Line2D.Float(x1, y1, x2, y2);
	}

	private Ellipse2D.Float makeOval(int x1, int y1, int x2, int y2) {
		return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2) * 2, Math.abs(y1 - y2) * 2);
	}

}
