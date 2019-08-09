package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

/**
 * Test case for WebsiteAnalysis class
 *
 */

public class TestWebsiteAnalysis 
{ 	
	@Test
	public void TestAnalysisTime()
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String testTime = "this is a time, just trust me";
		
		assertNull(testAnalysis.getAnalysisTime() );
		
		testAnalysis.setAnalysisTime(testTime);
		
		assertThat(testAnalysis.getAnalysisTime(), is(testTime) );
	}
	
	@Test
	public void TestIsReady()
	{
		//make an empty analysis
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis(); 
		
		String filepath = new File("src/test/resources").getAbsolutePath().toString();
		
		//it should not be ready
		assertThat(testAnalysis.isReady(), is(false) ); 
		
		//make an analysis with valid information
		testAnalysis = new WebsiteAnalysis( new String[]{ filepath,"https://www.test.com/testing/test/test1","https://www.test.com/testing/test/test2" }, "now" ); 
		//it should be ready
		assertThat(testAnalysis.isReady(), is(true) ); 
				
		//do not pass in time, all other information is valid
		testAnalysis = new WebsiteAnalysis( new String[]{ filepath,"https://www.test.com/testing/test/test1","https://www.test.com/testing/test/test2" }, null ); 
		//it should not be ready
		assertThat(testAnalysis.isReady(), is(false) ); 
	}
	
}
