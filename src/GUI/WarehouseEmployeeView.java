package GUI;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ServiceItems.Order;
import Users.Customer;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class WarehouseEmployeeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Order order = new Order();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseEmployeeView frame = new WarehouseEmployeeView();
					frame.setVisible(true);
					frame.setTitle("Warehouse Shipping Centre");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WarehouseEmployeeView() {
		setBackground(UIManager.getColor("Button.background"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 317);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("DesktopIcon.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> orders = new JComboBox<String>();
		orders.setBackground(SystemColor.text);
		for(String o: order.getOrdersToBeShipped()) {
			orders.addItem(o);
		}
		orders.setBounds(47, 94, 668, 24);
		contentPane.add(orders);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 128, 0));
		label.setBounds(145, 204, 482, 15);
		contentPane.add(label);
		
		JButton btnShipOrder = new JButton("Ship Order");
		btnShipOrder.setForeground(SystemColor.text);
		btnShipOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orders.getSelectedItem().toString().equals(null) && orders.getItemAt(0) == null){
					label.setText("Select order to cancel");
				}
				// if item status delivered  or countdown is finished then you can return
				else if(orders.getSelectedItem().toString().contains("Order is being processed")) {
					// add loyalty point to customer
					int point = 1;
					Customer.addLoyaltyPoints(order.getUserName(orders.getSelectedItem().toString()),point);
					// update status to "delivered"
					order.shipOrder(orders.getSelectedItem().toString());
					// remove from orders combobox
					orders.removeItem(orders.getSelectedItem());
					// print message
					label.setText("Order Shipped!");	
					// This fixed bug where item still displayed when combobox was empty
					if(orders.getItemAt(0) == null) {
						orders.removeAllItems();
					}
				}
			}
		});
		btnShipOrder.setBackground(SystemColor.desktop);
		btnShipOrder.setBounds(215, 147, 318, 43);
		contentPane.add(btnShipOrder);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(25, 25, 112));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView login = new LoginView();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(625, 231, 90, 37);
		contentPane.add(btnLogOut);
		
	}
}
