package dfs;
import java.io.Serializable;

public class Message implements Serializable {
	protected MessageType type; //This should not be changed
    protected MessageStatus status;
    protected String text;
    
    public Message() {
    	this.status = MessageStatus.Sent;
    	this.text = "";
    	this.type = MessageType.Communication;
    }
    
    public Message(String text) {
    	this.status = MessageStatus.Sent;
    	this.text = text;
    	this.type = MessageType.Communication;
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
    
    public String getText() {
    	return text;
    }
    
    public void setText(String text) {
    	this.text = text;
    }
}
