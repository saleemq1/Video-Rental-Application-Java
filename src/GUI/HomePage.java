package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import ServiceItems.Movie;
import ServiceItems.Order;
import Users.Customer;
import Users.Login;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Login login = new Login();
	Customer customer = new Customer();
	Movie movie = new Movie();
	int price = 0;
	Order order = new Order();
	private JTextField keyword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					frame.setTitle("Home Page");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 470);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.caretForeground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> movies = new JComboBox<String>();
		movies.addItem("View Movies");
		for(String m: movie.getMovies()) {
			movies.addItem(m);
		}
		movies.setBounds(31, 56, 381, 32);
		contentPane.add(movies);
		
		JComboBox<String> cart = new JComboBox<String>();
		cart.setBounds(31, 147, 381, 32);
		contentPane.add(cart);
		
		JSpinner searchSelector = new JSpinner();
		searchSelector.setModel(new SpinnerListModel(new String[] {"All","Title", "Year", "Genre"}));
		searchSelector.setBounds(31, 100, 70, 20);
		contentPane.add(searchSelector);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setBounds(31, 269, 580, 15);
		contentPane.add(label);
		
		JLabel lblTotal = new JLabel("Total: $0");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTotal.setBounds(31, 210, 70, 15);
		contentPane.add(lblTotal);
		
		JButton btnRentMovie = new JButton("Add to cart");
		btnRentMovie.setForeground(new Color(255, 255, 255));
		btnRentMovie.setBackground(new Color(128, 0, 0));
		btnRentMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(movie.getTitle((String) movies.getSelectedItem()) != null && movie.getStock((String) movies.getSelectedItem()) > 0) {
					// add movie to cart and orders
					Order.movies.add(movie.getTitle((String) movies.getSelectedItem()));
					cart.addItem(movie.getTitle((String) movies.getSelectedItem()));
				
					// decrement stock for movie then redisplay on combobox and adjust price
					label.setText(movie.getTitle((String) movies.getSelectedItem()) + " added to cart");
					price = price + 5;
					lblTotal.setText("Total: $" + price);
					movie.decrementStock((String) movies.getSelectedItem());
					movies.removeAllItems();
					movies.addItem("View Movies");
					for(String m: movie.getMovies()) {
						movies.addItem(m);
					}
				}
				
				else {
					label.setText("Sorry, selected movie out of stock");
				}
			}
		});
		btnRentMovie.setBounds(442, 56, 169, 32);
		contentPane.add(btnRentMovie);
		
		JButton btnNewButton = new JButton("Remove from cart");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cart.getSelectedItem() != null && cart.getItemAt(0) != null) {
				// increment stock for movie then update combobox
				movie.incrementStock((String) cart.getSelectedItem());
				movies.removeAllItems();
				movies.addItem("View Movies");
				for(String m: movie.getMovies()) {
					movies.addItem(m);
				}
				// remove item from cart and order
				label.setText(cart.getSelectedItem() + " removed from cart");
				price = price - 5;
				lblTotal.setText("Total: $" + price);
				Order.movies.remove((String) cart.getSelectedItem());
				cart.removeItem(cart.getSelectedItem());
				}
				// This fixed bug where item still displayed when combobox was empty
				if(cart.getItemAt(0) == null) {
					cart.removeAllItems();
				}

			}
		});
		btnNewButton.setBounds(442, 147, 169, 32);
		contentPane.add(btnNewButton);
		
		JLabel lblYourCart = new JLabel("Your Cart");
		lblYourCart.setBounds(31, 120, 70, 15);
		contentPane.add(lblYourCart);
		
		JLabel lblVideocoMovieRental = new JLabel("VideoCo Movie Rental Services");
		lblVideocoMovieRental.setForeground(new Color(210, 180, 140));
		lblVideocoMovieRental.setBackground(new Color(210, 180, 140));
		lblVideocoMovieRental.setFont(new Font("Open Sans Light", Font.BOLD, 20));
		lblVideocoMovieRental.setBounds(31, 12, 340, 32);
		contentPane.add(lblVideocoMovieRental);
		
		JLabel lblLoyaltyPoints = new JLabel("");
		lblLoyaltyPoints.setForeground(Color.WHITE);
		lblLoyaltyPoints.setText("Loyalty points: " + customer.getLoyaltyPoints(Login.savedUserName));
		lblLoyaltyPoints.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoyaltyPoints.setBounds(31, 237, 150, 15);
		contentPane.add(lblLoyaltyPoints);
				
		JButton btnRentMovies = new JButton("Order");
		btnRentMovies.setFont(new Font("Dialog", Font.BOLD, 15));
		btnRentMovies.setForeground(new Color(255, 255, 255));
		btnRentMovies.setBackground(SystemColor.desktop);
		btnRentMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cart.getItemCount()> 0) {
				// go to payment page
				ShippingPaymentView sp = new ShippingPaymentView();
				sp.setVisible(true);
				dispose();
				}
				else {
					label.setText("Your cart is empty.");
				}
			}
		});
		btnRentMovies.setBounds(31, 292, 580, 37);
		contentPane.add(btnRentMovies);
		
		JButton btnViewOrders = new JButton("View/Return/Cancel Orders");
		btnViewOrders.setForeground(new Color(255, 255, 255));
		btnViewOrders.setBackground(new Color(128, 0, 0));
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdersView ov = new OrdersView();
				if(cart.getItemCount() == 0) {
				ov.setVisible(true);
				dispose();
				}
				else {
					label.setText("Empty your cart to continue");
				}
			}
			
		});
		btnViewOrders.setBounds(248, 360, 253, 41);
		contentPane.add(btnViewOrders);
				
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(25, 25, 112));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView loginview = new LoginView();
				loginview.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(513, 360, 98, 41);
		contentPane.add(btnLogOut);
		
		JLabel label_1 = new JLabel("");
		label_1.setForeground(new Color(210, 180, 140));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("Welcome " + Login.savedFirstName + "!");
		label_1.setBounds(433, 12, 193, 15);
		contentPane.add(label_1);
		
		keyword = new JTextField();
		keyword.setBounds(113, 102, 182, 19);
		contentPane.add(keyword);
		keyword.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(keyword.getText().isEmpty() == false) {
					// search by title
					if(searchSelector.getValue().equals("Title")) {
						movies.removeAllItems();
						movies.addItem("Your results");
						for(String m: movie.getMoviesWithTitle(keyword.getText())) {
							movies.addItem(m);
						}
					}
					// search by Year
					else if(searchSelector.getValue().equals("Year")) {
						movies.removeAllItems();
						movies.addItem("Your results");
						for(String m: movie.getMoviesWithYear(keyword.getText())) {
							movies.addItem(m);
						}
					}
					// search by Genre
					else if(searchSelector.getValue().equals("Genre")) {
						movies.removeAllItems();
						movies.addItem("Your results");
						for(String m: movie.getMoviesWithGenre(keyword.getText())) {
							movies.addItem(m);
						}
					}
				}
				
				if(searchSelector.getValue().equals("All")) {
					movies.removeAllItems();
					movies.addItem("View Movies");
					for(String m: movie.getMovies()) {
						movies.addItem(m);
					}
				}
				
			}
		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setBounds(307, 99, 105, 25);
		contentPane.add(btnSearch);
						
	}
}
