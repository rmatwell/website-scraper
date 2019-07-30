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
	
	@Test
	public void testTXTReport() {
		
		assertThat(testTXT.getAnalysisTime(), is("20190724-113553-summary"));

		testTXT.setFileName(analysisTime);
		assertThat(testTXT.getFileName(), is("20190724-113553-summary.txt"));
		assertThat(testTXT.toString(), containsString(""));
		assertThat(testTXT.getWebsite(), is(website));
		assertThat(testTXT,  instanceOf(TXTReport.class));
		assertThat(analysisTime, not(instanceOf(TXTReport.class)));
		assertNotEquals(testTXT, testDefault);
		
	}
	
	@Test
	public void testSetFileName() {
		
		int oldHashCode = testTXT.hashCode();

		testTXT.setFileName("20190724-113553-summary");

		assertEquals("20190724-113553-summary.txt", testTXT.getFileName());

		assertNotEquals(oldHashCode, testTXT.hashCode());
		
	}
	
	@Test
	public void testClone() throws CloneNotSupportedException {

		testTXT.setFileName(analysisTime);
		TXTReport aCopy = testTXT.clone();

		assertThat(aCopy.getAnalysisTime(), equalTo(testTXT.getAnalysisTime()));
		assertThat(aCopy.getFileName(), equalTo(testTXT.getFileName()));
		assertThat(aCopy.getWebsite(), equalTo(testTXT.getWebsite()));
		assertThat(aCopy.hashCode(), equalTo(testTXT.hashCode()));
		assertThat(aCopy.toString(), equalTo(testTXT.toString()));
		assertThat(aCopy, equalTo(testTXT));
		
	}
}
	
}
	