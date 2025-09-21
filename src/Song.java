
public class Song
{
	private String name;
	private int duration; //in ms
	private Artist artist;
	//private String songGenre;
	
	
	public Song(String name, Artist artist, int duration) {
		this.name = name;
		this.artist = artist;
		this.duration = duration;
	}
	
	public Song(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getDuration() {
		int minutes = duration / 60000;
		int seconds = (duration % 60000) / 1000;
		return String.format(" (%d:%02d)", minutes, seconds);
	}

	public void play() {
		System.out.println("Playing: " + name);
	}

	
	@Override
	public String toString() {
		return name + " by " + artist.getName() + getDuration();
	}
}
