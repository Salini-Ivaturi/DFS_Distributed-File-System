package dfs;

public class LoginMessage extends Message {
	private String username;
	private String password;

	public LoginMessage(String username, String password) {
		super();
		this.type = MessageType.LoginRequest;
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}



}
