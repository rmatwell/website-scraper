package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.apache.tika.Tika;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 *  HTML element extractor that utilizes Jsoup api to parse files and scrape.
 *
 */
public class TagExtractor implements Cloneable {

    /**
     *  The time the analysis begins in a formatted string.
     */
    private String analysisTime;

    /**
     *  The root directory of the Website.
     */
    private File rootDirectory;

    /**
     *  The variations of possible URL's for the same Website.
     */
    private HashSet<URL> userURLs = new HashSet<URL>();

    /**
     *  Set used to store image resources found by extractor.
     */
    private Set<Resource> images = new HashSet<Resource>();

    /**
     *  Set used to store CSS resources found by extractor.
     */
    private Set<Resource> stylesheets = new HashSet<Resource>();

    /**
     *  Set used to store CSS resources found by extractor.
     */
    private Set<Resource> scripts = new HashSet<Resource>();

    /**
     *  Set used to store anchor tag
     *   resources found by extractor.
     */
    private Set<Resource> anchors = new HashSet<Resource>();

    /**
     * Tika object to determine MIME type.
     */
    private Tika tika = new Tika();

    /**
     * The Webpage object that contains the file's html extracted resources.
     */
    private Webpage page;

    /**
     * One MiB is approximately (Byte/(1.04858e6)).
     */
    private static final double BYTE_TO_MEBIBYTE = 1.04858 * Math.pow(10, 6);

    /**
     * Used for rounding and formatting file size.
     */
    private static final double ONE_HUNDRED = 100.0;

    /**
     * Prime number to use with hashcode function.
     */
    private static final int PRIME_NUM_HASH = 31;

    /**
     *  Initializes the tag extractor with the starting root directory along
     *  with user supplied URLs.
     *
     * @param rootDirectory **The local root directory of the website**
     * @param userURLs **The user supplied url(s) of the website that
     *                      correspond with the local root directory**
     */
    
    public TagExtractor(Website input) {
        this.setRootDirectory(input.getUserFilePath());
        this.setUserURLs(input.getUserURLs() );
        analysisTime = "";
    }


    /**
     * Parses and strips HTML resources from file regardless of the file
     * extension.
     *
     * @param page **The webpage object that resources will be written to**
     * @param file **The local html file**
     * @throws IOException **Thrown if file is not valid**
     */
    public void extractResources(File file, Webpage page) throws IOException {

        String mimeType = tika.detect(file);

        //Determines if this is a text file, all other types are ignored.
        if (mimeType.contains("text")) {
            Document document;
            document = Jsoup.parse(file, "UTF-8");

            extractImageTags(document);
            extractScriptTags(document);
            extractLinkTags(document);
            extractAnchorTags(document);

            //TODO methods from Webpage to categorize extracted data.

        }
    }


    /**
     *  TODO Implement method to extract from "a" tags and classify as
     *  video, audio, archive, and other. Possibly using tika.detect().
     *
     *@param document **The Jsoup document created from the local file**
     */
    public void extractAnchorTags(Document document) {
        Resource resource = new Resource();
        Elements links = document.select("a");

        for (Element link : links) {
            String path = link.attr("href");
            resource.setUrl(path);
            anchors.add(resource);
        }
    }

    /**
     * Extracts JS elements from HTML document.
     *
     * @param document **The Jsoup document created from the local file**
     */
    public void extractScriptTags(Document document) {

        Resource resource = new Resource();
        Elements links = document.select("script");

        for (Element link : links) {
            String path = link.attr("src");
            resource.setUrl(path);
            scripts.add(resource);
        }

    }

    /**
     * Extracts CSS elements from HTML document.
     *
     *@param document **The Jsoup document created from the local file**
     */
    public void extractLinkTags(Document document) {

        Resource resource = new Resource();
        Elements links = document.select("link");

        for (Element link : links) {
            String path = link.attr("href");
            resource.setUrl(path);
            stylesheets.add(resource);
        }

    }

    /**
     *  Extracts image elements from HTML document.
     *@param document **The Jsoup document created from the local file**
     */
    public void extractImageTags(Document document) {

        Resource resource = new Resource();
        Elements links = document.select("img");

        for (Element link : links) {
            String path = link.attr("src");
            resource.setUrl(path);
            images.add(resource);

        }

    }


    /**
     *  Starts at the root directory of the local Website and parses all files
     *  including the files of sub-directories.
     * 
     *@param rootDirectory **The root directory of the local website**
     *@throws IOException **Thrown if file is invalid**
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
                page = new Webpage(file.getAbsolutePath());
                extractResources(file, page);

                //TODO method to push Webpage into a set contained on Website.

            }
        }
    }

    /**
     *
     * Creates a formatted String with the date the analysis started,
     * "yyyyMMdd-hhmmss'-summary'".
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
    public Set<URL> getUserURLs() {
        return userURLs;
    }

    /**
     * @param userURLs **The URL website paths that corresspond with the local
     *                      root directory**
     */
    public void setUserURLs(HashSet<URL> userURLs) {
        this.userURLs = userURLs;
    }

    /**
     * @return the root directory of the Website.
     */
    public File getRootDirectory() {
        return rootDirectory;
    }

    /**
     * @param rootDirectory **The root directory of the local website**
     */
    public void setRootDirectory(File rootDirectory) {
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

        return PRIME_NUM_HASH * (analysisTimeHash + rootDirectoryHash
                + userURLsHash);
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

        boolean isEqual = analysisTime.equals(rhs.analysisTime)
                && rootDirectory.equals(rhs.rootDirectory)
                && userURLs.equals(rhs.userURLs);

        return isEqual;
    }

    /**
     * Takes the size of a file in Bytes and converts to MiB.
     *
     *@param file **A local file**
     * @return The file size in Mebibytes(MiB)
     */
    public double calculateMiB(File file) {

        long fileSize = file.length();

        double sizeInMiB = fileSize / BYTE_TO_MEBIBYTE;

        return sizeInMiB;

    }

    /**
     * Takes the double type file size in MiB and rounds to the nearest
     * two digits as well as appending "MiB" to the end.
     *
     *@param fileSize **The size of the local file in bytes**
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

    /**
     *  Returns the set of stylesheets.
     * @return the set of stylesheets
     */
    public Set<Resource> getLinks() {
        return stylesheets;
    }

    /**
     * Returns the set of scripts.
     * @return the set of scripts
     */
    public Set<Resource> getScripts() {
        return scripts;
    }

    /**
     * Returns the set of images.
     * @return the set of images.
     */
    public Set<Resource> getImages() {
        return images;
    }

    /**
     * Returns the set of data from anchor tags.
     * @return anchors
     */
    public Set<Resource> getAnchors() {
        return anchors;
    }

    /**
     * Sets the set of data from anchors.
     *@param anchors **Resource extracted from html anchor tag**
     */
    public void setAnchors(Set<Resource> anchors) {
        this.anchors = anchors;
    }

}
