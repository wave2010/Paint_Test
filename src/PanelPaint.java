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
	// boolean result=false;
	public Point startDrag, endDrag;
	private Shapes shape;
	private User userasli;
	static UserEntityManager uem = new UserEntityManager();

	public void loadShapeFromDB() throws SQLException {
		shapes = uem.loadShapes();
	}

	PanelPaint(User user) throws SQLException {

		userasli = user;
		setBounds(10, 11, 374, 293);
		loadShapeFromDB();
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color != null) {
					endDrag = new Point(e.getX(), e.getY());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color != null) {
					startDrag = new Point(e.getX(), e.getY());
					endDrag = startDrag;
					repaint();
				} else {
					for (Shapes shapes2 : shapes) {

						if (shapes2 instanceof Rectangle) {

							boolean result = shapes2.contains(e.getPoint());
							if (result == true) {
								shapes2.setColor(PaintMain.color);

								try {
									uem.updateShape(shapes2);
									repaint();
									continue;
								} catch (SQLException e1) {
									e1.printStackTrace();
								}

							}
						} else if (shapes2 instanceof Circle) {
							boolean result = shapes2.contains(e.getPoint());
							if (result == true) {
								shapes2.setColor(PaintMain.color);
								try {
									uem.updateShape(shapes2);
									repaint();
									continue;
								} catch (SQLException ex) {
									ex.printStackTrace();
								}
							}

						} else if (shapes2 instanceof Line) {
							boolean result = shapes2.contains(e.getPoint());
							if (result == true) {
								shapes2.setColor(PaintMain.color);
								try {
									uem.updateShape(shapes2);
									repaint();
									continue;
								} catch (SQLException ex) {
									ex.printStackTrace();
								}

							}

						}
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (PaintMain.name != null && PaintMain.color != null) {
					if (PaintMain.name == "Line") {
						shape = new Line(startDrag, endDrag, PaintMain.color, userasli);
					} else if (PaintMain.name == "Rectangle") {
						shape = new Rectangle(startDrag, endDrag, PaintMain.color, userasli);

					} else if (PaintMain.name == "Circle") {
						shape = new Circle(startDrag, endDrag, PaintMain.color, userasli);
					}

					try {
						shapes.add(shape);
						uem.saveShape(shape);
						startDrag = null;
						endDrag = null;
						repaint();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

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
		// PishNamayesh
		if (startDrag != null && endDrag != null) {
			g2.setPaint(Color.LIGHT_GRAY);
			Shape shapejava = null;
			if (PaintMain.name == "Line") {
				shapejava = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
			} else if (PaintMain.name == "Circle") {
				shapejava = makeOval();
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

	private Ellipse2D.Double makeOval() {
		int cX = (int) Math.pow((endDrag.x - startDrag.x), 2);
		int cY = (int) Math.pow((endDrag.y - startDrag.y), 2);
		int radius = (int) Math.sqrt(cX + cY);
		cX = startDrag.x - radius;
		cY = startDrag.y - radius;
		return (new Ellipse2D.Double(cX, cY, 2 * radius, 2 * radius));
	}

	public static void deleteAlllShape() throws SQLException {
		uem.deleteShapes();
	}
}