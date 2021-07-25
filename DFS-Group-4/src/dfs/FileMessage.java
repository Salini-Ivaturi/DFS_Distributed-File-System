package dfs;

import java.io.File;

public class FileMessage extends Message {
	private File file;

	public FileMessage(File file, MessageType type) {
		super();
		this.file = file;
		this.type = type;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

}
