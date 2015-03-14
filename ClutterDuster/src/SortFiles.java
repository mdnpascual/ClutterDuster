import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class SortFiles {
	
	Boolean isFolderGrouping = false;
	Boolean isRetainFiles = false;
	String sourcePath = "";
	String destinationPath = "";
	String folderName = "";
	
	public SortFiles(ArrayList<Object> Input){
		isFolderGrouping = (Boolean)Input.get(0);
		isRetainFiles = (Boolean)Input.get(1);
		sourcePath = (String)Input.get(2);
		destinationPath = (String)Input.get(3);
		folderName = (String)Input.get(4);
	}
	
	public void alphanumeric(List unsorted){
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			int parts = path.lastIndexOf("\\");
			String filename = path.substring(parts+1);
			
			if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'z') 
			|| (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'Z')){
				//Alphabet check
				if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'm') 
			|| (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'M')){
					//A-M
				}
				else {
					//N-Z
				}
			}
			else if (filename.charAt(0) >= '0' && filename.charAt(0) <= '9'){
				//Digit check
			}
			else{
				//Unicode check
			}
			i++;
		}
	}
	
	public void fileType(List unsorted){
		//Modify file types here
		List<String> music = Arrays.asList("mp2", "mp3", "wav", "wma", "flac", "m4a", "aac", "ogg", "ofr", "ofs", "off", 
									"aiff", "aifc", "aif", "wv", "tta", "vox", "m3u", "asx", "fpl");
		List<String> video = Arrays.asList("mp4", "mkv", "avi", "flv", "3gp", "3gpp", "wmv", "dat", "m1v", "m2v", "fla", 
									"m4v", "mov", "mpg", "mpeg", "mpe", "rm", "swf", "bik");
		List<String> document = Arrays.asList("doc", "docm", "docx", "dot", "dotx", "epub", "log", "lwp", "mcw", "md", "nb", 
									"nbp", "odm", "odt", "ott", "pdf", "rtf", "cvs", "txt", "xml", "wps", "wpt", "wrd");
		List<String> archives = Arrays.asList("7z", "bz2", "rar", "zip", "gz", "ezip", "ecab", "ipg", "lz", "lzh", "mpq", "par", "par2", "tar", "tgz", "iso", "img");
		List<String> executables = Arrays.asList("exe", "jar", "bat", "apk", "app", "com", "ipa");
		
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			String ext = FilenameUtils.getExtension(path);
			
			//Sorting if sequence
			if(music.contains(ext)){
				//sort to music
			}
			else if (video.contains(ext)){
				//sort to video
			}
			else if (document.contains(ext)){
				//sort to document
			}
			else if (archives.contains(ext)){
				//sort to archives
			}
			else if (executables.contains(ext)){
				//sort to executables
			}else{
				//sort to others
			}
			i++;
		}
	}
	
	public void dateSort(List unsorted, int type){
		/*
		 * Type:
		 * 0 - Present - 1 week
		 * 1 - 1 week - 1 month
		 * 2 - 1 Month - 6 months
		 * 3 - 6 Months - 1 year
		 * 4 - > 1 year
		 */
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			String ext = FilenameUtils.getExtension(path);
			
			//Sorting if sequence
			if(type == 0){
				//sort to Present - 1 week
			}
			else if(type == 1){
				//sort to 1 week - 1 month
			}
			else if(type == 2){
				//sort to 1 Month - 6 months
			}
			else if(type == 3){
				//sort to 6 Months - 1 year
			}
			else if(type == 4){
				//sort to > 1 year
			}
			else{
				//ERROR
			}
			i++;
		}
	}
	
	public void sizeSort(List unsorted, int type){
		/*
		 * Type:
		 * 0 - < 1MB
		 * 1 - 1 MB - 10MB
		 * 2 - 10MB - 100MB
		 * 3 - 100MB - 1GB
		 * 4 - > 1 GB
		 */
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			String ext = FilenameUtils.getExtension(path);
			
			//Sorting if sequence
			if(type == 0){
				//sort to < 1MB
			}
			else if(type == 1){
				//sort to 1 MB - 10MB
			}
			else if(type == 2){
				//sort to 10MB - 100MB
			}
			else if(type == 3){
				//sort to 100MB - 1GB
			}
			else if(type == 4){
				//sort to > 1 GB
			}
			else{
				//ERROR
			}
			i++;
		}
	}
}