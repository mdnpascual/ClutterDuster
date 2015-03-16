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
		
	}
	//FileType Filter
	public static FileFilter typeFilter(String filetype){
		FileFilter fooDirFilter = FileFilterUtils.suffixFileFilter(filetype);
		return fooDirFilter;
	}
	//Date Filter
	public static FileFilter dateFilter(int days, boolean olderInstead){
		Date date = new Date();
		Time t7Days = Time.valueOf("24:00:00");
		FileFilter fooDirFilter = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*days)), olderInstead);
		return fooDirFilter;
	}
	public static FileFilter dateFilter(int startDays, int endDays){
		Date date = new Date();
		Time t7Days = Time.valueOf("24:00:00");
		FileFilter fooDirFilter = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*startDays)), true);
		FileFilter fooDirFilter2 = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*endDays)), false);
		FileFilter fooDirFilter3 = FileFilterUtils.andFileFilter((IOFileFilter)fooDirFilter, (IOFileFilter)fooDirFilter2);
		return fooDirFilter3;
	}
	//Size Filter
	public static FileFilter sizeFilter(long minMB, long maxMB){
		FileFilter fooDirFilter = FileFilterUtils.sizeRangeFileFilter(minMB*1048576, maxMB*1048576);
		return fooDirFilter;
	}
}