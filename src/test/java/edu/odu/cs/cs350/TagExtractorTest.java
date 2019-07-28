package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
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

	TagExtractor testExtractor;

	private String rootDirectory = "/c/directory/location/";

	private String url1 = "https://www.test.com/test1";
	private String url2 = "https://www.test.com/test2";

	private HashSet<URI> urls = new HashSet<>();

	@Before
	public void setUp() throws URISyntaxException {


		urls.add(new URI(url1) );
		urls.add(new URI(url2) );


		testExtractor = new TagExtractor(rootDirectory, urls);
	}

	@Test
	public void testTagExtractor() {

		File file = new File("src/test/resources/edu/odu/cs/cs350/image.jpg");

		double fileSize = testExtractor.calculateMiB(file);

		assertThat(fileSize, is(closeTo(1.21, .01)));

		String formatSize = testExtractor.formatFileSize(fileSize);

		assertThat(formatSize, containsString("1.21 MiB"));

		assertThat(testExtractor.getAnalysisTime(),
				containsString("-summary"));
		assertThat(testExtractor.toString(),
				containsString("/c/directory/location/ , "
						+ "[https://www.test.com/test2, "
						+ "https://www.test.com/test1"));
		assertThat(testExtractor, equalTo(testExtractor));
	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		TagExtractor aCopy = testExtractor.clone();

		assertThat(aCopy.getAnalysisTime(), equalTo(testExtractor.getAnalysisTime()));
		assertThat(aCopy.getRootDirectory(), equalTo(testExtractor.getRootDirectory()));
		assertThat(aCopy.getUserURLs(), equalTo(testExtractor.getUserURLs()));

		assertThat(aCopy.hashCode(), equalTo(testExtractor.hashCode()));
		assertThat(aCopy, equalTo(testExtractor));
		assertThat(aCopy.toString(), equalTo(testExtractor.toString()));

	}


}
