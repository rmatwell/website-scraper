package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class XLSXReport {
	
	private String filename;
	private String analysisTime;
	private String xlsx;
	private FileOutputStream xlsxFile;
	private Website website;
	
	public XLSXReport(Website website, String analysisTime) {
		
		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);
		setFilename(analysisTime);
		
	}
	
	public String writeXLSX(Website website) {
		
		//...
		
	}
	
	public void createXLSXFile() {
		
		//...
		
	}
	
	public void setFilename(String analysisTime) {
		
		this.filename = analysisTime + ".xlsx";
	}
	
	public String getFilename() {
		
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
	
}