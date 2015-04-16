import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

import org.apache.commons.io.FileUtils;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JFormattedTextField;

public class maingui {
	
	private JFrame frmClutterduster;
	private JTextField textSourcePath;
	private JTextField textFolderName;
	private JTextField textDestinationPath;
	private JButton btnGo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public String lastFinal;
	public String lastFinal2;
	public Boolean isRunning = false;
	public Boolean isRunning2 = false;
	Thread t, t2;
	
	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maingui window = new maingui();
					window.frmClutterduster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Create the application
	public maingui() {
		initialize();
	}
	
	// Initialize the contents of the frame
	private void initialize() {
		
		frmClutterduster = new JFrame();
		frmClutterduster.setResizable(false);
		frmClutterduster.setTitle("ClutterDuster");
		frmClutterduster.setBounds(100, 100, 896, 530);
		frmClutterduster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClutterduster.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(30, 45, 82, 20);
		frmClutterduster.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSortMethod = new JLabel("Sort Method");
		lblSortMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortMethod.setBounds(0, 0, 82, 20);
		panel_1.add(lblSortMethod);
		lblSortMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		final JLabel lblSourcePath = new JLabel("Source Path:");
		lblSourcePath.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSourcePath.setBounds(10, 20, 99, 14);
		frmClutterduster.getContentPane().add(lblSourcePath);
		
		textSourcePath = new JTextField();
		
		textSourcePath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSourcePath.setBounds(110, 19, 494, 20);
		frmClutterduster.getContentPane().add(textSourcePath);
		textSourcePath.setColumns(10);
		
		final JButton btnBrowse = new JButton("Browse");
		
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBrowse.setBounds(614, 16, 102, 23);
		frmClutterduster.getContentPane().add(btnBrowse);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 55, 419, 155);
		frmClutterduster.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JLabel lblChooseCriteria = new JLabel("Choose criteria to sort by:");
		lblChooseCriteria.setEnabled(false);
		lblChooseCriteria.setBounds(18, 15, 146, 14);
		panel.add(lblChooseCriteria);
		
		final JRadioButton rdbtnAlphanumerically = new JRadioButton("Alphanumerically");
		
		buttonGroup.add(rdbtnAlphanumerically);
		rdbtnAlphanumerically.setEnabled(false);
		rdbtnAlphanumerically.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnAlphanumerically.setBounds(17, 35, 141, 23);
		panel.add(rdbtnAlphanumerically);
		
		JLabel lblAlphanumerically = new JLabel("(A-M, N-Z, 0-9, Unicode)");
		lblAlphanumerically.setEnabled(false);
		lblAlphanumerically.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlphanumerically.setBounds(166, 40, 185, 14);
		panel.add(lblAlphanumerically);
		
		final JRadioButton rdbtnDateCreated = new JRadioButton("Date Created");
		buttonGroup.add(rdbtnDateCreated);
		rdbtnDateCreated.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnDateCreated.setEnabled(false);
		rdbtnDateCreated.setBounds(17, 61, 117, 23);
		panel.add(rdbtnDateCreated);
		
		JLabel lblDateCreated = new JLabel("(< 1 wk, 1-2 wk, 2 wk - 1 mo, etc.)");
		lblDateCreated.setEnabled(false);
		lblDateCreated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateCreated.setBounds(140, 66, 269, 14);
		panel.add(lblDateCreated);
		
		final JRadioButton rdbtnFilzeSize = new JRadioButton("Filze Size");
		buttonGroup.add(rdbtnFilzeSize);
		rdbtnFilzeSize.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFilzeSize.setEnabled(false);
		rdbtnFilzeSize.setBounds(18, 87, 85, 23);
		panel.add(rdbtnFilzeSize);
		
		JLabel lblFileSize = new JLabel("(< 1MB, 1MB - 10MB, 10MB - 100MB, etc.)");
		lblFileSize.setEnabled(false);
		lblFileSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFileSize.setBounds(109, 92, 279, 14);
		panel.add(lblFileSize);
		
		final JRadioButton rdbtnFileType = new JRadioButton("File Type");
		buttonGroup.add(rdbtnFileType);
		rdbtnFileType.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFileType.setEnabled(false);
		rdbtnFileType.setBounds(18, 113, 85, 23);
		panel.add(rdbtnFileType);
		
		JLabel lblFileType = new JLabel("(Pictures, Videos, Documents, Archives, etc.)");
		lblFileType.setEnabled(false);
		lblFileType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFileType.setBounds(109, 118, 300, 14);
		panel.add(lblFileType);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBounds(463, 45, 113, 20);
		frmClutterduster.getContentPane().add(panel_3);
		
