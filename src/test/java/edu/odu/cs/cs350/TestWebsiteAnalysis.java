package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

import java.util.*;

import java.io.File;
import java.net.*;

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
	public void TestSetUserURLsHappyPath()
	{
		//setup
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		testAnalysis.setUserFilePath("testing/test");
		
		String goodURL1 = "https://www.test.com/testing/test/test1";
		String goodURL2 = "https://www.test.com/testing/test/test2";

		String[] goodURLs = {goodURL1,goodURL2};
		
		HashSet<File> goodURLsHash = new HashSet<File>();
		try
		{
			goodURLsHash.add(new File(new URL(goodURL1).getPath().replaceFirst("/", "") ) );
			goodURLsHash.add(new File(new URL(goodURL2).getPath().replaceFirst("/", "") ) );
		}
		catch(MalformedURLException e)
		{
			
		}
	
		//actual test
		assertThat(testAnalysis.getTranslatedUserURLs(), is(new HashSet<String>() ) );
		
		testAnalysis.setUserFilePath("testing/test");
		testAnalysis.setUserURLs(goodURLs);
		
		assertThat(testAnalysis.getTranslatedUserURLs(), is( goodURLsHash) );
	}
	
	@Test
	public void TestSetUserURLsInvalid()
	{
		//setup
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		testAnalysis.setUserFilePath("testing/test");
		
		String badURL1 = "testdir1//testdir2";
		String badURL2 = "com.IamNotAWebSite.www";

		String[] badURLs = {badURL1,badURL2};
		
		HashSet<URL> badURLsHash = new HashSet<URL>();
		try
		{
			badURLsHash.add(new URL(badURL1) );
			badURLsHash.add(new URL(badURL2) );
		}
		catch(MalformedURLException e)
		{
			
		}
	
		//actual test
		assertThat(testAnalysis.getTranslatedUserURLs(), is(new HashSet<File>() ) );
		
		testAnalysis.setUserURLs(badURLs);
		
		assertThat(testAnalysis.getTranslatedUserURLs(), is(new HashSet<File>() ) );
	}
	

	@Test
	public void TestSetUserURLsOutOfPath()
	{
		//setup
		WebsiteAnalysis testAnalysis = new WebsiteAnalysis();
		testAnalysis.setUserFilePath("testing\\notTest");
		
		String goodURL1 = "https://www.test.com/testing/test/test1";
		String goodURL2 = "https://www.test.com/testing/test/test2";

		String[] goodURLs = {goodURL1,goodURL2};
		
		HashSet<File> goodURLsHash = new HashSet<File>();
		try
		{
			goodURLsHash.add(new File(new URL(goodURL1).getPath().replaceFirst("/", "") ) );
			goodURLsHash.add(new File(new URL(goodURL2).getPath().replaceFirst("/", "") ) );
		}
		catch(MalformedURLException e)
		{
			
		}
	
		//actual test
		assertThat(testAnalysis.getTranslatedUserURLs(), is(new HashSet<String>() ) );
		
		testAnalysis.setUserURLs(goodURLs);
		
		assertThat(testAnalysis.getTranslatedUserURLs(), is(new HashSet<String>() ) );
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
