package edu.odu.cs.cs350;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Webpage object that comprised of information about resources located from a
 * local HTML file.
 *
 */
public class Webpage {

    /**
     * The absolute path of the Webpage file.
     */
    private transient String absolutePath;
    /**
     * The local path of the Webpage file.
     */
    private String path;
    /**
     * The sum of all image sizes on this page
     */
    private transient double sumOfImageSizes = 0.0;
    
    /**
     * The amount of image files classified as local or external.
     */
    private Map<String, Integer> imageCount = new HashMap<String, Integer>();

    /**
     * The amount of js files classified as local or external.
     */
    private Map<String, Integer> jsCount = new HashMap<String, Integer>();

    /**
     * The amount of css files classified as local or external.
     */
    private Map<String, Integer> cssCount = new HashMap<String, Integer>();

    /**
     * The url paths for image files.
     */
    private Set<String> imagePaths = new HashSet<String>();

    /**
     * The url paths for js files.
     */
    private Set<String> scriptPaths = new HashSet<String>();

    /**
     * The url paths for css files.
     */
    private Set<String> cssPaths = new HashSet<String>();

    /**
     * The amount of links classified as intra-page, intra-site, or external.
     */
    private Map<String, Integer> linkCount = new HashMap<String, Integer>();;

    private transient int sumImages = 0;
    private transient int sumJS = 0;
    private transient int sumCSS = 0;
    private transient int sumLocal = 0;
    private transient int sumExternal = 0;
    private transient int sumIntrapage = 0;

    /**
     * Creates an empty Webpage object.
     */
    public Webpage() {
        path = "";
    }

    /**
     * Creates a Webpage object set with the url path to the local file.
     * @param path **The url path of the webpage**
     */
    public Webpage(String path) {
        this.path = path;
    }

    /**
     * Adds image to resource object
     * @param resource **The resource object
     */
    public void addImageToWebpage(Resource resource) {
        imageCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        imagePaths.add(resource.getUrl());
        sumImages++;
        sumOfImageSizes += resource.getFileSize();
    }

    /**
     * Adds scripts to resource object
     * @param resource **The resource object
     */
    public void addScriptToWebpage(Resource resource) {
        jsCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        scriptPaths.add(resource.getUrl());
        sumJS++;
    }

    /**
     * Adds CSS to resource object
     * @param resource **The resource object
     */
    public void addCSSToWebpage(Resource resource) {
        cssCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        cssPaths.add(resource.getUrl());
        sumCSS++;
    }

    /**
     * Adds anchor links to resource object
     * @param resource **The resource object
     */
    public void addAnchortoWebpage(Resource resource) {
        if(resource.getTypeOfLink().contains("local")) {
            linkCount.merge("intra-site", 1, Integer::sum);
            sumLocal++;
        }
        else if(resource.getTypeOfLink().contains("external")){
            linkCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
            sumExternal++;
        }
        else {
            linkCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
            sumIntrapage++;
        }
    }

    /**
     * Gets the amount of local and external images.
     * @return imageCount **The count of local and external images.
     */
    public Map<String, Integer> getImageCount() {
        return imageCount;
    }

    /**
     * Sets the amount of images on the webpage.
     * @param imageCount **The total number of images, local and external**
     */
    public void setImageCount(Map<String, Integer> imageCount) {
        this.imageCount = imageCount;
    }

    /**
     * Gets the local url path of the webpage
     * @return path **The local path of webpage
     */
    public String getAbsolutePath() {
        return path;
    }

    /**
     * Sets the local url path of the Webpage.
     * @param path **The local path of webpage
     */
    public void setAbsolutePath(String path) {
        this.path = path;
    }

    /**
     * Returns the quantity of local and external .js files of the Webpage.
     * @return jsCount **Number of js files
     */
    public Map<String, Integer> getjsCount() {
        return jsCount ;
    }

    /**
     * Sets the quantity for local and external .js files for the Webpage.
     * @param jsCount **Number of js files
     */
    public void setJsCount(Map<String, Integer> jsCount) {
        this.jsCount = jsCount;
    }

    /**
     * Gets the quantity of local and external .css files of the Webpage.
     * @return cssCount **Number of css files
     */
    public Map<String, Integer> getCssCount() {

        return cssCount;
    }

