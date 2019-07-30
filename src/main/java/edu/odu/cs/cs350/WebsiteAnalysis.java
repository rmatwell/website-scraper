package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.
import java.io.File; //used for the 'File' object, which we're using to store directories
import java.io.IOException;
import java.net.*; //used for the 'URL' object, which is self explanatory

public class WebsiteAnalysis 
{
	
	//variables
	
	private File userFilePath;
	private String fullUserFilePathName;
	
	private Website analysisSubject = new Website();
	//TagExtractor extractor = new TagExtractor();
	
	private TXTReport txtWriter = new TXTReport();
	private XLSXReport xlsxWriter = new XLSXReport();
	private JSONReport jsonWriter = new JSONReport();
	
	private HashSet<File> translatedUserURLs = new HashSet<File>();
	private String analysisTime;
	
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
			try 
			{
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
	private boolean IsValidFilePath(File path)
	{
		//if the path exists, we will use it
		if(path.exists() && path.isDirectory() ) 
		{
			return true;	
		}
		//if the path does not exist, we won't use it, and we'll give the user an appropriate warning depending on the situation
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
		List<String> badURLset = new ArrayList<String>(); //set of URLs that are not properly formatted and will not be included in analysis
		
		HashSet<URL> validSet = new HashSet<URL>(); //set of properly formatted URLS
		
		//for each input
		for(int i = 0 ; i < input.length ; i++)
		{
			try
			{
				validSet.add(new URL(input[i]) ); //try to create a URL object from it and add it to the set
			}
			catch (MalformedURLException e)
			{
				badURLset.add(input[i] ); //if it fails, do not include it in the set
			}
		}
		
		//warn the user about the malformed URLs
		if(badURLset.size() > 0)
		{
			System.out.println("ERROR: The following URLs are malformed and will not be analyzed: ");
			for(int i = 0 ; i < badURLset.size(); i++)
			{
				System.out.println("        " + badURLset.get(i) );
			}
		}		
		
		//return the rest
		return validSet;
	}
	
	private HashSet<File> translateURLsToPath(HashSet<URL> input)
	{
		HashSet<File> translatedSet = new HashSet<File>(); //the set of paths we will return at the end of the translation process 
		
		List<URL> outOfScopeSet = new ArrayList<URL>(); //the set of paths that are outside of the directory the user specified
		List<URL> nonexistantSet = new ArrayList<URL>(); //the set of paths that do not exist
		
		Iterator<URL> inputItr = input.iterator();
		
		//while there are still URLs to translate...
		while(inputItr.hasNext())
		{
			URL inputURL = inputItr.next();
			File translatedPath = convertURLToCanonicalPath(inputURL); 
		
			//if that translated URL is in 'our' path
			if(translatedPath.toString().startsWith(userFilePath.toString() ) )
			{
				//...and it exists..
				if(translatedPath.exists() )
				{
					//add it to the set it return
					translatedSet.add(translatedPath);
				}
				else
				{
					//if it doesn't exist, add it to the list we will warn the user about
					nonexistantSet.add(inputURL);
				}
			}
			else
			{
				//if its out of scope, add it to the OTHER list we will warn the user about
				outOfScopeSet.add(inputURL);
			}
			
		}
		
		//aformentioned user warnings
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
	
	private File convertURLToCanonicalPath(URL input)
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
		//txtWriter.doWriteFunction
		//jsonWriter.doWriteFunction
		//xlsxWriter.doWriteFunction
	}
}
