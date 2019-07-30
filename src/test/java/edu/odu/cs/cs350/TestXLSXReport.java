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

public class TestXLSXReport {

	XLSXReport testXLSX, testDefaultC;

	private String analysisTime = "20190729-082547-summary";
	private Website website;

	@Before
	public void setUp() {

		testDefaultC = new XLSXReport();
		testXLSX = new XLSXReport(website, analysisTime);

	}

	@Test
	public void testResource() {

		assertThat(testDefaultC.getAnalysisTime(), is(""));
		assertThat(testDefaultC.getFileName(), is(""));
		assertThat(testDefaultC.toString(), containsString(""));
		assertThat(testDefaultC, equalTo(testDefaultC));

	}

	@Test
	public void testXLSXReport() {

		assertThat(testXLSX.getAnalysisTime(), is("20190729-082547-summary"));

		testXLSX.setFileName(analysisTime);
		assertThat(testXLSX.getFileName(), is("20190729-082547-summary.xlsx"));
		assertThat(testXLSX.toString(), containsString(""));
		assertThat(testXLSX.getWebsite(), is(website));
		assertThat(testXLSX, instanceOf(XLSXReport.class));
		assertThat(analysisTime, not(instanceOf(XLSXReport.class)));
		assertNotEquals(testXLSX, testDefaultC);


	}
	
	@Test
	public void testSetFileName() {
		
		int oldHashCode = testXLSX.hashCode();

		testXLSX.setFileName("20190729-082547-summary");

		assertEquals("20190729-082547-summary.xlsx", testXLSX.getFileName());

		assertNotEquals(oldHashCode, testXLSX.hashCode());
		
	}
	
	@Test
	public void testClone() throws CloneNotSupportedException {

		testXLSX.setFileName(analysisTime);
		XLSXReport aCopy = testXLSX.clone();

		assertThat(aCopy.getAnalysisTime(), equalTo(testXLSX.getAnalysisTime()));
		assertThat(aCopy.getFileName(), equalTo(testXLSX.getFileName()));
		assertThat(aCopy.getWebsite(), equalTo(testXLSX.getWebsite()));
		assertThat(aCopy.hashCode(), equalTo(testXLSX.hashCode()));
		assertThat(aCopy, equalTo(testXLSX));
		assertThat(aCopy.toString(), equalTo(testXLSX.toString()));

	}

}