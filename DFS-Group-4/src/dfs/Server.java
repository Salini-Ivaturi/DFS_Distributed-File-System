package dfs;

import java.io.*;
import java.net.*;


// Server class
class Server {
	private AccountManager accountManager = new AccountManager();
	private static Socket[] node = new Socket[10];
	private static int numSockets = 0;
	
	private static ObjectOutputStream[] outStream = new ObjectOutputStream[10];
	public static void main(String[] args)
	{
		ServerSocket server = null;

		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			System.out.println("Started listening for messages...");
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected: " + client.getInetAddress().getHostAddress() + ":" + client.getPort());
				System.out.println("----------------------------------------------");
				// create a new thread object
				ClientHandler clientSock = new ClientHandler(client);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ClientHandler class
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private int thisSocket;
		

		// Constructor
		public ClientHandler(Socket socket) throws IOException
		{
			this.clientSocket = socket;
			thisSocket = numSockets;
			node[thisSocket] = socket;
			outStream[thisSocket] = new ObjectOutputStream(clientSocket.getOutputStream());
			numSockets++;
		}

		public void run()
		{
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
				boolean clientVerified = false;
				while (true) {
					Message receivedMessage;
					try {
						receivedMessage = (Message) objectInputStream.readObject();
						System.out.println("This socket: " + thisSocket);
						System.out.println("----------------------------------------------");
						System.out.println(java.time.LocalTime.now());
						System.out.println("Incoming Message received from client " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
						System.out.println("Incoming Message status: " + receivedMessage.getStatus());
						System.out.println("Incoming Message text: " + receivedMessage.getText());
						System.out.println("Incoming Message type: " + receivedMessage.getType());
						System.out.println("");
						
						if (receivedMessage.getType()==MessageType.LoginRequest) {
							receivedMessage = (LoginMessage) receivedMessage;
							//code here is placeholder. Should actually be checking
							//with AccountManager before sending back a Success. Ex:
							//accountManager.checkCredentials(receivedMessage.getUsername(), receivedMessage.getPassword());
							receivedMessage.setText("Credentials verified.");
							receivedMessage.setStatus(MessageStatus.Success); //Send the message back to the client with status "Success"
							clientVerified = true;
							
						} else if (receivedMessage.getType()==MessageType.Communication && receivedMessage.getText().equals("Logout")) {
							System.out.println(clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + " disconnected. Closing thread...");
							break;
						} else {
							if (clientVerified==true) {
								if (receivedMessage.getType()==MessageType.DownloadRequest) {
									receivedMessage = (FileMessage) receivedMessage;
									//figure out which node contains the file needed (FileManager), and then
									//have that node send the file to either us or send it directly
									//to the requesting node
								} else if (receivedMessage.getType()==MessageType.UploadRequest) {
									receivedMessage = (FileMessage) receivedMessage;
									//for now, just check that this actually gets sent
									outStream[0].writeObject(receivedMessage);
									receivedMessage.setStatus(MessageStatus.Success);
									receivedMessage.setText("File sent to " + node[0].getInetAddress().getHostAddress() + ":" +  node[0].getPort());
									System.out.println("Sent uploaded file to " + node[0].getInetAddress().getHostAddress() + ":" + node[0].getPort());
									//pick a node and then send the file to that node
								}
							} else {
								receivedMessage.setText("Client not logged in/authorized for this/these action(s).");
								receivedMessage.setStatus(MessageStatus.Fail); //Send the message back to the client with status "Success"
							}
						}
						System.out.println("\nSending reply to IP " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
						System.out.println("Outgoing Message status: " + receivedMessage.getStatus());
						System.out.println("Outgoing Message text: " + receivedMessage.getText());
						System.out.println("Outgoing Message type: " + receivedMessage.getType());
						System.out.println("----------------------------------------------");
						outStream[thisSocket].writeObject(receivedMessage);
						outStream[thisSocket].flush();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}