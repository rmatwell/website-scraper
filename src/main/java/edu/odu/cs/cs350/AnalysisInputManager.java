package edu.odu.cs.cs350;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AnalysisInputManager that takes the users input and constructs the analysis.
 * Takes multiple string inputs from the user.
 */

public class AnalysisInputManager
{

    /**
     * Main function that takes user's input and starts the analysis using it
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException
    {
        //"If no command line arguments are provided, an appropriate usage message should be displayed."
        if(args.length <= 1)
        {
            System.out.println("ERROR: Inssufficent numer of arguments provided. Please specify (1) the directory containing the off-line site and (2) the URLs to be analyzed");
        }
        else
        {
            doAnalysis(args);
        }
    }


    /**
     * Checks to ensure that the WebsiteAnalysis has enough data to begin, and then triggers the analysis to parse files and then generate reports
     * @throws URISyntaxException
     */
    public static void doAnalysis(String[] args) throws URISyntaxException
    {
        WebsiteAnalysis analysis = new WebsiteAnalysis(args, getAnalysisTime() );

        if(analysis.isReady() )
        {
            analysis.parseFiles();
            analysis.generateReports();
        }
        else
        {
            //don't have what we need, do no analysis
            System.out.println("Aborting Analysis.");
        }
    }


    /**
     * Records and formats the time the Analysis was run at for report naming purposes
     */
    public static String getAnalysisTime()
    {
        return new SimpleDateFormat("yyyymmdd-hhmmss'-summary'").format(new Date() );
    }
}