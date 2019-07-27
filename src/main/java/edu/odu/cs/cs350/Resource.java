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
public class Resource implements Cloneable {


	/**
	 *  Size of the resource.
	 */
	private double fileSize;

	/**
	 *  Type of link, either internal, external, or intra-page.
	 */
	private String typeOfLink;

	/**
	 * 	The url of the resource.
	 */
	private String url;

	/**
	 * 	The webpage url that the resource is found on.
	 */
	private String pageFoundOn;

	/**
	 *  Prime number to use with hashcode function.
	 */
	private static final int PRIME_NUM_HASH = 31;

	/**
	 *
	 * Initializes the resource.
	 * Sets file size to 0.
	 * Sets type of link, url, and the page found on to empty.	 *
	 *
	 */
	public Resource() {

		fileSize = 0;
		typeOfLink = "";
		url = "";
		pageFoundOn = "";

	}

	/**
	 * @param fileSize Size of the file
	 * @param typeOfLink External, Internal, or intra-page link
	 * @param url
	 * @param pageFoundOn
	 */
	public Resource(double fileSize, String typeOfLink,
			String url, String pageFoundOn) {

		this.fileSize = fileSize;
		this.typeOfLink = typeOfLink;
		this.url = url;
		this.pageFoundOn = pageFoundOn;

	}

	/**
	 * @return fileSize
	 */
	public double getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 */
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}


	/**
	 * @return typeOfLink
	 * external, internal, or intra-page
	 */
	public String getTypeOfLink() {
		return typeOfLink;
	}


	/**
	 * @param typeOfLink
	 * sets the type of link to external, internal, or intra-page
	 */
	public void setTypeOfLink(String typeOfLink) {
		if (typeOfLink.equals("internal") || typeOfLink.equals("external")
				|| typeOfLink.equals("intrapage")) {
			this.typeOfLink = typeOfLink;
		}
	}


	/**
	 * @return url
	 * the url of the resource
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url
	 * sets the url of the resource
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return pageFoundOn
	 * the page the resource is found on
	 */
	public String getPageFoundOn() {
		return pageFoundOn;
	}

	/**
	 * Sets the url of the page the resource is found on.
	 * 
	 * @param pageFoundOn
	 */
	public void setPageFoundOn(String pageFoundOn) {
		this.pageFoundOn = pageFoundOn;
	}


	/*
	 *
	 */
	@Override
	public int hashCode() {
		Double newHash = new  Double(fileSize);
		int hashValue = newHash.hashCode();

		return PRIME_NUM_HASH * (hashValue + url.hashCode()
		+ typeOfLink.hashCode() + pageFoundOn.hashCode());
	}


	/*
	 * Checks to see if two Resource objects are equal if their
	 * data types are the same values.
	 */
	@Override
	public boolean equals(Object rhs) {

		// If the object is compared with itself then return true.
		if (rhs == this) {
			return true;
		}

		/* Check if rhs is an instance of Resource or not
        "null instanceof [type]" also returns false */
		if (!(rhs instanceof Resource)) {
			return false;
		}

		// typecast rhs to Resource so that we can compare data members.
		Resource resource = (Resource) rhs;

		//Compare the data members and return accordingly.
		return Double.compare(fileSize, resource.fileSize) == 0
				&& resource.typeOfLink.equals(typeOfLink)
				&& resource.url.equals(url)
				&& resource.pageFoundOn.equals(pageFoundOn);


	}


	/**
	 * Creates identical copy of Resource object.
	 *
	 */
	@Override
	public Object clone()
	{
		Resource clone = new Resource(fileSize,
				typeOfLink, url, pageFoundOn);


		return clone;
	}

	/**
	 * @return string
	 * 
	 * Displays the contents of the Resource.
	 *
	 */
	@Override
	public String toString()
	{
		String string = Double.toString(fileSize) + "," + typeOfLink
				+ "," + url + "," + pageFoundOn;
		return string;
	}

}

