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
	private JTextField text_Name;
	private JTextField text_Family;
	private JLabel lblName;
	private JLabel lblFamily;
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
		setBounds(100, 100, 298, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(43, 30, 74, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(101, 27, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(43, 61, 74, 14);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 58, 155, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		text_Name = new JTextField();
		text_Name.setBounds(101, 104, 155, 20);
		contentPane.add(text_Name);
		text_Name.setColumns(10);
		
		text_Family = new JTextField();
		text_Family.setBounds(101, 135, 157, 20);
		contentPane.add(text_Family);
		text_Family.setColumns(10);
		
		lblName = new JLabel("name:");
		lblName.setBounds(45, 107, 46, 14);
		contentPane.add(lblName);
		
		lblFamily = new JLabel("family:");
		lblFamily.setBounds(45, 138, 46, 14);
		contentPane.add(lblFamily);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user =new User(textField.getText(), textField_1.getText(),text_Name.getText(),text_Family.getText());
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
		btnRegister.setBounds(112, 181, 119, 33);
		contentPane.add(btnRegister);
		
		
	}

}
