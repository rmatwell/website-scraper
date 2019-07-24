package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
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
		
		Map<String, Object> txtArgs = new Hashmap<String, Object>();
		
		//...
		
		return txt;
		
	}
	
	public void setFname() {
		
		this.fname = analysisTime + ".txt";
		
	}
	
	public void getFname() {
		
		return fname;
		
	}
	
}