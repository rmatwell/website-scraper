package edu.odu.cs.cs350;

import java.io.FileOutputStream;


public class XLSXReport implements Cloneable {

	private String filename;
	private String analysisTime;
	private String xlsx;
	private FileOutputStream xlsxFile;
	private Website website;


	/**
	 * 
	 */
	public XLSXReport() {

		filename = "";
		analysisTime ="";
		xlsx = "";

	}


	/**
	 * @param website
	 * @param analysisTime
	 */
	public XLSXReport(Website website, String analysisTime) {

		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);
		this.setFileName(analysisTime);

	}

	public String writeXLSX(Website website) {

		//TODO

		return "";

	}

	public void createXLSXFile() {

		//TODO

	}

	public void setFileName(String analysisTime) {

		filename = analysisTime + ".xlsx";
	}

	public String getFileName() {

		return filename;
	}

	@Override
	public String toString() {

		return xlsx;
	}

	public Website getWebsite() {

		return website;
	}

	public void setWebsite(Website website) {

		this.website = website;
	}

	public String getAnalysisTime() {

		return analysisTime;
	}

	public void setAnalysisTime(String analysisTime) {

		this.analysisTime = analysisTime;
	}
	
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

}
