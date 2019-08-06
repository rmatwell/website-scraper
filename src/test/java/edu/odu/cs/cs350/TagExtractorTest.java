package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;


/**
 * 	Test Case for TagExtractor Class.
 *
 */
public class TagExtractorTest {

    private File file = new File("src/test/resources/edu/odu/cs/cs350/test.html");

    private File root = new File("src/test/resources/edu/odu/cs/cs350/");

    private File subDirectoryTest= new File("src/test/resources/edu/odu/cs/");

    TagExtractor testExtractor, anotherExtractor;

    private URI rootDirectory;

    private URI root2;

    private String rootToSource = "./src/test/resources/edu/odu/cs/cs350/";

    private String anotherRoot = "/home/system/directory/";

    private String url1 = "https://www.test1.com/test1";

    private String url2 = "https://www.test2.com/test2";

    private String url3 = "https://www.test3.com/test3";

    private HashSet<URI> urls = new HashSet<>();

    private HashSet<URI> anotherURL = new HashSet<>();

    private Website website;


    @Before
    public void setUp() throws URISyntaxException {

        website = new Website();

        rootDirectory = new URI(rootToSource);

        root2 = new URI(anotherRoot);

        urls.add(new URI(url1) );

        urls.add(new URI(url2) );

        anotherURL.add(new URI(url3));

        testExtractor = new TagExtractor(rootToSource, urls, website);

        anotherExtractor = new TagExtractor(anotherRoot, anotherURL, website);
    }

    @Test
    public void testEqualsNull() {
        assertFalse (testExtractor.equals(null));
    }

    @Test
    public void testTagExtractor() {

        double hash = testExtractor.hashCode();

        File file = new File("src/test/resources/edu/odu/cs/cs350/image.jpg");

        double fileSize = testExtractor.calculateMiB(file);

        assertThat(fileSize, is(closeTo(1.21, .01)));

        String formatSize = testExtractor.formatFileSize(fileSize);

        assertThat(formatSize, containsString("1.21 MiB"));

        testExtractor.timeOfAnalysis();
        assertThat(testExtractor.getAnalysisTime(),
                containsString("-summary"));
        assertThat(testExtractor.toString(),
                containsString("src/test/resources/edu/odu/cs/cs350 , "
                        + "[https://www.test2.com/test2, "
                        + "https://www.test1.com/test1"));
        assertThat(testExtractor, equalTo(testExtractor));

        double finalHash = testExtractor.hashCode();

        assertThat(hash, not(equalTo(finalHash)));

        assertThat(testExtractor, instanceOf(TagExtractor.class));

    }

    @Test
    public void testNotEqualExtractors() {
        assertFalse(testExtractor.equals(anotherExtractor));
    }

    //    @Test
    //    public void testExtractImageTags() throws CloneNotSupportedException, IOException, URISyntaxException {
    //
    //        Document document;
    //        document = Jsoup.parse(file,"UTF-8");
    //
    //        testExtractor.extractImageTags(document);
    //
    //        Iterator<Resource> itr = testExtractor.getImages().iterator();
    //
    //
    //        String string = itr.next().toString();
    //        assertThat(string, containsString("pic.jpg"));
    //
    //    }

    //    @Test
    //    public void testExtractLinkTags() throws CloneNotSupportedException, IOException, URISyntaxException {
    //
    //        Document document;
    //        document = Jsoup.parse(file,"UTF-8");
    //
    //        testExtractor.extractLinkTags(document);
    //
    //        Iterator<Resource> itr = testExtractor.getLinks().iterator();
    //
    //
    //        String string = itr.next().getUrl();
    //        assertThat(string, containsString("https://maxcdn.bootstrapcdn.com/"
    //                + "bootstrap/3.3.5/css/bootstrap.min.css"));
    //
    //    }

    //    @Test
    //    public void testExtractScriptTags() throws CloneNotSupportedException, IOException, URISyntaxException {
    //
    //        Document document;
    //        document = Jsoup.parse(file,"UTF-8");
    //
    //        testExtractor.extractScriptTags(document);
    //
    //        Iterator<Resource> itr = testExtractor.getScripts().iterator();
    //
    //        String string = itr.next().getUrl();
    //        assertThat(string, containsString("https://maxcdn.bootstrapcdn.com"
    //                + "/bootstrap/3.3.5/js/bootstrap.min.js"));
    //
    //    }



