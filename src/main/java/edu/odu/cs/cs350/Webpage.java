package edu.odu.cs.cs350;

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
     * The local path of the Webpage file.
     */
    private String path;

    /**
     * The sum of all image sizes on this page
     */
    private double sumOfImageSizes = 0.0;
    
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
     * @param resource
     */
    public void addImageToWebpage(Resource resource) {
        imageCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        imagePaths.add(resource.getUrl());
        sumImages++;
        sumOfImageSizes += resource.getFileSize();
    }

    /**
     * @param resource
     */
    public void addScriptToWebpage(Resource resource) {
        jsCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        scriptPaths.add(resource.getUrl());
        sumJS++;
    }

    /**
     * @param resource
     */
    public void addCSSToWebpage(Resource resource) {
        cssCount.merge(resource.getTypeOfLink(), 1, Integer::sum);
        cssPaths.add(resource.getUrl());
        sumCSS++;
    }

    /**
     * @param resource
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
     * @return The count of local and external images.
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
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the local url path of the Webpage.
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the quantity of local and external .js files of the Webpage.
     * @return jsCount
     */
    public Map<String, Integer> getjsCount() {
        return jsCount ;
    }

    /**
     * Sets the quantity for local and external .js files for the Webpage.
     * @param jsCount
     */
    public void setJsCount(Map<String, Integer> jsCount) {
        this.jsCount = jsCount;
    }

    /**
     * Gets the quantity of local and external .css files of the Webpage.
     * @return cssCount;
     */
    public Map<String, Integer> getCssCount() {

        return cssCount;
    }

    /**
     * Sets the quantity of local and external .css files.
     * @param cssCount
     */
    public void setCssCount(Map<String, Integer> cssCount) {
        this.cssCount = cssCount;
    }

    /**
     * Returns the url paths for the images of the Webpage.
     * @return imagePaths
     */
    public Set<String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Sets the url paths for image files of the Webpage.
     * @param imagePaths
     */
    public void setImagePaths(Set<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    /**
     * Returns the set of .js url paths of a webpage.
     * @return scriptPaths
     */
    public Set<String> getScriptPaths() {
        return scriptPaths;
    }

    /**
     * Sets the url path of the .js file.
     * @param scriptPaths
     */
    public void setScriptPaths(Set<String> scriptPaths) {
        this.scriptPaths = scriptPaths;
    }

    /**
     * Returns the url path of the .css file.
     * @return cssPaths
     */
    public Set<String> getCssPaths() {
        return cssPaths;
    }

    /**
     * Sets the url path to .css files.
     * @param cssPaths
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
     * @param linkCount
     */
    public void setLinkCount(Map<String, Integer> linkCount) {
        this.linkCount = linkCount;
    }

    public int getSumImages() {
        return sumImages;
    }

    public void setSumImages(int sumImages) {
        this.sumImages = sumImages;
    }

    public int getSumJS() {
        return sumJS;
    }

    public void setSumJS(int sumJS) {
        this.sumJS = sumJS;
    }

    public int getSumCSS() {
        return sumCSS;
    }

    public void setSumCSS(int sumCSS) {
        this.sumCSS = sumCSS;
    }

    public int getSumLocal() {
        return sumLocal;
    }

    public void setSumLocal(int sumLocal) {
        this.sumLocal = sumLocal;
    }

    public int getSumExternal() {
        return sumExternal;
    }

    public void setSumExternal(int sumExternal) {
        this.sumExternal = sumExternal;
    }

    public int getSumIntrapage() {
        return sumIntrapage;
    }

    public void setSumIntrapage(int sumIntrapage) {
        this.sumIntrapage = sumIntrapage;
    }
    
    public double getSumOfImageSizes()
    {
    	return sumOfImageSizes;
    }

}
