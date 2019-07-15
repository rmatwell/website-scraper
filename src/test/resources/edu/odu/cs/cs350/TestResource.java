
package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.closeTo;

/**
 * @author Richard Atwell
 *
 */
public class TestResource {

	Resource defaultConstruct;
	Resource nonDefaultConstruct;

	@Before
	public void setUp() {
		defaultConstruct = new Resource();
		nonDefaultConstruct = new Resource(1.70, "external", "/image.jpg", "/root/desktop/directory");
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
		assertThat(nonDefaultConstruct.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(nonDefaultConstruct.getTypeOfLink(), is("external"));
		assertThat(nonDefaultConstruct.getUrl(), is("/image.jpg"));
		assertThat(nonDefaultConstruct.getPageFoundOn(), is("/root/desktop/directory"));
		assertThat(nonDefaultConstruct.toString(), containsString("1.7,external,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(nonDefaultConstruct)));

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
		initialHash = nonDefaultConstruct.hashCode();

		nonDefaultConstruct.setFileSize(-34.7);
		assertThat(nonDefaultConstruct.getFileSize(), is(closeTo(-34.7, .1)));

		finalHash = nonDefaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(nonDefaultConstruct.getTypeOfLink(), is("external"));
		assertThat(nonDefaultConstruct.getUrl(), is("/image.jpg"));
		assertThat(nonDefaultConstruct.getPageFoundOn(), is("/root/desktop/directory"));
		assertThat(nonDefaultConstruct.toString(), containsString("-34.7,external,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(nonDefaultConstruct)));
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
		initialHash = nonDefaultConstruct.hashCode();

		nonDefaultConstruct.setTypeOfLink("invalid");
		// testing that "invalid" is not a valid option for type of link
		assertThat(nonDefaultConstruct.getTypeOfLink(), is(not("invalid")));
		nonDefaultConstruct.setTypeOfLink("intrapage");
		assertThat(nonDefaultConstruct.getTypeOfLink(), is("intrapage"));

		finalHash = nonDefaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(nonDefaultConstruct.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(nonDefaultConstruct.getUrl(), is("/image.jpg"));
		assertThat(nonDefaultConstruct.getPageFoundOn(), is("/root/desktop/directory"));
		assertThat(nonDefaultConstruct.toString(), containsString("1.7,intrapage,/image.jpg,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(nonDefaultConstruct)));

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
		initialHash = nonDefaultConstruct.hashCode();

		nonDefaultConstruct.setUrl("archive.gz");
		assertThat(nonDefaultConstruct.getUrl(), is("archive.gz"));

		finalHash = nonDefaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(nonDefaultConstruct.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(nonDefaultConstruct.getTypeOfLink(), is("external"));
		assertThat(nonDefaultConstruct.getPageFoundOn(), is("/root/desktop/directory"));
		assertThat(nonDefaultConstruct.toString(), containsString("1.7,external,archive.gz,/root/desktop/directory"));
		assertThat(defaultConstruct, not(equalTo(nonDefaultConstruct)));
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
		initialHash = nonDefaultConstruct.hashCode();

		nonDefaultConstruct.setPageFoundOn("e:/OneDrive");
		assertThat(nonDefaultConstruct.getPageFoundOn(), is("e:/OneDrive"));

		finalHash = nonDefaultConstruct.hashCode();
		assertThat(initialHash, is(not(finalHash)));

		assertThat(nonDefaultConstruct.getFileSize(), is(closeTo(1.7, .01)));
		assertThat(nonDefaultConstruct.getTypeOfLink(), is("external"));
		assertThat(nonDefaultConstruct.getUrl(), is("/image.jpg"));
		assertThat(nonDefaultConstruct.toString(), containsString("1.7,external,/image.jpg,e:/OneDrive"));
		assertThat(defaultConstruct, not(equalTo(nonDefaultConstruct)));

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
