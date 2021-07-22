package dfs;

public class FileMessage extends Message {
	private FileItem file;

	public FileMessage(FileItem file) {
		super();
		this.file = file;
		this.type = MessageType.UploadRequest;
	}
	
	public FileItem getFile() {
		return file;
	}
	
	public void setFile(FileItem file) {
		this.file = file;
	}

}
