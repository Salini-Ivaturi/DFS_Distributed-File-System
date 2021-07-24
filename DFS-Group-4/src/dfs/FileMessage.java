package dfs;

public class FileMessage extends Message {
	private FileItem file;

	public FileMessage(FileItem file, MessageType type) {
		super();
		this.file = file;
		this.type = type;
	}
	
	public FileItem getFile() {
		return file;
	}
	
	public void setFile(FileItem file) {
		this.file = file;
	}

}