		JLabel lblAdditionalOptions = new JLabel("Additional Options");
		lblAdditionalOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdditionalOptions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdditionalOptions.setBounds(0, 0, 113, 20);
		panel_3.add(lblAdditionalOptions);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(449, 55, 409, 155);
		frmClutterduster.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		final JCheckBox chckbxFolderGrouping = new JCheckBox("Folder Grouping");
		
		chckbxFolderGrouping.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxFolderGrouping.setEnabled(false);
		chckbxFolderGrouping.setBounds(17, 25, 137, 23);
		panel_2.add(chckbxFolderGrouping);
		
		JLabel lblFolderGrouping = new JLabel("(Groups folders containing your sorted files into one folder)");
		lblFolderGrouping.setEnabled(false);
		lblFolderGrouping.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFolderGrouping.setBounds(38, 45, 361, 23);
		panel_2.add(lblFolderGrouping);
		
		final JLabel lblFolderName = new JLabel("Folder Name: ");
		lblFolderName.setEnabled(false);
		lblFolderName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFolderName.setBounds(17, 70, 81, 23);
		panel_2.add(lblFolderName);
		
		textFolderName = new JTextField();
		textFolderName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
		        if (!(Character.isLetter(c) || (Character.isDigit(c)) || (Character.isSpaceChar(c))))
		            evt.consume();
			}
		});
		textFolderName.setEnabled(false);
		textFolderName.setBounds(98, 72, 258, 20);
		panel_2.add(textFolderName);
		textFolderName.setColumns(10);
		
		final JCheckBox chckbxRetainOriginalFiles = new JCheckBox("Retain Original Files");
		chckbxRetainOriginalFiles.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxRetainOriginalFiles.setEnabled(false);
		chckbxRetainOriginalFiles.setBounds(17, 97, 159, 23);
		panel_2.add(chckbxRetainOriginalFiles);
		
		JLabel lblRetainOriginalFiles = new JLabel("(Retains a copy of your original files unsorted)");
		lblRetainOriginalFiles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetainOriginalFiles.setEnabled(false);
		lblRetainOriginalFiles.setBounds(38, 120, 361, 14);
		panel_2.add(lblRetainOriginalFiles);
		
		final JLabel lblDestinationPath = new JLabel("Destination Path:");
		lblDestinationPath.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDestinationPath.setBounds(10, 222, 123, 14);
		frmClutterduster.getContentPane().add(lblDestinationPath);
		
		textDestinationPath = new JTextField();
		
		textDestinationPath.setEnabled(false);
		textDestinationPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDestinationPath.setColumns(10);
		textDestinationPath.setBounds(143, 221, 494, 20);
		frmClutterduster.getContentPane().add(textDestinationPath);
		
		final JButton btnChangeDestinationFolder = new JButton("Change Destination Folder");
		
		btnChangeDestinationFolder.setEnabled(false);
		btnChangeDestinationFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChangeDestinationFolder.setBounds(143, 252, 194, 23);
		frmClutterduster.getContentPane().add(btnChangeDestinationFolder);
		
		final JButton btnUseSource = new JButton("Use Source");
				
		btnUseSource.setEnabled(false);
		btnUseSource.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUseSource.setBounds(347, 252, 123, 23);
		frmClutterduster.getContentPane().add(btnUseSource);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(647, 221, 211, 54);
		frmClutterduster.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		btnGo = new JButton("GO");
		
		btnGo.setBounds(0, 0, 211, 54);
		panel_4.add(btnGo);
		btnGo.setEnabled(false);
		btnGo.setForeground(new Color(0, 128, 0));
		btnGo.setFont(new Font("Tahoma", Font.BOLD, 36));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 286, 847, 155);
		frmClutterduster.getContentPane().add(scrollPane);
		
		final JTextArea txtrWdwSft = new JTextArea();
		txtrWdwSft.setEditable(false);
		txtrWdwSft.setBackground(Color.LIGHT_GRAY);
		txtrWdwSft.setEnabled(false);
		scrollPane.setViewportView(txtrWdwSft);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressBar.setStringPainted(true);
		progressBar.setBounds(84, 452, 774, 28);
		frmClutterduster.getContentPane().add(progressBar);
		
		final JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgress.setBounds(10, 454, 70, 21);
		frmClutterduster.getContentPane().add(lblProgress);
		frmClutterduster.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSourcePath, textSourcePath, btnBrowse}));
		
		textSourcePath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!textSourcePath.getText().isEmpty()) {
					if (rdbtnAlphanumerically.isEnabled() && !textDestinationPath.getText().isEmpty()) {
						btnGo.setEnabled(true);
					}
					lblChooseCriteria.setEnabled(true);
					rdbtnAlphanumerically.setEnabled(true);
					rdbtnDateCreated.setEnabled(true);
					rdbtnFilzeSize.setEnabled(true);
					rdbtnFileType.setEnabled(true);
				}
				else if (textSourcePath.getText().isEmpty()) {
					btnGo.setEnabled(false);
				}
				if (rdbtnAlphanumerically.isSelected() || rdbtnDateCreated.isSelected() || rdbtnFilzeSize.isSelected() || rdbtnFileType.isSelected()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnAlphanumerically.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnAlphanumerically.isSelected()) {
					chckbxFolderGrouping.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textDestinationPath.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnDateCreated.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnDateCreated.isSelected()) {
					chckbxFolderGrouping.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textDestinationPath.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnFilzeSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFilzeSize.isSelected()) {
					chckbxFolderGrouping.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textDestinationPath.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnFileType.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFileType.isSelected()) {
					chckbxFolderGrouping.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textDestinationPath.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(true);
					}
				}
			}
		});
		
		chckbxFolderGrouping.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chckbxFolderGrouping.isSelected()) {
					textFolderName.setEnabled(true);
					lblFolderName.setEnabled(true);
				}
				else if (!chckbxFolderGrouping.isSelected()) {
					textFolderName.setEnabled(false);
					lblFolderName.setEnabled(false);
				}
			}
		});
		
		textDestinationPath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (textDestinationPath.getText().equals(textSourcePath.getText())) {
					btnUseSource.setEnabled(false);
				}
				else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
					btnUseSource.setEnabled(true);
				}
				if (textDestinationPath.getText().isEmpty()) {
					btnGo.setEnabled(false);
				}
				else if (!(textDestinationPath.getText().isEmpty() || textSourcePath.getText().isEmpty())) {
					btnGo.setEnabled(true);
				}
			}
		});
		
		btnUseSource.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnUseSource.isEnabled()) {
					textDestinationPath.setText(textSourcePath.getText());
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
						btnUseSource.setEnabled(true);
					}
					if (!textDestinationPath.getText().isEmpty()) {
						btnGo.setEnabled(true);
					}
				}
			}
		});
		
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser;
				if (lastFinal != null) {
					chooser = new JFileChooser(lastFinal);
				}
				else {
					chooser = new JFileChooser();
				}
				chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("Folder Browser");
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textSourcePath.setText(chooser.getSelectedFile().getPath());
					lastFinal = textSourcePath.getText();
					if (!textSourcePath.getText().isEmpty()) {
						if (rdbtnAlphanumerically.isEnabled() && !textDestinationPath.getText().isEmpty()) {
							btnGo.setEnabled(true);
						}
						lblChooseCriteria.setEnabled(true);
						rdbtnAlphanumerically.setEnabled(true);
						rdbtnDateCreated.setEnabled(true);
						rdbtnFilzeSize.setEnabled(true);
						rdbtnFileType.setEnabled(true);
					}
					if (rdbtnAlphanumerically.isSelected() || rdbtnDateCreated.isSelected() || rdbtnFilzeSize.isSelected() || rdbtnFileType.isSelected()) {
						if (textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(false);
						}
						else if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(true);
						}
					}
				}
			}
		});
		
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnGo.isEnabled()) {
					txtrWdwSft.setEnabled(true);
					txtrWdwSft.setBackground(Color.white);
					progressBar.setEnabled(true);
					
					// ---------ERROR CHECKING---------
					File fileSourcePath = new File(textSourcePath.getText());
					File fileDestinationPath = new File(textDestinationPath.getText());
					if (!fileSourcePath.exists()) {
						JOptionPane.showMessageDialog(null, "Invalid Source Path", "", 2);
						return;
					}
					else if (!fileDestinationPath.exists()) {
						JOptionPane.showMessageDialog(null, "Invalid Destination Path", "", 2);
						return;
					}
					
					if (!fileSourcePath.canRead()) {
						JOptionPane.showMessageDialog(null, "Access Denied on Source Path", "", 2);
						return;
					}
					if (!fileDestinationPath.canRead()) {
						JOptionPane.showMessageDialog(null, "Access Denied on Destination Path", "", 2);
						return;
					}
					if (!fileDestinationPath.canWrite()) {
						JOptionPane.showMessageDialog(null, "Access Denied on Destination Path", "", 2);
						return;
					}
					// ---------ERROR CHECKING END---------
					
					// Passing the settings chosen
					ArrayList<Object> settings = new ArrayList<Object>();
					settings.add(chckbxFolderGrouping.isSelected());
					settings.add(chckbxRetainOriginalFiles.isSelected());
					settings.add(textSourcePath.getText());
					settings.add(textDestinationPath.getText());
					if (chckbxFolderGrouping.isSelected())
						settings.add(textFolderName.getText());
					else
						settings.add("");
					
					// Initialize Sorter
					final SortFiles sorter = new SortFiles(settings);
					
					// ------------Runnable thread #1 - Makes GUI Responsive------------
					try {
						if (t.isAlive()) {
							// Only goes in if goButton is pressed while sorting is still active, the only time this happens
							// is if sorting is aborted							
							t.interrupt();
							sorter.interrupted = true;	// Tells the sorter thread to halt
						}
					} catch (Exception e2) {
					}
					t = new Thread(new Runnable() {
						
						public void run() {					    	
							isRunning = true;
					    	disable();		// Disable GUI control
					    	
					    	int i = 0;
							// While less than 100%
							while (i < 100) {
								i = sorter.percentage;		// Gets percentage from sorter class, percentage variable is volatile
								progressBar.setValue(i); 
								txtrWdwSft.setText(sorter.outputStatus);		// Gets message from sorter class, outputStatus string is volatile
								txtrWdwSft.setCaretPosition(txtrWdwSft.getDocument().getLength());	// Auto Scroll
								try {
									Thread.sleep(20);		// Waits max 4 cycles theoretically of list files loop before updating
								} catch (InterruptedException e) {
									// catch block is the Sequence to halt sorting
									btnGo.setFont(new Font("Tahoma", Font.BOLD, 24));
									btnGo.setText("ABORTING");
									btnGo.setForeground(Color.red);
									btnGo.setEnabled(false);
									try {
										/* If aborted, waits 1000ms to give time to:
										 * 1) pass the interrupted flag to sorter class
										 * 2) sorter class detects the flag
										 * 3) Halts the sorting
										 * 4) sorter class to output the final string update
										 */
										Thread.sleep(1000);	
									} catch (InterruptedException e1) {
									}
									// START DESTINATION PATH DELETER
									int index = 0;
									File toBeDeleted;
									while (index < sorter.files.size()) {
										toBeDeleted = new File(sorter.files.get(index).toString());
										FileUtils.deleteQuietly(toBeDeleted);	// Deleted files created
										index++;
									}
									index = 0;
									while (index < sorter.folders.size()) {
										toBeDeleted = new File(sorter.folders.get(index).toString());
										FileUtils.deleteQuietly(toBeDeleted);	// Deleted folders created
										index++;
									}
									// END DESTINATION PATH DELETER
									btnGo.setEnabled(true);
									btnGo.setFont(new Font("Tahoma", Font.BOLD, 36));
									txtrWdwSft.setText(sorter.outputStatus);	// Get final string update, Theoretical (1000ms - 50ms) response time window
									if (!txtrWdwSft.getText().isEmpty()) {
										// If logbox is empty, it means sorter thread wasn't started implying there was no files to be sorted
										// This will only be executed if there are files to be sorted
										txtrWdwSft.append("--Aborted--\n");
									}
									enable();	// Enable GUI control
									isRunning = false;
									isRunning2 = false;
									return;
								}
							}
							// Will only execute these if sorting is succesful
							txtrWdwSft.setText(sorter.outputStatus);	// Get final string update
							if (!chckbxRetainOriginalFiles.isSelected()) {
								// START SOURCE PATH DELETER
								int index = 0;
								File toBeDeleted;
								while (index < sorter.files_source.size()) {
									toBeDeleted = new File(sorter.files_source.get(index).toString());
									FileUtils.deleteQuietly(toBeDeleted);	// Deleted files created
									index++;
								}
								index = 0;
								while (index < sorter.folders_source.size()) {
									toBeDeleted = new File(sorter.folders_source.get(index).toString());
									FileUtils.deleteQuietly(toBeDeleted);	// Deleted folders created
									index++;
								}
								// END SOURCE PATH DELETER
							}
							enable();	// Enable GUI control
							isRunning = false;
					    }
					}, "worker");
					if (!isRunning) {
						t.start();
					}
					// --------Runnable thread #1 - Makes GUI Responsive END--------
					
					// Sorter thread
					try {
						if (t2.isAlive()) {
							// Only goes in if goButton is pressed while sorting is still active, the only time this happens
							// is if sorting is aborted. 
							sorter.interrupted = true;
							t2.interrupt();
						}
					} catch (Exception e2) {	
					}
					t2 = new Thread(new Runnable() {
						
						public void run() {
							isRunning2 = true;
							File usethis = new File(textSourcePath.getText());
							ListFiles execution = new ListFiles();
							List results;
							try {
								results = execution.grabFileList(usethis);		// No Filter
								if (results.size() == 0) {
									// Source Folder has no files to sort
									t.interrupt();
									isRunning = false;
									isRunning2 = false;
									Thread.sleep(200);
									JOptionPane.showMessageDialog(null, "No Files to Sort", "", 2);
									return;
								}
								// If there's files to sort, execute sorter depending on user choice
								if (rdbtnAlphanumerically.isSelected()) {
									sorter.alphanumeric(results);
						    	}
								else if (rdbtnDateCreated.isSelected()) {
						    		sorter.dateSort(results);
						    	}
								else if (rdbtnFilzeSize.isSelected()) {
						    		sorter.sizeSort(results);
						    	}
								else if (rdbtnFileType.isSelected()) {
						    		sorter.fileType(results);
						    	}
							} catch (IOException e) {
							} catch (InterruptedException e) {
							}
							isRunning2 = false;
						}
					}, "sorter");
					if (!isRunning2) {
						t2.start();
					}
					
				}
			}
			
			public void disable() {
				// DISABLE GUI AND CHANGE BUTTON TEXT
				textSourcePath.setEnabled(false);
				btnBrowse.setEnabled(false);
				lblChooseCriteria.setEnabled(false);
				rdbtnAlphanumerically.setEnabled(false);
				rdbtnDateCreated.setEnabled(false);
				rdbtnFilzeSize.setEnabled(false);
				rdbtnFileType.setEnabled(false);
				chckbxFolderGrouping.setEnabled(false);
				lblFolderName.setEnabled(false);
				textFolderName.setEnabled(false);
				chckbxRetainOriginalFiles.setEnabled(false);
				textDestinationPath.setEnabled(false);
				btnChangeDestinationFolder.setEnabled(false);
				btnUseSource.setEnabled(false);
				btnGo.setText("ABORT");
				btnGo.setForeground(Color.red);
				txtrWdwSft.setText("");
				txtrWdwSft.setEditable(false);
				txtrWdwSft.setCaretPosition(txtrWdwSft.getDocument().getLength());
			}
			
			public void enable() {
				// ENABLE GUI AND CHANGE BUTTON TEXT
				textSourcePath.setEnabled(true);
				btnBrowse.setEnabled(true);
				lblChooseCriteria.setEnabled(true);
				rdbtnAlphanumerically.setEnabled(true);
				rdbtnDateCreated.setEnabled(true);
				rdbtnFilzeSize.setEnabled(true);
				rdbtnFileType.setEnabled(true);
				chckbxFolderGrouping.setEnabled(true);
				if (chckbxFolderGrouping.isSelected()) {
					lblFolderName.setEnabled(true);
					textFolderName.setEnabled(true);
				}
				chckbxRetainOriginalFiles.setEnabled(true);
				textDestinationPath.setEnabled(true);
				btnChangeDestinationFolder.setEnabled(true);
				if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
					btnUseSource.setEnabled(true);
				}
				btnGo.setText("GO");
				btnGo.setForeground(new Color(0, 128, 0));
				txtrWdwSft.setEditable(true);
				txtrWdwSft.setCaretPosition(txtrWdwSft.getDocument().getLength());
			}
		});
		
		btnChangeDestinationFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnChangeDestinationFolder.isEnabled()) {
					JFileChooser chooser;
					if (lastFinal2 != null) {
						chooser = new JFileChooser(lastFinal2);
					}
					else {
						chooser = new JFileChooser();
					}
					chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
					chooser.setDialogTitle("Folder Browser");
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textDestinationPath.setText(chooser.getSelectedFile().getPath());
						lastFinal2 = textDestinationPath.getText();
						if (textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(false);
						}
						else if (!textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(true);
						}
					}
					if (textDestinationPath.getText().isEmpty()) {
						btnGo.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().isEmpty() || textSourcePath.getText().isEmpty())) {
						btnGo.setEnabled(true);
					}
				}
			}
		});
	}
}