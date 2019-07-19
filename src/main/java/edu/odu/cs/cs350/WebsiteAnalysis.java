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
		setUserFilePath(args[0] );
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
	
	public boolean validFilePath(String path)
	{
		return true; //FIX THIS
	}
}
