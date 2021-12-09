package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Users.Login;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	Login login = new Login();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
					frame.setTitle("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 336);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("TabbedPane.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.LIGHT_GRAY);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setBounds(40, 77, 78, 15);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(40, 117, 78, 15);
		contentPane.add(lblPassword);
		
		userName = new JTextField();
		userName.setBounds(128, 72, 152, 25);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(128, 112, 152, 25);
		contentPane.add(password);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(40, 232, 256, 15);
		contentPane.add(label);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// if user is registered and everything filled in
				if(login.login(userName.getText(), password.getText()) && userName.getText().isEmpty()== false && password.getText().isEmpty()== false) {
					//if user is Admin
					if(login.isAdmin(userName.getText(), password.getText())) {
						AdminView adminView = new AdminView();
						adminView.setVisible(true);
						dispose();
					
					}
					// if user is Customer
					else if(login.isCustomer(userName.getText(), password.getText())){
						HomePage home = new HomePage();
						home.setVisible(true);
						dispose();
					}
					// user is Employee
					else {
						WarehouseEmployeeView wev = new WarehouseEmployeeView();
						wev.setVisible(true);
						dispose();
					}
				}
				
				//else print invalid login
				else {
					label.setText("Invalid Username or password");
				}
			}
		});
		btnLogin.setBounds(40, 177, 94, 43);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(25, 25, 112));
		btnRegister.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			RegisterView reg = new RegisterView();
			reg.setVisible(true);
			dispose();
		}
		});
		btnRegister.setBounds(148, 177, 94, 43);
		contentPane.add(btnRegister);
		
		JLabel lblWelcomeToVideoco = new JLabel("Welcome to VideoCo");
		lblWelcomeToVideoco.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToVideoco.setForeground(new Color(210, 180, 140));
		lblWelcomeToVideoco.setBackground(Color.BLUE);
		lblWelcomeToVideoco.setFont(new Font("Open Sans Light", Font.BOLD, 24));
		lblWelcomeToVideoco.setBounds(148, 12, 262, 25);
		contentPane.add(lblWelcomeToVideoco);
		
		
	}
}
