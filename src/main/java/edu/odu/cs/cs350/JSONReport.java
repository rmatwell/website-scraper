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
 */
public class JSONReport implements Cloneable {

    /**
     *     The name of the JSON file that will be created.
     */
    private String fileName;

    /**
     *      The formatted time that the analysis began.
     */
    private String analysisTime;

    /**
     *      The formatted JSON string created from the website
     *      object.
     */
    private String json;

    /**
     *      The file output stream that will write the formatted JSON
     *      string to the file.
     */
    private FileOutputStream jsonFile;

    /**
     *      The website object from which the JSON file will be
     *      generated.
     */
    private Website website;

    /**
     *      The webpage object from which the JSON file will be
     *      generated.
     */
    private Webpage page;

    /**
     *      Prime number to use with hashcode function.
     */
    private static final int PRIME_NUM_HASH = 31;

    /**
     *  The empty JSON Report.
     */
    public JSONReport() {

        analysisTime = "";
        json = "";
        fileName = "";

    }

    /**
     * JSONReport takes a webpage object and the time the analysis started
     * to created a formatted JSON file.
     * @param page
     * @param analysisTime
     */
    public JSONReport(Webpage page, String analysisTime) {

        this.setPage(page);
        this.setAnalysisTime(analysisTime);
        json = "";
        fileName = "";
    }

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
        json = "";
        fileName = "";
    }

    /**
     * Creates formatted JSON string from a webpage.
     * 
     * @param page
     * @return json **The formatted json string
     */
    public String writeJSON(Webpage page) {

        json = JsonWriter.objectToJson(page);

        Map<String, Object> jsonArgs = new HashMap<String, Object>();

        jsonArgs.put(JsonWriter.TYPE, false);
        jsonArgs.put(JsonWriter.PRETTY_PRINT, true);

        json = JsonWriter.objectToJson(page, jsonArgs);

        return json;
    }

    /**
     * Creates formatted JSON string from a website and all webpage and
     * extracted html resources.
     * @param website **The website object that will be used in the JSON file
     * @return json **The JSON created from the objects of the website as a
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
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void createJSONFile() throws IOException, FileNotFoundException {

        setFileName(analysisTime);
        jsonFile = new FileOutputStream(fileName, true);
        jsonFile.write(json.getBytes(Charset.forName("UTF-8")));
        System.out.println(fileName);

        jsonFile.close();

    }

    /**
     * Sets the file name with the formatted analysis time appended with .json.
     * @param analysisTime **The formatted time that the analysis starts
     *
     */
    public void setFileName(String analysisTime) {
        fileName = analysisTime + ".json";
    }

    /**
     * Returns the name of json file
     * @return fileName
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
     * Returns the website object
     * @return website
     */
    public Website getWebsite() {
        return website;
    }

    /**
     * Sets the website object
     * @param website
     *
     */
    public void setWebsite(Website website) {
        this.website = website;
    }

    /**
     * Returns the analysisTime string
     * @return analysisTime
     */
    public String getAnalysisTime() {
        return analysisTime;
    }

    /**
     * Sets the analysisTime string
     * @param analysisTime **The time the report began**
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
        aCopy.page = page;

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

        boolean isEqual = analysisTime.equals(rhs.analysisTime)
                && fileName.equals(rhs.fileName)
                && json.equals(rhs.json);

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


    /**
     * Returns the webpage object.
     * @return page **The webpage
     */
    public Webpage getPage() {
        return page;
    }


    /**
     * Sets the webpage object.
     * @param page **The webpage**
     */
    public void setPage(Webpage page) {
        this.page = page;
    }


}
