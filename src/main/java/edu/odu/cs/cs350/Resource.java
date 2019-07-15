package edu.odu.cs.cs350;


/**
 * Container for data extracted from HTML files. Data includes file size,
 * whether the resource is internal, external, or intra-page, the url of
 * the resource, and the page that it is found on
 * 
 * @author Richard Atwell
 * 
 * @since 7-12-19
 */
public class Resource implements Cloneable{
	
	
	double fileSize;
	String typeOfLink;
	String url;
	String pageFoundOn;
	
	/**
	 * 
	 * Initializes the resource
	 * Sets file size to 0
	 * Sets type of link, url, and the page found on to empty.	 * 
	 * 
	 */
	Resource(){
		
		fileSize=0;
		typeOfLink="";
		url="";
		pageFoundOn="";
		
	}
	
	/**
	 * @param fileSize Size of the file
	 * @param typeOfLink External, Internal, or intra-page link
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
	
	
	/* 
	 * Checks to see if two Resource objects are equal if their
	 * data types are the same values.
	 */
	@Override
	public boolean equals(Object rhs) {
		
		// If the object is compared with itself then return true   
        if (rhs == this) { 
            return true; 
        } 
        
        /* Check if rhs is an instance of Resource or not 
        "null instanceof [type]" also returns false */
      if (!(rhs instanceof Resource)) { 
          return false; 
      } 
        
        // typecast rhs to Resource so that we can compare data members  
        Resource r = (Resource) rhs; 
        
        //Compare the data members and return accordingly  
        return Double.compare(this.fileSize, r.fileSize) == 0
                && r.typeOfLink.equals(this.typeOfLink)
                && r.url.equals(this.url)
                && r.pageFoundOn.equals(this.pageFoundOn);
                
		
		}
	
	
	/**
     * Creates identical copy of Resource object
     * 
     */
	@Override
    public Object clone()
    {
        Resource clone= new Resource(this.fileSize,
                this.typeOfLink,this.url,this.pageFoundOn);

     
        return clone;
    }
	
	/**
     * Displays the contents of the Resource 
     *  
     */
    @Override
    public String toString()
    {
        String s = Double.toString(fileSize) + "," + typeOfLink + "," + url + ","+ pageFoundOn;
		return s;
    }	

}

