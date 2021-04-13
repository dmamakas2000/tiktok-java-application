import java.util.List;

public interface Broker extends Node {

	public List<Consumer> registeredUsers = null;
	public List<Publisher> registeredPublishers = null;
}
