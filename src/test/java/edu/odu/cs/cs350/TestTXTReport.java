package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestTXTReport {
	
	TXTReport testTXT, testDefault;
	
	private String analysisTime = "20190724-113553-summary";
	private Website website;
	
	@Before 
	public void setUp() {
		
		testDefault = new TXTReport();
		testTXT = new TXTReport(website, analysisTime);
		
	}
	
	@Test
	public void testResource() {
		
		assertThat(testDefault.getAnalysisTime(), is(""));
		assertThat(testDefault.getFileName(), is(""));
		assertThat(testDefault.toString(), containsString(""));
		assertThat(testDefault, equalTo(testDefault));
		
	}
	
}
	