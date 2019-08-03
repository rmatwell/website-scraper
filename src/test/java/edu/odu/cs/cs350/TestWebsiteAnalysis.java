package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

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
		
		//it should not be ready
		assertThat(testAnalysis.isReady(), is(false) ); 
		
		//make an analysis with valid information
		testAnalysis = new WebsiteAnalysis( new String[]{ "testing/test","https://www.test.com/testing/test/test1","https://www.test.com/testing/test/test2" }, "now" ); 
		//it should be ready
		assertThat(testAnalysis.isReady(), is(true) ); 
		
		 //make an analysis with bogus information
		testAnalysis = new WebsiteAnalysis( new String[]{ "testing/NOTREALDIR","https://www.test.com/testing/test/test1","https://www.test.com/testing/test/test2" }, "now" );
		//it should not be ready 
		assertThat(testAnalysis.isReady(),is(false) ); 
		
		//make an analysis with one bad URL
		testAnalysis = new WebsiteAnalysis( new String[]{ "testing/test","https://www.test.com/testing/test/notreal","https://www.test.com/testing/test/test2" }, "now" ); 
		//it should be ready
		assertThat(testAnalysis.isReady(),is(true) ); 
		
		//make an analysis with two bad URLs
		testAnalysis = new WebsiteAnalysis( new String[]{ "testing/test","https://www.test.com/testing/test/notreal","https://www.test.com/testing/test/alsonotreal" }, "now" ); 
		//it should not be ready
		assertThat(testAnalysis.isReady(),is(false) ); 
		
		//do not pass in time, all other information is valid
		testAnalysis = new WebsiteAnalysis( new String[]{ "testing/test","https://www.test.com/testing/test/test1","https://www.test.com/testing/test/test2" }, null ); 
		//it should not be ready
		assertThat(testAnalysis.isReady(), is(false) ); 
	}
}
