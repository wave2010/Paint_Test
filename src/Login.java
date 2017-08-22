import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtpassword;
	UserEntityManager uem = new UserEntityManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 162);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtname.setBounds(95, 23, 155, 28);
		contentPane.add(txtname);
		txtname.setColumns(10);

		txtpassword = new JTextField();
		txtpassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpassword.setColumns(10);
		txtpassword.setBounds(95, 58, 155, 28);
		contentPane.add(txtpassword);

		JButton btnButton = new JButton("Login");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User(txtname.getText(), txtpassword.getText());
				boolean login = uem.informationUser(user);
				if (login == false) {
					JOptionPane.showMessageDialog(null, "Please Try Again", "Warning", 0);
				} else {
					Login.this.setVisible(false);
					PaintMain.enter(user);
				}

			}
		});
		btnButton.setBounds(95, 98, 89, 23);
		contentPane.add(btnButton);

		JLabel lblUser = new JLabel("Username :");
		lblUser.setBounds(10, 28, 75, 21);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 68, 75, 21);
		contentPane.add(lblPassword);
	}
}
