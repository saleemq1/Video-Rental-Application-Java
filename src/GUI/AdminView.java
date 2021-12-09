package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Users.Admin;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class AdminView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField title;
	private JTextField year;
	private JTextField category;
	private JTextField stock;
	private JTextField userName;
	Admin admin = new Admin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
					frame.setVisible(true);
					frame.setTitle("Admin");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 415);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(UIManager.getColor("CheckBoxMenuItem.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JTextField();
		title.setBounds(36, 48, 120, 19);
		contentPane.add(title);
		title.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(25, 25, 112));
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(137, 199, 335, 15);
		contentPane.add(label);
		
		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setBackground(new Color(128, 0, 0));
		btnAddMovie.setForeground(Color.WHITE);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(title.getText().isEmpty() == false && year.getText().isEmpty() == false && category.getText().isEmpty() == false && stock.getText().isEmpty() == false) {
				admin.addMovie(title.getText(), year.getText(), category.getText(), stock.getText());
				label.setText("Movie added!");
				}
				else {
					label.setText("Fill in all entries");
				}
			}
		});
		btnAddMovie.setBounds(92, 116, 134, 44);
		contentPane.add(btnAddMovie);
		
		JButton btnRemoveMovie = new JButton("Remove Movie (Title only)");
		btnRemoveMovie.setBackground(new Color(128, 0, 0));
		btnRemoveMovie.setForeground(Color.WHITE);
		btnRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(title.getText().isEmpty() == false) {
					admin.removeMovie(title.getText());
					label.setText("Movie removed if it was in Database");
					}
					else {
						label.setText("Insert movie in Title entry");
					}
			}
		});
		btnRemoveMovie.setBounds(241, 116, 234, 44);
		contentPane.add(btnRemoveMovie);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setBackground(new Color(128, 0, 0));
		btnRemoveUser.setForeground(Color.WHITE);
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userName.getText().isEmpty() == false) {
					admin.removeUser(userName.getText());
					label.setText("User removed if they were in Database");
					}
					else {
						label.setText("Enter User Name");
					}
			}
		});
		btnRemoveUser.setBounds(291, 242, 134, 43);
		contentPane.add(btnRemoveUser);
		
		year = new JTextField();
		year.setBounds(168, 48, 128, 19);
		contentPane.add(year);
		year.setColumns(10);
		
		category = new JTextField();
		category.setBounds(305, 48, 124, 19);
		contentPane.add(category);
		category.setColumns(10);
		
		stock = new JTextField();
		stock.setBounds(441, 48, 114, 19);
		contentPane.add(stock);
		stock.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setBounds(73, 69, 77, 19);
		contentPane.add(lblTitle);
		
		JLabel lblYearOfRelease = new JLabel("Year of Release");
		lblYearOfRelease.setForeground(Color.LIGHT_GRAY);
		lblYearOfRelease.setBounds(168, 71, 113, 15);
		contentPane.add(lblYearOfRelease);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.LIGHT_GRAY);
		lblCategory.setBounds(325, 71, 70, 15);
		contentPane.add(lblCategory);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.LIGHT_GRAY);
		lblStock.setBounds(472, 71, 70, 15);
		contentPane.add(lblStock);
		
		userName = new JTextField();
		userName.setBounds(163, 251, 114, 25);
		contentPane.add(userName);
		userName.setColumns(10);
		
		JLabel lblLastName = new JLabel("User Name");
		lblLastName.setForeground(Color.LIGHT_GRAY);
		lblLastName.setBounds(183, 282, 90, 15);
		contentPane.add(lblLastName);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(new Color(25, 25, 112));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView login = new LoginView();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(472, 321, 90, 25);
		contentPane.add(btnLogOut);
		
	}
}
