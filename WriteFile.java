package com.crome.ReadAndWrite;

import java.io.*;

public class WriteFile {
	/*
	 * This class writes a simple text file 
	 * it takes a path in at the constructor
	 * and calls the method createFile()
	 * Java requires the throw IOException
	 * whenever there is file input or output
	 * the method addText allows a user to add
	 * as much text as they want to the file
	 * the closeFile() method must be called
	 * or the file will be inaccessible
	 */
	private String path;
	private PrintWriter writer;
	
	public WriteFile(String path) throws IOException{
		this.path=path;
		createFile();
	}
	
	private void createFile() throws IOException{
		//create the file, the true means append to
		//the file if it exists, creates it if it doesn't exist
		FileWriter outFile = new FileWriter(path, true);
		writer = new PrintWriter(outFile);
		
	}
	
	public void addText(String content){
		//add content to the file
		writer.println(content);
	}
	
	public void closeFile(){
		//close the file for editing
		writer.close();
	}	
}
