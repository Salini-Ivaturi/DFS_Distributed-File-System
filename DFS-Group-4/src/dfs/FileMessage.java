package dfs;

public class FileMessage extends Message {
	private File file;

	public FileMessage(File file) {
		super();
		this.file = file;
		this.type = MessageType.UploadRequest;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

}
