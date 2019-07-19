package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.

public class WebsiteAnalysis 
{
	String userFilePath;
	HashSet<String> userURLs = new HashSet<>();
	String analysisTime;
	
	//'default' constructor
	public WebsiteAnalysis()
	{
		
	}
	//constructor used by analysis input manager
	public WebsiteAnalysis(String[] args, String analysisTime)
	{
			setUserFilePath(args[0]);
			setUserURLs(Arrays.copyOfRange(args, 1 ,args.length) );
			setAnalysisTime(analysisTime);
	}
	
	public void setUserFilePath(String input)
	{
		if(validFilePath(input) )
		{
			userFilePath = input;
		}
	}
	public void setUserURLs(String[] input)
	{
		Collections.addAll(userURLs, input ); 
	}
	public void setAnalysisTime(String input)
	{
		analysisTime = input;
	}
	//determines whether the user's file path is valid (correctly formatted, exists)
	public boolean validFilePath(String path)
	{
		/*
		if(some condition that makes sure the file is good)
		{
		*/
			return true;
		/*
		}
		else if(Some condition that makes sure the file exists)
		{
			System.out.println("ERROR: File path does not exist. Please input an existing file path");
		}
		else if(Some other logic that checks format)
		{
			System.out.println("ERROR: File path is not formatted correctly. Please input the file path in a proper format");
		}
		*/	
	}
	//determines whether or not the analysis has the information necessary to begin (i.e. date time, valid user path, valid user sites)
	public boolean IsReady()
	{
		if(userFilePath != null && analysisTime != null && !userURLs.isEmpty() )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void parseFiles()
	{
		//make tag extractor do things to our web pages
	}
	public void generateReports()
	{
		//take our resources and make the report writers use them
	}
}
