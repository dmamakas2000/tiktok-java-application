import java.util.List;

public interface Node {
	
	// attributes
	public List<Broker> brokers = null;
	
	// methods
	public void init(int nodeNumber);
	
	public List<Broker> getBrokers();
	
	public void connect();
	
	public void disconnect();
	
	public void updateNodes();
	
}
