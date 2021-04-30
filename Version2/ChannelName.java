import java.util.ArrayList;
import java.util.HashMap;

public class ChannelName {
	/* -------------- This class represents a Channel -------------- */
	
	/* Attributes */
	
	private String channelName;
	private ArrayList<String> hashtagsPublished;
	private  HashMap<String, ArrayList<Value>> userVideoFilesMap;
	
	// Constructor
	public ChannelName(String name, ArrayList<String> hashtags) {
		this.channelName = name;
		this.hashtagsPublished = hashtags;
	}
	
	// Default constructor 
	public ChannelName() {
		this.channelName = null;
		this.hashtagsPublished = null;
		this.userVideoFilesMap = null;
	}
	
	
	// getters
	
	public String getChannelName() {
		return channelName;
	}
	
	public ArrayList<String> getHashtagsPublished() {
		return hashtagsPublished;
	}
	
	public HashMap<String, ArrayList<Value>> getUserVideoFilesMap() {
		return userVideoFilesMap;
	}
	
	// setters 
	
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	
	public void setHashtagsPublished(ArrayList<String> hashtagsPublished) {
		this.hashtagsPublished = hashtagsPublished;
	}
	
	public void setUserVideoFilesMap(HashMap<String, ArrayList<Value>> userVideoFilesMap) {
		this.userVideoFilesMap = userVideoFilesMap;
	}
	
	

}
