package edu.odu.cs.cs350;

import java.util.HashSet;
import java.util.Set;

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
     * 	The webpage urls that the resource are found on.
     */
    private Set<String> usedOn = new HashSet<String>();

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


    }

    /**
     * @param fileSize Size of the file
     * @param typeOfLink External, Internal, or intra-page link
     * @param url
     * @param usedOn
     */
    public Resource(double fileSize, String typeOfLink,
            String url, Set<String> usedOn) {

        this.fileSize = fileSize;
        this.typeOfLink = typeOfLink;
        this.url = url;
        this.usedOn = usedOn;

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
        if (typeOfLink.equals("local") || typeOfLink.equals("external")
                || typeOfLink.equals("intra-page") || typeOfLink.equals("intra-site")) {
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
     * @return usedOn
     * the page the resource is found on
     */
    public Set<String> getUsedOn() {
        return usedOn;
    }

    /**
     * Sets the url of the page the resource is found on.
     * 
     * @param usedOn
     */
    public void setUsedOn(String usedOn) {
        this.usedOn.add(usedOn);
    }


    /*
     *
     */
    @Override
    public int hashCode() {
        Double newHash = new  Double(fileSize);
        int hashValue = newHash.hashCode();

        return PRIME_NUM_HASH * (hashValue + url.hashCode()
        + typeOfLink.hashCode() + usedOn.hashCode());
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
                && resource.usedOn.equals(usedOn);


    }


    /**
     * Creates identical copy of Resource object.
     * @throws CloneNotSupportedException
     *
     */
    @Override
    public Resource clone() throws CloneNotSupportedException
    {
        Resource aCopy = (Resource) super.clone();

        aCopy.fileSize = fileSize;
        aCopy.typeOfLink = typeOfLink;
        aCopy.url = url;
        aCopy.usedOn = usedOn;

        return aCopy;
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
                + "," + url + "," + usedOn;
        return string;
    }

}

