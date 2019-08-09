package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for Webpage class
 *
 */

public class TestWebpage {

    Webpage testWebpage, testDefault;

    private String path = "test/directory/works";

    private Map<String, Integer> imageCount = new HashMap<String, Integer>();
    private Map<String, Integer> jsCount = new HashMap<String, Integer>();
    private Map<String, Integer> cssCount = new HashMap<String, Integer>();
    private Set<String> imagePaths = new HashSet<String>();
    private Set<String> scriptPaths = new HashSet<String>();
    private Set<String> cssPaths = new HashSet<String>();
    private Map<String, Integer> linkCount = new HashMap<String, Integer>();

    JSONReport report;

    @Before
    public void setUp() {

        testDefault = new Webpage();
        testWebpage = new Webpage(path);

    }

    @Test
    public void testWebpageConstructor() {
        assertThat(testWebpage.getPath(), is(equalTo(path)));

        testWebpage.setAbsolutePath(path);

        cssCount.put("local", 2);
        cssCount.put("external", 1);
        testWebpage.setCssCount(cssCount);

        assertThat(testWebpage.getCssCount(), is(equalTo(cssCount)));

        jsCount.put("local", 2);
        jsCount.put("external", 1);
        testWebpage.setJsCount(jsCount);

        assertThat(testWebpage.getjsCount(), is(equalTo(jsCount)));

        imageCount.put("local", 2);
        imageCount.put("external", 1);
        testWebpage.setImageCount(imageCount);

        assertThat(testWebpage.getImageCount(), is(equalTo(imageCount)));

        linkCount.put("intra-page", 2);
        linkCount.put("external", 1);
        linkCount.put("intra-site", 1);
        testWebpage.setLinkCount(linkCount);

        assertThat(testWebpage.getLinkCount(), is(equalTo(linkCount)));

        String analysisTime = "20190725-103257-summary";

        report = new JSONReport(testWebpage, analysisTime);

        String json = report.writeJSON(testWebpage);

        assertThat(json, containsString("test/directory/works"));




    }


}