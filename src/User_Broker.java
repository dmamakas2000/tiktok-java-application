import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class User_Broker extends User_Node implements Broker {

	// attributes 
	 PrintWriter outBroker;    // Broker's output stream
     BufferedReader inBroker;  // Broker's input stream
	
	// superclass constructor
	public User_Broker(int new_nodeNumber) {
		super(new_nodeNumber);
		this.inBroker = null;
		this.outBroker = null;
	}

	@Override
	public void calculateKeys() {
		
		
	}

	@Override
	public Consumer acceptConnection() {
		
		ServerSocket server = null;
		  
        try {
  
            // server is listening on port 1234
            server = new ServerSocket(4796);
            server.setReuseAddress(true);
  
            // running infinite loop for getting
            // client request
            System.out.println("Waiting for clients");
            while (true) {
  
                // socket object to receive incoming client
                // requests
                Socket client = server.accept();
                
                this.outBroker = new PrintWriter(client.getOutputStream(), true);
      
                this.inBroker= new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                System.out.println("--------------------------------------------------------------------");
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());
                int id = inBroker.read();
                System.out.println("Client id: " + id);
                
                
                // create a new thread object
                Thread t = new Thread();
                t.start();
  
                // This thread will handle the client separately
                System.out.println("Thread id for client " + id + " is: " + t.getId());
                System.out.println("--------------------------------------------------------------------\n");
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
		return null;
		
	}
	
	public static void main(String[] args) {
		User_Broker b = new User_Broker(5);
		b.acceptConnection();
	}

}
