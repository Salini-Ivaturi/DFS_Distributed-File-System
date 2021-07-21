/*
 * Martin Le
 * 
 * Data Node
 * accesses file storage
 * pulls files FileSystem locations
 * 
 */

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class DataNode {
	
	//IP address of Data Node on server
	private String nodeIP = "127.0.0.1"; //default for demo set to localhost
	
	//Saved Start Time of Data Node
	private Date nodeStartTime;
	
	//Total number of Data Nodes instantiated
	public static int sizeOfCluster;
	
	//The incremental number of the instantiated Data Node, counted number
	public final Integer dataNodeInstanceNum;
	
	private static final AtomicInteger idGenerator = new AtomicInteger(1);
	
	//Default Constructor
	public DataNode() {
		this.setNodeIP(null);
		this.nodeStartTime = new Date();
		dataNodeInstanceNum = idGenerator.getAndIncrement();
		sizeOfCluster++;
	}

	/**
	 * @return the nodeIP
	 */
	public String getNodeIP() {
		return nodeIP;
	}

	/**
	 * @param nodeIP the nodeIP to set
	 */
	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}
	
}
