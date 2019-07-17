package edu.odu.cs.cs350;

import java.io.File;

public class TagExtractor 
{

	
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
	        	
	            //tbd method to extract resources from file
	        }
	    }
	}
}
