package com.crome.ReadAndWrite;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Form extends JFrame{
		private JPanel mainPanel;
		private JPanel topPanel;
		private JPanel centerPanel;
		private JPanel bottomPanel;
		private JLabel lblFileName;
		private JTextField txtFileName;
		private JTextArea txaTextViewer;
		private JButton btnNewFile;
		private JButton btnOpenFile;
		private JButton btnSaveFile;
		private JButton btnExit;

		public Form(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(640, 200, 400, 400);
			add(createMainPanel());
			setVisible(true);
			setTitle("Text Viewer/Editor");
		}
		
		public JPanel createMainPanel(){
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(createTopPanel(), BorderLayout.NORTH);
			mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
			mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
			
			return mainPanel;
		}
		
		public JPanel createTopPanel(){
			topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(1, 3, 5, 5));
			
			lblFileName = new JLabel("File Name:");
			lblFileName.setHorizontalAlignment(JLabel.RIGHT);
			txtFileName = new JTextField();
			
			topPanel.add(lblFileName);
			topPanel.add(txtFileName);
			
			return topPanel;
		}
		
		public JPanel createCenterPanel(){
			centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			txaTextViewer = new JTextArea();
			txaTextViewer.setLineWrap(true);
			txaTextViewer.setWrapStyleWord(true);
			centerPanel.add(txaTextViewer, BorderLayout.CENTER);
			
			return centerPanel;
		}
		
		public JPanel createBottomPanel(){
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			btnNewFile = new JButton("New");
			btnNewFile.addActionListener(new NewFileListener());
			btnOpenFile = new JButton("Open");
			btnOpenFile.addActionListener(new OpenFileListener());
			btnSaveFile = new JButton("Save");
			btnSaveFile.addActionListener(new SaveFileListener());
			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ExitListener());
			
			bottomPanel.add(btnNewFile);
			bottomPanel.add(btnOpenFile);
			bottomPanel.add(btnSaveFile);
			bottomPanel.add(btnExit);
			
			return bottomPanel;
		}
		
		private class NewFileListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtFileName.setText("");
				txaTextViewer.setText("");				
			}	
		}		
		
		
		private class OpenFileListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//ReadFile read = new ReadFile(cboFileName.getSelectedItem().toString());
				ReadFile read = new ReadFile("C:\\temp\\" + txtFileName.getText());
				try {
					String fileContent = read.getText();
					txaTextViewer.setText(fileContent);
				} catch (IOException f) {			
					System.out.println(f.getMessage());
				}
			}					
		}		
				
		private class SaveFileListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					WriteFile write = new WriteFile("C:\\temp\\" + txtFileName.getText());
					String content = txaTextViewer.getText();
					write.addText(content);
					write.closeFile();
					txaTextViewer.setText("");
					txaTextViewer.setText("Successfully Added!");
				}catch(IOException f){
					System.out.println(f.getMessage());		
				}						
			}
		}
		
		private class ExitListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}		
		}		
}