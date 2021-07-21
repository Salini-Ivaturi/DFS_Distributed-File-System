package dfs;

import java.io.*;
import java.net.*;

// Client class
class Client {
	
	// driver code
	public static void main(String[] args)
	{
		// establish a connection by providing host and port
		// number
		try (Socket socket = new Socket("localhost", 1234)) {			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			Message testMessage = new Message();
			objectOutputStream.writeObject(testMessage);
			Message receivedMessage;
			while (true) {
				receivedMessage = (Message) objectInputStream.readObject();
				System.out.println("Message received.");
				System.out.println("Message status: " + receivedMessage.getStatus());
				System.out.println("Message text: " + receivedMessage.getText());
				if (receivedMessage.getType()==MessageType.LoginRequest) {
					if (receivedMessage.getStatus()==MessageStatus.Success) {
						System.out.println("Login Success!");
						//Open up the client GUI with options for Upload, Download, and Logout here
					} else {
						System.out.println("Login failed. Please input correct credentials");
						//Don't do anything, just prompt for credentials again
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
