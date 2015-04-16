import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class ListFiles extends DirectoryWalker {
	
	public Boolean folderOnly = false;
	
	public ListFiles() {
		// Default Constructor
		super(FileFilterUtils.trueFileFilter(), 1);
	}
	
	public ListFiles(FileFilter filter) {
		// Constructor for filtering
		super(filter, 1);
	}
	
	public ListFiles(Boolean x) {	// True if grabbing only folder, false if files only
		super(FileFilterUtils.trueFileFilter(), 1);
		folderOnly = x;
	}
	
	public List grabFileList(File startDirectory)throws IOException {
		// Initiate Directory Walker
		List results = new ArrayList();
		walk(startDirectory, results);
		return results;
	}
	
	protected boolean handleDirectory(File directory, int depth, Collection results) {
		if (folderOnly && depth == 1)
			results.add(directory);
		return true;
	}
	
	protected void handleFile(File file, int depth, Collection results) {
		if (depth == 1 && !folderOnly)		// Only handle depth 1 files
			results.add(file);
	}
}