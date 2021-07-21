package dfs;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileManager {

	private int numFiles;
	
	private File[] filesArray;
	
	public FileManager() {
		numFiles = 0;
		filesArray = new File[0];
	}
	
	public String toString() {
		// Return a string containing all the files in the
		// order they are stored in the array along with
		// the values for numFiles and the length of the array.
		String s = "numFiles=" + numFiles + "\nfileArray.length = " + filesArray.length + "\n";
	       for (int i = 0; i < numFiles; i++) {
	           s += "fileArray[" + i + "] = " + filesArray[i] + "\n";
	       }
	       return s;
	}





	
}
