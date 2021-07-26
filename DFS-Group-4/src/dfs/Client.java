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
		try (Socket socket = new Socket("localhost", 1234)) {	
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());		// File not object
			LoginMessage loginMessage = new LoginMessage("peter", "abcd1234");
			objectOutputStream.writeObject(loginMessage);
			MessageListener test = new MessageListener(socket);
			Thread testThread = new Thread(test); //Open up a seperate thread for intercepting and acting upon messages
			testThread.start();
			int choice;
			FileGUI fileInterface;
			String[] commands = { 
				 	"Download File",
					"Upload File",
					"Logout",
				};
			Message testOutMessage = new Message();
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
				 		//fileInterface = new FileGUI();
				 		//FileMessage DLRequest = new FileMessage(someFile, MessageType.DownloadRequest);
				 		//objectOutputStream.writeObject(DLRequest);
				 		break;
				 	case 1:  
				 		//fileInterface = new FileGUI();
				 		File testFile = new File("test.txt");
				 		testOutMessage = new FileMessage(testFile, MessageType.UploadRequest);
				 		testOutMessage.setText("Has file attached");
				 		objectOutputStream.writeObject(testOutMessage);
				 		break;
				 	case 2: 
				 		Message logoutMsg = new Message("Logout");
				 		objectOutputStream.writeObject(logoutMsg);
				 		System.out.println("Sent disconnect notification to Server.");
				 		break;
				 	default:  // do nothing
				}
				System.out.println(java.time.LocalTime.now());
				System.out.println("Outgoing Message status: " + testOutMessage.getStatus());
				System.out.println("Outgoing Message text: " + testOutMessage.getText());
				System.out.println("Outgoing Message type: " + testOutMessage.getType());
				System.out.println("Outgoing message sent to Server");
				System.out.println("----------------------------------------------");
			} while (choice!=commands.length-1);
		} catch (IOException e) {
			System.out.println("No server found. Exiting...");
		}
			//e.printStackTrace();
			// establish a connection by providing host and port
			// number
			//LoginHandler login = loginInterface.loginStatus();
			// if (login == true) do the client and server thingies
	}
	private static class MessageListener implements Runnable {
		private Socket socket;
		public MessageListener (Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			try {
				System.out.println("Client: " + socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());		// File not object
			Message receivedMessage;
			boolean clientVerified = false;
			System.out.println("Started listening for messages...");
			System.out.println("----------------------------------------------");
			while (true) {
				receivedMessage = (Message) objectInputStream.readObject();
				System.out.println("----------------------------------------------");
				System.out.println(java.time.LocalTime.now());
				System.out.println("Incoming Message received.");
				System.out.println("Incoming Message status: " + receivedMessage.getStatus());
				System.out.println("Incoming Message text: " + receivedMessage.getText());
				if (clientVerified == false) {
					if (receivedMessage.getType()==MessageType.LoginRequest && receivedMessage.getStatus()==MessageStatus.Success) {
						System.out.println("Login Success!");
						clientVerified = true;
						
							
					} else {
						System.out.println("Login failed. Please input correct credentials");
						//Don't do anything, just prompt for credentials again
					}
				} else {
					if (receivedMessage.getType()==MessageType.UploadRequest && receivedMessage.getStatus()==MessageStatus.Sent) {
						receivedMessage = (FileMessage) receivedMessage;
						System.out.println("Saving file to system");
						//File file = new File(((FileMessage) receivedMessage).getFile().get);
					}
				}	
				System.out.println("----------------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}


