package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

import java.util.*;

public class TestWebsiteAnalysis 
{
	@Test
	public void TestSetUserPath() //once logic is introduced to actually test the validity of the path, this test should be modified to reflect that
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String goodPath = "dir1/dir2";
		//String badPath = "dir1:dir2";
		
		assertNull(testAnalysis.getUserFilePath() );
		testAnalysis.setUserFilePath(goodPath);
		//because the path was good, it should take
		assertThat(testAnalysis.getUserFilePath(), is(goodPath) );
	}
	@Test
	public void TestSetUserURLs()
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String[] goodURLs = {"www.test.com/test1","www.test.com/test2"};
		
		assertThat(testAnalysis.getUserURLs(), is(new HashSet<String>() ) );
		
		testAnalysis.setUserURLs(goodURLs);
		
		assertThat(testAnalysis.getUserURLs(), is(new HashSet<String>(Arrays.asList(goodURLs) ) ) );
	}
	@Test
	public void TestAnalysisTime()
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String testTime = "this is a time, just trust me";
		
		assertNull(testAnalysis.getAnalysisTime() );
		
		testAnalysis.setAnalysisTime(testTime);
		
		assertThat(testAnalysis.getAnalysisTime(), is(testTime) );
		
	}
}
