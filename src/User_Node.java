import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class User_Node implements Node{

	// attributes
	private int nodeNumber;
	private Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	public User_Node(int new_nodeNumber) {
		this.setNodeNumber(new_nodeNumber);
		this.socket = null;
		this.out = null;
		this.in = null;
	}
	
	@Override
	public void init(int new_nodeNumber) {
		this.setNodeNumber(new_nodeNumber);
		this.socket = null;
		this.out = null;
		this.in = null;
	}

	// getter
	public int getNodeNumber() {
		return nodeNumber;
	}

	// setter
	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	@Override
	public List<Broker> getBrokers() {
		return Node.brokers;
	}

	@Override
	public void connect() {
	
        try {
            
        	// establish a socket connection by providing host and port number
        	this.socket = new Socket("localhost", 4796);
        	
            // writing to server
             this.out = new PrintWriter(socket.getOutputStream(), true);
  
            // reading from server
             this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
            out.write(this.getNodeNumber());
            out.flush();
            
            
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
			
	}

	@Override
	public void disconnect() {
		try {
			this.socket.close();
			System.out.println("Connection from client " + this.getNodeNumber() + " closed!");
		} catch (Exception e) {
			System.out.println("Could not disconnect!");
		}
	}

	@Override
	public void updateNodes() {
		
		
	}
	
	// testing main method
	public static void main(String[] args) {
		User_Node d = new User_Node(4);
		d.connect();
		User_Node dd = new User_Node(8);
		dd.connect();
		User_Node ddd = new User_Node(12);
		ddd.connect();
		d.disconnect();
		dd.disconnect();
		ddd.disconnect();
	}

	

}
