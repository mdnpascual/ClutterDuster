import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFileChooser;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public maingui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		JLabel lblNewLabel_1 = new JLabel("Sort Method");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 82, 20);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("Source Path: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 20, 99, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = 
				new JTextField();
				
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(110, 19, 494, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(614, 16, 102, 23);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 55, 419, 155);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JLabel lblChooseCriteriaTo = new JLabel("Choose criteria to sort by:");
		lblChooseCriteriaTo.setEnabled(false);
		lblChooseCriteriaTo.setBounds(18, 15, 146, 14);
		panel.add(lblChooseCriteriaTo);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Alphanumerically");
		
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(17, 35, 141, 23);
		panel.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("(A-M, N-Z, 0-9, Unicode)");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(166, 40, 185, 14);
		panel.add(lblNewLabel_2);
		
		final JRadioButton rdbtnDateCreated = new JRadioButton("Date Created");
		buttonGroup.add(rdbtnDateCreated);
		rdbtnDateCreated.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnDateCreated.setEnabled(false);
		rdbtnDateCreated.setBounds(17, 61, 117, 23);
		panel.add(rdbtnDateCreated);
		
		JLabel lblnowweek = new JLabel("(< 1 wk, 1-2 wk, 2 wk - 1 mo, etc.)");
		lblnowweek.setEnabled(false);
		lblnowweek.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnowweek.setBounds(140, 66, 269, 14);
		panel.add(lblnowweek);
		
		final JRadioButton rdbtnFilzeSize = new JRadioButton("Filze Size");
		buttonGroup.add(rdbtnFilzeSize);
		rdbtnFilzeSize.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFilzeSize.setEnabled(false);
		rdbtnFilzeSize.setBounds(18, 87, 85, 23);
		panel.add(rdbtnFilzeSize);
		
		JLabel lblmb = new JLabel("(< 1MB, 1MB - 10MB, 10MB - 100MB, etc.)");
		lblmb.setEnabled(false);
		lblmb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmb.setBounds(109, 92, 279, 14);
		panel.add(lblmb);
		
		final JRadioButton rdbtnFileType = new JRadioButton("File Type");
		buttonGroup.add(rdbtnFileType);
		rdbtnFileType.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFileType.setEnabled(false);
		rdbtnFileType.setBounds(18, 113, 85, 23);
		panel.add(rdbtnFileType);
		
		JLabel lblpicturesVideos = new JLabel("(Pictures, Videos, Documents, Archives, etc.)");
		lblpicturesVideos.setEnabled(false);
		lblpicturesVideos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpicturesVideos.setBounds(109, 118, 300, 14);
		panel.add(lblpicturesVideos);
		
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
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Folder Grouping");
		
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setBounds(17, 25, 137, 23);
		panel_2.add(chckbxNewCheckBox);
		
		JLabel lblcheckToMove = new JLabel("(Check to move all files into a new folder \r\nunder a specified name)");
		lblcheckToMove.setEnabled(false);
		lblcheckToMove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcheckToMove.setBounds(38, 45, 361, 23);
		panel_2.add(lblcheckToMove);
		
		final JLabel lblFolderName = new JLabel("Folder Name: ");
		lblFolderName.setEnabled(false);
		lblFolderName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFolderName.setBounds(17, 70, 81, 23);
		panel_2.add(lblFolderName);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(98, 72, 258, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		final JCheckBox chckbxRetainOriginalFiles = new JCheckBox("Retain Original Files");
		chckbxRetainOriginalFiles.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxRetainOriginalFiles.setEnabled(false);
		chckbxRetainOriginalFiles.setBounds(17, 97, 159, 23);
		panel_2.add(chckbxRetainOriginalFiles);
		
		JLabel lblWillNotDelete = new JLabel("(Check to copy files instead of move them)");
		lblWillNotDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWillNotDelete.setEnabled(false);
		lblWillNotDelete.setBounds(38, 120, 361, 14);
		panel_2.add(lblWillNotDelete);
		
		JLabel lblDestinationPath = new JLabel("Destination Path: ");
		lblDestinationPath.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDestinationPath.setBounds(10, 222, 123, 14);
		frame.getContentPane().add(lblDestinationPath);
		
		textField_2 = new JTextField();
		
		textField_2.setEnabled(false);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(143, 221, 494, 20);
		frame.getContentPane().add(textField_2);
		
		final JButton btnChangeDestinationFolder = new JButton("Change Destination Folder");
		
		btnChangeDestinationFolder.setEnabled(false);
		btnChangeDestinationFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChangeDestinationFolder.setBounds(143, 252, 194, 23);
		frame.getContentPane().add(btnChangeDestinationFolder);
		
		final JButton btnUseCurrent = new JButton("Use Source");
				
		btnUseCurrent.setEnabled(false);
		btnUseCurrent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUseCurrent.setBounds(347, 252, 123, 23);
		frame.getContentPane().add(btnUseCurrent);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(647, 221, 211, 54);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		btnNewButton_1 = new JButton("GO");
		
		btnNewButton_1.setBounds(0, 0, 211, 54);
		panel_4.add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setForeground(new Color(0, 128, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 36));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 286, 847, 155);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea txtrWdwSft = new JTextArea();
		txtrWdwSft.setBackground(Color.LIGHT_GRAY);
		txtrWdwSft.setEnabled(false);
		scrollPane.setViewportView(txtrWdwSft);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setEnabled(false);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressBar.setStringPainted(true);
		progressBar.setBounds(84, 452, 774, 28);
		frame.getContentPane().add(progressBar);
		
		JLabel lblProgress = new JLabel("Progress: ");
		lblProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgress.setBounds(10, 454, 70, 21);
		frame.getContentPane().add(lblProgress);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, textField, btnNewButton}));
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!textField.getText().isEmpty()){
					rdbtnNewRadioButton.setEnabled(true);
					rdbtnDateCreated.setEnabled(true);
					rdbtnFilzeSize.setEnabled(true);
					rdbtnFileType.setEnabled(true);
					//lblChooseCriteriaTo.setEnabled(true);
				}
				if(chckbxNewCheckBox.isEnabled()){
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnNewRadioButton.isSelected()){
					chckbxNewCheckBox.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textField_2.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					//btnNewButton_1.setEnabled(true);
				}
				if(chckbxNewCheckBox.isEnabled()){
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnDateCreated.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnDateCreated.isSelected()){
					chckbxNewCheckBox.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textField_2.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					//btnNewButton_1.setEnabled(true);
				}
				if(chckbxNewCheckBox.isEnabled()){
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnFilzeSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFilzeSize.isSelected()){
					chckbxNewCheckBox.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textField_2.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					//btnNewButton_1.setEnabled(true);
				}
				if(chckbxNewCheckBox.isEnabled()){
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
				}
			}
		});
		
		rdbtnFileType.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFileType.isSelected()){
					chckbxNewCheckBox.setEnabled(true);
					chckbxRetainOriginalFiles.setEnabled(true);
					textField_2.setEnabled(true);
					btnChangeDestinationFolder.setEnabled(true);
					//btnNewButton_1.setEnabled(true);
				}
				if(chckbxNewCheckBox.isEnabled()){
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
				}
			}
		});
		
		chckbxNewCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chckbxNewCheckBox.isSelected()){
					textField_1.setEnabled(true);
					lblFolderName.setEnabled(true);
				}
				else if(!chckbxNewCheckBox.isSelected()){
					textField_1.setEnabled(false);
					lblFolderName.setEnabled(false);
				}
			}
		});
		
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textField_2.getText().equals(textField.getText())){
					btnUseCurrent.setEnabled(false);
				}
				else if(!(textField_2.getText().equals(textField.getText()))){
					btnUseCurrent.setEnabled(true);
				}
				if(textField_2.getText().isEmpty()){
					btnNewButton_1.setEnabled(false);
				}
				else{
					btnNewButton_1.setEnabled(true);
				}
			}
		});
		
		btnUseCurrent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnUseCurrent.isEnabled()){
					textField_2.setText(textField.getText());
					if(textField_2.getText().equals(textField.getText())){
						btnUseCurrent.setEnabled(false);
					}
					else if(!(textField_2.getText().equals(textField.getText()))){
						btnUseCurrent.setEnabled(true);
					}
					if(textField_2.getText().isEmpty()){
						btnNewButton_1.setEnabled(false);
					}
					else{
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("Folder Browser");
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textField.setText(chooser.getSelectedFile().getPath());
					if (!textField.getText().isEmpty()){
						rdbtnNewRadioButton.setEnabled(true);
						rdbtnDateCreated.setEnabled(true);
						rdbtnFilzeSize.setEnabled(true);
						rdbtnFileType.setEnabled(true);
					}
					
					if(chckbxNewCheckBox.isEnabled()){
						if(textField_2.getText().equals(textField.getText())){
							btnUseCurrent.setEnabled(false);
						}
						else if(!(textField_2.getText().equals(textField.getText()))){
							btnUseCurrent.setEnabled(true);
						}
					}
				}
				
			}
		});
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnNewButton_1.isEnabled()){
					txtrWdwSft.setEnabled(true);
					txtrWdwSft.setBackground(Color.white);
					progressBar.setEnabled(true);
					//PUT START SORT SEQUENCE HERE
					
					//SUCESS
				}				
			}
		});
		
		btnChangeDestinationFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnUseCurrent.isEnabled()){
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
					chooser.setDialogTitle("Folder Browser");
					if(btnChangeDestinationFolder.isEnabled()){
						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							textField_2.setText(chooser.getSelectedFile().getPath());
							if(chckbxNewCheckBox.isEnabled()){
								if(textField_2.getText().equals(textField.getText())){
									btnUseCurrent.setEnabled(false);
								}
								else if(!(textField_2.getText().equals(textField.getText()))){
									btnUseCurrent.setEnabled(true);
								}
							}
						}
						if(textField_2.getText().isEmpty()){
							btnNewButton_1.setEnabled(false);
						}
						else{
							btnNewButton_1.setEnabled(true);
						}
					}
				}
		}
				
		});
		
	}
	
}