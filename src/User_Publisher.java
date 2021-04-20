import java.util.ArrayList;

public class User_Publisher extends User_Node implements Publisher{

	public User_Publisher(int new_nodeNumber) {
		super(new_nodeNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addHashTag(String hashtag) {
		
		
	}

	@Override
	public void removeHashTag(String hashtag) {
		
		
	}

	@Override
	public void getBrokerList() {
		
		
	}

	@Override
	public Broker hashTopic(String hashtag) {
		
		return null;
	}

	@Override
	public void push(String video, Value val) {
		
		
	}

	@Override
	public void notifyFailure(Broker b) {
		
		
	}

	@Override
	public void notifyBrokersForHashTags(String hashtag) {
		
		
	}

	@Override
	public ArrayList<Value> generateChunks(String video) {
		
		return null;
	}

}
