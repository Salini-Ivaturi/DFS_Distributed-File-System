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
			LoginMessage LoginMessage = new LoginMessage("peter", "abcd1234");
			objectOutputStream.writeObject(LoginMessage);
			Message receivedMessage;
			while (true) {
				receivedMessage = (Message) objectInputStream.readObject();
				if (receivedMessage.getType()==MessageType.LoginRequest) {
					if (receivedMessage.getStatus()==MessageStatus.Success) {
						System.out.println("Login Success!");
					} else {
						System.out.println("Login failed. Please input correct credentials");
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
