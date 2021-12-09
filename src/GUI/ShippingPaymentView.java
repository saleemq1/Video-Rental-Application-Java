package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import ServiceItems.Movie;
import ServiceItems.Order;
import Users.Customer;
import Users.Login;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;

public class ShippingPaymentView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Login login = new Login();
	private JTextField creditCardInput;
	private Customer customer = new Customer();
	private JTextField address;
	private JTextField postalCode;
	Order order = new Order();
	Movie movie = new Movie();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShippingPaymentView frame = new ShippingPaymentView();
					frame.setVisible(true);
					frame.setTitle("Order shipping and payment info");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShippingPaymentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 493);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("EditorPane.inactiveForeground"));
		contentPane.setBackground(UIManager.getColor("DesktopIcon.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoyaltyPoints = new JLabel("");
		lblLoyaltyPoints.setForeground(Color.WHITE);
		lblLoyaltyPoints.setBackground(Color.WHITE);
		lblLoyaltyPoints.setText("Loyalty points: " + customer .getLoyaltyPoints(Login.savedUserName));
		lblLoyaltyPoints.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblLoyaltyPoints.setBounds(257, 163, 150, 15);
		contentPane.add(lblLoyaltyPoints);
		
		JButton btnViewOrders = new JButton("View/Return/Cancel Orders");
		btnViewOrders.setBackground(new Color(128, 0, 0));
		btnViewOrders.setForeground(new Color(255, 255, 255));
		btnViewOrders.setVisible(false);
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdersView ov = new OrdersView();
				ov.setVisible(true);
				dispose();
			}
		});
		btnViewOrders.setBounds(304, 383, 247, 46);
		contentPane.add(btnViewOrders);
		
		creditCardInput = new JTextField();
		creditCardInput.setBounds(28, 201, 193, 25);
		contentPane.add(creditCardInput);
		creditCardInput.setColumns(10);
		
		JLabel lblEnterCreditCard = DefaultComponentFactory.getInstance().createLabel("Enter credit card number (16-digit)");
		lblEnterCreditCard.setForeground(Color.LIGHT_GRAY);
		lblEnterCreditCard.setBackground(Color.WHITE);
		lblEnterCreditCard.setFont(new Font("Dialog", Font.ITALIC, 9));
		lblEnterCreditCard.setBounds(38, 231, 193, 25);
		contentPane.add(lblEnterCreditCard);
		
		JComboBox<String> paymentType = new JComboBox<String>();
		paymentType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(paymentType.getSelectedItem().equals("Pay with Credit Card")) {
					creditCardInput.setVisible(true);
					lblEnterCreditCard.setVisible(true);
				}
				else {
					creditCardInput.setVisible(false);
					lblEnterCreditCard.setVisible(false);
				}
			}
		});
		paymentType.addItem("Pay with Credit Card");
		paymentType.addItem("Pay with Loyalty Points");
		paymentType.setBounds(28, 158, 190, 24);
		contentPane.add(paymentType);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(124, 268, 342, 15);
		contentPane.add(label);
		
		JButton btnPlaceOrder = new JButton("Place order");
		btnPlaceOrder.setBackground(SystemColor.desktop);
		btnPlaceOrder.setForeground(new Color(255, 255, 255));
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(address.getText().isEmpty() == false && postalCode.getText().isEmpty() == false) {
					
					// payment type is credit
					if(paymentType.getSelectedItem().equals("Pay with Credit Card") && creditCardInput.getText().length() == 16) {
						for(String m: Order.movies) {
						order.addOrder(m, address.getText(), postalCode.getText(), (String)paymentType.getSelectedItem(), Login.savedUserName);
						}
						label.setText("Your order is complete!");
						// clear orders
						Order.clear();
						btnViewOrders.setVisible(true);
					}
					
					// payment type is points
					else if(paymentType.getSelectedItem().equals("Pay with Loyalty Points") && customer.getLoyaltyPoints(Login.savedUserName) >= Order.movies.size()*10) {
						for(String m: Order.movies) {
							order.addOrder(m, address.getText(), postalCode.getText(), (String)paymentType.getSelectedItem(), Login.savedUserName);
							}
						// subtract points
						Customer.addLoyaltyPoints(Login.savedUserName, -(Order.movies.size()*10));
						label.setText("Your order is complete!");
						// clear orders
						Order.clear();
						btnViewOrders.setVisible(true);
					}
					
					else {
						if(paymentType.getSelectedItem().equals("Pay with Credit Card")) {
						label.setText("Invalid card number");
						}
						else {
							label.setText("Not enough loyalty points");
						}
					}

				}
				else {
					label.setText("Fill in all entries!");
				}
			}		
		});
		btnPlaceOrder.setBounds(28, 295, 509, 52);
		contentPane.add(btnPlaceOrder);
		
		JLabel lblAddressuse = new JLabel("Address");
		lblAddressuse.setForeground(Color.LIGHT_GRAY);
		lblAddressuse.setBounds(28, 68, 94, 15);
		contentPane.add(lblAddressuse);
		
		JLabel lblPostalCode = new JLabel("Postal code");
		lblPostalCode.setForeground(Color.LIGHT_GRAY);
		lblPostalCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblPostalCode.setBounds(28, 111, 100, 15);
		contentPane.add(lblPostalCode);
		
		address = new JTextField();
		address.setBounds(124, 63, 193, 25);
		contentPane.add(address);
		address.setColumns(10);
		
		postalCode = new JTextField();
		postalCode.setBounds(124, 106, 193, 25);
		contentPane.add(postalCode);
		postalCode.setColumns(10);
		
		JLabel lblCompleteYourOrder = new JLabel("Complete your order");
		lblCompleteYourOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompleteYourOrder.setForeground(new Color(210, 180, 140));
		lblCompleteYourOrder.setFont(new Font("Open Sans Light", Font.BOLD, 19));
		lblCompleteYourOrder.setBounds(136, 12, 294, 25);
		contentPane.add(lblCompleteYourOrder);
		
		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.setBackground(new Color(25, 25, 112));
		btnBackToHome.setForeground(new Color(255, 255, 255));
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//increment stock of movies
				for(String o: Order.movies) {
					movie.incrementStock(o);
				}
				// remove movies from order
				Order.clear();
				HomePage homepage = new HomePage();
				homepage.setVisible(true);
				dispose();
			}
		});
		btnBackToHome.setBounds(42, 383, 135, 46);
		contentPane.add(btnBackToHome);
		
	}
}
