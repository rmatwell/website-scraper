package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.*;

/**
 * Test case for Webpage class
 *
 */

public class TestWebpage {
	
	Webpage testWebpage, testDefault;
	
	private String path;

    private Map<String, Integer> imageCount = new HashMap<String, Integer>();
    private Map<String, Integer> jsCount = new HashMap<String, Integer>();
    private Map<String, Integer> cssCount = new HashMap<String, Integer>();
    private Set<String> imagePaths = new HashSet<String>();
    private Set<String> scriptPaths = new HashSet<String>();
    private Set<String> cssPaths = new HashSet<String>();
    private Map<String, Integer> linkCount = new HashMap<String, Integer>();
    
    @Before
    public void setUp() {
    	
    	testDefault = new Webpage();
    	testWebpage = new Webpage(path);
    	
    }  
	
}