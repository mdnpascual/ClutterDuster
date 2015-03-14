import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class ExportFiles {
	
	public void newFolder(String path, String folderName) throws IOException{
		if("\\".equals(path.charAt(path.length()))){
			//Path string ends with slash
			FileUtils.forceMkdir(new File(path + folderName));
		}
		else{
			//Path string DOESN'T end with slash
			FileUtils.forceMkdir(new File(path + "\\" + folderName));
		}
		
	}
}
