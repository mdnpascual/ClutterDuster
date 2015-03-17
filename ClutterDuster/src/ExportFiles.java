import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class ExportFiles {
	
	public void newFolder(String path, String folderName) throws IOException{
		if(path.charAt(path.length()-1) == 92){
			//Path string ends with slash
			FileUtils.forceMkdir(new File(path + folderName));
		}
		else{
			//Path string DOESN'T end with slash
			FileUtils.forceMkdir(new File(path + "\\" + folderName));
		}
	}
	
	public void moveFile(String source, String destination) throws IOException{
		int parts = source.lastIndexOf("\\");
		String filename = source.substring(parts+1);
		if(destination.charAt(destination.length()-1) == 92){
			//Destination string ends with slash
			FileUtils.copyFile(new File(source), new File(destination + filename), true);
		}
		else{
			//Destination string DOESN'T end with slash
			FileUtils.copyFile(new File(source), new File(destination + "\\" + filename), true);
		}
		
	}
	
	public void moveDirectory(){
		//Implement ME
	}
}
