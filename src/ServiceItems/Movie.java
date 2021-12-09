package ServiceItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
	
	private static String moviePath = "../eecs3311-project/Database/MovieDatabase.txt";
	private static Scanner x;
		  
	 public Movie() {
	 }
	 
	 public ArrayList<String> getMovies(){
			ArrayList<String> movies = new ArrayList<String>();
			try {
			      File myObj = new File(moviePath);
			      Scanner myReader = new Scanner(myObj);
			      
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        movies.add(data);
			      }
			      myReader.close();
			    } 
			
				catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }
			return movies;
		}
	 
	 public ArrayList<String> getMoviesWithTitle(String keyword){
			ArrayList<String> movies = new ArrayList<String>();
			try {
				x = new Scanner(new File(moviePath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					String title = x.next();
					String year = x.next();
					String category = x.next();
					String stock = x.next();

					if (title.equals(keyword)) {
						movies.add(title + "," + year + "," + category + "," + stock);
					}		
				}
				x.close();
			    } 
			
				catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }
			return movies;
		}
	 
	 public ArrayList<String> getMoviesWithGenre(String keyword){
			ArrayList<String> movies = new ArrayList<String>();
			try {
				x = new Scanner(new File(moviePath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					String title = x.next();
					String year = x.next();
					String category = x.next();
					String stock = x.next();

					if (category.equals(keyword)) {
						movies.add(title + "," + year + "," + category + "," + stock);
					}		
				}
				x.close();
			    } 
			
				catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }
			return movies;
		}
	 
	 public ArrayList<String> getMoviesWithYear(String keyword){
			ArrayList<String> movies = new ArrayList<String>();
			try {
				x = new Scanner(new File(moviePath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					String title = x.next();
					String year = x.next();
					String category = x.next();
					String stock = x.next();

					if (year.equals(keyword)) {
						movies.add(title + "," + year + "," + category + "," + stock);
					}		
				}
				x.close();
			    } 
			
				catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }
			return movies;
		}
	 
	 
	public String getTitle(String movie) {
		 try {
				x = new Scanner(new File(moviePath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					String title = x.next();
					x.next();
					x.next();
					x.next();

					if (movie.contains(title)) {
						return title;
					}		
				}
				x.close();
				}
				catch(IOException e) {
					
				}
		return null;
	 }
	 
	 public void incrementStock(String movieTitle) {
		 System.out.println(movieTitle);

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
					
					if (movieTitle.equals(title)) {
						int temp = Integer.parseInt(stock);
						temp++;
						stock = String.valueOf(temp);
						printWriter.printf("%s,%s,%s,%s\n", title, year, category, stock);
						System.out.println(stock);
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
	 
	 public void decrementStock(String movie) {
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

					if (movie.contains(title)) {
						int temp = Integer.parseInt(stock);
						temp--;
						stock = String.valueOf(temp);
						printWriter.printf("%s,%s,%s,%s\n", title, year, category, stock);
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
	 
	 public int getStock(String movie) {
		 try {				
				x = new Scanner(new File(moviePath));
				x.useDelimiter("[,\n]");	
				
				while (x.hasNext()) {
					String title = x.next();
					x.next();
					x.next();
					String stock = x.next();

					if (movie.contains(title)) {
						int result = Integer.parseInt(stock);
						return result;
					}
				}
				x.close();
				}
				catch(IOException e) {
					
				}
		return 0;
	 }


}
