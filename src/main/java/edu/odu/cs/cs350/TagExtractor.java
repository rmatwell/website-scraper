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
	 * @return "0" just to compile before implementation.
	 */
	@Override
	public int hashCode() {
		//TODO implementation
		return 0;
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
	 * TODO implementation
	 */
	@Override
	public boolean equals(Object rhs) {
		return true;
	}

	/**
	 * Takes the size of a file in Bytes and converts to MiB.
	 * 
	 * @param fileSize
	 * @return The file size in Mebibytes(MiB)
	 */
	public double calculateMiB(File file) {

		long fileSize = file.length();

		//One MiB is approximately (Byte/(1.04858e6))
		double sizeInMiB = fileSize/(1.04858*Math.pow(10,6));

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

		double roundedSize = Math.round(fileSize * 100.0) / 100.0;

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
