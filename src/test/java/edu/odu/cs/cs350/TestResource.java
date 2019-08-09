
package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for Resource class
 * @author Richard Atwell
 *
 */
public class TestResource {

    /**
     *	The default, unparameterized constructor.
     */
    Resource defaultConstruct;

    /**
     *	A constructor with variable members set.
     */
    Resource testConstructor;

    private double fileSize = 1.70;

    private String typeOfLink = "external";

    private String url = "/image.jpg";

    private Set<String> usedOn = new HashSet<String>();

    @Before
    public void setUp() {

        usedOn.add("/root/desktop/directory");

        defaultConstruct = new Resource();
        testConstructor = new Resource(fileSize, typeOfLink, url, usedOn);
    }

    @Test
    public void testResource() {
        assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .1)));
        assertThat(defaultConstruct.getTypeOfLink(), is(""));
        assertThat(defaultConstruct.getUrl(), is(""));
        assertThat(defaultConstruct.getUsedOn().isEmpty(), is(true));
        assertThat(defaultConstruct.toString(), containsString("0,,,"));
        assertThat(defaultConstruct, equalTo(defaultConstruct));

        assertThat(defaultConstruct, instanceOf(Resource.class));

    }

    @Test
    public void testNonDefault() {
        assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
        assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
        assertThat(testConstructor.getUrl(), is(url));
        assertThat(testConstructor.getUsedOn(), is(usedOn));
        assertThat(testConstructor.toString(),
                containsString("1.7,external,/image.jpg,"
                        + "[/root/desktop/directory]"));
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
        assertThat(defaultConstruct.getUsedOn().isEmpty(), is(true));
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
        assertThat(testConstructor.getUsedOn(), is(usedOn));
        assertThat(testConstructor.toString(),
                containsString("-34.7,external,/image.jpg,"
                        + "[/root/desktop/directory]"));
        assertThat(defaultConstruct, not(equalTo(testConstructor)));
    }

    @Test
    public void testSetTypeOfLink() {

        // testing default
        int initialHash = defaultConstruct.hashCode();

        defaultConstruct.setTypeOfLink("invalid");
        // testing that "invalid" is not a valid option for type of link
        assertThat(defaultConstruct.getTypeOfLink(), is(not("invalid")));
        defaultConstruct.setTypeOfLink("local");
        assertThat(defaultConstruct.getTypeOfLink(), is("local"));

        int finalHash = defaultConstruct.hashCode();
        assertThat(initialHash, is(not(finalHash)));

        assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .1)));
        assertThat(defaultConstruct.getUrl(), is(""));
        assertThat(defaultConstruct.getUsedOn().isEmpty(),is(true));
        assertThat(defaultConstruct.toString(),
                containsString("0,local,,"));
        assertThat(defaultConstruct, equalTo(defaultConstruct));

        // testing nonDefault
        initialHash = testConstructor.hashCode();

        testConstructor.setTypeOfLink("invalid");
        // testing that "invalid" is not a valid option for type of link
        assertThat(testConstructor.getTypeOfLink(), is(not("invalid")));
        testConstructor.setTypeOfLink("intra-page");
        assertThat(testConstructor.getTypeOfLink(), is("intra-page"));

        finalHash = testConstructor.hashCode();
        assertThat(initialHash, is(not(finalHash)));

        assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
        assertThat(testConstructor.getUrl(), is(url));
        assertThat(testConstructor.getUsedOn(), is(usedOn));
        assertThat(testConstructor.toString(),
                containsString("1.7,intra-page,/image.jpg,"
                        + "[/root/desktop/directory]"));
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
        assertThat(defaultConstruct.getUsedOn().isEmpty(), is(true));
        assertThat(defaultConstruct.toString(),
                containsString("0,,script.js,"));
        assertThat(defaultConstruct, equalTo(defaultConstruct));

        // testing nonDefault
        initialHash = testConstructor.hashCode();

        testConstructor.setUrl("archive.gz");
        assertThat(testConstructor.getUrl(), is("archive.gz"));

        finalHash = testConstructor.hashCode();
        assertThat(initialHash, is(not(finalHash)));

        assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
        assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
        assertThat(testConstructor.getUsedOn(), is(usedOn));
        assertThat(testConstructor.toString(),
                containsString("1.7,external,archive.gz,"
                        + "[/root/desktop/directory]"));
        assertThat(defaultConstruct, not(equalTo(testConstructor)));
    }

    @Test
    public void testSetUsedOn() {

        // testing default
        int initialHash = defaultConstruct.hashCode();

        defaultConstruct.setUsedOn("/desktop/folder");
        defaultConstruct.setUsedOn("/desktop/here");
        assertThat(defaultConstruct.getUsedOn().isEmpty(), is(false));

        int finalHash = defaultConstruct.hashCode();
        assertThat(initialHash, is(not(finalHash)));

        assertThat(defaultConstruct.getFileSize(), is(closeTo(0, .01)));
        assertThat(defaultConstruct.getTypeOfLink(), is(""));
        assertThat(defaultConstruct.getUrl(), is(""));
        assertThat(defaultConstruct.toString(),
                containsString("0.0,,,[/desktop/here, /desktop/folder]"));
        assertThat(defaultConstruct, equalTo(defaultConstruct));

        // testing nonDefault
        initialHash = testConstructor.hashCode();

        testConstructor.setUsedOn("e:/OneDrive");
        assertThat(testConstructor.getUsedOn().toString()
                .contains("[/root/desktop/directory, e:/OneDrive]"), is(true));

        finalHash = testConstructor.hashCode();
        assertThat(initialHash, is(not(finalHash)));

        assertThat(testConstructor.getFileSize(), is(closeTo(1.7, .01)));
        assertThat(testConstructor.getTypeOfLink(), is(typeOfLink));
        assertThat(testConstructor.getUrl(), is(url));
        assertThat(testConstructor.toString(),
                containsString("1.7,external,/image.jpg,"
                        + "[/root/desktop/directory, e:/OneDrive]"));
        assertThat(defaultConstruct, not(equalTo(testConstructor)));

    }

    @Test
    public void testClone() throws CloneNotSupportedException {

        defaultConstruct.setFileSize(10.2);
        defaultConstruct.setTypeOfLink("local");
        defaultConstruct.setUrl("style.css");
        defaultConstruct.setUrl("c:/");

        Resource aCopy = defaultConstruct.clone();

        assertThat(aCopy.getFileSize(),
                equalTo(defaultConstruct.getFileSize()));
        assertThat(aCopy.getTypeOfLink(),
                equalTo(defaultConstruct.getTypeOfLink()));
        assertThat(aCopy.getUrl(), equalTo(defaultConstruct.getUrl()));
        assertThat(aCopy.getUsedOn(), equalTo(defaultConstruct.getUsedOn()));

        assertThat(aCopy.hashCode(), equalTo(defaultConstruct.hashCode()));
        assertThat(aCopy, equalTo(defaultConstruct));
        assertThat(aCopy.toString(), equalTo(defaultConstruct.toString()));

    }

}
