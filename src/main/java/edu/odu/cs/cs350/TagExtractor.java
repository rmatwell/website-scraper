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
    private String rootDirectory;

    /**
     *  The variations of possible URL's for the same Website.
     */
    private Set<URI> userURLs = new HashSet<URI>();

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
     * @param rootDirectory **The local root site**
     * @param userURLs **The website urls that correspond to the local root**
     */
    public TagExtractor(String rootDirectory, Set<URI> userURLs) {
        this.setRootDirectory(rootDirectory);
        this.setUserURLs(userURLs);
        analysisTime = "";
    }


    /**
     * Parses and strips HTML resources from file regardless of the file
     * extension.
     *
     * @param page **The webpage file**
     * @param file **The local file containing html resources**
     * @throws IOException **Thrown if file is invalid**
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
     *@param document **The Jsoup document of the local file**
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
     * @param document **The Jsoup document of the local file**
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
     *@param document **The Jsoup document of the local file**
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
     * Extracts image elements from HTML document.
     *@param document **The Jsoup document of the local file**
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
     * Translates a website link url path to the local directory path.
     * @return The translated local path.
     */
    public String translateURL() {
        //TODO Implement translation of website url to local canonical path.
        return "";
    }

    /**
     * Determines if a given extracted resource URL is internal, intra-page,
     * or external.
     * @param currentFile **The current webpage file**
     * @param rootDirectory **The local site root**
     * @param path **The url path of the extracted resource**
     * @return Returns either internal, intrapage, or external.
     * @throws IOException **Thrown if current file is invalid**
     */
    public String classifyURL(File currentFile, String rootDirectory,
            String path) throws IOException {
        if (path.contains(currentFile.getCanonicalPath())
                && path.contains("#"))
        {
            return "intra-page";
        }
        else if (path.contains(rootDirectory))
        {
            return "internal";
        }
        else
        {
            return "external";
        }

    }


    /**
     *  Starts at the root directory of the local Website and parses all files
     *  including the files of sub-directories.
     *
     *@param rootDirectory **The local root site**
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
     * Creates a formatted String with the date the analysis started
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
     * Gets the analysis time.
     * @return the formatted time of when the Website Analysis began.
     */
    public String getAnalysisTime() {
        return analysisTime;
    }

    /**
     * Gets the user supplied URLs connected to the local website copy.
     * @return the user entered URL variations for the Website.
     */
    public Set<URI> getUserURLs() {
        return userURLs;
    }

    /**
     * Sets the URLs connected to the local website.
     * @param userURLs **The URLs connected to the local website**
     */
    public void setUserURLs(Set<URI> userURLs) {
        this.userURLs = userURLs;
    }

    /**
     * Gets the local root site of the website.
     * @return the root directory of the Website.
     */
    public String getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Sets the local root site.
     * @param rootDirectory **The local root site**
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
     *@param file **A local resource file**
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
     *@param fileSize **The size of the local resource in bytes**
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
     * @return scripts
     */
    public Set<Resource> getScripts() {
        return scripts;
    }

    /**
     * Returns the set of images.
     * @return images
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
     *@param anchors **The extracted html resource from an anchor tag**
     */
    public void setAnchors(Set<Resource> anchors) {
        this.anchors = anchors;
    }

}
