import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PaintMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	UserEntityManager uem = new UserEntityManager();
	public static Color color = Color.MAGENTA;
	public static String name = null;
	public JRadioButton rdbtnBlack;
	public JRadioButton rdbtnBlue;
	public JRadioButton rdbtnGreen;
	public JRadioButton rdbtnRed;
	static User userlogin;
	public static boolean flagdelete = false;
	public static int scale = 1;
	static public BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void enter(User user) {
		userlogin = user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintMain frame = new PaintMain(userlogin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param userloginn
	 * @throws SQLException
	 * @throws IOException 
	 */
	public PaintMain(final User userloginn) throws SQLException, IOException {
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 463);
		contentPane = new JPanel();
		setContentPane(contentPane);
	    contentPane.setLayout(null);
		final PanelPaint panelpaint = new PanelPaint(userloginn);
		

		final JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnRectangle.getText();
			}
		});
		btnRectangle.setBounds(452, 11, 113, 23);
		contentPane.add(btnRectangle);

		final JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnLine.getText();

				 
				 
			}
		});
		btnLine.setBounds(452, 45, 113, 23);
		contentPane.add(btnLine);

		final JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnCircle.getText();

				
			}
		});
		btnCircle.setBounds(452, 79, 113, 23);
		contentPane.add(btnCircle);

		rdbtnRed = new JRadioButton("Red", false);
		rdbtnRed.setBounds(452, 109, 89, 23);
		rdbtnRed.setActionCommand("Red");
		contentPane.add(rdbtnRed);

		rdbtnGreen = new JRadioButton("Green", false);
		rdbtnGreen.setBounds(452, 134, 89, 23);
		rdbtnGreen.setActionCommand("Green");
		contentPane.add(rdbtnGreen);

		rdbtnBlue = new JRadioButton("Blue", false);
		rdbtnBlue.setBounds(452, 160, 93, 23);
		rdbtnBlue.setActionCommand("Blue");
		contentPane.add(rdbtnBlue);

		rdbtnBlack = new JRadioButton("Black", false);
		rdbtnBlack.setBounds(452, 186, 93, 23);
		rdbtnBlack.setActionCommand("Black");
		contentPane.add(rdbtnBlack);

		final ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnBlack);
		bG.add(rdbtnGreen);
		bG.add(rdbtnBlue);
		bG.add(rdbtnRed);

		JButton btnsave = new JButton("Exit");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnsave.setBounds(452, 401, 113, 23);
		contentPane.add(btnsave);

		JButton btnselect = new JButton("Select");
		btnselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// color=Color.MAGENTA;
				name = null;
				flagdelete = false;
			}
		});
		btnselect.setBounds(452, 216, 113, 23);
		contentPane.add(btnselect);

		JButton btnZoomIn = new JButton("Zoom In");
		btnZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelpaint.zoomIn();
			}
		});
		btnZoomIn.setBounds(452, 256, 113, 23);
		contentPane.add(btnZoomIn);

		JButton btnZoomOut = new JButton("Zoom Out");
		btnZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelpaint.zoomOut();
			}
		});
		btnZoomOut.setBounds(452, 280, 113, 23);
		contentPane.add(btnZoomOut);

		JButton btnD = new JButton("Delete All ");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					// flagdelete=true;
					name = null;
					color = null;
					uem.deleteShapes();
					PanelPaint panelpaint2 = new PanelPaint(userloginn);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnD.setBounds(452, 337, 113, 23);
		contentPane.add(btnD);

		JButton btnDeleteShape = new JButton("Delete Shape");
		btnDeleteShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = null;
				flagdelete = true;

			}
		});
		btnDeleteShape.setBounds(452, 314, 113, 23);
		contentPane.add(btnDeleteShape);

		JButton btnimage = new JButton("Save Image");
		btnimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Container c = panelpaint.getParent();
				BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
				c.paint(im.getGraphics());
				try {
					ImageIO.write(im, "PNG", new File("shot.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		btnimage.setBounds(452, 371, 113, 23);
		contentPane.add(btnimage);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 432, 418);
	
		panelpaint.setBackground(Color.WHITE);
		JScrollPane scroll=new JScrollPane(panelpaint);
		
		panel.add(scroll);
		contentPane.add(panel);
		contentPane.setVisible(true);

		class VoteActionListener implements ActionListener {
			public void actionPerformed(ActionEvent ex) {
				String choice = bG.getSelection().getActionCommand();
				color = readColor(choice);
			}
		}
		ActionListener al = new VoteActionListener();
		rdbtnBlack.addActionListener(al);
		rdbtnBlue.addActionListener(al);
		rdbtnGreen.addActionListener(al);
		rdbtnRed.addActionListener(al);
	}

	public static Color readColor(String str) {
		switch (str) {
		case "Black":
			return Color.BLACK;
		case "Red":
			return Color.RED;
		case "Blue":
			return Color.BLUE;
		case "Green":
			return Color.GREEN;
		default:
			return Color.CYAN;
		}
	}

	public static String writeColor(Color c) {
		if (c.equals(Color.black))
			return "Black";
		else if (c.equals(Color.red))
			return "Red";
		else if (c.equals(Color.blue))
			return "Blue";
		else if (c.equals(Color.green))
			return "Green";
		return "Cyan";
	}
}