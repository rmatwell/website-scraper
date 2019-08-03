package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Excel (XLSX) file generator from a Website object to create a single sheet
 * summary consisting of headers and the corresponding counts
 * resulting from the analysis.
 */

public class XLSXReport implements Cloneable {

	private String fileName;
	private String analysisTime;
	private String xlsx;
	private FileOutputStream xlsxFile;
	private Website website;
	private Webpage page;

	/**
	 * Empty XLSX report
	 */
	public XLSXReport() {

		fileName = "";
		analysisTime ="";
		xlsx = "";

	}


	/**
	 * Creates an XLSX file using the website object and 
	 * time analysis began. 
	 * @param website
	 * @param analysisTime
	 */
	public XLSXReport(Website website, String analysisTime) {

		this.setPage(page);
		this.setAnalysisTime(analysisTime);
		xlsx = "";
		fileName = "";

	}

	/** 
	 * Creates formatted XLSX string from analyzed HTML content
	 * @param website The object that will be used in the XLSX file
	 * @return xlsx The XLSX file as a formatted string
	 */
	public String writeXLSX(Website website) {

		//TODO

		return xlsx;

	}

    /**
     * Creates a XLSX file containing the formatted
     * XLSX string from website.
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
	public void createXLSXFile() throws IOException, FileNotFoundException {

		//TODO

	}

    /**
     * Sets the file name and adds .xlsx file extension.
     * @param analysisTime
     *
     */
	public void setFileName(String analysisTime) {

		fileName = analysisTime + ".xlsx";
	}

	/**
     * @return filename as analysis time with .xlsx
     */
	public String getFileName() {

		return fileName;
	}

	/**
     * @return the formatted xlsx string
     */
	@Override
	public String toString() {

		return xlsx;
	}

	/**
     * @return the website object
     */
	public Website getWebsite() {

		return website;
	}

	/**
     * @param website
     *
     */
	public void setWebsite(Website website) {

		this.website = website;
	}
	
    /**
     * @return page The webpage object
     */
    public Webpage getPage() {
        return page;
    }


    /**
     * @param page Sets the webpage object
     */
    public void setPage(Webpage page) {
        this.page = page;
    }

	/**
     * @return the analysisTime string
     */
	public String getAnalysisTime() {

		return analysisTime;
	}

    /**
     * @param analysisTime
     */
	public void setAnalysisTime(String analysisTime) {

		this.analysisTime = analysisTime;
	}

	/*
	 * Creates a copy of the XLSX object.
	 */
	@Override
	public XLSXReport clone() throws CloneNotSupportedException{

		XLSXReport aCopy = (XLSXReport)super.clone();

		
		aCopy.analysisTime = analysisTime;
		aCopy.fileName = fileName;
		aCopy.xlsx = xlsx;
		aCopy.xlsxFile = xlsxFile;
		aCopy.website = website;

		return aCopy;
	}
	
    /*
     * Checks if XLSXReport objects are equal.
     * 
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof XLSXReport))
        {
            return false;
        }

        XLSXReport rhs = (XLSXReport) obj;

        boolean isEqual = analysisTime.equals(rhs.analysisTime)
                && fileName.equals(rhs.fileName)
                && xlsx.equals(rhs.xlsx);

        return isEqual;
    }
    

}
