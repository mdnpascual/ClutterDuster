/**
 * SortFiles.java
 */

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.io.comparator.*;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.io.input.*;
import org.apache.commons.io.monitor.*;
import org.apache.commons.io.output.*;

public class SortFiles {
	
	public void alphanumeric(List unsorted){
		int i = 0;
		while (i < unsorted.size()){
			String path = unsorted.get(i).toString();
			int parts = path.lastIndexOf("\\");
			String filename = path.substring(parts+1);
			
			if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'z') || (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'Z')){
				//Alphabet check
				if ((filename.charAt(0) >= 'a' && filename.charAt(0) <= 'm') || (filename.charAt(0) >= 'A' && filename.charAt(0) <= 'M')){
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
		
	}
	
	public void dateSort(List unsorted){
		
	}
	
	public void sizeSort(List unsorted){
		
	}
	
}