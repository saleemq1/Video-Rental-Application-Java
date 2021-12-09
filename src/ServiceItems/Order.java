package ServiceItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import Users.Login;

public class Order {
	
	private static String orderPath = "../eecs3311-project/Database/OrderDatabase.txt";
	public static ArrayList<String> movies = new ArrayList<String>();
	Login login = new Login();
	private static Scanner x;
	
	public Order() {
		
	}
	
public void addOrder(String movie, String address, String postalCode, String paymentType, String userName){
		
		//write to OrderDatabase
		try {
			FileWriter fileWriter = new FileWriter(orderPath,true);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    // standard procedure
		    String date = this.getDate();
		    String price = "$5";
		    String status = "Order is being processed";
		   
		    printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", movie, date, paymentType, price, status, userName, address, postalCode);
		    printWriter.close();	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

public void removeOrder(String orderToRemove){
	
	//remove from OrderDatabase
	try {
	String tempFile = "temp.txt";

	File oldFile = new File(orderPath);
	File newFile = new File(tempFile);
	
	FileWriter fileWriter = new FileWriter(tempFile, true);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	PrintWriter printWriter = new PrintWriter(bufferedWriter);
	
	x = new Scanner(new File(orderPath));
	x.useDelimiter("[,\n]");	
	
	while (x.hasNext()) {
		String movie = x.next();
		String date = x.next();
		String paymentType = x.next();
		String price = x.next();
		String status = x.next();
		String userName = x.next();
		String address = x.next();
		String postalCode = x.next();

		if (orderToRemove.equals(movie + "," + date + "," + paymentType + "," + price + "," + status + "," + userName + "," + address + "," + postalCode)) {
			printWriter.print("");
		}
		
		else {
			printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", movie, date, paymentType, price, status, userName, address, postalCode);
		}
	}
	x.close();
	printWriter.flush();
	printWriter.close();
	oldFile.delete();
	File dump = new File(orderPath);
	newFile.renameTo(dump);
	}
	catch(IOException e) {
		
	}
}

public void shipOrder(String orderToUpdate){
	
	try {
	String tempFile = "temp.txt";

	File oldFile = new File(orderPath);
	File newFile = new File(tempFile);
	
	FileWriter fileWriter = new FileWriter(tempFile, true);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	PrintWriter printWriter = new PrintWriter(bufferedWriter);
	
	x = new Scanner(new File(orderPath));
	x.useDelimiter("[,\n]");	
	
	while (x.hasNext()) {
		String movie = x.next();
		String date = x.next();
		String paymentType = x.next();
		String price = x.next();
		String status = x.next();
		String userName = x.next();
		String address = x.next();
		String postalCode = x.next();

		if (orderToUpdate.equals(movie + "," + date + "," + paymentType + "," + price + "," + status + "," + userName + "," + address + "," + postalCode)){
			status = "Delivered";
			printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", movie, date, paymentType, price, status, userName, address, postalCode);
		}
		
		else {
			printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", movie, date, paymentType, price, status, userName, address, postalCode);
		}
	}
	x.close();
	printWriter.flush();
	printWriter.close();
	oldFile.delete();
	File dump = new File(orderPath);
	newFile.renameTo(dump);
	}
	catch(IOException e) {
		
	}
}

public String getTitle(String order){
	
	try {	
	x = new Scanner(new File(orderPath));
	x.useDelimiter("[,\n]");	
	
	while (x.hasNext()) {
		String movie = x.next();
		String date = x.next();
		String paymentType = x.next();
		String price = x.next();
		String status = x.next();
		String userName = x.next();
		String address = x.next();
		String postalCode = x.next();

		if (order.equals(movie + "," + date + "," + paymentType + "," + price + "," + status + "," + userName + "," + address + "," + postalCode)) {
			return movie;
		}
		
	}
	x.close();
	}
	catch(IOException e) {
		
	}
	return null;
}

public String getUserName(String order){

	try {	
	x = new Scanner(new File(orderPath));
	x.useDelimiter("[,\n]");	
	
	while (x.hasNext()) {
		String movie = x.next();
		String date = x.next();
		String paymentType = x.next();
		String price = x.next();
		String status = x.next();
		String userName = x.next();
		String address = x.next();
		String postalCode = x.next();

		if (order.equals(movie + "," + date + "," + paymentType + "," + price + "," + status + "," + userName + "," + address + "," + postalCode)) {
			return userName;
		}
		
	}
	x.close();
	}
	catch(IOException e) {
		
	}
	return null;
}

// gets the customer's orders
public ArrayList<String> getOrders(String userName){
	ArrayList<String> orders = new ArrayList<String>();
	try {
	      File myObj = new File(orderPath);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        if(data.contains(userName)) {
	        	orders.add(data);
	        }
	      }
	      myReader.close();
	    } 
	
		catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	return orders;
}

public ArrayList<String> getOrdersToBeShipped(){
	ArrayList<String> orders = new ArrayList<String>();
	try {
	      File myObj = new File(orderPath);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        if(data.contains("Order is being processed")) {
	        	orders.add(data);
	        }
	      }
	      myReader.close();
	    } 
	
		catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	return orders;
}


public String getDate() {
	LocalDate myDateObj = LocalDate.now();  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM yyyy");  
    
    String formattedDate = myDateObj.format(myFormatObj);  
    return formattedDate;  
}

public static void clear() {
	movies.clear();
}

}
