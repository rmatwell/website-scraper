package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestXLSXReport {
	
	XLSXReport testXLSX, testDefault;
	
	private String analysisTime = "20190729-082547-summary";
	private Website website;
	
	@Before
	public void setUp() {
		
		testDefault = new XLSXReport();
		testXLSX = new XLSXReport(website, analysisTime);
		
	}
	
	@Test
	public void testResource() {
		
		assertThat(testDefault.getAnalysisTime(), is(""));
		assertThat(testDefault.getFileName(), is(""));
		assertThat(testDefault.toString(), containsString(""));
		assertThat(testDefault, equalTo(testDefault));
		
	}
	
	@Test
	public void testXLSXReport() {
		
		assertThat(testXLSX.getAnalysisTime(), is("20190729-082547-summary"));
		
		testXLSX.setFilename(analysisTime);
		assertThat(testXLSX.getFileName(), is("20190729-082547-summary.xlsx"));
		assertThat(testXLSX.toString(), containsString(""));
		assertThat(testXLSX.getWebsite(), is(website));
		assertThat(testXLSX, instanceOf(XLSXReport.class));
		assertThat(analysisTime, not(instanceOf(XLSXReport.class)));
		assertNotEquals(testXLSX, testDefault);
		
		
	}
	
}