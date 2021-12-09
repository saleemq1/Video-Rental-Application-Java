package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ServiceItems.Order;

public class Login {
	
	public static String savedFirstName = "";
	public static String savedUserName = "";	
	private static String userPath = "../eecs3311-project/Database/UserDatabase.txt";
	private static Scanner x;
	
	public Login() {
	}
	
	
	// authenticate
	public boolean login(String userName, String password) {
			
			try {
		      File myObj = new File(userPath);
		      @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        // if userName and password exist
		        if(data.contains(userName + "," + password)) {
		        	savedUserName = userName;
		        	savedFirstName = this.getFirstName(userName);
		        	Order.clear(); //clear static movie list in Order class from previous uses
		        	return true;
		        }
		      }
		      myReader.close();
		    } 
		
			catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
		
			return false;
	}
	
	public boolean isAdmin(String userName, String password) {
		try {
		      File myObj = new File(userPath);
		      @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        // if userName and password exist and is Admin
		        if(data.contains(userName + "," + password) && data.contains("Admin")) {
		        	return true;
		        }
		      }
		      myReader.close();
		    } 
		
			catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
		
			return false;		
	}
	
	public boolean isCustomer(String userName, String password) {
		try {
		      File myObj = new File(userPath);
		      @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        // if userName and password exist and is Admin
		        if(data.contains(userName + "," + password) && data.contains("Customer")) {
		        	return true;
		        }
		      }
		      myReader.close();
		    } 
		
			catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
			return false;			
	}
	
	//create new Admin account
	public void registerAdmin(String firstName, String lastName, String email, String userName, String password){
		
		//write to UserDatabase
		try {
			FileWriter fileWriter = new FileWriter(userPath,true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.printf("Admin,%s,%s,%s,%s,%s,%s\n", firstName, lastName, email, userName, password, "N/A");
		     
		     printWriter.close();	
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
	}
	
	//create new Customer account
	public void registerCustomer(String firstName, String lastName, String email, String userName, String password){
		
		//write to UserDatabase
		try {
			FileWriter fileWriter = new FileWriter(userPath,true);
		     PrintWriter printWriter = new PrintWriter(fileWriter);
		     printWriter.printf("Customer,%s,%s,%s,%s,%s,%s\n", firstName, lastName, email, userName, password, "0");
		     printWriter.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		   
	}
	
		//create new Warehouse Employee account
		public void registerEmployee(String firstName, String lastName, String email, String userName, String password){
			
			//write to UserDatabase
			try {
				FileWriter fileWriter = new FileWriter(userPath,true);
				PrintWriter printWriter = new PrintWriter(fileWriter);
			    printWriter.printf("Employee,%s,%s,%s,%s,%s,%s\n", firstName, lastName, email, userName, password, "N/A");
			     
			     printWriter.close();	
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   
		}
	
	public String getFirstName(String uName) {
		try {				
			x = new Scanner(new File(userPath));
			x.useDelimiter("[,\n]");	
			
			while (x.hasNext()) {
				x.next();
				String firstName = x.next();
				x.next();
				x.next();
				String userName = x.next();
				x.next();
				x.next();
					
				if (userName.equals(uName)) {
					return firstName;
				}
			}
			x.close();
			}
			catch(IOException e) {
				
			}
	return null;
	}

}
