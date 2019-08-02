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
     * 	The local path of the Webpage file.
     */
    private String path;

    /**
     * The amount of image files classified as local or external.
     */
    private Map<String, Integer> imageCount = new HashMap<String, Integer>();

    /**
     * The amount of image files classified as local or external.
     */
    private Map<String, Integer> jsCount = new HashMap<String, Integer>();

    /**
     * The amount of image files classified as local or external.
     */
    private Map<String, Integer> cssCount = new HashMap<String, Integer>();

    /**
     * The url paths for image files.
     */
    private Set<String> imagePaths = new HashSet<String>();

    /**
     * The url paths for .js files.
     */
    private Set<String> scriptPaths = new HashSet<String>();

    /**
     * The url paths for .css files.
     */
    private Set<String> cssPaths = new HashSet<String>();

    /**
     * The amount of links classified as intra-page, intra-site, or external.
     */
    private Map<String, Integer> linkCount = new HashMap<String, Integer>();;

    /**
     * Creates an empty Webpage object.
     */
    public Webpage() {
        path = "";
    }

    /**
     * Creates a Webpage object set with the url path to the local file.
     */
    public Webpage(String path) {
        this.path = path;
    }

    /**
     * @return The count of local and external images.
     */
    public Map<String, Integer> getImageCount() {
        return imageCount;
    }

    /**
     * @param imageCount
     */
    public void setImageCount(Map<String, Integer> imageCount) {
        this.imageCount=imageCount;
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
    public Map<String, Integer> getJsCount() {
        return jsCount;
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

}
