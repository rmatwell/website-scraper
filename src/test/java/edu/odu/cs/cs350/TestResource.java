
package edu.odu.cs.cs350;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Richard Atwell
 *
 */
public class TestResource {

	/**
	 *
	 */
	Resource defaultConstruct;

	/**
	 *
	 */
	Resource testConstructor;

	private double fileSize = 1.70;

	private String typeOfLink = "external";

	private String url = "/image.jpg";

	private String pageFoundOn = "/root/desktop/directory";

	@Before
	public void setUp() {
		defaultConstruct = new Resource();
		testConstructor = new Resource(fileSize, typeOfLink, url, pageFoundOn);
	}

	@Test
	public void testResource() {
		assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .1)));
		assertThat(defaultConstruct.getTypeOfLink(), is(""));
		assertThat(defaultConstruct.getUrl(), is(""));
		assertThat(defaultConstruct.getPageFoundOn(), is(""));
		assertThat(defaultConstruct.toString(), containsString("0,,,"));
		assertThat(defaultConstruct, equalTo(defaultConstruct));

	}

	@Test
	public void testNonDefault() {
		assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
		assertThat(testConstructor.getUrl(), is(url));
		assertThat(testConstructor.getPageFoundOn(), is(pageFoundOn));
		assertThat(testConstructor.toString(), containsString("1.7,external,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(testConstructor)));

	}

	@Test
	public void testSetFileSize() {
		// testing default
		int initialHash = defaultConstruct.hashCode();

		defaultConstruct.setFileSize(4.7);
		assertThat(defaultConstruct.getFileSize(), is(closeTo(4.7, .1)));

		int finalHash = defaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(defaultConstruct.getTypeOfLink(), is(""));
		assertThat(defaultConstruct.getUrl(), is(""));
		assertThat(defaultConstruct.getPageFoundOn(), is(""));
		assertThat(defaultConstruct.toString(), containsString("4.7,,,"));
		assertThat(defaultConstruct, equalTo(defaultConstruct));

		// testing nonDefault
		initialHash = testConstructor.hashCode();

		testConstructor.setFileSize(-34.7);
		assertThat(testConstructor.getFileSize(), is(closeTo(-34.7, .1)));

		finalHash = testConstructor.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
		assertThat(testConstructor.getUrl(), is(url));
		assertThat(testConstructor.getPageFoundOn(), is(pageFoundOn));
		assertThat(testConstructor.toString(), containsString("-34.7,external,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(testConstructor)));
	}

	@Test
	public void testSetTypeOfLink() {

		// testing default
		int initialHash = defaultConstruct.hashCode();

		defaultConstruct.setTypeOfLink("invalid");
		// testing that "invalid" is not a valid option for type of link
		assertThat(defaultConstruct.getTypeOfLink(), is(not("invalid")));
		defaultConstruct.setTypeOfLink("internal");
		assertThat(defaultConstruct.getTypeOfLink(), is("internal"));

		int finalHash = defaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .1)));
		assertThat(defaultConstruct.getUrl(), is(""));
		assertThat(defaultConstruct.getPageFoundOn(), is(""));
		assertThat(defaultConstruct.toString(), containsString("0,internal,,"));
		assertThat(defaultConstruct, equalTo(defaultConstruct));

		// testing nonDefault
		initialHash = testConstructor.hashCode();

		testConstructor.setTypeOfLink("invalid");
		// testing that "invalid" is not a valid option for type of link
		assertThat(testConstructor.getTypeOfLink(), is(not("invalid")));
		testConstructor.setTypeOfLink("intrapage");
		assertThat(testConstructor.getTypeOfLink(), is("intrapage"));

		finalHash = testConstructor.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(testConstructor.getUrl(), is(url));
		assertThat(testConstructor.getPageFoundOn(), is(pageFoundOn));
		assertThat(testConstructor.toString(), containsString("1.7,intrapage,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(testConstructor)));

	}

	@Test
	public void testSetUrl() {
		// testing default
		int initialHash = defaultConstruct.hashCode();

		defaultConstruct.setUrl("script.js");
		assertThat(defaultConstruct.getUrl(), is("script.js"));

		int finalHash = defaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .01)));
		assertThat(defaultConstruct.getTypeOfLink(), is(""));
		assertThat(defaultConstruct.getPageFoundOn(), is(""));
		assertThat(defaultConstruct.toString(), containsString("0,,script.js,"));
		assertThat(defaultConstruct, equalTo(defaultConstruct));

		// testing nonDefault
		initialHash = testConstructor.hashCode();

		testConstructor.setUrl("archive.gz");
		assertThat(testConstructor.getUrl(), is("archive.gz"));

		finalHash = testConstructor.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
		assertThat(testConstructor.getPageFoundOn(), is(pageFoundOn));
		assertThat(testConstructor.toString(), containsString("1.7,external,archive.gz,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(testConstructor)));
	}

	@Test
	public void testSetPageFoundOn() {

		// testing default
		int initialHash = defaultConstruct.hashCode();

		defaultConstruct.setPageFoundOn("/desktop/folder");
		assertThat(defaultConstruct.getPageFoundOn(), is("/desktop/folder"));

		int finalHash = defaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .01)));
		assertThat(defaultConstruct.getTypeOfLink(), is(""));
		assertThat(defaultConstruct.getUrl(), is(""));
		assertThat(defaultConstruct.toString(), containsString("0,,,/desktop/folder"));
		assertThat(defaultConstruct, equalTo(defaultConstruct));

		// testing nonDefault
		initialHash = testConstructor.hashCode();

		testConstructor.setPageFoundOn("e:/OneDrive");
		assertThat(testConstructor.getPageFoundOn(), is("e:/OneDrive"));

		finalHash = testConstructor.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
		assertThat(testConstructor.getUrl(), is(url));
		assertThat(testConstructor.toString(), containsString("1.7,external,/image.jpg,e:/OneDrive"));
		assertThat(defaultConstruct, not(equalTo(testConstructor)));

	}

	@Test
	public void testClone() {

		defaultConstruct.setFileSize(10.2);
		defaultConstruct.setTypeOfLink("internal");
		defaultConstruct.setUrl("style.css");
		defaultConstruct.setUrl("c:/");

		Resource aCopy = (Resource) defaultConstruct.clone();

		assertThat(aCopy.getFileSize(), equalTo(defaultConstruct.getFileSize()));
		assertThat(aCopy.getTypeOfLink(), equalTo(defaultConstruct.getTypeOfLink()));
		assertThat(aCopy.getUrl(), equalTo(defaultConstruct.getUrl()));
		assertThat(aCopy.getPageFoundOn(), equalTo(defaultConstruct.getPageFoundOn()));

		assertThat(aCopy.hashCode(), equalTo(defaultConstruct.hashCode()));
		assertThat(aCopy, equalTo(defaultConstruct));
		assertThat(aCopy.toString(), equalTo(defaultConstruct.toString()));

	}

}
