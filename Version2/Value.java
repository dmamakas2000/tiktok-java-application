public class Value {
	/* -------------- This class represents a general Value-------------- */
	
	/* Attributes */
	private VideoFile videoFile;

	
	// Constructor
	public Value (VideoFile videofile) {
		this.videoFile = videofile;
	}
	
	// Default constructor
	public Value () {
		this.videoFile = null;
	}
	
	
	
	// Getter 
	
	public VideoFile getVideoFile() {
		return videoFile;
	}

	// Setter 
	
	public void setVideoFile(VideoFile videoFile) {
		this.videoFile = videoFile;
	}

}
