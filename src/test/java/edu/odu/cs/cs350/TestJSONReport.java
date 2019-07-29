package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//in progress


public class TestJSONReport {
	
	JSONReport testJSON;
	
	private String analysisTime = "20190725-103257-summary.json";
	private Website website;
	
	@Before
	public void setUp() {
		
		testJSON = new JSONReport(website, analysisTime);
		
	}
	
	@Test
	public void testJSONReport() {
		
		assertThat(testJSON.getFileName(), is(""));
		
	}
	
	@Test
	public void testSetFileName() {
		
		JSONReport report = (JSONReport) testJSON.clone();
		int oldHashCode = testJSON.hashCode();
		
		testJSON.setFileName("20190725-103257-summary.json");
		
		assertEquals("20190725-103257-summary.json", testJSON.getFileName());
		
		assertNotEquals(oldHashCode, testJSON.hashCode());
		
	}
	
}