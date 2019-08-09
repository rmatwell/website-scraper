package edu.odu.cs.cs350;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	 * The images we will be reporting sizes of
	 */
	private Set<Resource> images;
	
	/**
	 * Default constructor
	 */
	public TXTReport()
	{

	}
	
	/**
	 * Non-default constructor, used by WebsiteAnalysis
	 * @param website **The website object
	 * @param analysisTime **Time of analysis
	 */
	public TXTReport(Website website, String analysisTime) 
	{
		setWebsite(website);
		setFileName(analysisTime);
	}

	/**
	 * Writes the data from the Website's resources to our TXT file
	 */
	public void writeTXT() 
	{
		PrintWriter writer; //actually makes the file
		
		List<Webpage> pages = generateLexicographicList(); //the list we will be iterating over
		double totalSize = 0.0;	//the total filesize, added to in the 'while' loop below, we will be reporting
		Iterator pageItr = pages.iterator(); //an iterator
		try 
		{
			writer = new PrintWriter(fileName, "UTF-8");
			
			while(pageItr.hasNext())			
			{
				Webpage nextPage = (Webpage) pageItr.next();
				writer.println(String.format("%.2f", nextPage.getSumOfImageSizes() ) + " MiB     " + nextPage.getPath() );
				totalSize += nextPage.getSumOfImageSizes();
			}
			writer.println(String.format("%.2f", totalSize) + " MiB     .");
			writer.close();
			System.out.println(fileName);
		} catch (FileNotFoundException | UnsupportedEncodingException e) 
		{}
	}

	/**
	 * Sets the filename of our txt file
	 * @param analysisTime **Time of analysis
	 */
	public void setFileName(String analysisTime) 
	{
		this.fileName = analysisTime + ".txt";
	}
	
	/**
	 * Returns the filename of our txt file
	 * @return fileName **Name of txt file
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * Returns the Website object we will get pages from
	 * @return website **The website object
	 */
	public Website getWebsite() 
	{
		return website;
	}
	
	/**
	 * Sets the Website object we will get pages from
	 * @param website **The website object
	 */
	public void setWebsite(Website website) 
	{
		this.website = website;
	}
	
	/**
	 * Sets the image set we will get sizes from
	 * @param input **Input for resource object
	 */
	public void setImages(Set<Resource> input)
	{
		images = input;
	}
	
	/**
	 * Gets the image set we will get sizes from
	 * @return images **Images from webpage
	 */
	public Set<Resource> getImages()
	{
		return images;
	}
	
	/**
	 * Converts our HashSet of images to a sorted List for reporting
	 * @return sortedList **List of paths sorted lexicographically
	 */
	private List<Webpage> generateLexicographicList()
	{
		List<Webpage> sortedList = new ArrayList<Webpage>(website.getPages());
		
		Collections.sort(sortedList, new Comparator<Webpage>(){
			public int compare (Webpage page1, Webpage page2)
			{
				return page2.getAbsolutePath().compareTo(page1.getAbsolutePath());
			}
		});
		
		return sortedList;
	}
	
}