import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserEntityManager {
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	static User users;

	public boolean informationUser(User user) {
		boolean flag = false;
		try {
			users = user;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * FROM user WHERE name ='" + user.getUsername() + "' and family='"
					+ user.getPassword() + "' ;");
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;

	}

	public void saveShape(Shapes shape) throws SQLException {
		if (shape instanceof Line) {
			Line l = (Line) shape;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO `line` (x1, y1, x2, y2, color, user) VALUES ('" + l.startDrag.x + "', '"
					+ l.startDrag.y + "', '" + l.endDrag.x + "', '" + l.endDrag.y + "', '"
					+ PaintMain.writeColor(l.getColor()) + "', '" + shape.getUser().getUsername() + "');");

			// test
			System.out.println("INSERT INTO `line` (x1, y1, x2, y2, color, user) VALUES ('" + l.startDrag.x + "', '"
					+ l.startDrag.y + "', '" + l.endDrag.x + "', '" + l.endDrag.y + "', '"
					+ PaintMain.writeColor(l.getColor()) + "', '" + shape.getUser().getUsername() + "');");

		} else if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			stmt.executeUpdate("INSERT INTO `circle` (x1, y1, hight, width, color, user) VALUES ('" + c.startDrag.x
					+ "', '" + c.startDrag.y + "', '" + c.endDrag.x + "', '" + c.endDrag.y + "', '"
					+ PaintMain.writeColor(c.getColor()) + "', '" + shape.getUser().getUsername() + "');");

			// test
			System.out.println("INSERT INTO `circle` (x1, y1, x2, y2, color, user) VALUES ('" + c.startDrag.x + "', '"
					+ c.startDrag.y + "', '" + c.endDrag.x + "', '" + c.endDrag.y + "', '"
					+ PaintMain.writeColor(c.getColor()) + "', '" + shape.getUser().getUsername() + "');");

		} else if (shape instanceof Rectangle) {
			Rectangle r = (Rectangle) shape;
			stmt.executeUpdate("INSERT INTO `rectangle` (x1, y1, x2, y2, color, user) VALUES ('" + r.startDrag.x
					+ "', '" + r.startDrag.y + "', '" + r.endDrag.x + "', '" + r.endDrag.y + "', '"
					+ PaintMain.writeColor(r.getColor()) + "', '" + shape.getUser().getUsername() + "');");
			// test
			System.out.println("INSERT INTO `rectangle` (x1, y1, x2, y2, color, user) VALUES ('" + r.startDrag.x
					+ "', '" + r.startDrag.y + "', '" + r.endDrag.x + "', '" + r.endDrag.y + "', '"
					+ PaintMain.writeColor(r.getColor()) + "', '" + shape.getUser().getUsername() + "');");

		}

	}

	public ArrayList<Shapes> loadShapes() throws SQLException {
		ArrayList<Shapes> shapesuser = new ArrayList<>();
		addCircles(shapesuser);
		addLines(shapesuser);
		addRectangles(shapesuser);
		return shapesuser;
	}

	private void addRectangles(ArrayList<Shapes> shapes) throws SQLException {
		con = ConnectionManager.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * from rectangle ;");
		
		try {
			if (rs != null)
				while (rs.next()) {
					int x = rs.getInt(1);
					int y = rs.getInt(2);
					int w = rs.getInt(3);
					int h = rs.getInt(4);
					Color c = PaintMain.readColor(rs.getString(5));
					User u = new User(rs.getString(6), null);

					if (u.getUsername().equals(users.getUsername())) {
						shapes.add(new Rectangle(new Point(x, y), new Point(w, h), c, u));
						
					}

				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addCircles(ArrayList<Shapes> shapes) throws SQLException {
		con = ConnectionManager.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * from circle ;");
		try {
			if (rs != null)
				while (rs.next()) {
					int x = rs.getInt(1);
					int y = rs.getInt(2);
					int r = rs.getInt(3);
					int r2 = rs.getInt(4);
					Color c = PaintMain.readColor(rs.getString(5));
					User u = new User(rs.getString(6), null);
					if (u.getUsername().equals(users.getUsername())) {
						shapes.add(new Circle(new Point(x, y), new Point(r, r2), c, u));
						
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addLines(ArrayList<Shapes> shapes) throws SQLException {
		con = ConnectionManager.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * from line ;");
		try {
			if (rs != null)
				while (rs.next()) {
					int x1 = rs.getInt(1);
					int y1 = rs.getInt(2);
					int x2 = rs.getInt(3);
					int y2 = rs.getInt(4);
					Color c = PaintMain.readColor(rs.getString(5));
					User u = new User(rs.getString(6), null);
					if (u.getUsername().equals(users.getUsername())) {
						shapes.add(new Line(new Point(x1, y1), new Point(x2, y2), c, u));
						
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
