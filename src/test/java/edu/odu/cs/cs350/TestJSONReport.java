package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class TestJSONReport {
	
	JSONReport defaultConstruct;
	JSONReport testConstructor;
	
	private String analysisTime = "20190725-103257-summary.json";
	
	@Before
	public void setUp() {
		
		defaultConstruct = new JSONReport();
		testConstructor = new JSONReport();
		
	}
	
	@Test
	public void testJSONReport() {
		
		assertThat(defaultConstruct.getFileName(), is(""));
		//still working on, will continue tonight
		
	}
	
}