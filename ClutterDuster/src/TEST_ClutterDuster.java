import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	public void testDateFilter2() throws IOException{
		
		String[] expected = {"D:\\Downloads\\testsuite\\11 Lips Are Movin.mp3", "D:\\Downloads\\testsuite\\hamcrest-core-1.3.jar", "D:\\Downloads\\testsuite\\junit-4.11.jar"};
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(ClutterDuster.dateFilter(31, 183));
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
	public void testSizeFilter2() throws IOException{
		
		String[] expected = {"D:\\Downloads\\testsuite\\%SteamSetup.exe", "D:\\Downloads\\testsuite\\&ProcessExplorer.zip", 
							"D:\\Downloads\\testsuite\\-AcousticBridge_trial.exe", "D:\\Downloads\\testsuite\\11 Lips Are Movin.mp3", 
							"D:\\Downloads\\testsuite\\Attachments_201533.zip", "D:\\Downloads\\testsuite\\MDuh logo final.mp4", 
							"D:\\Downloads\\testsuite\\OriginThinSetup.exe", "D:\\Downloads\\testsuite\\platform.rar", 
							"D:\\Downloads\\testsuite\\seng301-3.docx", "D:\\Downloads\\testsuite\\streaming_client 2014-05-22 08-58-10-70.png", 
							"D:\\Downloads\\testsuite\\ドラマー K-ON! Hokago Live!!.ISO"};

		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles(ClutterDuster.sizeFilter((long)1, false));
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
	
	@Test
	public void testNewFolder() throws IOException{
		ExportFiles execution = new ExportFiles();
		File ff = new File("D:\\Downloads\\testsuite\\tester");
		if (ff.exists()){
			FileUtils.deleteDirectory(ff);
			fail();
		}
		execution.newFolder("D:\\Downloads\\testsuite", "tester");
		if (!ff.exists()){
			fail();
		}
		FileUtils.deleteDirectory(ff);
		if (ff.exists()){
			fail();
		}
	}
	
	@Test
	public void testNewFolder2() throws IOException{
		ExportFiles execution = new ExportFiles();
		File ff = new File("D:\\Downloads\\testsuite\\tester");
		if (ff.exists()){
			fail();
		}
		execution.newFolder("D:\\Downloads\\testsuite\\", "tester");
		if (!ff.exists()){
			fail();
		}
		FileUtils.deleteDirectory(ff);
		if (ff.exists()){
			fail();
		}
	}
	
	@Test
	public void testExportFile() throws IOException{
		ExportFiles execution = new ExportFiles();
		String source = "D:\\Downloads\\testsuite\\OriginThinSetup.exe";
		File ff = new File("D:\\Downloads\\testsuite\\tester");
		if (ff.exists()){
			fail();
		}
		execution.moveFile(source, ff.toString());
		
		File results = new File("D:\\Downloads\\testsuite\\tester\\OriginThinSetup.exe");
		if(!results.exists()){
			fail();
		}
		FileUtils.deleteDirectory(ff);
		if (ff.exists()){
			fail();
		}
	}
	
	@Test
	public void testExportFile2() throws IOException{
		ExportFiles execution = new ExportFiles();
		String source = "D:\\Downloads\\testsuite\\OriginThinSetup.exe";
		File ff = new File("D:\\Downloads\\testsuite\\tester");
		if (ff.exists()){
			fail();
		}
		execution.moveFile(source, ff.toString()+"\\");
		
		File results = new File("D:\\Downloads\\testsuite\\tester\\OriginThinSetup.exe");
		if(!results.exists()){
			fail();
		}
		FileUtils.deleteDirectory(ff);
		if (ff.exists()){
			fail();
		}
	}
	
	@Test
	public void testAlphaNumericSort_1() throws IOException{
		String[] expected = {"Assignment2.tar.gz", "Attachments_201533.zip", 
							 "doc1.docx", "elongated image.docx", 
							 "hamcrest-core-1.3.jar", "HexEditor_0_9_5_UNI_dll.zip", 
							 "HuniePop.xlsx", "HuniePopSaveData1.game", 
							 "items (organized) 26.01.2013.txt", "i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", 
							 "junit-4.11.jar", "MDuh logo final.mp4"};

		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.alphanumeric(results);
		
		String unstrippedResult = sorter.test_Alphanumeric_A_M;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testAlphaNumericSort_2() throws IOException{
		String[] expected = {"NCG_Steam_Early_Access_instructions.txt", "OriginThinSetup.exe", 
							 "pdf.pdf", "Ping All Dota 2 Servers.exe", "platform.rar", "seng301-3.docx", 
							 "streaming_client 2014-05-22 08-58-10-70.png", "untitled-1.png", "whatrever.docx"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.alphanumeric(results);
		
		String unstrippedResult = sorter.test_Alphanumeric_N_Z;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testAlphaNumericSort_3() throws IOException{
		String[] expected = {"11 Lips Are Movin.mp3"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.alphanumeric(results);
		
		String unstrippedResult = sorter.test_Alphanumeric_0_9;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testAlphaNumericSort_4() throws IOException{
		String[] expected = {"#floatingpoint.ppt", "$vac412full.zip", "%SteamSetup.exe", "&ProcessExplorer.zip", 
							 "(PSP Cipher.7z", "+add.jas", "-AcousticBridge_trial.exe", "=msg0001.WAV", "@60b7d522755d02210a.pdf", 
							 "[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
							 "^XNB Exporter.rar", "_pingdota2.bat", "~yuan 201(07) F13.doc", "покойник.txt", "امتصاص بلدي الفاسد ديك.CT", 
							 "ảm giác rát.CT", "ドラマー K-ON! Hokago Live!!.ISO"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.alphanumeric(results);
		
		String unstrippedResult = sorter.test_Alphanumeric_Unicode;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_1() throws IOException{
		String[] expected = {"11 Lips Are Movin.mp3", "=msg0001.WAV"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Music;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_2() throws IOException{
		String[] expected = {"MDuh logo final.mp4"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Video;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_3() throws IOException{
		String[] expected = {"streaming_client 2014-05-22 08-58-10-70.png", "untitled-1.png"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Images;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_4() throws IOException{
		String[] expected = {"#floatingpoint.ppt", "@60b7d522755d02210a.pdf", "doc1.docx", "elongated image.docx", 
							 "HuniePop.xlsx", "items (organized) 26.01.2013.txt", "NCG_Steam_Early_Access_instructions.txt", 
							 "pdf.pdf", "seng301-3.docx", "whatrever.docx", "~yuan 201(07) F13.doc", "покойник.txt"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Document;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_5() throws IOException{
		String[] expected = {"$vac412full.zip", "&ProcessExplorer.zip", "(PSP Cipher.7z", "Assignment2.tar.gz", 
							 "Attachments_201533.zip", 
							 "HexEditor_0_9_5_UNI_dll.zip", "i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", 
							 "platform.rar", "^XNB Exporter.rar", "ドラマー K-ON! Hokago Live!!.ISO"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Archives;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_6() throws IOException{
		String[] expected = {"%SteamSetup.exe", "-AcousticBridge_trial.exe", "hamcrest-core-1.3.jar", 
							 "junit-4.11.jar", "OriginThinSetup.exe", "Ping All Dota 2 Servers.exe", "_pingdota2.bat"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Executables;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testFileTypeSort_7() throws IOException{
		String[] expected = {"+add.jas", "HuniePopSaveData1.game", 
							 "[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
							 "امتصاص بلدي الفاسد ديك.CT", "ảm giác rát.CT"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.fileType(results);
		
		String unstrippedResult = sorter.test_FileType_Others;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testDateTypeSort_1() throws IOException{
		String[] expected = {"ドラマー K-ON! Hokago Live!!.ISO"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.dateSort(results);
		
		String unstrippedResult = sorter.test_DateSort_L7days;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testDateTypeSort_2() throws IOException{
		String[] expected = {"Attachments_201533.zip", "HuniePop.xlsx", "HuniePopSaveData1.game", "OriginThinSetup.exe", 
							 "pdf.pdf", "platform.rar", "seng301-3.docx"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.dateSort(results);
		
		String unstrippedResult = sorter.test_DateSort_L1month;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testDateTypeSort_3() throws IOException{
		String[] expected = {"11 Lips Are Movin.mp3", "hamcrest-core-1.3.jar", "junit-4.11.jar"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.dateSort(results);
		
		String unstrippedResult = sorter.test_DateSort_L6months;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testDateTypeSort_4() throws IOException{
		String[] expected = {"#floatingpoint.ppt", "&ProcessExplorer.zip", "(PSP Cipher.7z", "+add.jas", 
							 "-AcousticBridge_trial.exe", "=msg0001.WAV", "@60b7d522755d02210a.pdf", "Assignment2.tar.gz", 
							 "doc1.docx", "elongated image.docx", "HexEditor_0_9_5_UNI_dll.zip", "items (organized) 26.01.2013.txt", 
							 "i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", "NCG_Steam_Early_Access_instructions.txt", 
							 "Ping All Dota 2 Servers.exe", "streaming_client 2014-05-22 08-58-10-70.png", "untitled-1.png", "whatrever.docx", 
							 "[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
							 "^XNB Exporter.rar", "_pingdota2.bat", "~yuan 201(07) F13.doc", "покойник.txt", "امتصاص بلدي الفاسد ديك.CT", "ảm giác rát.CT"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.dateSort(results);
		
		String unstrippedResult = sorter.test_DateSort_L1year;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testDateTypeSort_5() throws IOException{
		String[] expected = {"$vac412full.zip", "%SteamSetup.exe", "MDuh logo final.mp4"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.dateSort(results);
		
		String unstrippedResult = sorter.test_DateSort_M1year;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testSizeTypeSort_1() throws IOException{
		String[] expected = {"#floatingpoint.ppt", "$vac412full.zip", "(PSP Cipher.7z", "+add.jas", "=msg0001.WAV", 
							 "@60b7d522755d02210a.pdf", "Assignment2.tar.gz", "doc1.docx", "elongated image.docx", 
							 "hamcrest-core-1.3.jar", "HexEditor_0_9_5_UNI_dll.zip", "HuniePop.xlsx", "HuniePopSaveData1.game", 
							 "items (organized) 26.01.2013.txt", "i_love_oreo___the_wallpaper_pack_by_tinkupuri-d4pkt5s.rar", 
							 "junit-4.11.jar", "NCG_Steam_Early_Access_instructions.txt", "pdf.pdf", "Ping All Dota 2 Servers.exe", 
							 "untitled-1.png", "whatrever.docx", 
							 "[UTW-Mazui]_Toaru_Majutsu_no_Index-tan_-_Endymion_no_Kiseki_[BD][h264-1080p_FLAC][C4772B66].mkv.torrent", 
							 "^XNB Exporter.rar", "_pingdota2.bat", "~yuan 201(07) F13.doc", "покойник.txt", "امتصاص بلدي الفاسد ديك.CT", "ảm giác rát.CT"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.sizeSort(results);
		
		String unstrippedResult = sorter.test_SizeSort_L1;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testSizeTypeSort_2() throws IOException{
		String[] expected = {"%SteamSetup.exe", "&ProcessExplorer.zip", "-AcousticBridge_trial.exe", "11 Lips Are Movin.mp3", 
							 "Attachments_201533.zip", "MDuh logo final.mp4", "seng301-3.docx", "streaming_client 2014-05-22 08-58-10-70.png"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.sizeSort(results);
		
		String unstrippedResult = sorter.test_SizeSort_L10;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testSizeTypeSort_3() throws IOException{
		String[] expected = {"OriginThinSetup.exe", "platform.rar"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.sizeSort(results);
		
		String unstrippedResult = sorter.test_SizeSort_L100;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testSizeTypeSort_4() throws IOException{
		String[] expected = {""};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.sizeSort(results);
		
		String unstrippedResult = sorter.test_SizeSort_L1000;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}
	
	@Test
	public void testSizeTypeSort_5() throws IOException{
		String[] expected = {"ドラマー K-ON! Hokago Live!!.ISO"};
		
		ArrayList<Object> settings = new ArrayList<Object>();
		settings.add(false);						// Folder Grouping
		settings.add(false);						// Retain Original Files
		settings.add("D:\\Downloads\\testsuite");	// Source Path
		settings.add("D:\\Downloads\\testsuite");	// Destination Path
		settings.add("");							// Folder Name
		
		final SortFiles sorter = new SortFiles(settings);
		
		File usethis = new File("D:\\Downloads\\testsuite");
		ListFiles execution = new ListFiles();
		List results = execution.grabFileList(usethis);
		sorter.sizeSort(results);
		
		String unstrippedResult = sorter.test_SizeSort_M1000;
		String[] stringResults = unstrippedResult.split("\n");
		
		assertArrayEquals(expected, stringResults);
	}

}
