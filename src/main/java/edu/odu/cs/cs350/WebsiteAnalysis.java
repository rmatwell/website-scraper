package edu.odu.cs.cs350;

public class WebsiteAnalysis 
{
	//variables
	private Website analysisSubject = new Website();
	//TagExtractor extractor;
	
	private String analysisTime;
	
	//constructors
	
	//'default' constructor
	public WebsiteAnalysis()
	{
		
	}
	//constructor used by analysis input manager
	public WebsiteAnalysis(String[] args, String analysisTime)
	{
		setAnalysisSubject(args); //hand the Website information it needs to build itself
		setAnalysisTime(analysisTime); //keep track of when the analysis was started for reports
	}
	
	public String getAnalysisTime()
	{
		return analysisTime;
	}
	
	public void setAnalysisTime(String input)
	{
		analysisTime = input;
	}
	
	public Website getAnalysisSubject()
	{
		return analysisSubject;
	}
	
	public void setAnalysisSubject(String[] args)
	{
		analysisSubject = new Website(args);
	}
	
	//determines whether or not the analysis has the information necessary to begin (i.e. date time, valid user path, valid user sites)
	public boolean isReady()
	{
		return ( analysisSubject.getUserFilePath() != null && analysisTime != null && !analysisSubject.getPages().isEmpty() );
	}
	
	public void parseFiles()
	{
		//make tag extractor do things to the HTMLFiles in Website to make Resources
	}
	public void generateReports()
	{
		TXTReport txtWriter = new TXTReport(analysisSubject, analysisTime);
		//txtWriter.createTxtFile();
		
		XLSXReport xlsxWriter = new XLSXReport(analysisSubject, analysisTime);
		//xlsxWriter.createXLSXFile();
		
		JSONReport jsonWriter = new JSONReport(analysisSubject, analysisTime);
		//jsonWriter.createJSONFile();
	}
}
