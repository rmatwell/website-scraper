package edu.odu.cs.cs350;

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
}
  