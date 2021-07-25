package dfs;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

// Client class
class Client {
	private LoginManager loginManager = new LoginManager();
	//private LoginInterface loginInterface = new LoginInterface(loginManager);
	// driver code
	public static void main(String[] args)
	{
		// establish a connection by providing host and port
		// number
		//LoginHandler login = loginInterface.loginStatus();
		// if (login == true) do the client and server thingies
		try (Socket socket = new Socket("localhost", 1234)) {			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());		// File not object
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());		// File not object
			LoginMessage loginMessage = new LoginMessage("peter", "abcd1234");
			objectOutputStream.writeObject(loginMessage);
			Message receivedMessage;
			boolean clientVerified = false;
			
			while (true) {
				receivedMessage = (Message) objectInputStream.readObject();
				System.out.println("---------------------");
				System.out.println("Message received.");
				System.out.println("Message status: " + receivedMessage.getStatus());
				System.out.println("Message text: " + receivedMessage.getText());
				if (receivedMessage.getType()==MessageType.LoginRequest) {
					if (receivedMessage.getStatus()==MessageStatus.Success) {
						System.out.println("Login Success!");
						clientVerified = true;
						String[] commands = { 
							 	"Download File",
								"Upload File",
								"Logout",
							};
							int choice;
							FileGUI fileInterface;
							do {
								 choice = JOptionPane.showOptionDialog(null,
									 "Select an option:",
									 "Distributed File System", 
									 JOptionPane.YES_NO_CANCEL_OPTION, 
									 JOptionPane.QUESTION_MESSAGE, 
									 null, 
									 commands,
									 commands[commands.length - 1]);
								 switch (choice) {
								 	case 0: 
								 		fileInterface = new FileGUI();
								 		//FileMessage DLRequest = new FileMessage(someFile, MessageType.DownloadRequest);
								 		//objectOutputStream.writeObject(DLRequest);
								 		break;
								 	case 1:  
								 		fileInterface = new FileGUI();
								 		//FileMessage ULRequest = new FileMessage(someFile, MessageType.UploadRequest);
								 		//objectOutputStream.writeObject(DLRequest);
								 		break;
								 	case 2: 
								 		Message logoutMsg = new Message("Logout");
								 		objectOutputStream.writeObject(logoutMsg);
								 		System.out.println("Sent disconnect notification to Server.");
								 		break;
								 	default:  // do nothing
								 }
							} while (choice!=commands.length-1);
					} else {
						System.out.println("Login failed. Please input correct credentials");
						//Don't do anything, just prompt for credentials again
					}
				}
			}
		}
		catch (IOException e) {
			System.out.println("No server found. Exiting...");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
