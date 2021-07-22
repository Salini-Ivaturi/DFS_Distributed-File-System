package dfs;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/*
 * Martin Le
 * 
 */

public class FileGUI extends JFrame implements ActionListener{
	
	private FileManager filelist;
	
	JButton button;
	
	/* public FileGUI(FileManager nf) {
	*
	*
	* filelist = nf;
	* 
	*/
	
	public FileGUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button = new JButton("Select File to Upload");
		button.addActionListener(this);
		
		this.add(button);
		this.pack();
		this.setVisible(true);
	}
	
	public void processCommands() {
		//stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button) {
			 JFileChooser fileChooser = new JFileChooser();
			 //getting location of the file to store & send to the server for location recording
			 
			 int response = fileChooser.showOpenDialog(null); //select a file to open
			 
			 if(response == JFileChooser.APPROVE_OPTION) {
				 File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				 System.out.println(file);
			 }
			 
			 //System.out.println(fileChooser.showOpenDialog(null)); //debug
		}
	}
	
	
	
}
