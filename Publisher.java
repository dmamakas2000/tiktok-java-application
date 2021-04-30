import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.sax.BodyContentHandler;

public class Publisher extends Node{
	
	private ChannelName channelname;
	
	// --------------------------- Getters ---------------------------
	public ChannelName getChannelname() {
		return channelname;
	}

	// --------------------------- Setters ---------------------------
	public void setChannelname(ChannelName channelname) {
		this.channelname = channelname;
	}
	
	// --------------------------- Methods ---------------------------
	public void addHashTag(String hashtag) {
		// TODO Auto-generated method stub
		
	}

	public void removeHashTag(String hashtag) {
		// TODO Auto-generated method stub
		
	}

	public void getBrokerList() {
		// TODO Auto-generated method stub
		
	}

	public Broker hashTopic(String hashtag) {
		
		String hashtext = null; // this is going to be the hashed text
		
		try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
  
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(hashtag.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            hashtext = no.toString(16);
  
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
  
            // return the HashText
        }
  
        // For specifying wrong message digest algorithms
        catch (Exception e) {
            System.err.println("Could not hash text!");
        }
				
		long sum = 0, mul = 1;
		for (int i = 0; i < hashtext.length(); i++) {
		    mul = (i % 4 == 0) ? 1 : mul * 256;
		    sum += hashtext.charAt(i) * mul;
		}
		
		System.out.println("Finally, text goes on broker n: " + (int)(Math.abs(sum) % 3));
		
		return null;
	}

	public void push(String video, Value val) {
		// TODO Auto-generated method stub
		
	}

	public void notifyFailure(Broker b) {
		// TODO Auto-generated method stub
		
	}

	public void notifyBrokersForHashTags(String hashtag) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Value> generateChunks(String video) {
	
		ArrayList<Value> chunks = new ArrayList<Value>();
		
		
		
		// Step 1: Convert video file into a byte array
		
		File file = new File(video);
		int size = (int) file.length();
		byte[] bytes = new byte[size]; // byte array
		
		
		
		try {
			BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
			buf.read(bytes, 0, bytes.length);
			buf.close();
			
			
			// Step 2: Create chunks
			
			// Each chunk is going to contain 12 KB of data
			
			// Detecting the file type
		    BodyContentHandler handler = new BodyContentHandler();
		    Metadata metadata = new Metadata();
		    FileInputStream inputstream = new FileInputStream(new File(video));
		    ParseContext pcontext = new ParseContext();
		      
		    // Parser 
		    MP4Parser MP4Parser = new MP4Parser();
		    MP4Parser.parse(inputstream, handler, metadata, pcontext);
		     
		    
		    // File size in bytes  
			int file_size = bytes.length;
			
			// Whole chunks required
			int numberOfChunks = file_size/12288;
			int remainingData = (file_size-(numberOfChunks*12288)); // Remaining bytes = 1 extra chunk
			
			if (remainingData == 0) {
				
				// Case 1 - Bytes fit perfectly
				
				for (int i=0; i< numberOfChunks; i++) {
					
					String videoName = i + "_" + video;
					String channelname = this.getChannelname().getChannelName();
					String dateCreated = metadata.get("creation_date");
					String length =  metadata.get("image_length");
					String frameRate = "";
					String frameWidth = metadata.get("image_width");
					String frameHeight = metadata.get("image_height");
					ArrayList<String> associatedHashtags = this.getChannelname().getHashtagsPublished();
					
					byte[] videoFileChunk = new byte[12288];
					
					// initializing videoFileChunk array 
					int index = 0;
					for (int j=i*12288; j<(i+1)*12288; j++) {
						videoFileChunk[index] = bytes[j];
						index++;
					}
					
					VideoFile v = new VideoFile(videoName, channelname, dateCreated, length, frameRate, frameWidth, frameHeight, associatedHashtags, videoFileChunk);
					chunks.add(new Value(v));
				}
				
			} else {
				
				// Case 2 - Bytes don't fit perfectly
				
				for (int i=0; i< numberOfChunks+1; i++) {
					
					String videoName = i + "_" + video;
					String channelname = this.getChannelname().getChannelName();
					String dateCreated = metadata.get("creation_date");
					String length =  metadata.get("image_length");
					String frameRate = "";
					String frameWidth = metadata.get("image_width");
					String frameHeight = "";
					ArrayList<String> associatedHashtags = this.getChannelname().getHashtagsPublished();
					
					byte[] videoFileChunk = new byte[12288];
					
					if (i == numberOfChunks) {
						// Last chunk remaining
						
						// initializing videoFileChunk array
						int index = 0;
						for (int j=i*12288; j<bytes.length; j++) {
							videoFileChunk[index] = bytes[j];
							index++;
						}
						
					} else {
						
						// initializing videoFileChunk array
						int index = 0;
						for (int j=i*12288; j<(i+1)*12288; j++) {
							videoFileChunk[index] = bytes[j];
							index++;
						}
					}
					VideoFile v = new VideoFile(videoName, channelname, dateCreated, length, frameRate, frameWidth, frameHeight, associatedHashtags, videoFileChunk);
					chunks.add(new Value(v));
				}
				
			}
			
		} catch (Exception e) {
			System.err.println("Couldn't convert video into chunks ");
		}
		
		return chunks;
	}

	

}
