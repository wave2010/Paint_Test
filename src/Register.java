import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private UserEntityManager dbms = new UserEntityManager();
	/**
	 * Launch the application.
	 */
	public static void enterreg() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(43, 30, 74, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(117, 27, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(43, 61, 74, 14);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 58, 155, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user =new User(textField.getText(), textField_1.getText());
				if(dbms.informationUser(user))
					JOptionPane.showMessageDialog(null, "user exist");
				else{
					dbms.addUser(user);
						JOptionPane.showMessageDialog(null, "add succesfully");
						Register.this.setVisible(false);
						Login.main(null);
					
				}
					
				}
		});
		btnRegister.setBounds(96, 98, 89, 23);
		contentPane.add(btnRegister);
	}

}
