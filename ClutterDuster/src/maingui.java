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

public class maingui {
	
	private JFrame frame;
	private JTextField textSourcePath;
	private JTextField textFolderName;
	private JTextField textDestinationPath;
	private JButton btnGo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public String lastFinal;
	public String lastFinal2;
	
	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maingui window = new maingui();
					window.frame.setVisible(true);
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 896, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(30, 45, 82, 20);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSortMethod = new JLabel("Sort Method");
		lblSortMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortMethod.setBounds(0, 0, 82, 20);
		panel_1.add(lblSortMethod);
		lblSortMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		final JLabel lblSourcePath = new JLabel("Source Path:");
		lblSourcePath.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSourcePath.setBounds(10, 20, 99, 14);
		frame.getContentPane().add(lblSourcePath);
		
		textSourcePath = new JTextField();
		
		textSourcePath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSourcePath.setBounds(110, 19, 494, 20);
		frame.getContentPane().add(textSourcePath);
		textSourcePath.setColumns(10);
		
		final JButton btnBrowse = new JButton("Browse");
		
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBrowse.setBounds(614, 16, 102, 23);
		frame.getContentPane().add(btnBrowse);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 55, 419, 155);
		frame.getContentPane().add(panel);
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
		frame.getContentPane().add(panel_3);
		
		JLabel lblAdditionalOptions = new JLabel("Additional Options");
		lblAdditionalOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdditionalOptions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdditionalOptions.setBounds(0, 0, 113, 20);
		panel_3.add(lblAdditionalOptions);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(449, 55, 409, 155);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		final JCheckBox chckbxFolderGrouping = new JCheckBox("Folder Grouping");
		
		chckbxFolderGrouping.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxFolderGrouping.setEnabled(false);
		chckbxFolderGrouping.setBounds(17, 25, 137, 23);
		panel_2.add(chckbxFolderGrouping);
		
		JLabel lblFolderGrouping = new JLabel("(Check to move all files into a new folder \r\nunder a specified name)");
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
		textFolderName.setEnabled(false);
		textFolderName.setBounds(98, 72, 258, 20);
		panel_2.add(textFolderName);
		textFolderName.setColumns(10);
		
		final JCheckBox chckbxRetainOriginalFiles = new JCheckBox("Retain Original Files");
		chckbxRetainOriginalFiles.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxRetainOriginalFiles.setEnabled(false);
		chckbxRetainOriginalFiles.setBounds(17, 97, 159, 23);
		panel_2.add(chckbxRetainOriginalFiles);
		
		JLabel lblRetainOriginalFiles = new JLabel("(Check to copy files instead of move them)");
		lblRetainOriginalFiles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetainOriginalFiles.setEnabled(false);
		lblRetainOriginalFiles.setBounds(38, 120, 361, 14);
		panel_2.add(lblRetainOriginalFiles);
		
		final JLabel lblDestinationPath = new JLabel("Destination Path: ");
		lblDestinationPath.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDestinationPath.setBounds(10, 222, 123, 14);
		frame.getContentPane().add(lblDestinationPath);
		
		textDestinationPath = new JTextField();
		
		textDestinationPath.setEnabled(false);
		textDestinationPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDestinationPath.setColumns(10);
		textDestinationPath.setBounds(143, 221, 494, 20);
		frame.getContentPane().add(textDestinationPath);
		
		final JButton btnChangeDestinationFolder = new JButton("Change Destination Folder");
		
		btnChangeDestinationFolder.setEnabled(false);
		btnChangeDestinationFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChangeDestinationFolder.setBounds(143, 252, 194, 23);
		frame.getContentPane().add(btnChangeDestinationFolder);
		
		final JButton btnUseSource = new JButton("Use Source");
				
		btnUseSource.setEnabled(false);
		btnUseSource.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUseSource.setBounds(347, 252, 123, 23);
		frame.getContentPane().add(btnUseSource);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(647, 221, 211, 54);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		btnGo = new JButton("GO");
		
		btnGo.setBounds(0, 0, 211, 54);
		panel_4.add(btnGo);
		btnGo.setEnabled(false);
		btnGo.setForeground(new Color(0, 128, 0));
		btnGo.setFont(new Font("Tahoma", Font.BOLD, 36));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 286, 847, 155);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea txtrWdwSft = new JTextArea();
		txtrWdwSft.setEditable(false);
		txtrWdwSft.setBackground(Color.LIGHT_GRAY);
		txtrWdwSft.setEnabled(false);
		scrollPane.setViewportView(txtrWdwSft);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressBar.setStringPainted(true);
		progressBar.setBounds(84, 452, 774, 28);
		frame.getContentPane().add(progressBar);
		
		final JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgress.setBounds(10, 454, 70, 21);
		frame.getContentPane().add(lblProgress);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSourcePath, textSourcePath, btnBrowse}));
		
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
				if (btnGo.isEnabled()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					btnUseSource.setEnabled(true);
				}
				if (btnGo.isEnabled()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					btnUseSource.setEnabled(true);
				}
				if (btnGo.isEnabled()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					btnUseSource.setEnabled(true);
				}
				if (btnGo.isEnabled()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					btnUseSource.setEnabled(true);
				}
				if (btnGo.isEnabled()) {
					if (textDestinationPath.getText().equals(textSourcePath.getText())) {
						btnUseSource.setEnabled(false);
					}
					else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					if (textDestinationPath.getText().isEmpty()) {
						btnGo.setEnabled(false);
					}
					else {
						btnGo.setEnabled(true);
					}
				}
			}
		});
		
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser;
				if (lastFinal != null){
					chooser = new JFileChooser(lastFinal);
				}
				else{
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
					if (btnGo.isEnabled()) {
						if (textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(false);
						}
						else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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
					// ERROR CHECKING
					
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
					
					// SIMULATION FOR DEMO
					JOptionPane.showMessageDialog(null, "Yay!", "", JOptionPane.INFORMATION_MESSAGE);
					
					// ENABLE GUI AND CHANGE BUTTON TEXT
					textSourcePath.setEnabled(true);
					btnBrowse.setEnabled(true);
					lblChooseCriteria.setEnabled(true);
					rdbtnAlphanumerically.setEnabled(true);
					rdbtnDateCreated.setEnabled(true);
					rdbtnFilzeSize.setEnabled(true);
					rdbtnFileType.setEnabled(true);
					chckbxFolderGrouping.setEnabled(true);
					lblFolderName.setEnabled(true);
					textFolderName.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textDestinationPath.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					btnUseSource.setEnabled(true);
					btnGo.setText("GO");
					btnGo.setForeground(new Color(0, 128, 0));
					txtrWdwSft.setEditable(true);
				}
			}
		});
		
		btnChangeDestinationFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnChangeDestinationFolder.isEnabled()) {
					JFileChooser chooser;
					if (lastFinal2 != null){
						chooser = new JFileChooser(lastFinal2);
					}
					else{
						chooser = new JFileChooser();
					}
					chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
					chooser.setDialogTitle("Folder Browser");
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textDestinationPath.setText(chooser.getSelectedFile().getPath());
						lastFinal2 = textDestinationPath.getText();
						if (btnGo.isEnabled()) {
							if (textDestinationPath.getText().equals(textSourcePath.getText())) {
								btnUseSource.setEnabled(false);
							}
							else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
								btnUseSource.setEnabled(true);
							}
						}
						if (textDestinationPath.getText().equals(textSourcePath.getText())) {
							btnUseSource.setEnabled(false);
						}
						else if (!(textDestinationPath.getText().equals(textSourcePath.getText()))) {
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