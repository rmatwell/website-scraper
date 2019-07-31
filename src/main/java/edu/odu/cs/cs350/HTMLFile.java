package edu.odu.cs.cs350;

import java.net.*;
import java.io.File;
import java.io.IOException;

public class HTMLFile 
{
	private boolean isAnalyzed = false;
	
	private String contents;
	
	private File localCopyPath;
	
	private URL pageURL;
	
	public HTMLFile()
	{
		
	}
	public HTMLFile(File inputPath, URL inputURL)
	{
		setLocalCopyPath(inputPath);
		setPageURL(inputURL);
	}
	public void setLocalCopyPath(File inputPath)
	{
		localCopyPath = inputPath;
	}
	public void setPageURL(URL inputURL)
	{
		pageURL = inputURL;
	}
	
	public String toString()
	{
		try {
			return isAnalyzed + " - " + localCopyPath.getCanonicalPath()+ " - " + pageURL + " - " + contents;
		} catch (IOException e) 
		{
			return "nope";
		}
	}
	
	public boolean equals(HTMLFile other)
	{
		return true;
	}
}
