package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TXTReport {

	
	private String fileName;
	private String analysisTime;
	private String txt;
	private FileOutputStream txtFile;
	private Website website;
	
	public TXTReport() {

		fileName = "";
		analysisTime ="";
		txt = "";

	}

	public TXTReport(Website website, String analysisTime) {

		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);
		setFileName(analysisTime);

	}

	public String writeTXT(Website website) {

		//TODO txt = TxtWriter.objectToTxt(website);

		//TODO sort pages lexicographically

		return txt;

	}

	public void createTxtFile() throws Exception, IOException, FileNotFoundException {



	}

	public void setFname(String analysisTime) {

		this.fileName = analysisTime + ".txt";

	}

	public String getFname() {

		return fileName;

	}

	@Override
	public String toString() {

		return txt;
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
	public TXTReport clone() throws CloneNotSupportedException{

		TXTReport aCopy = (TXTReport)super.clone();

		
		aCopy.analysisTime = analysisTime;
		aCopy.fileName = fileName;
		aCopy.txt = txt;
		aCopy.txtFile = txtFile;
		aCopy.website = website;

		return aCopy;
	}

}