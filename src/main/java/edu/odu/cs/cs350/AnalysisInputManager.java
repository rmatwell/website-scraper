package edu.odu.cs.cs350;

import java.text.SimpleDateFormat;
import java.util.Date;

//The purpose of this class is to: 
//	-Take input from the user
//	-Validate that the input
//	-Create a WebsiteAnalysis object (which controls the actual data storage, parsing, and reporting parts of the program
//	-and finally, display appropriate error messages relating to the user's input.

public class AnalysisInputManager 
{	
	
	public static void main(String[] args)
	{
		//"If no command line arguments are provided, an appropriate usage message should be displayed."
		if(args.length <= 1)
		{
			System.out.println("ERROR: Inssufficent numer of arguments provided. Please specify (1) the directory containing the off-line site and (2) the URLs to be analyzed");
		}
		else
		{
			makeAnalysis(args);
		}

	}
	
	public static void makeAnalysis(String[] args)
	{
		WebsiteAnalysis analysis = new WebsiteAnalysis(args, getAnalysisTime() );
	}
	
	public static String getAnalysisTime()
	{
		return new SimpleDateFormat("yyyymmdd-hhmmss'-summary'").format(new Date() );
	}
} 