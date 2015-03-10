import java.io.*;
import java.sql.Time;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class ClutterDuster {

	public static void main(String[] args)throws IOException {
		//FileFunctions sample usage #1
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		System.out.println("breakpoint");
		
		//FileFunctions sample usage #2
		ListFiles execution2 = new ListFiles(typeFilter(".docx"));
		List results2 = execution2.grabFileList(usethis);
		System.out.println("breakpoint");
		
		//FileFunctions sample usage #3
		ListFiles execution3 = new ListFiles(dateFilter(100, false));
		List results3 = execution3.grabFileList(usethis);
		System.out.println("breakpoint");
		
		//FileFunctions sample usage #4
		ListFiles execution4 = new ListFiles(sizeFilter((long)0, (long)1));
		List results4 = execution4.grabFileList(usethis);
		System.out.println("breakpoint");
	}
	
	public static FileFilter typeFilter(String filetype){
		FileFilter fooDirFilter = FileFilterUtils.suffixFileFilter(filetype);
		return fooDirFilter;
	}
	
	public static FileFilter dateFilter(int days, boolean olderInstead){
		Date date = new Date();
		Time t7Days = Time.valueOf("24:00:00");
		FileFilter fooDirFilter = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*days)), olderInstead);
		return fooDirFilter;
	}
	
	public static FileFilter sizeFilter(long minMB, long maxMB){
		FileFilter fooDirFilter = FileFilterUtils.sizeRangeFileFilter(minMB*1048576, maxMB*1048576);
		return fooDirFilter;
	}
	
}