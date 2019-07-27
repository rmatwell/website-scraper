package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.
import java.io.File; //used for the 'File' object, which we're using to store directories
import java.io.IOException;
import java.net.*; //used for the 'URL' object, which is self explanatory

public class WebsiteAnalysis 
{
	
	//variables
	
	File userFilePath;
	String fullUserFilePathName;
	
	HashSet<File> translatedUserURLs = new HashSet<File>();
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
			try {
				fullUserFilePathName = inputPath.getCanonicalPath();
			} 
			catch (IOException e) 
			{
				System.out.println("This error should never occur");
			}
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
				System.out.println("ERROR: '" + path.toString() + "' does not exist. Please input an existing directory.");
			}
			else if(!path.isDirectory() )
			{
			System.out.println("ERROR: '" + path.toString() + "' is not a directory. Please input a directory (not a file). ");
			}
		return false;
		}
	}
	
	public HashSet<File> getTranslatedUserURLs()
	{
		return translatedUserURLs;
	}
	public void setUserURLs(String[] input)
	{	
		translatedUserURLs = constructValidTranslatedURLSet(input);
		
	}
	
	private HashSet<File> constructValidTranslatedURLSet(String[] input)
	{
		if(userFilePath != null)
		{
			HashSet<URL> validSet = new HashSet<URL>();
			
			validSet = removeBadURLs(input);
			
			HashSet<File> translatedValidSet = translateURLsToPath(validSet);
		
			return  translatedValidSet;
		}
		else
		{
			System.out.println("ERROR: No File Path provided, thus the analysis cannot proceed.");
			return new HashSet<File>();
		}

	}
	
	private HashSet<URL> removeBadURLs(String[] input)
	{
		List<String> badURLset = new ArrayList<String>();
		
		HashSet<URL> validSet = new HashSet<URL>();
		
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
			System.out.println("ERROR: The following URLs are malformed and will not be analyzed: ");
			for(int i = 0 ; i < badURLset.size(); i++)
			{
				System.out.println("        " + badURLset.get(i) );
			}
		}		
		
		return validSet;
	}
	
	public HashSet<File> translateURLsToPath(HashSet<URL> input)
	{
		HashSet<File> translatedSet = new HashSet<File>();
		
		List<URL> outOfScopeSet = new ArrayList<URL>();
		List<URL> nonexistantSet = new ArrayList<URL>();
		
		Iterator<URL> inputItr = input.iterator();
		
		while(inputItr.hasNext())
		{
			URL inputURL = inputItr.next();
			File translatedPath = convertURLToCanonicalPath(inputURL); 
			
			/*
			try {
				System.out.println(fullUserFilePathName + " " + translatedPath.getCanonicalPath() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			if(translatedPath.toString().startsWith(userFilePath.toString() ) )
			{
				if(translatedPath.exists() )
				{
					translatedSet.add(translatedPath);
				}
				else
				{
					nonexistantSet.add(inputURL);
				}

			}
			else
			{
				outOfScopeSet.add(inputURL);
			}
			
		}
		
		if(outOfScopeSet.size() > 0 )
		{
	
			System.out.println("ERROR: The following URLs are not in '" + fullUserFilePathName + "' and will not be analyzed:  ");
			for(int i = 0 ; i < outOfScopeSet.size(); i++)
			{
				System.out.println("        " + outOfScopeSet.get(i) );
			}
		}
		if(nonexistantSet.size() > 0)
		{
			System.out.println("ERROR: The following URLs do not exist in '" + fullUserFilePathName + "' and cannot be analyzed:  ");
			for(int i = 0 ; i < nonexistantSet.size(); i++)
			{
				System.out.println("        " + nonexistantSet.get(i) );
			}
		}
		
		return translatedSet;
	}
	
	public File convertURLToCanonicalPath(URL input)
	{
		return new File(input.getPath().replaceFirst("/", "") );
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
		return ( userFilePath != null && analysisTime != null && !translatedUserURLs.isEmpty() );
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
