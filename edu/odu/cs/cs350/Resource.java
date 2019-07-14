
package edu.odu.cs.cs350


/**
 * @author Richard Atwell
 * @version 1.0
 * @since 7-12-19
 */
public class Resource{
	
	double fileSize;
	String typeOfLink;
	String url;
	String pageFoundOn;
	
	/**
	 * 
	 */
	Resource(){
		
		fileSize=0;
		typeOfLink="";
		url="";
		pageFoundOn="";
		
	}
	
	/**
	 * @param fileSize
	 * @param typeOfLink
	 * @param url
	 * @param pageFoundOn
	 */
	Resource(double fileSize, String typeOfLink, String url, String pageFoundOn){
		
		this.fileSize=fileSize;
		this.typeOfLink=typeOfLink;
		this.url=url;
		this.pageFoundOn=pageFoundOn;
		
	}
	
	/**
	 * @return the file size of the resource
	 */
	public double getFileSize() {
		return fileSize;
	}
	
	/**
	 * @param sets the size of the file
	 */
	public void setFileSize(double fileSize) {
		this.fileSize=fileSize;
	}
	
	
	/**
	 * @return the type of link of the resource
	 * external, internal, or intra-page
	 */
	public String getTypeOfLink() {
		return typeOfLink;
	}
	
	
	/**
	 * @param sets the type of link to external, internal, or intra-page
	 */
	public void setTypeOfLink(String typeOfLink) {
		if(typeOfLink.equals("internal")||typeOfLink.equals("external")||typeOfLink.equals("intrapage"))
			this.typeOfLink=typeOfLink;
	}
	
	
	/**
	 * @return the url of the resource
	 */
	public String getUrl() {
		return url;
	}
	
	
	/**
	 * @param sets the url of the resource
	 */
	public void setUrl(String url) {
		this.url=url;
	}
	
	
	/**
	 * @return the page the resource is found on
	 */
	public String getPageFoundOn() {
		return pageFoundOn;
	}
	
	

}
