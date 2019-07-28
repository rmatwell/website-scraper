package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 	HTML element extractor that utilizes Jsoup api to scrape and parse files.
 *
 */
public class TagExtractor implements Cloneable {

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
	private Set<URI> userURLs = new HashSet<URI>();


	/**
	 * One MiB is approximately (Byte/(1.04858e6)).
	 */
	private final static double BYTE_TO_MEBIBYTE = (1.04858*Math.pow(10,6));


	/**
	 * Used for rounding and formatting file size.
	 */
	private final static double ONE_HUNDRED = 100.0;

	/**
	 * 		Prime number to use with hashcode function.
	 */
	private static final int PRIME_NUM_HASH = 31;

	/**
	 *	Initializes the tag extractor with the starting root directory along
	 *	with user supplied URLs.
	 * 
	 * @param rootDirectory
	 * @param userURLs
	 */
	public TagExtractor(String rootDirectory, Set<URI> userURLs ) {
		this.setRootDirectory(rootDirectory);
		this.setUserURLs(userURLs);

		timeOfAnalysis();

		//TODO incomplete implementation

	}


	/**
	 * Extracts image elements from HTML document.
	 * 
	 * @param document
	 */
	public void extractImage(Document document) {

		Resource resource = new Resource();
		Elements links = document.select("img");

		for (Element link : links) {
			String path = link.attr("src");
			resource.setUrl(path);
		}

	}


	/**
	 * 	Starts at the root directory of the local Website and parses all files
	 *  including the files of sub-directories.
	 * 	Method to extract resources from file will be called in else branch of
	 *  method when available.
	 *  @param rootDirectory
	 * @throws IOException
	 */
	public void traverseFiles(File[] rootDirectory) throws IOException
	{
		for (File file : rootDirectory)
		{
			if (file.isDirectory())
			{
				//Calls method again if current "file" is a directory to
				//traverse sub-directory.
				traverseFiles(file.listFiles());
			} else
			{
				//Current file is converted to parsed JSoup document
				Document document;
				document = Jsoup.parse(file,"UTF-8");

				//TODO create extractResource method that will include
				//various extract methods for specific resources as well
				//as their member variables such as path, size, foundOn.
				extractImage(document);
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

		TimeZone est = TimeZone.getTimeZone("US/Eastern");
		timeStamp.setTimeZone(est);

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
	public Set<URI> getUserURLs() {
		return userURLs;
	}

	/**
	 * @param userURLs
	 */
	public void setUserURLs(Set<URI> userURLs) {
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
	 * Creates hashcode based on sum of member variables hashcodes.
	 */
	@Override
	public int hashCode() {
		int analysisTimeHash = analysisTime.hashCode();
		int rootDirectoryHash = rootDirectory.hashCode();
		int userURLsHash = userURLs.hashCode();

		return PRIME_NUM_HASH * (analysisTimeHash + rootDirectoryHash +
				userURLsHash);
	}

	/*
	 *  Generates a copy of a TagExtractor object.
	 */
	@Override
	public TagExtractor clone() throws CloneNotSupportedException {
		TagExtractor aCopy = (TagExtractor) super.clone();
		aCopy.analysisTime = analysisTime;
		aCopy.rootDirectory = rootDirectory;
		aCopy.userURLs = userURLs;
		return aCopy;
	}

	/*
	 * Checks to see if two TagExtractor objects are equal based on
	 * their member variables.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TagExtractor))
		{
			return false;
		}

		TagExtractor rhs = (TagExtractor) obj;

		boolean isEqual = analysisTime.equals(rhs.analysisTime)&&
				rootDirectory.equals(rhs.rootDirectory) &&
				userURLs.equals(rhs.userURLs);

		return isEqual;
	}

	/**
	 * Takes the size of a file in Bytes and converts to MiB.
	 * 
	 * @param fileSize
	 * @return The file size in Mebibytes(MiB)
	 */
	public double calculateMiB(File file) {

		long fileSize = file.length();

		double sizeInMiB = fileSize/BYTE_TO_MEBIBYTE;

		return sizeInMiB;

	}

	/**
	 * Takes the double type file size in MiB and rounds to the nearest
	 * two digits as well as appending "MiB" to the end.
	 * 
	 * @param fileSize
	 * @return The formatted file size
	 */
	public String formatFileSize(double fileSize) {

		String mebibyte = "MiB";

		double roundedSize = Math.round(fileSize * ONE_HUNDRED) / ONE_HUNDRED;

		return Double.toString(roundedSize) + " " + mebibyte;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = rootDirectory + " , " + userURLs.toString();
		return string;
	}

}
