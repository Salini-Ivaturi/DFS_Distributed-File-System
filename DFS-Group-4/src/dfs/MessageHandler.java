package dfs;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageHandler {
	private String ipAddress;
	private ObjectOutputStream objectOutputStream;
	
	public MessageHandler(String ipAddress) throws IOException {
		this.ipAddress = ipAddress;
		Socket socket = new Socket(ipAddress, 1234);
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void SendFile(FileItem file, String ipAddress) {
		Message FileMessage = new Message(file, MessageType.File);
		try {
			objectOutputStream.writeObject(FileMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
