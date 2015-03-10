/**
 * ListFiles.java
 * 
 */

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class ListFiles extends DirectoryWalker {
	
	public ListFiles() {
		// Default Constructor
		super();
	}
	
	public ListFiles(FileFilter filter) {
		// Constructor for filtering
		super(filter, 1);
	}

	
	public List grabFileList(File startDirectory)throws IOException{
		//Initiate Directory Walker
		List results = new ArrayList();
		walk(startDirectory, results);
		return results;
	}
	
	protected boolean handleDirectory(File directory, int depth, Collection results) {
		return true;
	}

	protected void handleFile(File file, int depth, Collection results) {
		results.add(file);
	}	    
	
}