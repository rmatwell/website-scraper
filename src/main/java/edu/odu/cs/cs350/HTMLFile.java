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
	
	public void getLocalCopyPath() {
		
		return localCopyPath;
	}
	
	public void setLocalCopyPath(File inputPath)
	{
		localCopyPath = inputPath;
	}
	
	public void getPageURL() {
		
		return pageURL;
	}
	
	public void setPageURL(URL inputURL)
	{
		pageURL = inputURL;
	}
	
	@Override
	public String toString()
	{
		try {
			return isAnalyzed + " - " + localCopyPath.getCanonicalPath()+ " - " + pageURL + " - " + contents;
		} catch (IOException e) 
		{
			return "nope";
		}
	}

	@Override
	public boolean equals(HTMLFile other)
	{
		return true;
	}
}
