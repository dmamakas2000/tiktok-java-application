public interface Consumer extends Node {
	
	public void register(Broker broker, String user);
	
	public void disconnect(Broker broker, String user);
	
	public void playData(String video, Value value);

}
