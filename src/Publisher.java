import java.util.ArrayList;

public interface Publisher extends Node {
	
	public ChannelName channelName = null;
	
	public void addHashTag(String hashtag);
	
	public void removeHashTag(String hashtag);
	
	public void getBrokerList();
	
	public Broker hashTopic(String hashtag);
	
	public void push(String video, Value val);
	
	public void notifyFailure(Broker b);
	
	public void notifyBrokersForHashTags(String hashtag);
	
	public ArrayList<Value> generateChunks(String video);

}
