package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class TestJSONReport {
	
	JSONReport testReport;
	
	private String analysisTime = "20190725-103257-summary.json";
	private Website website;
	
	@Before
	public void setUp() {
		
		testReport = new JSONReport(website, analysisTime);
		
	}
	
	@Test
	public void testJSONReport() {
		
		assertThat(testReport.getFileName(), is(""));
		//still working on, will continue tonight
		
	}
	
}