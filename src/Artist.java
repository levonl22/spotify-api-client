import java.util.List;

public class Artist
{
	private String name;
	private List<Song> songs;
	//private ArrayList<Album> artistAlbums;
	
	public Artist(String name, List<Song> songs) {
		this.name = name;
		this.songs = songs;
	}
	
	public Artist(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setArtistName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
