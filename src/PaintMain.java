import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
public class PaintMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// public JPanel panel_2;

	public static Color color;
	public static String name;
	public JRadioButton rdbtnBlack;
	public JRadioButton rdbtnBlue;
	public JRadioButton rdbtnGreen;
	public JRadioButton rdbtnRed;
	  static User userlogin;

	/**
	 * Launch the application.
	 */
	public static void enter( User user) {
	
	 userlogin=user;
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
	 * @param userloginn 
	 * @throws SQLException 
	 */
	public PaintMain(User userloginn) throws SQLException {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 354);
		contentPane = new JPanel();
		setContentPane(contentPane);

		contentPane.setLayout(null);
		PanelPaint panelpaint = new PanelPaint(userloginn);
		panelpaint.setBounds(10, 11, 374, 293);
		panelpaint.setBackground(Color.WHITE);
		contentPane.add(panelpaint);

		final JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnRectangle.getText();
			}
		});
		btnRectangle.setBounds(394, 11, 113, 23);
		contentPane.add(btnRectangle);

		final JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnLine.getText();
			}
		});
		btnLine.setBounds(394, 45, 113, 23);
		contentPane.add(btnLine);

		final JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = btnCircle.getText();
			}
		});
		btnCircle.setBounds(394, 79, 113, 23);
		contentPane.add(btnCircle);

		rdbtnRed = new JRadioButton("Red", false);
		rdbtnRed.setBounds(394, 109, 89, 23);
		rdbtnRed.setActionCommand("Red");
		contentPane.add(rdbtnRed);

		rdbtnGreen = new JRadioButton("Green", false);
		rdbtnGreen.setBounds(394, 134, 89, 23);
		rdbtnGreen.setActionCommand("Green");
		contentPane.add(rdbtnGreen);

		rdbtnBlue = new JRadioButton("Blue", false);
		rdbtnBlue.setBounds(394, 160, 93, 23);
		rdbtnBlue.setActionCommand("Blue");
		contentPane.add(rdbtnBlue);

		rdbtnBlack = new JRadioButton("Black", false);
		rdbtnBlack.setBounds(394, 186, 93, 23);
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
		btnsave.setBounds(394, 250, 113, 23);
		contentPane.add(btnsave);
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
