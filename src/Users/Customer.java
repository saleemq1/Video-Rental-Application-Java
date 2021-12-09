package Users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Customer {
	
	private static String userPath = "../eecs3311-project/Database/UserDatabase.txt";
	private static Scanner x;

	public Customer() {

	}


	public int getLoyaltyPoints(String uName) {
		 try {				
				x = new Scanner(new File(userPath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					x.next();
					x.next();
					x.next();
					x.next();
					String userName = x.next();
					x.next();
					String loyaltyPoints = x.next();
						
					if (userName.equals(uName)) {
						int result = Integer.parseInt(loyaltyPoints);
						return result;
					}
				}
				x.close();
				}
				catch(IOException e) {
					
				}
		return 0;
	}


	public static void addLoyaltyPoints(String uName,int amount) {
		
		try {
			String tempFile = "temp.txt";

			File oldFile = new File(userPath);
			File newFile = new File(tempFile);
			
			FileWriter fileWriter = new FileWriter(tempFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			
			x = new Scanner(new File(userPath));
			x.useDelimiter("[,\n]");	
			
			while (x.hasNext()) {
				String userType = x.next();
				String firstName = x.next();
				String lastName = x.next();
				String email = x.next();
				String userName = x.next();
				String password = x.next();
				String loyaltyPoints = x.next();

				if (userName.equals(uName)) {
					int pts = Integer.parseInt(loyaltyPoints);
					pts += amount;
					loyaltyPoints = String.valueOf(pts);
					printWriter.printf("%s,%s,%s,%s,%s,%s,%s\n", userType, firstName, lastName, email, userName, password, loyaltyPoints);
				}
				
				else {
					 printWriter.printf("%s,%s,%s,%s,%s,%s,%s\n", userType, firstName, lastName, email, userName, password, loyaltyPoints);
				}
			}
			x.close();
			printWriter.flush();
			printWriter.close();
			oldFile.delete();
			File dump = new File(userPath);
			newFile.renameTo(dump);
			}
			catch(IOException e) {
				
			}
		
	}


}
