package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TXTReport handles creating the text report, which contains a list of images and their sizes.
 */
public class TXTReport 
{

	/**
	 * The name of the file: should be the analysis time.
	 */
	private String fileName;

	/**
	 * Handles the writing of the TXT file.
	 */
	private FileOutputStream txtFile;
	
	/**
	 * The Website object contains the resources (images) to be reported on
	 */
	private Website website;
	
	/**
	 * Default constructor
	 */
	public TXTReport() 
	{

	}

	/**
	 * Non-default constructor, used by WebsiteAnalysis
	 */
	public TXTReport(Website website, String analysisTime) 
	{

		this.setWebsite(website);
		setFileName(analysisTime);

	}

	/**
	 * Writes the data from the Website's resources to our TXT file
	 */
	public String writeTXT(Website website) 
	{
		String txt = "";
		//TODO txt = TxtWriter.objectToTxt(website);

		//TODO sort pages lexicographically

		return txt;

	}

	/**
	 * Creates the text file we need to write to
	 */
	public void createTxtFile() throws Exception, IOException, FileNotFoundException 
	{



	}

	
	/**
	 * Sets the filename of our txt file
	 */
	public void setFileName(String analysisTime) 
	{

		this.fileName = analysisTime + ".txt";

	}

	
	/**
	 * Returns the filename of our txt file
	 */
	public String getFileName()
	{

		return fileName;

	}

	
	//I see no reason that we actually need to get a toString() of the TXT reporter, so I'm commenting it out -Jason
	/*
	@Override
	public String toString() 
	{

		return txt;
	}
	*/
	
	
	/**
	 * Returns the Website object we will get image resources from 
	 */

	public Website getWebsite() 
	{
		return website;
	}

	
	/**
	 * Sets the Website object we will get image resources from 
	 */
	
	public void setWebsite(Website website) 
	{
		this.website = website;
	}
	
	//I see no reason that we actually need to clone the TXTReport object, so I'm commenting it out -Jason
	/*
	@Override
	public TXTReport clone() throws CloneNotSupportedException
	{

		TXTReport aCopy = (TXTReport)super.clone();

		
		aCopy.fileName = fileName;
		aCopy.txt = txt;
		aCopy.txtFile = txtFile;
		aCopy.website = website;

		return aCopy;
	}
	*/

}