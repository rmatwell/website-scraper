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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;

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

    private Iterator<String> itr;

    private File rootToSource = new File( "src/test/resources/edu/odu/cs/cs350/");

    private File anotherRoot = new File( "home/system/directory/");

    private String url1 = "https://www.test1.com/test1";

    private String url2 = "https://www.test2.com/test2";

    private String url3 = "https://www.test3.com/test3";

    private HashSet<URI> urls = new HashSet<>();

    private HashSet<URI> anotherURL = new HashSet<>();

    private Website website;

    private String absRoot;


    @Before
    public void setUp() throws URISyntaxException, MalformedURLException {

        absRoot = rootToSource.getAbsolutePath().toString();

        String [] args = {absRoot, "https://www.test1.com/test1", "https://www.test2.com/test2"};

        website = new Website(args);


        root2 = anotherRoot.toURI();

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

        String expectedString = testExtractor.getRootDirectory().toString();

        testExtractor.timeOfAnalysis();
        assertThat(testExtractor.getAnalysisTime(),
                containsString("-summary"));
        assertThat(testExtractor.toString(),
                containsString(expectedString + " , "
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

        String json = report.writeJSON(testExtractor.getPage());

        assertThat(json, containsString("{\n" +
                "  \"path\":\"./test.html\",\n" +
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
                "    \"https://www.google.com/image.bmp\",\n" +
                "    \"image.jpg\"\n" +
                "  ],\n" +
                "  \"scriptPaths\":[\n" +
                "    \"scripts/jquery-1.11.1.min.js\",\n" +
                "    \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "    \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "  ],\n" +
                "  \"cssPaths\":[\n" +
                "    \"styles/layout.css\",\n" +
                "    \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\",\n" +
                "    \"styles/home.css\"\n" +
                "  ],\n" +
                "  \"linkCount\":{\n" +
                "    \"external\":1,\n" +
                "    \"intra-page\":1,\n" +
                "    \"intra-site\":5\n" +
                "  }\n" +
                "}"));


    }

    @Test
    public void testTraverseSubDirectories()
            throws CloneNotSupportedException, IOException, URISyntaxException {

        File[] files = subDirectoryTest.listFiles();

        testExtractor.traverseFiles(files);

        JSONReport report = new JSONReport();

        String json = report.writeJSON(testExtractor.getWebsite());


        assertThat(json, containsString("{\n" +
                "  \"basePath\":{\n" +
                "    \"path\":\"/mnt/c/Users/Richa/OneDrive/Desktop/CS350PROJECT/Avocado1/src/test/resources/edu/odu/cs/cs350\"\n" +
                "  },\n" +
                "  \"urls\":[\n" +
                "    \"https://www.test1.com/test1\",\n" +
                "    \"https://www.test2.com/test2\"\n" +
                "  ],\n" +
                "  \"pages\":[\n" +
                "    {\n" +
                "      \"path\":\"./folder/test1.html\",\n" +
                "      \"imageCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"jsCount\":{\n" +
                "        \"external\":2,\n" +
                "        \"local\":1\n" +
                "      },\n" +
                "      \"cssCount\":{\n" +
                "        \"external\":3,\n" +
                "        \"local\":2\n" +
                "      },\n" +
                "      \"imagePaths\":[\n" +
                "        \"https://www.google.com/image2.bmp\",\n" +
                "        \"image.jpg\"\n" +
                "      ],\n" +
                "      \"scriptPaths\":[\n" +
                "        \"folder/scripts/jquery-1.11.1.min.js\",\n" +
                "        \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "      ],\n" +
                "      \"cssPaths\":[\n" +
                "        \"folder/styles/layout1.css\",\n" +
                "        \"https://testsite.com/css/test1.min.css\",\n" +
                "        \"folder/styles/home.css\",\n" +
                "        \"https://anotherfakesite.org/test2.min.css\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"\n" +
                "      ],\n" +
                "      \"linkCount\":{\n" +
                "        \"external\":3,\n" +
                "        \"intra-page\":3,\n" +
                "        \"intra-site\":3\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"path\":\"./test.html\",\n" +
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
                "        \"https://www.google.com/image.bmp\",\n" +
                "        \"image.jpg\"\n" +
                "      ],\n" +
                "      \"scriptPaths\":[\n" +
                "        \"scripts/jquery-1.11.1.min.js\",\n" +
                "        \"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"\n" +
                "      ],\n" +
                "      \"cssPaths\":[\n" +
                "        \"styles/layout.css\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\",\n" +
                "        \"styles/home.css\"\n" +
                "      ],\n" +
                "      \"linkCount\":{\n" +
                "        \"external\":1,\n" +
                "        \"intra-page\":1,\n" +
                "        \"intra-site\":5\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"path\":\"./folder/another/subdirectory/test3.html\",\n" +
                "      \"imageCount\":{\n" +
                "        \"external\":2,\n" +
                "        \"local\":2\n" +
                "      },\n" +
                "      \"jsCount\":{\n" +
                "        \"external\":2,\n" +
                "        \"local\":3\n" +
                "      },\n" +
                "      \"cssCount\":{\n" +
                "        \"external\":3,\n" +
                "        \"local\":4\n" +
                "      },\n" +
                "      \"imagePaths\":[\n" +
                "        \"https://www.google.com/image2.bmp\",\n" +
                "        \"https://www.anotherexternalsite.com/image2.bmp\",\n" +
                "        \"logo.jpg\",\n" +
                "        \"image.jpg\"\n" +
                "      ],\n" +
                "      \"scriptPaths\":[\n" +
                "        \"folder/another/subdirectory/scripts/jquery-1.11.1.min.js\",\n" +
                "        \"https://randomname1.com/ajax/libs/jquery/1.11.3/fake1.min.js\",\n" +
                "        \"folder/another/subdirectory/local2.js\",\n" +
                "        \"https://randomname2.com/bootstrap/3.3.5/js/fake2.min.js\",\n" +
                "        \"folder/another/subdirectory/local1.js\"\n" +
                "      ],\n" +
                "      \"cssPaths\":[\n" +
                "        \"folder/another/subdirectory/styles/layout3.css\",\n" +
                "        \"https://testsite.com/css/test1.min.css\",\n" +
                "        \"https://anotherfakesite.org/test2.min.css\",\n" +
                "        \"folder/another/subdirectory/styles/layout2.css\",\n" +
                "        \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\",\n" +
                "        \"folder/another/subdirectory/styles/home.css\",\n" +
                "        \"folder/another/subdirectory/styles/layout1.css\"\n" +
                "      ],\n" +
                "      \"linkCount\":{\n" +
                "        \"external\":4,\n" +
                "        \"intra-page\":5,\n" +
                "        \"intra-site\":4\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"));

    }

}
