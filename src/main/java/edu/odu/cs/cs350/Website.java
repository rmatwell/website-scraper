package edu.odu.cs.cs350;

import java.util.*; //used to make Lists.
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Website 
{
	private HashSet<HTMLFile> pages = new HashSet<HTMLFile>();
	
	private HashSet<Resource> resources = new HashSet<Resource>();
	
	private File userFilePath;

	public Website()
	{
		//default constructor
		
	}
	
	public Website(String[] args)
	{
		setUserFilePath(args[0]); //the first argument is the path
		setPages(Arrays.copyOfRange(args, 1 ,args.length) ); //second to N-th arguments are URLs that we need to make HTMLFiles of
	}
	
	public void setPages(String[] args)
	{
		if(userFilePath != null)
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
				
				if(isValidURL(inputURL) )
				{
					File inputPath = convertURLToCanonicalPath(inputURL);
					pages.add(new HTMLFile(inputPath,inputURL) );
				}
			}
		}
	}
	
	private boolean isValidURL(URL input)
	{
		return ( ( input != null ) && isInScope(input) && localCopyExists(input) );
	}
	
	private boolean isInScope(URL input)
	{			
		return convertURLToCanonicalPath(input).toString().startsWith(userFilePath.toString() );
	}
	
	private boolean localCopyExists(URL input)
	{
		return convertURLToCanonicalPath(input).exists();
	}
	
	public void setUserFilePath(String input)
	{
		File inputPath = new File(input); 
		
		if(IsValidFilePath(inputPath) )
		{
			userFilePath = inputPath;
		}
		else
		{
			//do nothing (user warnings handled in IsValidFilePath)
		}
	}
	
	//determines whether the user's file path is valid (correctly formatted, exists)
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
	
	private File convertURLToCanonicalPath(URL input)
	{
		return new File(input.getPath().replaceFirst("/", "") );
	}
	
	public File getUserFilePath()
	{
		return userFilePath;
	}
	
	public HashSet<HTMLFile> getPages()
	{
		return pages;
	}
}