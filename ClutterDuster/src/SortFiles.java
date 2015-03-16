import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

/*
 * NOTE: LINE 89 IS DISABLED, HANDLES COPYING FOLDER INSIDE SOURCE FOLDER
 */
public class SortFiles {
	
	Boolean isFolderGrouping = false;
	Boolean isRetainFiles = false;
	String sourcePath = "";
	String destinationPath = "";
	String folderName = "";
	volatile String outputStatus = "";
	volatile int percentage = 0;
	volatile Boolean interrupted = false;
	
	public SortFiles(ArrayList<Object> Input){
		isRetainFiles = (Boolean)Input.get(1);
		sourcePath = (String)Input.get(2);
		destinationPath = (String)Input.get(3);
		folderName = (String)Input.get(4);
	}
	
	public void alphanumeric(List unsorted) throws IOException{
		outputStatus = "";
		percentage = 0;
		interrupted = false;
		int i = 0;
		
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);
				
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
					outputStatus = outputStatus.concat(filename + " >> A-M\n");
				}
				else {
					//N-Z
					outputStatus = outputStatus.concat(filename + " >> N-Z\n");
				}
			}
			else if (filename.charAt(0) >= '0' && filename.charAt(0) <= '9'){
				//Digit check
				outputStatus = outputStatus.concat(filename + " >> 0-9\n");
			}
			else{
				//Unicode check
				outputStatus = outputStatus.concat(filename + " >> Unicode\n");
			}
			i++;
			
			if(update(i, unsorted.size(), results.size()) == 1)
				break;
			else if (update(i, unsorted.size(), results.size()) == 2)
				return;
		}
		
		//Handle Folders in the Source Path here
		if (results.size() != 0){
			int j = 0;
			while (j < results.size()){
				//FileUtils.copyDirectory(new File(unsorted.get(j).toString()), new File(destinationPath), true);		//Disabled for now
				outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + " Folder\n");
				j++;i++;
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
			}
		}
			
		outputStatus = outputStatus.concat("Sorting Complete!");
	}
	
	public void fileType(List unsorted) throws IOException{
		
		outputStatus = "";
		percentage = 0;
		interrupted = false;
		//Modify file types here
		List<String> music = Arrays.asList("aac", "act", "aifc", "aiff", "aimppl", "amr", "asx", "au", "awb", "dct", "dss", "dvf", "flac", 
									"fpl", "gsm", "iklax", "ivs", "m3u", "m4a", "m4p", "mmf", "mp2", "mp3", "mpc", "msv", "off", "ofr", 
									"ofs", "oga", "ogg", "opus", "pla", "ra", "tta", "tta", "vlc", "vox", "wav", "wma", "wpl", "wv");
		List<String> video = Arrays.asList("3g2", "3gp", "asf", "avi", "bik", "f4v", "flv", "m2v", "m4v", "mkv", "mng", "mov", "mp2", "mp4", 
									"mpe", "mpeg", "mpg", "mpv", "mxf", "nsv", "qt", "rm", "rmvb", "swf", "vob", "webm", "wmv", "yuv");
		List<String> images = Arrays.asList("3dm", "3ds", "max", "obj", "bmp", "dds", "gif", "jpg", "png", "psd", "pspimage", "tga", "thm", 
									"tif", "tiff", "ai", "eps", "ps", "svg");
		List<String> document = Arrays.asList("doc", "docm", "docx", "dot", "dotx", "epub", "log", "lwp", "mcw", "md", "nb", 
									"nbp", "odm", "odt", "ott", "pdf", "ppt", "pptx", "rtf", "cvs", "txt", "xls", "xlsx", "xml", "wps", "wpt", "wrd");
		List<String> archives = Arrays.asList("7z", "bz2", "rar", "zip", "gz", "ezip", "ecab", "ipg", "lz", "lzh", "mpq", "par", "par2", "tar", "tgz", "iso", "img");
		List<String> executables = Arrays.asList("exe", "jar", "bat", "apk", "app", "com", "ipa");
		
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);
		
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			String ext = FilenameUtils.getExtension(path).toLowerCase();
			int parts = path.lastIndexOf("\\");
			String filename = path.substring(parts+1);
			
			//Sorting if sequence
			if(music.contains(ext)){
				//sort to music
				outputStatus = outputStatus.concat(filename + " >> Music\n");
			}
			else if (video.contains(ext)){
				//sort to video
				outputStatus = outputStatus.concat(filename + " >> Video\n");
			}
			else if (images.contains(ext)){
				//sort to images
				outputStatus = outputStatus.concat(filename + " >> Images\n");
			}
			else if (document.contains(ext)){
				//sort to document
				outputStatus = outputStatus.concat(filename + " >> Document\n");
			}
			else if (archives.contains(ext)){
				//sort to archives
				outputStatus = outputStatus.concat(filename + " >> Archives\n");
			}
			else if (executables.contains(ext)){
				//sort to executables
				outputStatus = outputStatus.concat(filename + " >> Executables\n");
			}else{
				//sort to others
				outputStatus = outputStatus.concat(filename + " >> Others\n");
			}
			i++;
			if(update(i, unsorted.size(), results.size()) == 1)
				break;
			else if (update(i, unsorted.size(), results.size()) == 2)
				return;
		}
		
		//Handle Folders in the Source Path here
		if (results.size() != 0){
			int j = 0;
			while (j < results.size()){
				//FileUtils.copyDirectory(new File(unsorted.get(j).toString()), new File(destinationPath), true);		//Disabled for now
				outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + " Folder\n");
				j++;i++;
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
			}
		}
		
		outputStatus = outputStatus.concat("Sorting Complete!");
	}
	
	public void dateSort(List unsorted) throws IOException{
		outputStatus = "";
		percentage = 0;
		interrupted = false;
		int i = 0;
		int type = 0;
		
		
		/*
		 * Initialize list for:
		 * 1) Folder list inside source path
		 * 2) 5 Date filters
		 */
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		ListFiles listpto7 = new ListFiles(ClutterDuster.dateFilter(7, false));
		List getpto7 = listpto7.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		ListFiles list7to31 = new ListFiles(ClutterDuster.dateFilter(7, 31));
		List get7to31 = list7to31.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		ListFiles list1to6 = new ListFiles(ClutterDuster.dateFilter(31, 183));
		List get1to6 = list1to6.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		ListFiles list6to1 = new ListFiles(ClutterDuster.dateFilter(183, 365));
		List get6to1 = list6to1.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		ListFiles list1over = new ListFiles(ClutterDuster.dateFilter(365, true));
		List get1over = list1over.grabFileList(usethis);
		if (update(i, unsorted.size(), results.size()) > 0)
			return;
		
		// Start sorting loop for dateSort
		while (i < unsorted.size()){
			int j = 0;
			while(j < getpto7.size()){
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
				
				String path = getpto7.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				//sort to Present - 1 week
				outputStatus = outputStatus.concat(filename + " >> Present - 7 Days\n");
				i++;j++;
				
			}
			j = 0;
			while(j < get7to31.size()){
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
				
				String path = get7to31.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				//sort to 1 week - 1 month
				outputStatus = outputStatus.concat(filename + " >> 1 Week - 1 Month\n");
				i++;j++;
			}
			j = 0;
			while(j < get1to6.size()){
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
				
				String path = get1to6.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				//sort to 1 Month - 6 months
				outputStatus = outputStatus.concat(filename + " >> 1 Month - 6 Months\n");
				i++;j++;
			}
			j = 0;
			while(j < get6to1.size()){
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
				
				String path = get6to1.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				//sort to 6 Months - 1 year
				outputStatus = outputStatus.concat(filename + " >> 6 Months - 1 Year\n");
				i++;j++;
			}
			j = 0;
			while(j < get1over.size()){
				if(update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
				
				String path = get1over.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				//sort to > 1 year
				outputStatus = outputStatus.concat(filename + " >> 1 Year and Older\n");
				i++;j++;
			}
		}
		
		//Handle Folders in the Source Path here
				if (results.size() != 0){
					int j = 0;
					while (j < results.size()){
						//FileUtils.copyDirectory(new File(unsorted.get(j).toString()), new File(destinationPath), true);		//Disabled for now
						outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + " Folder\n");
						j++;i++;
						if(update(i, unsorted.size(), results.size()) == 1)
							break;
						else if (update(i, unsorted.size(), results.size()) == 2)
							return;
					}
				}
				
		outputStatus = outputStatus.concat("Sorting Complete!");
	}
	
	public void sizeSort(List unsorted){
		/*
		 * Type:
		 * 0 - < 1MB
		 * 1 - 1 MB - 10MB
		 * 2 - 10MB - 100MB
		 * 3 - 100MB - 1GB
		 * 4 - > 1 GB
		 */
		int i = 0;
		int type = 0;
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
			percentage = i/unsorted.size();
			if (interrupted == false){
				break;
			}
		}
	}
	
	public int update(int i, int unsortedSize, int resultsSize){
		//Updater
		percentage = (int)(((float)i/(unsortedSize+resultsSize-1))*100);
		if (interrupted == true){
			return 1;
		}
		try {
			Thread.sleep(5);	// Pauses 5ms to get interrupt flag, expose percentage and output string
		} catch (InterruptedException e) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			outputStatus = outputStatus.concat(stackTraceElements[2].getMethodName() + " Sorting Aborted!\n");
			try {
				Thread.sleep(300);	// Thread will stay alive for 1 second (Not sure what happens if User started new sort thread within 800ms)
			} catch (InterruptedException e1) {
				return 2;
			}
			return 2;
		}
		return 0;
	}
}