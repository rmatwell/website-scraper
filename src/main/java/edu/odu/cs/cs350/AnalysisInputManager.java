package edu.odu.cs.cs350;

import java.text.SimpleDateFormat;
import java.util.*; //used to make Lists.

//The purpose of this class is to: 
//	-Take input from the user
//	-Validate that the input
//	-Trigger other parts of the system  appropriately based on the input
//	-and finally, display appropriate error messages relating to the user's input.

public class AnalysisInputManager 
{	
	//the 'user' variables are used to record the user's input when invoking WebAnalysis.jar
	private static String userFilePath;
	private static List<String> userURLs = new ArrayList<>();
	//the time at which the analysis is created: collected as quickly as possible so the analysis time is as accurate as possible.
	private static String analysisTime;
	
	public static void main(String[] args)
	{
		handleUserArgs(args);
		makeAnalysis();
	}
	public static void collectAnalysisTime()
	{
		analysisTime = new SimpleDateFormat("yyyymmdd-hhmmss'-summary'").format(new Date());
	}
	
	//This should only be called ONCE, at the beginning of the program to handle the arguments the user supplies when invoking WebAnalysis.jar
	public static void handleUserArgs(String[] args)
	{
		//"If no command line arguments are provided, an appropriate usage message should be displayed."
		if(args.length <= 1)
		{
			System.out.println("ERROR: Inssufficent numer of arguments provided. Please specify (1) the directory containing the off-line site and (2) the URLs to be analyzed");
		}

		//"After identifying a set of HTML files, an error message must be output if ... more than 1000 pages are present." 
		//adding 1 (1001 args max) to account for the directory argument. 
		//This is probably NOT the appropriate place to check this, as all 1000 URLS could be identical, etc. As we develop the system, this should be moved -jmora
		else if(args.length > 1001)
		{
			System.out.println("ERROR: Maximum number of pages reached: Please specify 1000 pages or less");
		}
		else
		{
			userFilePath = args[0];
			Collections.addAll(userURLs, Arrays.copyOfRange(args, 1 ,args.length)); 

		}	
	}
	
	public static void makeAnalysis()
	{
		//WebsiteAnalysis analysis = new WebsiteAnalysis(userFilePath, userURLs, analysisTime);
	}

	//'get' methods for the user's arguments
	public String getUserFilePath()
	{
		return userFilePath;
	}
	public List<String> getUserURLs()
	{
		return userURLs;
	}	

}	

  