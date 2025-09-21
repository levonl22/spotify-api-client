import java.util.ArrayList;

public class Playlist
{
	private String playlistName;
	private ArrayList<Song> playlistSongs;
	//private int playlistNumSongs;
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		this.playlistSongs = new ArrayList<>();
	}
	
	
	public String getPlaylistName() {
		return playlistName;
	}
	
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	
	
	public void addSong(Song songName) {
		playlistSongs.add(songName);
	}
	
	public void removeSong(Song songName) {
		playlistSongs.remove(songName);
	}
	
	public void playPlaylist() {
		for (Song song : playlistSongs) {
			song.play();
		}
	}
	
	public ArrayList<Song> getSongs() {
		return playlistSongs;
	}
	
	@Override
	public String toString() {
		StringBuilder sb  = new StringBuilder("Playlist: " + playlistName + "\nSongs:\n");
		
		int number = 1;
		for (Song song : playlistSongs) {
			String str = Integer.toString(number);
			sb.append(" ").append(str).append(". ").append(song).append("\n");
			number++;
			
		}
		return sb.toString();
	}
}
