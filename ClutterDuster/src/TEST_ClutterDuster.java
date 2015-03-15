import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class TEST_ClutterDuster {
	
	@Test
	public void testNoFilter() throws IOException{
		String[] expected = {"D:\\Downloads\\testsuite\\#floatingpoint.ppt", "D:\\Downloads\\testsuite\\$vac412full.zip", 
		                      "D:\\Downloads\\testsuite\\%SteamSetup.exe", "D:\\Downloads\\testsuite\\&ProcessExplorer.zip", 
		                      "D:\\Downloads\\testsuite\\(PSP Cipher.7z", "D:\\Downloads\\testsuite\\+add.jas", 
		                      "D:\\Downloads\\testsuite\\-AcousticBridge_trial.exe", "D:\\Downloads\\testsuite\\11 Lips Are Movin.mp3", 
		                      "D:\\Downloads\\testsuite\\=msg0001.WAV", "D:\\Downloads\\testsuite\\@60b7d522755d02210a.pdf", 
		                      "D:\\Downloads\\testsuite\\Assignment2.tar.gz", "D:\\Downloads\\testsuite\\Attachments_201533.zip", 
		                      "D:\\Downloads\\testsuite\\doc1.docx", "D:\\Downloads\\testsuite\\elongated image.docx", 
		                      "D:\\Downloads\\testsuite\\hamcrest-core-1.3.jar", "D:\\Downloads\\testsuite\\HexEditor_0_9_5_UNI_dll.zip", 
		                      "D:\\Downloads\\testsuite\\HuniePop.xlsx", "D:\\Downloads\\testsuite\\HuniePopSaveData1.game", 
		                      "D:\\Downloads\\testsuite\\items (organized) 26.01.2013.txt", "D:\\Downloads\\testsuite\\i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", 
		                      "D:\\Downloads\\testsuite\\junit-4.11.jar", "D:\\Downloads\\testsuite\\MDuh logo final.mp4", 
		                      "D:\\Downloads\\testsuite\\NCG_Steam_Early_Access_instructions.txt", "D:\\Downloads\\testsuite\\OriginThinSetup.exe", 
		                      "D:\\Downloads\\testsuite\\pdf.pdf", "D:\\Downloads\\testsuite\\Ping All Dota 2 Servers.exe", 
		                      "D:\\Downloads\\testsuite\\platform.rar", "D:\\Downloads\\testsuite\\seng301-3.docx", 
		                      "D:\\Downloads\\testsuite\\streaming_client 2014-05-22 08-58-10-70.png", "D:\\Downloads\\testsuite\\untitled-1.png", 
		                      "D:\\Downloads\\testsuite\\whatrever.docx", 
		                      "D:\\Downloads\\testsuite\\[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
		                      "D:\\Downloads\\testsuite\\^XNB Exporter.rar", "D:\\Downloads\\testsuite\\_pingdota2.bat", 
		                      "D:\\Downloads\\testsuite\\~yuan 201(07) F13.doc", "D:\\Downloads\\testsuite\\покойник.txt", 
		                      "D:\\Downloads\\testsuite\\امتصاص بلدي الفاسد ديك.CT", "D:\\Downloads\\testsuite\\ảm giác rát.CT", 
		                      "D:\\Downloads\\testsuite\\ドラマー K-ON! Hokago Live!!.ISO"};
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		String[] stringResults = new String[results.size()];
		
		int i = 0;
		while (i < results.size()){
			stringResults[i] = results.get(i).toString();
			i++;
		}
		
		assertArrayEquals(expected, stringResults);
	}

	@Test
	public void testTypeFilter() throws IOException{
		String[] expected = {"D:\\Downloads\\testsuite\\doc1.docx", "D:\\Downloads\\testsuite\\elongated image.docx", 
								"D:\\Downloads\\testsuite\\seng301-3.docx", "D:\\Downloads\\testsuite\\whatrever.docx"};
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(ClutterDuster.typeFilter(".docx"));
		List results = execution.grabFileList(usethis);
		String[] stringResults = new String[results.size()];
		
		int i = 0;
		while (i < results.size()){
			stringResults[i] = results.get(i).toString();
			i++;
		}
		
		assertArrayEquals(expected, stringResults);
	}

	@Test
	public void testDateFilter() throws IOException{
		
		String[] expected = {"D:\\Downloads\\testsuite\\11 Lips Are Movin.mp3", "D:\\Downloads\\testsuite\\Attachments_201533.zip", 
							"D:\\Downloads\\testsuite\\HuniePop.xlsx", "D:\\Downloads\\testsuite\\HuniePopSaveData1.game", 
							"D:\\Downloads\\testsuite\\OriginThinSetup.exe", "D:\\Downloads\\testsuite\\pdf.pdf", 
							"D:\\Downloads\\testsuite\\platform.rar", "D:\\Downloads\\testsuite\\seng301-3.docx", 
							"D:\\Downloads\\testsuite\\ドラマー K-ON! Hokago Live!!.ISO"};
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(ClutterDuster.dateFilter(100, false));
		List results = execution.grabFileList(usethis);
		String[] stringResults = new String[results.size()];
		
		int i = 0;
		while (i < results.size()){
			stringResults[i] = results.get(i).toString();
			i++;
		}
		
		assertArrayEquals(expected, stringResults);
	}

	@Test
	public void testSizeFilter() throws IOException{
		
		String[] expected = {"D:\\Downloads\\testsuite\\#floatingpoint.ppt", "D:\\Downloads\\testsuite\\$vac412full.zip", 
							"D:\\Downloads\\testsuite\\(PSP Cipher.7z", "D:\\Downloads\\testsuite\\+add.jas", 
							"D:\\Downloads\\testsuite\\=msg0001.WAV", "D:\\Downloads\\testsuite\\@60b7d522755d02210a.pdf", 
							"D:\\Downloads\\testsuite\\Assignment2.tar.gz", "D:\\Downloads\\testsuite\\doc1.docx", 
							"D:\\Downloads\\testsuite\\elongated image.docx", "D:\\Downloads\\testsuite\\hamcrest-core-1.3.jar", 
							"D:\\Downloads\\testsuite\\HexEditor_0_9_5_UNI_dll.zip", "D:\\Downloads\\testsuite\\HuniePop.xlsx", 
							"D:\\Downloads\\testsuite\\HuniePopSaveData1.game", "D:\\Downloads\\testsuite\\items (organized) 26.01.2013.txt", 
							"D:\\Downloads\\testsuite\\i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", "D:\\Downloads\\testsuite\\junit-4.11.jar", 
							"D:\\Downloads\\testsuite\\NCG_Steam_Early_Access_instructions.txt", "D:\\Downloads\\testsuite\\pdf.pdf", 
							"D:\\Downloads\\testsuite\\Ping All Dota 2 Servers.exe", "D:\\Downloads\\testsuite\\untitled-1.png", 
							"D:\\Downloads\\testsuite\\whatrever.docx", 
							"D:\\Downloads\\testsuite\\[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
							"D:\\Downloads\\testsuite\\^XNB Exporter.rar", "D:\\Downloads\\testsuite\\_pingdota2.bat", "D:\\Downloads\\testsuite\\~yuan 201(07) F13.doc", 
							"D:\\Downloads\\testsuite\\покойник.txt", "D:\\Downloads\\testsuite\\امتصاص بلدي الفاسد ديك.CT", "D:\\Downloads\\testsuite\\ảm giác rát.CT"};

		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(ClutterDuster.sizeFilter((long)0, (long)1));
		List results = execution.grabFileList(usethis);
		String[] stringResults = new String[results.size()];
		
		int i = 0;
		while (i < results.size()){
			stringResults[i] = results.get(i).toString();
			i++;
		}
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFolderListing() throws IOException{
		String[] expected = {"D:\\Downloads\\testsuite\\%^@%^#$%@#!!", "D:\\Downloads\\testsuite\\foldertest"};
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(true);
		List results = execution.grabFileList(usethis);
		String[] stringResults = new String[results.size()];
		
		int i = 0;
		while (i < results.size()){
		stringResults[i] = results.get(i).toString();
		i++;
		}
		
		assertArrayEquals(expected, stringResults);
	}

}