    @Test
    public void testClone() throws CloneNotSupportedException {

        testExtractor.timeOfAnalysis();
        TagExtractor aCopy = testExtractor.clone();

        assertThat(aCopy.getAnalysisTime(), equalTo(testExtractor.getAnalysisTime()));
        assertThat(aCopy.getRootDirectory(), equalTo(testExtractor.getRootDirectory()));
        assertThat(aCopy.getUserURLs(), equalTo(testExtractor.getUserURLs()));

        assertThat(aCopy.hashCode(), equalTo(testExtractor.hashCode()));
        assertThat(aCopy, equalTo(testExtractor));
        assertThat(aCopy.toString(), equalTo(testExtractor.toString()));
    }

    @Test
    public void testTraverseFiles() throws CloneNotSupportedException, IOException, URISyntaxException {


        testExtractor.runExtractor();

        JSONReport report = new JSONReport();

        String json = report.writeJSON(testExtractor.getWebsite());

        System.out.println(json);



        assertThat(json, containsString("{\n" +
                "  \"path\":\"src/test/resources/edu/odu/cs/cs350/test.html\",\n" +
                "  \"imageCount\":{\n" +
                "    \"external\":1,\n" +
                "    \"local\":1\n" +
                "  },\n" +
                "  \"jsCount\":{\n" +
                "    \"external\":2,\n" +
                "    \"local\":1\n" +
                "  },\n" +
                "  \"cssCount\":{\n" +
                "    \"external\":1,\n" +
                "    \"local\":2\n" +
                "  },\n" +
                "  \"imagePaths\":[\n" +
                "    \"src/test/resources/edu/odu/cs/cs350/pic.jpg\",\n" +
                "    \"https://www.google.com/image.bmp\"\n" +
                "  ],\n" +
                "  \"scriptPaths\":[\n" +
                "    \"src/test/resources/edu/odu/cs/cs350/scripts/jquery-1.11.1.min.js\",\n" +
                "    \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "    \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "  ],\n" +
                "  \"cssPaths\":[\n" +
                "    \"src/test/resources/edu/odu/cs/cs350/styles/layout.css\",\n" +
                "    \"src/test/resources/edu/odu/cs/cs350/styles/home.css\",\n" +
                "    \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"\n" +
                "  ],\n" +
                "  \"linkCount\":{\n" +
                "    \"external\":1,\n" +
                "    \"intra-page\":1,\n" +
                "    \"intra-site\":3\n" +
                "  }\n" +
                "}"));


    }

    @Test
    public void testTraverseSubDirectories()
            throws CloneNotSupportedException, IOException, URISyntaxException {

        File[] files = subDirectoryTest.listFiles();

        testExtractor.traverseFiles(files);

        JSONReport report = new JSONReport();

        String json = report.writeJSON(testExtractor.getPage());

        //System.out.println(json);



        assertThat(json, containsString("{\n" +
                "  \"pages\":[\n" +
                "    {\n" +
                "      \"path\":\"./src/test/resources/edu/odu/cs/cs350/test1.html\",\n" +
                "      \"imageCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"jsCount\":{\n" +
                "        \"external\":2,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"cssCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"local\":2\n" +
                "      },\n" +
                "      \"imagePaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/image.jpg\",\n" +
                "        \"https://www.google.com/image2.bmp\"\n" +
                "      ],\n" +
                "      \"scriptPaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/scripts/jquery-1.11.1.min.js\",\n" +
                "        \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "      ],\n" +
                "      \"cssPaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/styles/layout.css\",\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/styles/home.css\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"\n" +
                "      ],\n" +
                "      \"linkCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"intra-page\":1,\n" +
                "        \"intra-site\":3\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"path\":\"./src/test/resources/edu/odu/cs/cs350/test.html\",\n" +
                "      \"imageCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"jsCount\":{\n" +
                "        \"external\":2,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"cssCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"local\":2\n" +
                "      },\n" +
                "      \"imagePaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/pic.jpg\",\n" +
                "        \"https://www.google.com/image.bmp\"\n" +
                "      ],\n" +
                "      \"scriptPaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/scripts/jquery-1.11.1.min.js\",\n" +
                "        \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "      ],\n" +
                "      \"cssPaths\":[\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/styles/layout.css\",\n" +
                "        \"src/test/resources/edu/odu/cs/cs350/styles/home.css\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"\n" +
                "      ],\n" +
                "      \"linkCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"intra-page\":1,\n" +
                "        \"intra-site\":3\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"));

    }

}
