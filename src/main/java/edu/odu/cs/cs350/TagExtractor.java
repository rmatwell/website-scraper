package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
    private URI rootDirectory;

    /**
     *  The variations of possible URL's for the same Website.
     */
    private Set<URI> userURLs = new HashSet<URI>();

    /**
     * The website object container.
     */
    private Website website = new Website();

    /**
     * A URI iterator.
     */
    private Iterator<URI> itr;

    /**
     * A set of one or more webpage urls that correspond to the local file.
     */
    private Set<URI> webpageURLs = new HashSet<URI>();

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
     * A file array originating at the local root path.
     */
    private File[] pathToRoot;

    private URI currentLocal;

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
    private Webpage page = new Webpage();

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
     * @param userURLs **The website urls that correspond to the local root
     * @throws URISyntaxException **
     */
    public TagExtractor(String rootToPath, Set<URI> userURLs, Website website) throws URISyntaxException {

        rootDirectory = new URI(rootToPath);

        this.setUserURLs(userURLs);
        analysisTime = "";
        this.website=website;

    }


    /**
     * Parses and strips HTML resources from file regardless of the file
     * extension.
     *
     * @param page **The webpage file**
     * @param file **The local file containing html resources**
     * @throws IOException **Thrown if file is invalid
     * @throws URISyntaxException **
     */
    public void extractResources(File file, Webpage page) throws IOException, URISyntaxException {


        Document document;
        document = Jsoup.parse(file, "UTF-8");

        extractImageTags(document);
        extractScriptTags(document);
        extractLinkTags(document);
        extractAnchorTags(document);

    }


    /**
     *  TODO Implement method to extract from "a" tags and classify as
     *  video, audio, archive, and other. Possibly using tika.detect().
     *
     *@param document **The Jsoup document of the local file
     * @throws URISyntaxException **
     */
    public void extractAnchorTags(Document document) throws URISyntaxException {
        Resource resource = new Resource();
        Elements links = document.select("a");

        for (Element link : links) {
            String path = link.attr("href");
            resource.setUrl(path);
            translateURL(resource);
            page.addAnchortoWebpage(resource);
            anchors.add(resource);

        }
    }

    /**
     * Extracts JS elements from HTML document.
     *
     * @param document **The Jsoup document of the local file
     * @throws URISyntaxException **
     */
    public void extractScriptTags(Document document) throws URISyntaxException {

        Resource resource = new Resource();
        Elements links = document.select("script");

        for (Element link : links) {
            String path = link.attr("src");
            resource.setUrl(path);
            translateURL(resource);
            page.addScriptToWebpage(resource);
            scripts.add(resource);
        }

    }

    /**
     * Extracts CSS elements from HTML document.
     *
     *@param document **The Jsoup document of the local file
     * @throws URISyntaxException **
     */
    public void extractLinkTags(Document document) throws URISyntaxException {

        Resource resource = new Resource();
        Elements links = document.select("link");

        for (Element link : links) {
            String path = link.attr("href");
            resource.setUrl(path);
            translateURL(resource);
            page.addCSSToWebpage(resource);
            stylesheets.add(resource);
        }

    }

    /**
     * Extracts image elements from HTML document.
     *@param document **The Jsoup document of the local file
     * @throws URISyntaxException **
     */
    public void extractImageTags(Document document) throws URISyntaxException, IOException {

        Resource resource = new Resource();
        Elements links = document.select("img");

        for (Element link : links) {
            String path = link.attr("src");
            resource.setUrl(path);
            translateURL(resource);
            page.addImageToWebpage(resource);
            if(resource.getTypeOfLink().contains("local"))
            {
                File file = new File(resource.getUrl());
                double fileSize = calculateMiB(file);
                resource.setFileSize(fileSize);
            }
            images.add(resource);

        }

    }

    /**
     * Translates a website link url path to the local directory path.
     * @return The translated local path.
     * @throws URISyntaxException
     */
    public void translateURL(Resource resource) throws URISyntaxException {

        String path = resource.getUrl();

        if(path.contains("./") || path.contains("../")) {
            URI newPath = currentLocal.resolve(path);
            resource.setUrl(newPath.toString());
            resource.setTypeOfLink("local");
        }
        else if(path.contains("https://")) {
            itr = userURLs.iterator();
            while(itr.hasNext()) {
                URI userURL = itr.next();

                if(path.contains(userURL.toString())) {
                    URI resourcePath = new URI(path);
                    URI relative = userURL.relativize(resourcePath);
                    resource.setUrl(currentLocal.resolve(relative).toString());
                    resource.setTypeOfLink("local");
                }

                else {
                    resource.setTypeOfLink("external");
                }

            }

        }

        else if (path.contains("#")) {
            resource.setTypeOfLink("intra-page");
        }

        else {
            URI newPath1 = currentLocal.resolve(path);
            resource.setUrl(newPath1.toString());
            resource.setTypeOfLink("local");
        }

    }

    public void matchWebpageWithLocal(URI currentLocal) {
        if (!webpageURLs.isEmpty()) {
            webpageURLs.clear();
        }
        URI relative = rootDirectory.relativize(currentLocal);

        itr = userURLs.iterator();

        while(itr.hasNext())
        {
            webpageURLs.add(itr.next().resolve(relative));
        }

    }


    public void runExtractor() throws IOException, URISyntaxException {
        traverseFiles(getPathToRoot());
    }

    /**
     *  Starts at the root directory of the local Website and parses all files
     *  including the files of sub-directories.
     *
     *@param files **The local root site**
     *@throws IOException **Thrown if file is invalid
     * @throws URISyntaxException **
     */
    public void traverseFiles(File[] files) throws IOException, URISyntaxException
    {
        for (File file : files)
        {
            if (file.isDirectory())
            {
                //Recursive call if current location is sub-directory.

                traverseFiles(file.listFiles());
            } else
            {
                String mimeType = tika.detect(file);


                if (mimeType.contains("text")) {
                    currentLocal = new URI(file.getPath());
                    page = new Webpage(file.getPath());
                    matchWebpageWithLocal(currentLocal);
                    extractResources(file, page);
                    website.addWebpage(page);
                }
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
    public URI getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Sets the local root site.
     * @param rootDirectory **The local root site**
     */
    public void setRootDirectory(URI rootDirectory) {
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


    public Set<URI> getWebpageURLs() {
        return webpageURLs;
    }


    public void setWebpageURLs(Set<URI> webpageURLs) {
        this.webpageURLs = webpageURLs;
    }



    public File[] getPathToRoot() {
        pathToRoot = new File(rootDirectory.getPath()).listFiles();
        return pathToRoot;
    }

    public Webpage getPage() {
        return page;
    }

    public Website getWebsite() {
        return website;
    }
}
