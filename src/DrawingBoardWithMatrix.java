import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawingBoardWithMatrix extends JFrame {

  public static void main(String[] args) {
    new DrawingBoardWithMatrix();
  }
//for test example
  public DrawingBoardWithMatrix() {
    this.setSize(300, 300);
  // this.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.add(new PaintSurface(), BorderLayout.CENTER);
    this.setVisible(true);
  }

  private class PaintSurface extends JComponent {
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    Point startDrag, endDrag;

    public PaintSurface() {
      this.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          startDrag = new Point(e.getX(), e.getY());
          endDrag = startDrag;
          repaint();
        }

        public void mouseReleased(MouseEvent e) {
          Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
          shapes.add(r);
          startDrag = null;
          endDrag = null;
          repaint();
        }
      });
    

      this.addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
          endDrag = new Point(e.getX(), e.getY());
          repaint();
        }
      });
    }
  
    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
     
      for (Shape s : shapes) {
        g2.setPaint(Color.BLACK);
        g2.draw(s);
       g2.fill(s);
      }

      //pish namayesh
      if (startDrag != null && endDrag != null) {
        g2.setPaint(Color.LIGHT_GRAY);
        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
        g2.draw(r);
  
      }
    }

  
	private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
		return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	private Line2D.Float makeline(int x1, int y1, int x2, int y2) {
		return new Line2D.Float(x1, y1, x2, y2);
	}

	private Ellipse2D.Float makeoval(int x1, int y1, int x2, int y2) {
		return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

    
  }
}