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
	
	// FileType Filter
	public static FileFilter typeFilter(String filetype) {
		FileFilter fooDirFilter = FileFilterUtils.suffixFileFilter(filetype);
		return fooDirFilter;
	}
	
	// Date Filter
	public static FileFilter dateFilter(int days, boolean olderInstead) { // false = less than the date filter, true = over the date filter
		Date date = new Date();
		Time t7Days = Time.valueOf("24:00:00");		// Derive 24 hours for computation
		//Compute days * 24 hours and convert to date
		FileFilter fooDirFilter = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*days)), olderInstead);
		return fooDirFilter;
	}
	
	// Date range filter
	public static FileFilter dateFilter(int startDays, int endDays) {
		Date date = new Date();
		Time t7Days = Time.valueOf("24:00:00");		// Derive 24 hours for computation
		// Setup earlier date for filter and set to get older files
		FileFilter fooDirFilter = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*startDays)), true);
		// Setup later date for filter and set to get newer files
		FileFilter fooDirFilter2 = FileFilterUtils.ageFileFilter(new Date(date.getTime()-(t7Days.getTime()*endDays)), false);
		// Use AND filter to combine the 2 filets above
		FileFilter fooDirFilter3 = FileFilterUtils.andFileFilter((IOFileFilter)fooDirFilter, (IOFileFilter)fooDirFilter2);
		return fooDirFilter3;
	}
	
	// Size Range Filter
	public static FileFilter sizeFilter(long minMB, long maxMB) {
		FileFilter fooDirFilter = FileFilterUtils.sizeRangeFileFilter(minMB*1048576, maxMB*1048576);	// 1 Byte * 1048576 = 1MB
		return fooDirFilter;
	}
	
	// Size Filter
	public static FileFilter sizeFilter(long maxMB, Boolean choice) {
		FileFilter fooDirFilter = FileFilterUtils.sizeFileFilter(maxMB*1048576, true);
		return fooDirFilter;
	}
}