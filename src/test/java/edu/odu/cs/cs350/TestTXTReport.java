package edu.odu.cs.cs350;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

/**
 * Test case for TXTReport class
 *
 */

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
	public void testDefaultConstructor() 
	{
		assertNull(testDefault.getFileName());
		assertThat(testDefault.toString(), containsString(""));
		assertThat(testDefault, equalTo(testDefault));
	}
	
	@Test
	public void testNonDefaultConstructor() 
	{
		
		testTXT.setFileName(analysisTime);
		assertThat(testTXT.getFileName(), is("20190724-113553-summary.txt"));
		assertThat(testTXT.toString(), containsString(""));
		assertThat(testTXT.getWebsite(), is(website));
		assertThat(testTXT,  instanceOf(TXTReport.class));
		assertThat(analysisTime, not(instanceOf(TXTReport.class)));
		assertNotEquals(testTXT, testDefault);
		
	}
	
	@Test
	public void testSetFileName() 
	{

		assertThat(testTXT.getFileName(), is(analysisTime + ".txt"));
		
		testTXT.setFileName("20190724-113553-summary");

		assertEquals("20190724-113553-summary.txt", testTXT.getFileName());
		
	}

}
	