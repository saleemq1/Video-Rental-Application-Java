package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ServiceItems.Movie;
import ServiceItems.Order;
import Users.Customer;
import Users.Login;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class OrdersView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Login login = new Login();
	Order order = new Order();
	Movie movie = new Movie();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdersView frame = new OrdersView();
					frame.setVisible(true);
					frame.setTitle("Your Orders");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrdersView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 346);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("DesktopIcon.foreground"));
		contentPane.setForeground(UIManager.getColor("DesktopIcon.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> orders = new JComboBox<String>();
		for(String o: order.getOrders(Login.savedUserName)) {
			orders.addItem(o);
		}
		orders.setBounds(43, 41, 668, 24);
		contentPane.add(orders);
		
		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.setForeground(new Color(255, 255, 255));
		btnBackToHome.setBackground(new Color(25, 25, 112));
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage homepage = new HomePage();
				homepage.setVisible(true);
				dispose();	
			}
		});
		btnBackToHome.setBounds(26, 224, 142, 58);
		contentPane.add(btnBackToHome);
				
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(132, 98, 498, 15);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Cancel Order");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orders.getSelectedItem().toString().equals(null) && orders.getItemAt(0) == null) {
					label.setText("Select order to cancel");
				}
				else if(orders.getSelectedItem().toString().contains("Order is being processed")){
					// increment stock of movie
					movie.incrementStock(order.getTitle((String) orders.getSelectedItem()));
					// refund points if paid with points
					if(orders.getSelectedItem().toString().contains("Pay with Loyalty Points")) {
						Customer.addLoyaltyPoints(Login.savedUserName, 10);
					}
					// remove order from db
					order.removeOrder((String) orders.getSelectedItem());
					// remove order from combobox
					orders.removeItem(orders.getSelectedItem());
					// print message
					label.setText("Order canceled!");
					// This fixed bug where item still displayed when combobox was empty
					if(orders.getItemAt(0) == null) {
						orders.removeAllItems();
					}
				}
				else {
					label.setText("Cannot cancel order. Order is already delivered.");
				}
			}
		});
		btnNewButton.setBounds(377, 138, 142, 50);
		contentPane.add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setForeground(new Color(255, 255, 255));
		btnReturn.setBackground(SystemColor.desktop);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orders.getSelectedItem().toString().equals(null) && orders.getItemAt(0) == null){
					label.setText("Select order to cancel");
				}
				// if item status delivered  or countdown is finished then you can return
				else if(orders.getSelectedItem().toString().contains("Delivered")) {
					// increment stock
					movie.incrementStock(order.getTitle(orders.getSelectedItem().toString()));
					// remove from OrderDatabase
					order.removeOrder(orders.getSelectedItem().toString());
					// remove from orders combobox
					orders.removeItem(orders.getSelectedItem());
					// print message
					label.setText("Movie returned!");	
					// This fixed bug where item still displayed when combobox was empty
					if(orders.getItemAt(0) == null) {
						orders.removeAllItems();
					}
				}
				
				else {
					label.setText("Movie is not delivered yet");
				}
			}
		});
		btnReturn.setBounds(196, 138, 142, 50);
		contentPane.add(btnReturn);
			
	}
}
