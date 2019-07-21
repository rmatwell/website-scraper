package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.
import java.io.File; //used for the 'File' object, which we're using to store directories
import java.net.*; //used for the 'URL' object, which is self explanatory

public class WebsiteAnalysis 
{
	
	//variables
	
	File userFilePath;
	HashSet<URL> userURLs = new HashSet<>();
	String analysisTime;
	
	//constructors
	
	//'default' constructor
	public WebsiteAnalysis()
	{
		
	}
	//constructor used by analysis input manager
	public WebsiteAnalysis(String[] args, String analysisTime)
	{
		setUserFilePath(args[0]); //the first argument is the path
		setUserURLs(Arrays.copyOfRange(args, 1 ,args.length) ); //second to N-th arguments are URLs
		setAnalysisTime(analysisTime); //we also need to keep track of when the analysis was started for reports
	}
	
	//functions
	
	public File getUserFilePath()
	{
		return userFilePath;
	}
	
	public void setUserFilePath(String input)
	{
		File inputPath = new File(input); 
		
		if(IsValidFilePath(inputPath) )
		{
			userFilePath = inputPath;
		}
		else
		{
			//do nothing (user warnings handled in IsValidFilePath)
		}
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
	
	public HashSet<URL> getUserURLs()
	{
		return userURLs;
	}
	public void setUserURLs(String[] input)
	{
		
		userURLs = constructValidURLSet(input);
		
		System.out.println("The following URLs will be analyzed: ");
		Iterator urlitr = userURLs.iterator();
		
		while(urlitr.hasNext() )
		{
			System.out.println(urlitr.next() );
		}
	}
	
	public HashSet<URL> constructValidURLSet(String[] input)
	{
		HashSet<URL> validSet = new HashSet<URL>();
		
		List<String> badURLset = new ArrayList<String>();
		
		for(int i = 0 ; i < input.length ; i++)
		{
			try
			{
				validSet.add(new URL(input[i]) );
			}
			catch (MalformedURLException e)
			{
				badURLset.add(input[i] );
			}
		}
		
		if(badURLset.size() > 0)
		{
			System.out.println("The following URLs are malformed and will not be analyzed: ");
			for(int i = 0 ; i < badURLset.size(); i++)
			{
				System.out.println(badURLset.get(i) );
			}
		}		
		return validSet;
	}
	
	public String getAnalysisTime()
	{
		return analysisTime;
	}
	
	public void setAnalysisTime(String input)
	{
		analysisTime = input;
	}
	
	//determines whether or not the analysis has the information necessary to begin (i.e. date time, valid user path, valid user sites)
	public boolean isReady()
	{
		return ( userFilePath != null && analysisTime != null && !userURLs.isEmpty() );
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
