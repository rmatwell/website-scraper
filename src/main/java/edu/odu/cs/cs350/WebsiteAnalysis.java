package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.
import java.io.File;

public class WebsiteAnalysis 
{
	File userFilePath;
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
		File inputPath = new File(input);
		
		if(IsValidFilePath(inputPath) )
		{
			userFilePath = inputPath;
		}
	}
	public void setUserURLs(String[] input)
	{
		HashSet preliminarySet = new HashSet<>();
		Collections.addAll(preliminarySet, input ); 
		if(userURLsAreValid(preliminarySet) )
		{
			userURLs = preliminarySet;
		}
		else
		{
			//warn the user the files are not valid
		}
	}
	public void setAnalysisTime(String input)
	{
		analysisTime = input;
	}
	//determines whether the user's file path is valid (correctly formatted, exists)
	public boolean IsValidFilePath(File path)
	{
		
		if(path.exists() && path.isDirectory() )
		{
			return true;	
		}
		else
		{
			if(!path.exists() )
			{
				System.out.println("ERROR: " + path.toString() + " does not exist. Please input an existing directory.");
			}
			else if(!path.isDirectory() )
			{
			System.out.println("ERROR: " + path.toString() + " is not a directory. Please input a directory (not a file). ");
			}
			return false;
		}
	}
	public boolean userURLsAreValid(HashSet urls)
	{
		return true; //replace this with actual logic
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
	public File getUserFilePath()
	{
		return userFilePath;
	}
	public HashSet<String> getUserURLs()
	{
		return userURLs;
	}
	public String getAnalysisTime()
	{
		return analysisTime;
	}
}
