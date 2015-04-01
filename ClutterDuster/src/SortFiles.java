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
	
	// Breaker Variables
	volatile String outputStatus = "";
	volatile int percentage = 0;
	volatile Boolean interrupted = false;
	volatile String sortMethod = "";
	
	// Public list for deletion
	List files = new ArrayList();
	List folders = new ArrayList();
	List files_source = new ArrayList();
	List folders_source = new ArrayList();
	
	// Boolean variables for determining first pass
	Boolean tempval1 = false;
	Boolean tempval2 = false;
	Boolean tempval3 = false;
	Boolean tempval4 = false;
	Boolean tempval5 = false;
	Boolean tempval6 = false;
	Boolean tempval7 = false;
	
	public SortFiles(ArrayList<Object> Input) {
		/* Sortfiles Constructor usage
		 * Pass an arraylist with size 4
		 * 1st index = (Option to retain files) : Boolean
		 * 2nd index = (Source folder Path) : String
		 * 3rd index = (Destination folder path) : String
		 * 4th index = (Optional folder name) : String 
		 */
		isRetainFiles = (Boolean)Input.get(1);
		sourcePath = (String)Input.get(2);
		if (sourcePath.charAt(sourcePath.length()-1) != 92) {
			sourcePath = sourcePath.concat("\\");
		}
		destinationPath = (String)Input.get(3);
		folderName = (String)Input.get(4);	
		if (destinationPath.charAt(destinationPath.length()-1) != 92) {
			destinationPath = destinationPath.concat("\\");
		}
	}
	
	public void alphanumeric(List unsorted) throws IOException {
		outputStatus = "--Start--\n";
		sortMethod = "Alphanumeric";
		int i = 0;
		try {
			folders.clear();
			files.clear();
		} catch (Exception e) {
			
		}
		if (!folderName.isEmpty())
			folders.add(destinationPath + folderName);	// If folderName is set, it will be deleted incase user aborted

		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);		// Grab folder list inside source path
		if (sourcePath.compareToIgnoreCase(destinationPath) == 0) {
			results = new ArrayList();
		}
		folders_source = results;
		files_source = unsorted;
		ExportFiles newFiles = new ExportFiles();
				
		while (i < unsorted.size()) {
			String path = unsorted.get(i).toString();
			int parts = path.lastIndexOf("\\");
			String filename = path.substring(parts+1);
			
			if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'z') 
			|| (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'Z')) {
				// Alphabet check
				if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'm') 
			|| (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'M')) {
					// A-M
					if (!tempval1) {
						// Create folder if first pass
						createFolder("A-M");
						tempval1 = true;
					}
					// Slash checking again
					if (folderName.isEmpty())
						files.add(newFiles.moveFile(path, destinationPath.concat("A-M")));
					else
						files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\A-M")));
					outputStatus = outputStatus.concat(filename + " >> A-M\n");
				}
				else {
					// N-Z
					if (!tempval2) {
						// Create folder if first pass
						createFolder("N-Z");
						tempval2 = true;
					}
					// Slash checking again
					if (folderName.isEmpty())
						files.add(newFiles.moveFile(path, destinationPath.concat("N-Z")));
					else
						files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\N-Z")));				
					outputStatus = outputStatus.concat(filename + " >> N-Z\n");
				}
			}
			else if (filename.charAt(0) >= '0' && filename.charAt(0) <= '9') {
				// Digit check
				if (!tempval3) {
					// Create folder if first pass
					createFolder("0-9");
					tempval3 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("0-9")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\0-9")));
				outputStatus = outputStatus.concat(filename + " >> 0-9\n");
			}
			else {
				// Unicode check
				if (!tempval4) {
					// Create folder if first pass
					createFolder("Unicode");
					tempval4 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Unicode")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Unicode")));				
				outputStatus = outputStatus.concat(filename + " >> Unicode\n");
			}
			i++;
			
			if (update(i, unsorted.size(), results.size()) == 1)
				break;
			else if (update(i, unsorted.size(), results.size()) == 2)
				return;
		}
		
		update(i, unsorted.size(), results.size());
		
		// Handle folders in the Source Path here
		if (results.size() != 0 && (sourcePath.compareToIgnoreCase(destinationPath)) != 0) {
			int j = 0;
			while (j < results.size()) {
				String[] parts = new String[results.get(j).toString().split("\\\\").length];
				parts = results.get(j).toString().split("\\\\");
				FileUtils.copyDirectory(new File(results.get(j).toString()), new File(destinationPath+folderName+"\\"+parts[parts.length-1]), true);
				folders.add(destinationPath+folderName+"\\"+parts[parts.length-1]);
				outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + folderName + parts[parts.length-1] + "\\ Folder\n");
				j++;i++;
				if (update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
			}
		}
		// Reset temp values
		tempval1 = false;tempval2 = false;tempval3 = false;tempval4 = false;
		
		outputStatus = outputStatus.concat("--Done--\n");
	}
	
	public void fileType(List unsorted) throws IOException {
		outputStatus = "--Start--\n";
		sortMethod = "File Type";
		// Modify file types here
		List<String> music = Arrays.asList("aac", "act", "aif c", "aif f", "aimppl", "amr", "asx", "au", "awb", "dct", "dss", "dvf", "flac", 
											"fpl", "gsm", "iklax", "ivs", "m3u", "m4a", "m4p", "mmf", "mp2", "mp3", "mpc", "msv", "off", "ofr", 
											"ofs", "oga", "ogg", "opus", "pla", "ra", "tta", "tta", "vlc", "vox", "wav", "wma", "wpl", "wv");
		List<String> videos = Arrays.asList("3g2", "3gp", "asf", "avi", "bik", "f4v", "flv", "m2v", "m4v", "mkv", "mng", "mov", "mp2", "mp4", 
											"mpe", "mpeg", "mpg", "mpv", "mxf", "nsv", "qt", "rm", "rmvb", "swf", "vob", "webm", "wmv", "yuv");
		List<String> pictures = Arrays.asList("3dm", "3ds", "max", "obj", "bmp", "dds", "gif ", "jpg", "png", "psd", "pspimage", "tga", "thm", 
											"tif ", "tif f", "ai", "eps", "ps", "svg");
		List<String> documents = Arrays.asList("doc", "docm", "docx", "dot", "dotx", "epub", "log", "lwp", "mcw", "md", "nb", 
												"nbp", "odm", "odt", "ott", "pdf", "ppt", "pptx", "rtf", "cvs", "txt", "xls", "xlsx", "xml", "wps", "wpt", "wrd");
		List<String> archives = Arrays.asList("7z", "bz2", "rar", "zip", "gz", "ezip", "ecab", "ipg", "lz", "lzh", "mpq", "par", "par2", "tar", "tgz", "iso", "img");
		List<String> executables = Arrays.asList("exe", "jar", "bat", "apk", "app", "com", "ipa");
		
		try {
			folders.clear();
			files.clear();
		} catch (Exception e) {
			
		}
		if (!folderName.isEmpty())
			folders.add(destinationPath + folderName);		// If folderName is set, it will be deleted incase user aborted
		
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);		// Grab folder list inside source path
		if (sourcePath.compareToIgnoreCase(destinationPath) == 0) {
			results = new ArrayList();
		}
		folders_source = results;
		files_source = unsorted;
		ExportFiles newFiles = new ExportFiles();
		
		int i = 0;
		while (i < unsorted.size()) {
			String path = unsorted.get(i).toString();
			String ext = FilenameUtils.getExtension(path).toLowerCase();
			int parts = path.lastIndexOf("\\");
			String filename = path.substring(parts+1);
			
			// Sorting if sequence
			if (music.contains(ext)) {
				// Sort to music
				if (!tempval1) {
					// Create folder if first pass
					createFolder("Music");
					tempval1 = true;
			}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Music")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Music")));				
				outputStatus = outputStatus.concat(filename + " >> Music\n");
			}
			else if (videos.contains(ext)) {
				// Sort to videos
				if (!tempval2) {
					// Create folder if first pass
					createFolder("Videos");
					tempval2 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Videos")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Videos")));				
				outputStatus = outputStatus.concat(filename + " >> Videos\n");
			}
			else if (pictures.contains(ext)) {
				// Sort to pictures
				if (!tempval3) {
					// Create folder if first pass
					createFolder("Pictures");
					tempval3 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Pictures")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Pictures")));				
				outputStatus = outputStatus.concat(filename + " >> Pictures\n");
			}
			else if (documents.contains(ext)) {
				// Sort to documents
				if (!tempval4) {
					// Create folder if first pass
					createFolder("Documents");
					tempval4 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Documents")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Documents")));				
				outputStatus = outputStatus.concat(filename + " >> Documents\n");
			}
			else if (archives.contains(ext)) {
				// Sort to archives
				if (!tempval5) {
					// Create folder if first pass
					createFolder("Archives");
					tempval5 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Archives")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Archives")));				
				outputStatus = outputStatus.concat(filename + " >> Archives\n");
			}
			else if (executables.contains(ext)) {
				// Sort to executables
				if (!tempval6) {
					// Create folder if first pass
					createFolder("Executables");
					tempval6 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Executables")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Executables")));				
				outputStatus = outputStatus.concat(filename + " >> Executables\n");
			} else {
				// Sort to others
				if (!tempval7) {
					// Create folder if first pass
					createFolder("Others");
					tempval7 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Others")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Others")));				
				outputStatus = outputStatus.concat(filename + " >> Others\n");
			}
			i++;
			if (update(i, unsorted.size(), results.size()) == 1)
				break;
			else if (update(i, unsorted.size(), results.size()) == 2)
				return;
		}
		
		update(i, unsorted.size(), results.size());
		
		// Handle folders in the Source Path here
		if (results.size() != 0 && (sourcePath.compareToIgnoreCase(destinationPath)) != 0) {
			int j = 0;
			while (j < results.size()) {
				String[] parts = new String[results.get(j).toString().split("\\\\").length];
				parts = results.get(j).toString().split("\\\\");
				FileUtils.copyDirectory(new File(results.get(j).toString()), new File(destinationPath+folderName+"\\"+parts[parts.length-1]), true);
				folders.add(destinationPath+folderName+"\\"+parts[parts.length-1]);
				outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + folderName + parts[parts.length-1] + "\\ Folder\n");
				j++;i++;
				if (update(i, unsorted.size(), results.size()) == 1)
					break;
				else if (update(i, unsorted.size(), results.size()) == 2)
					return;
			}
		}
		// Reset temp values
		tempval1 = false;tempval2 = false;tempval3 = false;tempval4 = false;tempval5 = false;tempval6 = false;tempval7 = false;
		
		outputStatus = outputStatus.concat("--Done--\n");
	}
	
	public void dateSort(List unsorted) throws IOException {
		outputStatus = "--Start--\n";
		sortMethod = "Date Sort";
		int i = 0;
		try {
			folders.clear();
			files.clear();
		} catch (Exception e) {
			
		}
		if (!folderName.isEmpty())
			folders.add(destinationPath + folderName);		// If folderName is set, it will be deleted incase user aborted
		
		/*
		 * Initialize list for:
		 * 1) Folder list inside source path
		 * 2) 5 Date filters
		 */
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);		// Grab folder list inside source path
		if (sourcePath.compareToIgnoreCase(destinationPath) == 0) {
			results = new ArrayList();
		}
		folders_source = results;
		files_source = unsorted;
		
		if (update(1, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles listpto7 = new ListFiles(ClutterDuster.dateFilter(7, false));
		List getpto7 = listpto7.grabFileList(usethis);
		if (update(2, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list7to31 = new ListFiles(ClutterDuster.dateFilter(7, 31));
		List get7to31 = list7to31.grabFileList(usethis);
		if (update(3, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list1to6 = new ListFiles(ClutterDuster.dateFilter(31, 183));
		List get1to6 = list1to6.grabFileList(usethis);
		if (update(4, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list6to1 = new ListFiles(ClutterDuster.dateFilter(183, 365));
		List get6to1 = list6to1.grabFileList(usethis);
		if (update(5, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list1over = new ListFiles(ClutterDuster.dateFilter(365, true));
		List get1over = list1over.grabFileList(usethis);
		if (update(6, unsorted.size(), results.size()+6) > 0)
			return;
		
		ExportFiles newFiles = new ExportFiles();
		
		// Start sorting loop for dateSort
		while (i < unsorted.size()) {
			int j = 0;
			while (j < getpto7.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = getpto7.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to Today - 1 Week
				if (!tempval1) {
					// Create folder if first pass
					createFolder("Today - 1 Week");
					tempval1 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Today - 1 Week")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Today - 1 Week")));				
				outputStatus = outputStatus.concat(filename + " >> Today - 1 Week\n");
				i++;j++;
			}
			j = 0;
			while (j < get7to31.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get7to31.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 1 week - 1 month
				if (!tempval2) {
					// Create folder if first pass
					createFolder("1 Week - 1 Month");
					tempval2 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("1 Week - 1 Month")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\1 Week - 1 Month")));				
				outputStatus = outputStatus.concat(filename + " >> 1 Week - 1 Month\n");
				i++;j++;
			}
			j = 0;
			while (j < get1to6.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get1to6.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 1 Month - 6 months
				if (!tempval3) {
					// Create folder if first pass
					createFolder("1 Month - 6 Months");
					tempval3 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("1 Month - 6 Months")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\1 Month - 6 Months")));
				outputStatus = outputStatus.concat(filename + " >> 1 Month - 6 Months\n");
				i++;j++;
			}
			j = 0;
			while (j < get6to1.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get6to1.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 6 Months - 1 year
				if (!tempval4) {
					// Create folder if first pass
					createFolder("6 Months - 1 Year");
						tempval4 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("6 Months - 1 Year")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\6 Months - 1 Year")));				
				outputStatus = outputStatus.concat(filename + " >> 6 Months - 1 Year\n");
				i++;j++;
			}
			j = 0;
			while (j < get1over.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get1over.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to > 1 year
				if (!tempval5) {
					// Create folder if first pass
					createFolder("1 Year and Older");
					tempval5 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("1 Year and Older")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\1 Year and Older")));				
				outputStatus = outputStatus.concat(filename + " >> 1 Year and Older\n");
				i++;j++;
			}
		}
		
		update(i+6, unsorted.size(), results.size()+6);
		
		// Handle folders in the Source Path here
				if (results.size() != 0 && (sourcePath.compareToIgnoreCase(destinationPath)) != 0) {
					int j = 0;
					while (j < results.size()) {
						String[] parts = new String[results.get(j).toString().split("\\\\").length];
						parts = results.get(j).toString().split("\\\\");
						FileUtils.copyDirectory(new File(results.get(j).toString()), new File(destinationPath+folderName+"\\"+parts[parts.length-1]), true);
						folders.add(destinationPath+folderName+"\\"+parts[parts.length-1]);
						outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + folderName + parts[parts.length-1] + "\\ Folder\n");
						j++;i++;
						if (update(i+6, unsorted.size(), results.size()+6) == 1)
							break;
						else if (update(i+6, unsorted.size(), results.size()+6) == 2)
							return;
					}
				}
		// Reset temp values
		tempval1 = false;tempval2 = false;tempval3 = false;tempval4 = false;tempval5 = false;
				
		outputStatus = outputStatus.concat("--Done--\n");
	}
	
	public void sizeSort(List unsorted) throws IOException {
		outputStatus = "--Start--\n";
		sortMethod = "Size Sort";
		int i = 0;
		try {
			folders.clear();
			files.clear();
		} catch (Exception e) {
			
		}
		if (!folderName.isEmpty())
			folders.add(destinationPath + folderName);		// If folderName is set, it will be deleted incase user aborted
		
		File usethis = new File(sourcePath);
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);		// Grab folder list inside source path
		if (sourcePath.compareToIgnoreCase(destinationPath) == 0) {
			results = new ArrayList();
		}
		folders_source = results;
		files_source = unsorted;
		
		if (update(1, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list0to1 = new ListFiles(ClutterDuster.sizeFilter((long)0, (long)1));
		List get0to1 = list0to1.grabFileList(usethis);
		if (update(2, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list1to10 = new ListFiles(ClutterDuster.sizeFilter((long)1, (long)10));
		List get1to10 = list1to10.grabFileList(usethis);
		if (update(3, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list10to100 = new ListFiles(ClutterDuster.sizeFilter((long)10, (long)100));
		List get10to100 = list10to100.grabFileList(usethis);
		if (update(4, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list100to1 = new ListFiles(ClutterDuster.sizeFilter((long)100, (long)1000));
		List get100to1 = list100to1.grabFileList(usethis);
		if (update(5, unsorted.size(), results.size()+6) > 0)
			return;
		ListFiles list1000over = new ListFiles(ClutterDuster.sizeFilter((long)1000, true));
		List get1000over = list1000over.grabFileList(usethis);
		if (update(6, unsorted.size(), results.size()+6) > 0)
			return;
		
		ExportFiles newFiles = new ExportFiles();
		
		while (i < unsorted.size()) {
			int j = 0;
			while (j < get0to1.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get0to1.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to < 1MB
				if (!tempval1) {
					// Create folder if first pass
					createFolder("Less than 1MB");
					tempval1 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Less than 1MB")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Less than 1MB")));				
				outputStatus = outputStatus.concat(filename + " >> Less than 1MB\n");
				i++;j++;
			}
			j = 0;
			while (j < get1to10.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get1to10.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 1 - 10MB
				if (!tempval2) {
					// Create folder if first pass
					createFolder("1 - 10MB");
					tempval2 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("1 - 10MB")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\1 - 10MB")));				
				outputStatus = outputStatus.concat(filename + " >> 1 - 10MB\n");
				i++;j++;
			}
			j = 0;
			while (j < get10to100.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get10to100.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 10 - 100MB
				if (!tempval3) {
					// Create folder if first pass
					createFolder("10 - 100MB");
					tempval3 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("10 - 100MB")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\10 - 100MB")));				
				outputStatus = outputStatus.concat(filename + " >> 10 - 100MB\n");
				i++;j++;
			}
			j = 0;
			while (j < get100to1.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get100to1.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to 100MB - 1GB
				if (!tempval4) {
					// Create folder if first pass
					createFolder("100MB - 1GB");
					tempval4 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("100MB - 1GB")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\100MB - 1GB")));				
				outputStatus = outputStatus.concat(filename + " >> 100MB - 1GB\n");
				i++;j++;
			}
			j = 0;
			while (j < get1000over.size()) {
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
				
				String path = get1000over.get(j).toString();
				int parts = path.lastIndexOf("\\");
				String filename = path.substring(parts+1);
				// Sort to > 1GB
				if (!tempval5) {
					// Create folder if first pass
					createFolder("Greater than 1GB");
					tempval5 = true;
				}
				// Slash checking again
				if (folderName.isEmpty())
					files.add(newFiles.moveFile(path, destinationPath.concat("Greater than 1GB")));
				else
					files.add(newFiles.moveFile(path, destinationPath.concat(folderName).concat("\\Greater than 1GB")));				
				outputStatus = outputStatus.concat(filename + " >> Greater than 1GB\n");
				i++;j++;
			}	
		}
		
		update(i+6, unsorted.size(), results.size()+6);
		
		// Handle folders in the Source Path here
		if (results.size() != 0 && (sourcePath.compareToIgnoreCase(destinationPath)) != 0) {
			int j = 0;
			while (j < results.size()) {
				String[] parts = new String[results.get(j).toString().split("\\\\").length];
				parts = results.get(j).toString().split("\\\\");
				FileUtils.copyDirectory(new File(results.get(j).toString()), new File(destinationPath+folderName+"\\"+parts[parts.length-1]), true);
				folders.add(destinationPath+folderName+"\\"+parts[parts.length-1]);
				outputStatus = outputStatus.concat(results.get(j).toString() + " Folder >> " + destinationPath + folderName + parts[parts.length-1] + "\\ Folder\n");
				j++;i++;
				if (update(i+6, unsorted.size(), results.size()+6) == 1)
					break;
				else if (update(i+6, unsorted.size(), results.size()+6) == 2)
					return;
			}
		}
		// Reset temp values
		tempval1 = false;tempval2 = false;tempval3 = false;tempval4 = false;tempval5 = false;
		
		outputStatus = outputStatus.concat("--Done--\n");
	}
	
	public int update(int i, int unsortedSize, int resultsSize) throws IOException {
		// Updater
		percentage = (int)(((float)i/(unsortedSize+resultsSize))*100);
		if (interrupted == true) {
			return 1;
		}
		try {
			Thread.sleep(5);	// Pauses 5ms to get interrupt flag, expose percentage and output string
		} catch (InterruptedException e) {
			try {
				Thread.sleep(50);	// Thread will stay alive for 1 second (Not sure what happens if User started new sort thread within 800ms)
			} catch (InterruptedException e1) {
				return 2;
			}
			return 2;
		}
		return 0;
	}
	
	private void createFolder(String Name) throws IOException {
		ExportFiles create = new ExportFiles();
		if (folderName.isEmpty())
			folders.add(create.newFolder(destinationPath, Name));
		else
			folders.add(create.newFolder(destinationPath + folderName + "\\", Name));
	}
}