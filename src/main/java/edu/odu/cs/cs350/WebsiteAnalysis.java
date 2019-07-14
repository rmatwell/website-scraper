package edu.odu.cs.cs350;

import java.io.File;
import java.util.*; //used to make Lists.
import java.util.Scanner; //used to get input from user

public class WebsiteAnalysis 
{
	private static Scanner userInputListener = new Scanner(System.in);
	
	private static String userFilePath;
	private static List<String> userURLs = new ArrayList<>();
	
	public static void main(String[] args)
	{
		getUserInput();
		//do analysis
		//make report

	}
	private static void getUserInput()
	{
		System.out.println("Welcome to WebAnalysis.");
		
		getUserFilePath();
		getUserURLs();

	}
	private static void getUserFilePath()
	{
		System.out.println("Please specify the path to the local copy of the site you want to Analyze.");
		userFilePath = userInputListener.next();
	}
	
	private static void getUserURLs()
	{
		String input = "";

		System.out.println("Please specify the URLs you want to analyze, pressing 'ENTER' between each one.");
		System.out.println("When you are done, type 'done' and press 'ENTER'. ");

		while(true)
		{
			System.out.print( (userURLs.size()+1) +": " );
			input = userInputListener.next();
			
			if(input.equals("done") )
			{
				break;
			}
			else
			{
				userURLs.add(input);
			}
		}
	}
	
	/**
	 * 	Starts at the root directory of the local website and parses all files, including the files of sub-directories.
	 * 	Method to extract resources from file will be called in else branch of method when available.
	 * 
	 * @param rootDirectory
	 */
	public void traverseFiles(File[] rootDirectory) {
			
	    for (File file : rootDirectory) {
	        if (file.isDirectory()) {
	        	// Calls method again if current "file" is a directory
	        	traverseFiles(file.listFiles()); 
	        } else {
	        	
	            //tbd method to extract resources from file
	        }
	    }
	}
}
  