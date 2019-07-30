package edu.odu.cs.cs350;

import java.util.Map;
import java.util.Set;


/**
 * Webpage contains HTML element data,
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
	private Map<String, Integer> imageCount;


	/**
	 * The amount of image files classified as local or external.
	 */
	private Map<String, Integer> jsCount;


	/**
	 * The amount of image files classified as local or external.
	 */
	private Map<String, Integer> cssCount;


	/**
	 * The url paths for image files.
	 */
	private Set<String> imagePaths;


	/**
	 * The url paths for .js files.
	 */
	private Set<String> scriptPaths;


	/**
	 * The url paths for .css files.
	 */
	private Set<String> cssPaths;


	/**
	 * The amount of links classified as intra-page, intra-site, or external.
	 */
	private Map<String, Integer> linkCount;

	/**
	 * Creates an empty Webpage object.
	 */
	public Webpage() {
		path = "";
	}


	/**
	 * Creates a Webpage object set with the url to the local file.
	 */
	public Webpage(String path) {
		this.path = path;
	}


	public Map<String, Integer> getImageCount() {
		return imageCount;
	}


	public void setImageCount(Map<String, Integer> imageCount) {
		this.imageCount = imageCount;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Map<String, Integer> getJsCount() {
		return jsCount;
	}


	public void setJsCount(Map<String, Integer> jsCount) {
		this.jsCount = jsCount;
	}


	public Map<String, Integer> getCssCount() {
		return cssCount;
	}


	public void setCssCount(Map<String, Integer> cssCount) {
		this.cssCount = cssCount;
	}


	public Set<String> getImagePaths() {
		return imagePaths;
	}


	public void setImagePaths(Set<String> imagePaths) {
		this.imagePaths = imagePaths;
	}


	public Set<String> getScriptPaths() {
		return scriptPaths;
	}


	public void setScriptPaths(Set<String> scriptPaths) {
		this.scriptPaths = scriptPaths;
	}


	public Set<String> getCssPaths() {
		return cssPaths;
	}


	public void setCssPaths(Set<String> cssPaths) {
		this.cssPaths = cssPaths;
	}


	public Map<String, Integer> getLinkCount() {
		return linkCount;
	}


	public void setLinkCount(Map<String, Integer> linkCount) {
		this.linkCount = linkCount;
	}

}
