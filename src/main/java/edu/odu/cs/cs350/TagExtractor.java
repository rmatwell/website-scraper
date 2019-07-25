package edu.odu.cs.cs350;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

/**
 * 	HTML element extractor that utilizes Jsoup api to scrape and parse files.
 *
 */
public class TagExtractor implements Cloneable
{
	/**
	 * 	The time the analysis begins in a formatted string.
	 */
	private String analysisTime;

	/**
	 * 	The root directory of the Website.
	 */
	private String rootDirectory;

	/**
	 *	The variations of possible URL's for the same Website.
	 */
	private HashSet<URL> userURLs;

	/**
	 *	Initializes the tag extractor with the starting root directory along
	 *	with user supplied URLs.
	 * 
	 * @param rootDirectory
	 * @param userURLs
	 */
	public TagExtractor(String rootDirectory, HashSet<URL> userURLs ) {
		this.setRootDirectory(rootDirectory);
		this.setUserURLs(userURLs);

		timeOfAnalysis();

		//TODO incomplete implementation

	}

	/**
	 * 	Starts at the root directory of the local Website and parses all files,
	 *  including the files of sub-directories.
	 * 	Method to extract resources from file will be called in else branch of
	 *  method when available.
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

		analysisTime = timeStamp.format(new Date());

	}

	/**
	 * @return the formatted time of when the Website Analysis began.
	 */
	public String getAnalysisTime() {
		return analysisTime;
	}

	/**
	 * @return the user entered URL variations for the Website.
	 */
	public HashSet<URL> getUserURLs() {
		return userURLs;
	}

	/**
	 * @param userURLs
	 */
	public void setUserURLs(HashSet<URL> userURLs) {
		this.userURLs = userURLs;
	}

	/**
	 * @return the root directory of the Website.
	 */
	public String getRootDirectory() {
		return rootDirectory;
	}

	/**
	 * @param rootDirectory
	 */
	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	/**
	 * @return "0" just to compile before implementation.
	 */
	public int hashcode() {
		//TODO implementation
		return 0;
	}

	/*
	 *  TODO implementation
	 */
	@Override
	public Object clone() {
		TagExtractor aCopy = new TagExtractor(rootDirectory, userURLs);
		return aCopy;
	}

	/*
	 * TODO implementation
	 */
	@Override
	public boolean equals(Object rhs) {
		return true;
	}

}
