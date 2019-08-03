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

public class TestWebsite 
{
	@Test
	public void TestSetUserPathGoodPath()
	{
		Website testSite = new Website();
		
		String goodPath = "build\\libs";
		File goodPathFile = new File(goodPath);
		
		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(goodPath);
		
		//because the path was good, it should take
		assertThat(testSite.getUserFilePath(), is(goodPathFile) );
	}
	
	@Test
	public void TestSetUserPathNonExistantPath()
	{
		Website testSite = new Website();
		
		String nonPath = "build\\libz";
		File nonPathFile = new File(nonPath);

		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(nonPath);

		assertNull(testSite.getUserFilePath() );
	}
	
	@Test
	public void TestSetUserPathNotAPath() 	
	{
		Website testSite = new Website();
		
		String nonPath = "gradlew.bat"; //using this because gradlew.bat is basically guaranteed to work
		File nonPathFile = new File(nonPath);
		
		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(nonPath);
		

		assertNull(testSite.getUserFilePath() );
	}
		
	@Test
	public void TestSetPagesHappyPath()
	{
		Website testSite = new Website();
		testSite.setUserFilePath("testing/test");
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
		
		testSite.setUserURLs(new String[]{"https://www.test.com/testing/test/test1" , "https://www.test.com/testing/test/test2"});
		
		assertThat(testSite.getUserURLs().isEmpty(), is(false) );
	}
	
	@Test
	public void TestSetUserURLsInvalid()
	{
		Website testSite = new Website();
		testSite.setUserFilePath("testing/test");
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
		
		testSite.setUserURLs(new String[]{"testdir1//testdir2" , "com.IamNotAWebSite.www"});
	
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
	}
	
	@Test
	public void TestSetUserURLsOutOfPath()
	{
		//setup
		Website testSite = new Website();
		testSite.setUserFilePath("testing/nottest");
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
		
		testSite.setUserURLs(new String[]{"https://www.test.com/testing/test/test1" , "https://www.test.com/testing/test/test2"});
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
	}

	@Test
	public void TestSetUserURLsNonExistant()
	{
		//setup
		Website testSite = new Website();
		testSite.setUserFilePath("testing/test");
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
		
		testSite.setUserURLs(new String[]{"https://www.test.com/testing/test/test3" , "https://www.test.com/testing/test/test4"});
		
		assertThat(testSite.getPages(), is(new HashSet<HTMLFile>() ) );
	}	
}
