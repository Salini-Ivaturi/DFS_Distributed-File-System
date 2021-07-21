package dfs;

import java.io.*;
import java.net.*;

// Server class
class Server {
	public static void main(String[] args)
	{
		ServerSocket server = null;

		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected: "
								+ client.getInetAddress()
										.getHostAddress());

				// create a new thread object
				ClientHandler clientSock
					= new ClientHandler(client);

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

		// Constructor
		public ClientHandler(Socket socket)
		{
			this.clientSocket = socket;
		}

		public void run()
		{
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				boolean clientVerified = false;
				while (true) {
					Message receivedMessage;
					try {
						receivedMessage = (Message) objectInputStream.readObject();
						System.out.println("Message received from IP " + clientSocket.getInetAddress());
						System.out.println("Message status: " + receivedMessage.getStatus());
						System.out.println("Message text: " + receivedMessage.getText());
						System.out.println("Sending reply to IP " + clientSocket.getInetAddress());
						if (receivedMessage.getType()==MessageType.LoginRequest) {
							//code here is placeholder. Should actually be checking
							//with AccountManager before sending back a Success
							receivedMessage.setText("Credentials verified.");
							receivedMessage.setStatus(MessageStatus.Success); //Send the message back to the client with status "Success"
							clientVerified = true;
						} else {
							if (clientVerified==true) {
								if (receivedMessage.getType()==MessageType.DownloadRequest) {
									//figure out which node contains the file needed (FileManager), and then
									//have that node send the file to either us or send it directly
									//to the requesting node
								} else if (receivedMessage.getType()==MessageType.UploadRequest) {
									//pick a node and then send the file to that node
								}
							} else {
								receivedMessage.setText("Client not logged in/authorized for this/these action(s).");
								receivedMessage.setStatus(MessageStatus.Fail); //Send the message back to the client with status "Success"
							}
						}
						objectOutputStream.writeObject(receivedMessage);
						objectOutputStream.flush();
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