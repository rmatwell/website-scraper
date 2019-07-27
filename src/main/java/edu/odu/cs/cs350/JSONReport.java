package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.cedarsoftware.util.io.JsonWriter;


/**
 * JSON File generator which incorporates JSON-IO api.
 * Generator takes a Website object and creates a JSON
 * file that is formatted to be more "reader friendly".
 * 
 * 
 */
public class JSONReport implements Cloneable{

	/**
	 * 		The name of the JSON file that will be created.
	 */
	private String fileName;

	/**
	 * 		The formatted time that the analysis began.
	 */
	private String analysisTime;

	/**
	 * 		The formatted JSON string created from the website
	 * 		object.
	 */
	private String json;

	/**
	 * 		The file output stream that will write the formatted JSON
	 *      string to the file.
	 */
	private FileOutputStream jsonFile;

	/**
	 * 		The website object from which the JSON file will be
	 *      generated.
	 */
	private Website website;


	/**
	 * 		Prime number to use with hashcode function.
	 */
	private static final int PRIME_NUM_HASH = 31;

	/**
	 * JSONReport takes a website object and the time the analysis started
	 * to created a formatted JSON file.
	 *
	 * @param website
	 * @param analysisTime
	 */
	public JSONReport(Website website, String analysisTime) {

		this.setWebsite(website);
		this.setAnalysisTime(analysisTime);

		setFileName(analysisTime);

	}

	/**
	 * @param website The website object that will be used in the JSON file
	 * @return json The JSON created from the objects of the website as a
	 * formatted String.
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
	public void createJSONFile() throws IOException, FileNotFoundException {

		//TODO implement try & catch sections for exceptions
		jsonFile = new FileOutputStream(fileName, true);
		jsonFile.write(json.getBytes(Charset.forName("UTF-8")));
		jsonFile.close();

	}

	/**
	 * @param analysisTime The formatted time that the analysis starts
	 *
	 *  Takes the analysisTime and appends .json to the end.
	 */
	public void setFileName(String analysisTime) {
		fileName = analysisTime + ".json";
	}

	/**
	 * @return the filename, analysis time with .json.
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * @return the formatted json String
	 */
	@Override
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
	 * @param website
	 *
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
	 * @param analysisTime
	 */
	public void setAnalysisTime(String analysisTime) {
		this.analysisTime = analysisTime;
	}

	/*
	 * Creates an identical copy of the JSONReport object
	 * 
	 */
	@Override
	public JSONReport clone() throws CloneNotSupportedException{

		JSONReport aCopy = (JSONReport)super.clone();

		aCopy.analysisTime = analysisTime;
		aCopy.fileName = fileName;
		aCopy.json = json;
		aCopy.jsonFile = jsonFile;
		aCopy.website = website;

		return aCopy;
	}

	/*
	 * Checks to see if JSONReport objects are equal based on string contents.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof JSONReport))
		{
			return false;
		}

		JSONReport rhs = (JSONReport) obj;

		boolean isEqual = analysisTime.equals(rhs.analysisTime)&&
				fileName.equals(rhs.fileName) &&
				json.equals(rhs.json)&&
				jsonFile.equals(rhs.jsonFile) &&
				website.equals(rhs.website);

		return isEqual;
	}

	/*
	 * Creates hashcode based on json & fileName strings.
	 */
	@Override
	public int hashCode() {
		int jsonHash = json.hashCode();
		int fileNameHash = fileName.hashCode();

		return PRIME_NUM_HASH * (jsonHash + fileNameHash);
	}


}
