import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Broker extends Node {

	// ---------------------------------------- Attributes ----------------------------------------
	private List<Broker> brokers;
	private List<Consumer> registeredUsers;
	private List<Publisher> registeredPublishers;
	
	
	private int brokerId;             		// Broker's id
	private ArrayList<String> brokersInfo;  // Info about other brokers
	private PrintWriter outBroker;    		// Broker's output stream
    private BufferedReader inBroker;  		// Broker's input stream
    private ServerSocket socketBroker;		// Broker's connection server-socket
    private Socket socketBrokerConnect;		// Broker's connection socket
    private String keys;                    // Broker's keys 
    private String ip;						// Broker's address
    private int port;             			// Broker's port
	
    private Queue<Value> brokerQueue = new LinkedList<>();   // Broker's queue used to forward chunks into consumers
    
    // ---------------------------------------- Constructor ----------------------------------------
    
    public Broker(int nodeNumber) {
    	this.setBrokerId(nodeNumber);
    	this.setBrokersInfo(new ArrayList<String>());
    	this.setInBroker(null);
    	this.setOutBroker(null);
    }
    
    // ---------------------------------------- Default constructor ----------------------------------------
    
    public Broker() {
    	this.setBrokerId(-100);
    	this.setBrokersInfo(new ArrayList<String>());
    	this.setInBroker(null);
    	this.setOutBroker(null);
    }
    
    // ---------------------------------------- Getters ----------------------------------------
    
    public int getBrokerId() {
		return brokerId;
	}

    public ArrayList<String> getBrokersInfo() {
		return brokersInfo;
	}
    
    public PrintWriter getOutBroker() {
		return outBroker;
	}
    
    public BufferedReader getInBroker() {
		return inBroker;
	}
    
    public ServerSocket getSocketBroker() {
    	return this.socketBroker;
    }
    
    public Socket getSocketBrokerConnection() {
    	return this.socketBrokerConnect;
    }
    
    public String getKeys() {
		return keys;
	}
    
    public String getIpBroker() {
		return ip;
	}
    
    public int getPortBroker() {
		return port;
	}
    
    public List<Consumer> getRegisteredUsers() {
		return registeredUsers;
	}
    
    public List<Publisher> getRegisteredPublishers() {
		return registeredPublishers;
	}
    
    public Queue<Value> getBrokerQueue() {
		return brokerQueue;
	}
    
    // ---------------------------------------- Setters ----------------------------------------
    
    public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}
	
	public void setBrokersInfo(ArrayList<String> brokersInfo) {
		this.brokersInfo = brokersInfo;
	}

	public void setOutBroker(PrintWriter outBroker) {
		this.outBroker = outBroker;
	}
	
	public void setInBroker(BufferedReader inBroker) {
		this.inBroker = inBroker;
	}

	public void setSocketBroker(ServerSocket s) {
		this.socketBroker = s;
	}
	
	public void setSocketBrokerConnection(Socket s) {
		this.socketBrokerConnect = s;
	}
	
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	public void setPortBroker(int port) {
		this.port = port;
	}

	public void setIpBroker(String ip) {
		this.ip = ip;
	}
	
	public void setRegisteredUsers(List<Consumer> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}
	
	public void setRegisteredPublishers(List<Publisher> registeredPublishers) {
		this.registeredPublishers = registeredPublishers;
	}
	
	public void setBrokerQueue(Queue<Value> brokerQueue) {
		this.brokerQueue = brokerQueue;
	}
	
	// ---------------------------------------- Methods to implement ----------------------------------------
	
	public void init(int nodeNumber) {
		this.setBrokerId(nodeNumber);
	}

	public List<Broker> getBrokers() {
		return brokers;
	}
	
	public void connect() {
		
        try {
            
        	// Establish a socket connection by providing host and port number
        	this.socketBrokerConnect = new Socket(this.getIpBroker(), this.getPortBroker());
        	
            // Output stream
             this.outBroker = new PrintWriter(socketBrokerConnect.getOutputStream(), true);
  
            // Input stream
             this.inBroker = new BufferedReader(new InputStreamReader(socketBrokerConnect.getInputStream()));
                       
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	
	}

	public void disconnect() {
		
		try {
			
			this.socketBroker.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateNodes() {
		// TODO Auto-generated method stub
		
	}

	public void calculateKeys() {
		
		String key = this.getIpBroker() + this.getPortBroker();
		String hashkey = "";
		
		// Let's hash this String into a SHA-1 function
		
		try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
  
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(key.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            hashkey = no.toString(16);
  
            // Add preceding 0s to make it 32 bit
            while (hashkey.length() < 32) {
            	hashkey = "0" + hashkey;
            }
  
            // Set broker's keys
            this.setKeys(hashkey);
        }
  
        // For specifying wrong message digest algorithms
        catch (Exception e) {
            System.err.println("Could not hash text!");
        }
		
	}

	public Publisher acceptConnection(Publisher publisher) {
		
        try {
  
            // Server is listening on specific port 
            this.socketBroker = new ServerSocket(this.getPortBroker());
            this.socketBroker.setReuseAddress(true);
  
            // Running infinite loop for getting client requests
            
            while (true) {
  
                // Socket object to receive incoming client requests
                Socket client = this.socketBroker.accept();
                
                System.out.println("Connected");
                
                this.outBroker = new PrintWriter(client.getOutputStream(), true);
      
                this.inBroker= new BufferedReader(new InputStreamReader(client.getInputStream()));
                      
                // Create a new thread object
                // This thread will handle the client separately
                
                Thread t = new Thread();
                t.start();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.socketBroker != null) {
                try {
                	this.socketBroker.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return null;
	}

	public Consumer acceptConnection(Consumer consumer) {
		// TODO Auto-generated method stub
		return null;
	}

	public void notifyPublisher(String key) {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyBrokersOnChanges(String key) {
		// TODO Auto-generated method stub
		
	}

	public void pull(String key) {
		// TODO Auto-generated method stub
		
	}

	public void filterConsumers(String key) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		
		
	}
	
}
