package edu.odu.cs.cs350;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
//used to make Lists.
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Website is a validates and contains the information needed to drive the analysis and also contains the results of the analysis that must be reported on
 */

public class Website
{
    /**
     * The file path the user provided that contains everything that should be analyzed
     */
    private File basePath;

    /**
     *  Set containing the user supplied URLs as Strings.
     */
    private Set<String> urls = new HashSet<String>();

    /**
     * a Hash Set containing the URLs the user input when triggering the analysis. Used to determine whether parsed links are internal or external
     */
    private transient HashSet<URL> userURLs = new HashSet<URL>();

    /**
     * A Hash Set HTMLFiles that were encountered during the analysis and must be reported on
     */
    private HashSet<Webpage> pages = new HashSet<Webpage>();

    /**
     * A Hash Set of Resources such as images, stylesheets, scripts, that were encountered during the analysis and must be reported on
     */

    private transient HashSet<Resource> resources = new HashSet<Resource>();

    /**
     * Default constructor
     */
    public Website()
    {

    }

    /**
     * constructor used by WebsiteAnalysis: takes string arguments and creates File and URL objects from them
     */
    public Website(String[] args)
    {
        setUserFilePath(args[0]); //the first argument is the path
        setUserURLs(Arrays.copyOfRange(args, 1 ,args.length) ); //second to N-th arguments are URLs that we need to make HTMLFiles of

        urls.add(userURLs.toString());
    }

    /**
     * For each argument, checks to ensure its a valid uRL before adding it to the list of URLs
     */
    public void setUserURLs(String[] args)
    {
        if(basePath != null)
        {
            for(int i = 0 ; i < args.length ; i++)
            {
                URL inputURL = null;
                try
                {
                    inputURL = new URL(args[i]);
                }
                catch (MalformedURLException e)
                {}
                if(inputURL != null)
                {
                    userURLs.add(inputURL);
                }
            }
        }
    }

    /**
     * Checks to ensure the file path the user is valid and sets it if so
     */
    public void setUserFilePath(String input)
    {
        File inputPath = new File(input);

        if(IsValidFilePath(inputPath) )
        {
            basePath = inputPath;
        }
        else
        {
            //do nothing (user warnings handled in IsValidFilePath)
        }
    }

    /**
     * Logic to determine that a file path exists
     */
    private boolean IsValidFilePath(File path)
    {
        //if the path exists, we will use it
        if(path.exists() && path.isDirectory() )
        {
            return true;
        }
        //if the path does not exist, we won't use it, and we'll give the user an appropriate warning depending on the situation
        else
        {
            if(!path.exists() )
            {
                System.out.println("ERROR: '" + path.toString() + "' does not exist. Please input an existing directory.");
            }
            else if(!path.isDirectory() )
            {
                System.out.println("ERROR: '" + path.toString() + "' is not a directory. Please input a directory (not a file). ");
            }

            return false;
        }
    }
    /*
	private File convertURLToCanonicalPath(URL input)
	{
		return new File(input.getPath().replaceFirst("/", "") );
	}
     */
    /**
     * Returns the filepath the user input in File form
     */
    public File getUserFilePath()
    {
        return basePath;
    }

    /**
     * Returns the HTMLFiles that must be reported on
     */
    public HashSet<Webpage> getPages()
    {
        return pages;
    }
    
    /**
     * Returns the URLs the user provided as part of the input as URL objects
     */
    public HashSet<URL> getUserURLs()
    {
        return userURLs;
    }
    
    /**
     * Returns the URLs the user provided as part of the input as URI objects
     */
    public Set<URI> getUserURLsAsURI()
    {
        Set<URI> uriSet = new HashSet<URI>();

        Iterator<URL> urlItr = userURLs.iterator();

        while(urlItr.hasNext())
        {
            try
            {
                uriSet.add(urlItr.next().toURI() );
            }
            catch (URISyntaxException e)
            { }
        }

        return uriSet;

    }

    /**
     * Add pages to webpage object
     * @param page
     */
    public void addWebpage(Webpage page) {
        pages.add(page);
    }


    /**
     * The website url
     * @return urls
     */
    public Set<String> getUrls() {
        return urls;
    }


    /**
     * Sets the website url
     * @param urls
     */
    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}