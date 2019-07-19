package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cedarsoftware.util.io.JsonWriter;

public class JSONReport {
	
	private String fileName;
	private String analysisTime;
	private String json;
	private FileOutputStream jsonFile;
	
	private Website website;
	
	public JSONReport(Website website, String analysisTime){
		
		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);
		
		setFileName(analysisTime);
		
	}
	
	/**
	 * @param Takes the website object
	 * @return The JSON created from the objects of the website as a Formatted String 
	 */
	public String writeJSON(Website website) {
		
		json = JsonWriter.objectToJson(website);
		
		Map<String, Object> jsonArgs = new HashMap<String, Object>();
		
		jsonArgs.put(JsonWriter.TYPE, false);
		jsonArgs.put(JsonWriter.PRETTY_PRINT, true);		
		
		json = JsonWriter.objectToJson(website, jsonArgs);		
		
		return json;
	}
	
	/**
	 * Creates a JSON file with the jsonFile name
	 * whose contents are the formatted JSON string
	 * from the website object.
	 * 
	 * @throws Exception if fileName already exists
	 */
	public void createJSONFile() throws Exception, IOException, FileNotFoundException  {
				
		//TODO implement try & catch sections for exceptions
		jsonFile = new FileOutputStream(fileName, true);
		jsonFile.write(json.getBytes());
		jsonFile.close();
		
	}
		
	/**
	 * @param Takes the analysisTime and appends .json to the end.
	 */
	public void setFileName(String analysisTime) {
		this.fileName = analysisTime + ".json"; 
	}
	
	/**
	 * @return the file name, the formatted analysis time appended with .json
	 */
	public String getFileName() {
		return fileName;
	}
	
	/* 
	 * @return the formatted json String
	 */
	public String toString() {
		return json;
		
	}

	/**
	 * @return the website object
	 */
	public Website getWebsite() {
		return website;
	}

	/**
	 * @param set website object
	 */
	public void setWebsite(Website website) {
		this.website = website;
	}

	/**
	 * @return the analysisTime string
	 */
	public String getAnalysisTime() {
		return analysisTime;
	}

	/**
	 * @param sets the analysisTime string
	 */
	public void setAnalysisTime(String analysisTime) {
		this.analysisTime = analysisTime;
	}
	
	

}
