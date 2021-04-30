import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Node {
	
	private Socket socket;        // Node's socket
	private String ip;		      // Node's IP address
	private int port;             // Node's port
	private int nodeId;
	private PrintWriter out;      // Node's output stream
	private BufferedReader in;    // Node's input stream

	// -------------------------- Getter --------------------------
	
	public String getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public PrintWriter getOut() {
		return out;
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
	public int getNodeId() {
		return this.nodeId;
	}
	
	// -------------------------- Setters --------------------------
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
	public void setNodeId(int newId) {
		this.nodeId = newId;
	}
	
	// -------------------------- Node --------------------------
	
	public void init(int nodeNumber) {
		this.setNodeId(nodeNumber);
		
		System.out.println("Please, provide an IP addres for node " + this.getNodeId() + " :");
	}

	public void connect() {
		
        try {
            
        	// Establish a socket connection by providing host and port number
        	this.socket = new Socket(this.getIp(), this.getPort());
        	
            // Writing to server
            this.setOut(new PrintWriter(socket.getOutputStream(), true));
  
            // Reading from server
            this.setIn(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                       
        }
        catch (IOException e) {
            e.printStackTrace();
        }
			
	}
	
	public void disconnect() {
		
		try {
			
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateNodes() {
		// TODO Auto-generated method stub	
	}

}
