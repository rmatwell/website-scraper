package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class TestHTMLFile {
	
	HTMLFile defaultConstruct;
	HTMLFile testConstructor;
	
	private File inputPath;
	private URL inputURL;
	
	
	@Before
	public void setUp() {
		
		defaultConstruct = new HTMLFile();
        testConstructor = new HTMLFile(inputPath, inputURL);
		
	}
	
}