    /**
     * Sets the quantity of local and external .css files.
     * @param cssCount **Number of css files
     */
    public void setCssCount(Map<String, Integer> cssCount) {
        this.cssCount = cssCount;
    }

    /**
     * Returns the url paths for the images of the Webpage.
     * @return imagePaths **Url paths for images
     */
    public Set<String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Sets the url paths for image files of the Webpage.
     * @param imagePaths **Url paths for images
     */
    public void setImagePaths(Set<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    /**
     * Returns the set of .js url paths of a webpage.
     * @return scriptPaths **Url paths for js files
     */
    public Set<String> getScriptPaths() {
        return scriptPaths;
    }

    /**
     * Sets the url path of the .js file.
     * @param scriptPaths **Url paths for js files
     */
    public void setScriptPaths(Set<String> scriptPaths) {
        this.scriptPaths = scriptPaths;
    }

    /**
     * Returns the url path of the .css file.
     * @return cssPaths **Url paths for css files
     */
    public Set<String> getCssPaths() {
        return cssPaths;
    }

    /**
     * Sets the url path to .css files.
     * @param cssPaths **Url paths for css files
     */
    public void setCssPaths(Set<String> cssPaths) {
        this.cssPaths = cssPaths;
    }

    /**
     * Returns the quantity of local and external .css files.
     * @return linkCount
     */
    public Map<String, Integer> getLinkCount() {
        return linkCount;
    }

    /**
     * Sets the quantity of local and external .css files.
     * @param linkCount **Number of links on page
     */
    public void setLinkCount(Map<String, Integer> linkCount) {
        this.linkCount = linkCount;
    }

    /**
     * Gets the sum of all images on page
     * @return sumImages **Sum of image sizes on page
     */
    public int getSumImages() {
        return sumImages;
    }

    /**
     * Sets sum of all images on page
     * @param sumImages **Sum of image sizes on page
     */
    public void setSumImages(int sumImages) {
        this.sumImages = sumImages;
    }

    /**
     * Returns sum of javascripts
     * @return sumJS **Sum of js files on page
     */
    public int getSumJS() {
        return sumJS;
    }

    /**
     * Sets sum of javascripts
     * @param sumJS **Sum of js files on page
     */
    public void setSumJS(int sumJS) {
        this.sumJS = sumJS;
    }

    /**
     * Returns sum of CSS
     * @return sumCSS **Sum of css files on page
     */
    public int getSumCSS() {
        return sumCSS;
    }

    /**
     * Sets sum of CSS
     * @param sumCSS **Sum of css files on page
     */
    public void setSumCSS(int sumCSS) {
        this.sumCSS = sumCSS;
    }

    /**
     * Returns sum of local files
     * @return sumLocal **Sum of local files on page
     */
    public int getSumLocal() {
        return sumLocal;
    }

    /**
     * Sets sum of local files
     * @param sumLocal **Sum of local files on page
     */
    public void setSumLocal(int sumLocal) {
        this.sumLocal = sumLocal;
    }

    /**
     * Returns sum of external files
     * @return sumExternal **Sum of external files on page
     */
    public int getSumExternal() {
        return sumExternal;
    }

    /**
     * Sets sum of external files
     * @param sumExternal **Sum of external files on page
     */
    public void setSumExternal(int sumExternal) {
        this.sumExternal = sumExternal;
    }

    /**
     * Returns sum of intra-page links
     * @return sumIntrapage **Sum of intra-page links
     */
    public int getSumIntrapage() {
        return sumIntrapage;
    }

    /**
     * Sets sum of intra-page links
     * @param sumIntrapage **Sum of intra-page links
     */
    public void setSumIntrapage(int sumIntrapage) {
        this.sumIntrapage = sumIntrapage;
    }
    
    /**
     * Returns sum of image sizes
     * @return sumOfImageSizes **Sum of image sizes on page
     */
    public double getSumOfImageSizes()
    {
    	return sumOfImageSizes;
    }
    
    /**
     * Sets root path
     * @param root **The file root path
     */
    public void setPath(File root)
    {
    	path ="./" + root.toURI().relativize(new File(path).toURI()).getPath();
    }
    
    /**
     * Returns root path
     * @return path **The file root path
     */
	public String getPath()
	{
		return path;
	}
	
}
