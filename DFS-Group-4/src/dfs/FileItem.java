package dfs;
import java.util.Date;

public class FileItem implements Comparable {
	
	// Meta Data:
	private String fName;	 // name of this file
	private Date uploadDate; // date of this file since uploading
	private int sizeOfFile;	// actual size of the file
	private FileStatus fStatus = FileStatus.available; // status of file in enum format
	private String filePath; // file path in actual server directory

	
	public FileItem(String fName, Date upDate, int filesize, String fPath) {
		this.fName = fName;
	    this.uploadDate = upDate;
	    this.sizeOfFile = filesize;
	    this.filePath = fPath;
	}
	
	public String getFileName() {
		return fName;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	
	public int getSizeOfFile() {
		return sizeOfFile;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setfName(String newfName) {
		this.fName = newfName;
	}

	public void setFilePath(String fPath) {
		this.filePath = fPath;
	}

	public void setAvailable() {
		fStatus = FileStatus.available;
	}
	
	public void setCorrupted() {
		fStatus = FileStatus.corrupted;
	}
	
	public void setCopied() {
		fStatus = FileStatus.copied;
	}
	
	public void setDeleted() {
		fStatus = FileStatus.deleted;
	}
	
	public void setMissing() {
		fStatus = FileStatus.missing;
	}
	
	@Override
	public int compareTo(Object object) {
		FileItem fItem = (FileItem) object;
		return fName.compareTo(fItem.getFileName());
	}

	public String toString() {
		return fName.toUpperCase() + "/" + getFileName() + "/" + getUploadDate() + "/" + getSizeOfFile();
	}

}
