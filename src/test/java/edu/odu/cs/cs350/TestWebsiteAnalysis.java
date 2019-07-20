package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

import java.util.*;
import java.io.File;

public class TestWebsiteAnalysis 
{
	@Test
	public void TestSetUserPathGoodPath()
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String goodPath = "build\\libs";
		File goodPathFile = new File(goodPath);
		
		assertNull(testAnalysis.getUserFilePath() );
		testAnalysis.setUserFilePath(goodPath);
		
		//because the path was good, it should take
		assertThat(testAnalysis.getUserFilePath(), is(goodPathFile) );
	}
	
	@Test
	public void TestSetUserPathNonExistantPath()
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String nonPath = "build\\libz";
		File nonPathFile = new File(nonPath);

		assertNull(testAnalysis.getUserFilePath() );
		testAnalysis.setUserFilePath(nonPath);

		assertNull(testAnalysis.getUserFilePath() );
	}
	
	@Test
	public void TestSetUserPathNotAPath() 	
	{
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		
		String nonPath = "gradlew.bat"; //using this because gradlew.bat is basically guaranteed to work
		File nonPathFile = new File(nonPath);
		
		assertNull(testAnalysis.getUserFilePath() );
		testAnalysis.setUserFilePath(nonPath);
		

		assertNull(testAnalysis.getUserFilePath() );
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
