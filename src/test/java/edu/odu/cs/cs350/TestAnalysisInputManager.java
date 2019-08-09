package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
//import org.junit.Before;

import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.closeTo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

//this tests only that when main gets correct arguments, all the reports are generated. every other class along the way has its own tests, so no need to do detailed testing here
public class TestAnalysisInputManager 
{
	@SuppressWarnings("static-access")
	@Test
	public void analysisPipelineWorking()
	{
		AnalysisInputManager testManager = new AnalysisInputManager();
		try 
		{
		  testManager.main(new String[]{"src/test/resources/edu/odu/cs/cs350/" ,"https://www.test1.com/test1", "https://www.test2.com/test2"});
		} catch (URISyntaxException | IOException e) 
		{}

		assertTrue( new File(testManager.getAnalysisTime() + ".txt" ).getAbsoluteFile().exists() );
		assertTrue( new File(testManager.getAnalysisTime() + ".json" ).getAbsoluteFile().exists() );
		assertTrue( new File(testManager.getAnalysisTime() + ".xlsx" ).getAbsoluteFile().exists() );
	}
}
