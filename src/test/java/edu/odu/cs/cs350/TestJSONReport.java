package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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

		assertThat(testJSON.getAnalysisTime(), is("20190725-103257-summary"));

		testJSON.setFileName(analysisTime);
		assertThat(testJSON.getFileName(), is("20190725-103257-summary.json"));
		assertThat(testJSON.toString(), containsString(""));
		assertThat(testJSON.getWebsite(), is(website));


	}

	@Test
	public void testSetFileName() {

		int oldHashCode = testJSON.hashCode();

		testJSON.setFileName("20190725-103257-summary");

		assertEquals("20190725-103257-summary.json", testJSON.getFileName());

		assertNotEquals(oldHashCode, testJSON.hashCode());

	}

	@Test
	public void testClone() throws CloneNotSupportedException {

		testJSON.setFileName(analysisTime);
		JSONReport aCopy = testJSON.clone();

		assertThat(aCopy.getAnalysisTime(), equalTo(testJSON.getAnalysisTime()));
		assertThat(aCopy.getFileName(), equalTo(testJSON.getFileName()));
		assertThat(aCopy.getWebsite(), equalTo(testJSON.getWebsite()));

		assertThat(aCopy.hashCode(), equalTo(testJSON.hashCode()));
		assertThat(aCopy, equalTo(testJSON));
		assertThat(aCopy.toString(), equalTo(testJSON.toString()));

	}

}