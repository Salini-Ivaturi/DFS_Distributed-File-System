package dfs;
import java.io.Serializable;

public class Message implements Serializable {
	protected MessageType type; //This should not be changed
    protected MessageStatus status;
    
    public Message() {
    	this.status = MessageStatus.Sent;
    }
    
    
    public MessageStatus getStatus() {
    	return status;
    }
    
    public void setStatus(MessageStatus status) {
    	this.status = status;
    }
    
    public MessageType getType() {
    	return type;
    }
}
