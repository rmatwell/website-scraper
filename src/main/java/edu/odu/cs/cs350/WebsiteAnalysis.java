package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Driver for the Website Analysis process, holds reporting and parsing capabilities, as well as the core data structures
 */
public class WebsiteAnalysis
{
    //variables
    /**
     * The Website object that is subject of the analysis.
     */
    private Website analysisSubject = new Website();

    /**
     * Object used to parse through data in Website in order to create Resources that will be reported on
     */
    TagExtractor extractor;

    /**
     * The string that will be used to name our report files, generated in AnalysisInputManager by getting the current time
     */
    private String analysisTime;

    //constructors

    /**
     * The default constructor.
     */
    public WebsiteAnalysis()
    {

    }

    /**
     * Constructor that takes arguments and a string for the report names: used by AnalysisInputManager
     * @param args
     * @param analysisTime **Time of analysis
     */
    public WebsiteAnalysis(String[] args, String analysisTime)
    {
        setAnalysisSubject(args); //hand the Website information it needs to build itself
        setAnalysisTime(analysisTime); //keep track of when the analysis was started for reports
    }

    /**
     * Returns the string containing the analysis time
     * @return analysisTime **Time of analysis
     */
    public String getAnalysisTime()
    {
        return analysisTime;
    }

    /**
     * Sets the string containing the analysis time
     * @param input **Input for analysis time string
     */
    public void setAnalysisTime(String input)
    {
        analysisTime = input;
    }

    /**
     * Returns the website to be analyzed
     * @return analysisSubject **Subject to be analyzed
     */
    public Website getAnalysisSubject()
    {
        return analysisSubject;
    }

    /**
     * Sets the website to be analyzed
     * @param args
     */
    public void setAnalysisSubject(String[] args)
    {
        analysisSubject = new Website(args);
    }

    /**
     * Checks to ensure that all data required for analysis is present
     */
    public boolean isReady()
    {
        return analysisSubject.getUserFilePath() != null && analysisTime != null && !analysisSubject.getUserURLs().isEmpty();
    }

    /**
     * Triggers the tag extractor to do what it needs to do to parse files and generate resources
     * @throws URISyntaxException
     */
    public void parseFiles() throws URISyntaxException
    {
        extractor = new TagExtractor(analysisSubject.getUserFilePath() , analysisSubject.getUserURLsAsURI(), analysisSubject);

        try {
            extractor.runExtractor();
        } catch (IOException e)
        {}

        analysisSubject = extractor.getWebsite();
    }

    /**
     * Reports in multiple formats the results of the analysis
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void generateReports() throws FileNotFoundException, IOException
    {
        TXTReport txtWriter = new TXTReport(analysisSubject, analysisTime);
        txtWriter.writeTXT();

        XLSXReport xlsxWriter = new XLSXReport(analysisSubject, analysisTime);
        xlsxWriter.createXLSXFile();

        JSONReport jsonWriter = new JSONReport(analysisSubject, analysisTime);
        jsonWriter.writeJSON(analysisSubject);
        try
        {
            jsonWriter.createJSONFile();

        } catch (IOException e)
        {}

    }
}
