package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

//in progress


public class TestJSONReport {

	JSONReport testJSON;

	private String analysisTime = "20190725-103257-summary";
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

		int oldHashCode = testJSON.hashCode();

		testJSON.setFileName("20190725-103257-summary");

		assertEquals("20190725-103257-summary.json", testJSON.getFileName());

		assertNotEquals(oldHashCode, testJSON.hashCode());

	}

}