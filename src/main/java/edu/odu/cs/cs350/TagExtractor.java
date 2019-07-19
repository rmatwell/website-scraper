package edu.odu.cs.cs350;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TagExtractor 
{

	
	private String analysisTime;
	
	public TagExtractor() {
		timeOfAnalysis();
		//TODO incomplete implementation
	
	}
	
	
	
	//moved this here from AnalysisInputManager just to clean up -Jmora 7/16/19
	/**
	 * 	Starts at the root directory of the local website and parses all files, including the files of sub-directories.
	 * 	Method to extract resources from file will be called in else branch of method when available.
	 * 
	 * @param rootDirectory
	 */
	public void traverseFiles(File[] rootDirectory) 
	{
		
		
	    for (File file : rootDirectory) 
	    {
	        if (file.isDirectory()) 
	        {
	        	// Calls method again if current "file" is a directory
	        	traverseFiles(file.listFiles()); 
	        } else 
	        {
	        	
	            //TODO method to extract resources from file
	        }
	    }
	}
	
	/**
	 * 
	 * Creates a formatted String with the date the analysis started
	 * "yyyyMMdd-hhmmss'-summary'"
	 * 
	 * @author hbrow
	 */
	public void timeOfAnalysis() {
		
		String date = "yyyyMMdd-hhmmss'-summary'";
		SimpleDateFormat timeStamp = new SimpleDateFormat(date);
		
		TimeZone EST = TimeZone.getTimeZone("US/Eastern");
		timeStamp.setTimeZone(EST);
		
		this.analysisTime = timeStamp.format(new Date());
				
	}
	
	/**
	 * @return the formatted string of when the Website Analysis began.
	 */
	public String getAnalysisTime() {
		return analysisTime;
	}
	
}
