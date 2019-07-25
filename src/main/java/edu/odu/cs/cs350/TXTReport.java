package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/*
 * still working on this but let me know if it should not be similar to JSONReport class
 * and will redo
 * 
 */

public class TXTReport {
	
// name of txt file
	private String fname;
// time report was generated (for file name)
	private String analysisTime;
	private String txt;
	private FileOutputStream txtFile;
	private Website website;
	
	public TXTReport(Website website, String analysisTime) {
		
		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);
		setFname(analysisTime);
		
	}
	
	public String writeTXT(Website website) {
		
		txt = TxtWriter.objectToTxt(website);
		
		//TODO sort pages lexicographically
		
		return txt;
		
	}
	
	public void createTxtFile() throws Exception, IOException, FileNotFoundException {
		
		
		
	}
	
	public void setFname() {
		
		this.fname = analysisTime + ".txt";
		
	}
	
	public void getFname() {
		
		return fname;
		
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
	
	public void setAnalysisTime() {
		
		this.analysisTime = analysisTime;
	}
	
}