package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Users.Login;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class RegisterView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField userName;
	private JTextField password;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JLabel lblUserType;
	Login login = new Login();
	private JLabel label;
	private JTextField adminUserName;
	private JPasswordField adminPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView frame = new RegisterView();
					frame = new RegisterView();
					frame.setVisible(true);
					frame.setTitle("Register");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 358);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("TextField.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstName = new JTextField();
		firstName.setBounds(183, 10, 134, 19);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(183, 39, 134, 19);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		email = new JTextField();
		email.setBounds(183, 72, 134, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		userName = new JTextField();
		userName.setBounds(183, 99, 134, 19);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(183, 130, 135, 19);
		contentPane.add(password);
		password.setColumns(10);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(55, 280, 368, 15);
		contentPane.add(label);
		
		adminUserName = new JTextField();
		adminUserName.setBounds(329, 99, 132, 19);
		contentPane.add(adminUserName);
		adminUserName.setColumns(10);
		
		adminPassword = new JPasswordField();
		adminPassword.setBounds(330, 130, 131, 19);
		contentPane.add(adminPassword);
		
		JComboBox<String> userTypes = new JComboBox<String>();
		userTypes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(userTypes.getSelectedItem().toString().equals("Customer")) {
					adminUserName.setVisible(false);
					adminPassword.setVisible(false);
					label.setText("");
				}
				else {
					adminUserName.setVisible(true);
					adminPassword.setVisible(true);
					label.setText("Enter existing Admin credentials");
				}
			}
		});
		userTypes.addItem("Customer");
		userTypes.addItem("Employee");
		userTypes.addItem("Admin");
		userTypes.setBounds(183, 161, 135, 24);
		contentPane.add(userTypes);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.LIGHT_GRAY);
		lblFirstName.setBounds(70, 12, 108, 15);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.LIGHT_GRAY);
		lblLastName.setBounds(70, 43, 98, 15);
		contentPane.add(lblLastName);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(107, 72, 88, 19);
		contentPane.add(lblNewLabel);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.LIGHT_GRAY);
		lblUserName.setBounds(70, 101, 108, 15);
		contentPane.add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setBounds(80, 132, 98, 15);
		contentPane.add(lblPassword);
		
		lblUserType = new JLabel("User Type");
		lblUserType.setForeground(Color.LIGHT_GRAY);
		lblUserType.setBounds(77, 166, 88, 15);
		contentPane.add(lblUserType);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(128, 0, 0));
		btnRegister.setForeground(SystemColor.text);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				if(firstName.getText().isEmpty() == false && lastName.getText().isEmpty() == false && email.getText().isEmpty() == false && userName.getText().isEmpty() == false && password.getText().isEmpty() == false) {
	
						// if Customer
						if(userTypes.getSelectedItem().toString().equals("Customer")) {
							login.registerCustomer(firstName.getText(), lastName.getText(), email.getText(), userName.getText(), password.getText());
							label.setText("You are now a registered Customer!");
						}
						// if Employee
						else if(userTypes.getSelectedItem().toString().equals("Employee") && adminUserName.getText().isEmpty() == false && adminPassword.getText().isEmpty() == false && login.isAdmin(adminUserName.getText(), adminPassword.getText())) {
							login.registerEmployee(firstName.getText(), lastName.getText(), email.getText(), userName.getText(), password.getText());
							label.setText("You are now registered as an Employee!");
							}
						// if Admin
						else if(userTypes.getSelectedItem().toString().equals("Admin") && adminUserName.getText().isEmpty() == false && adminPassword.getText().isEmpty() == false && login.isAdmin(adminUserName.getText(), adminPassword.getText())) {
							login.registerAdmin(firstName.getText(), lastName.getText(), email.getText(), userName.getText(), password.getText());
							label.setText("You are now registered as an Admin!");
							}
						else {
							label.setText("Invalid Admin credentials");
						}
								
			 }
				// empty fields
				else {
					label.setText("Fill in all entries!");
				}
							
			}
		});
		btnRegister.setBounds(70, 203, 98, 41);
		contentPane.add(btnRegister);
		
		JButton btnBackToLogin = new JButton("Back to Login");
		btnBackToLogin.setBackground(new Color(25, 25, 112));
		btnBackToLogin.setForeground(SystemColor.text);
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView log = new LoginView();
				log.setVisible(true);
				dispose();
			}
		});
		btnBackToLogin.setBounds(183, 203, 135, 41);
		contentPane.add(btnBackToLogin);
							
	}
}