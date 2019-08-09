package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

import java.util.*;

import java.io.File;
import java.io.IOException;
import java.net.*;

public class TestWebsite 
{
	@Test
	public void TestSetUserPathGoodPath()
	{
		Website testSite = new Website();
		
		String goodPath = "build/libs";
		File goodPathFile = new File(goodPath);

		
		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(goodPathFile.getAbsolutePath().toString());
		
		//because the path was good, it should take
		assertThat(testSite.getUserFilePath().getAbsolutePath(), is(goodPathFile.getAbsolutePath()) );
	}
	
	@Test
	public void TestSetUserPathNonExistantPath()
	{
		Website testSite = new Website();
		
		String nonPath = "build/libz";
		File nonPathFile = new File(nonPath);

		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(nonPathFile.getAbsolutePath().toString());

		assertNull(testSite.getUserFilePath() );
	}
	
	@Test
	public void TestSetUserPathNotAPath() 	
	{
		Website testSite = new Website();
		
		String nonPath = "gradlew.bat"; //using this because gradlew.bat is basically guaranteed to work
		File nonPathFile = new File(nonPath );
		
		assertNull(testSite.getUserFilePath() );
		testSite.setUserFilePath(nonPathFile.getAbsolutePath().toString());
		

		assertNull(testSite.getUserFilePath() );
	}
			
	@Test
	public void TestSetPagesHappyPath()
	{
		Website testSite = new Website();
		
		assertThat(testSite.getPages(), is(new HashSet<Webpage>() ) );
		
		testSite.setUserURLs(new String[]{"https://www.test.com/testing/test/test1" , "https://www.test.com/testing/test/test2"});
		
		assertThat(testSite.getUserURLs().isEmpty(), is(false) );
	}
	
	@Test
	public void TestURLtoURI()
	{
		Website testSite = new Website();
		testSite.setUserURLs(new String[]{"https://www.test.com/testing/test/test1" , "https://www.test.com/testing/test/test2"});
		
		Set<URI> testURIset = new HashSet<URI>();
		try 
		{
			testURIset.add(new URI("https://www.test.com/testing/test/test1") );
			testURIset.add(new URI("https://www.test.com/testing/test/test2") );
		} 
		catch (URISyntaxException e) 
		{}
		
		assertThat(testSite.getUserURLsAsURI(), is(testURIset) );


		
	}
}
