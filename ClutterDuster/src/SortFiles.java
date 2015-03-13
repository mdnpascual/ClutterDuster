import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class SortFiles {
	
	//Modify file types here
	public List<String> music = Arrays.asList("mp2", "mp3", "wav", "wma", "flac", "m4a", "aac", "ogg", "ofr", "ofs", "off", 
								"aiff", "aifc", "aif", "wv", "tta", "vox", "m3u", "asx", "fpl");
	public List<String> video = Arrays.asList("mp4", "mkv", "avi", "flv", "3gp", "3gpp", "wmv", "dat", "m1v", "m2v", "fla", 
								"m4v", "mov", "mpg", "mpeg", "mpe", "rm", "swf", "bik");
	public List<String> document = Arrays.asList("doc", "docm", "docx", "dot", "dotx", "epub", "log", "lwp", "mcw", "md", "nb", 
								"nbp", "odm", "odt", "ott", "pdf", "rtf", "cvs", "txt", "xml", "wps", "wpt", "wrd");
	public List<String> archives = Arrays.asList("7z", "bz2", "rar", "zip", "gz", "ezip", "ecab", "ipg", "lz", "lzh", "mpq", "par", "par2", "tar", "tgz", "iso", "img");
	public List<String> executables = Arrays.asList("exe", "jar", "bat", "apk", "app", "com", "ipa");
	
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
	
	public void dateSort(List unsorted){
		
	}
	
	public void sizeSort(List unsorted){
		
	}
}