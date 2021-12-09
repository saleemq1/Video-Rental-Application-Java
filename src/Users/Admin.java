package Users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Admin {
	
	private static String moviePath = "../eecs3311-project/Database/MovieDatabase.txt";
	private static String userPath = "../eecs3311-project/Database/UserDatabase.txt";
	private static Scanner x;



	public Admin() {	
		
	}
	
	public void addMovie(String title, String year, String category, String stock){
		
		//write to MovieDatabase
		try {
			FileWriter fileWriter = new FileWriter(moviePath,true);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.printf("%s,%s,%s,%s\n", title, year, category, stock);
		    printWriter.close();	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeMovie(String titleToRemove){
		
		//remove from Moviedatabase
		try {
		String tempFile = "temp.txt";

		File oldFile = new File(moviePath);
		File newFile = new File(tempFile);
		
		FileWriter fileWriter = new FileWriter(tempFile, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		
		x = new Scanner(new File(moviePath));
		x.useDelimiter("[,\n]");	
		
		while (x.hasNext()) {
			String title = x.next();
			String year = x.next();
			String category = x.next();
			String stock = x.next();

			if (title.equals(titleToRemove)) {
				printWriter.print("");
			}
			
			else {
				 printWriter.printf("%s,%s,%s,%s\n", title, year, category, stock);
			}
		}
		x.close();
		printWriter.flush();
		printWriter.close();
		oldFile.delete();
		File dump = new File(moviePath);
		newFile.renameTo(dump);
		}
		catch(IOException e) {
			
		}
	}
	
	
	public void removeUser(String uName) {
		// remove from UserDatabase
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
					printWriter.print("");
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
