import java.util.List;

public class SpotifyTester
{
	public static void main(String[] args) throws Exception{
		/*
		Playlist playlist1 = new Playlist("My Favorite Songs");
		Artist artist1 = new Artist("Cocomelon");
		
		
		Song song1 = new Song("ABC", artist1, 61);
		
		System.out.println(song1.getSongName());
		System.out.println(playlist1.getPlaylistName());
		
		playlist1.addSong(song1);
		playlist1.addSong(new Song ("Twinkle Twinkle", artist1, 125));
		
		System.out.println(playlist1);
		
		playlist1.playPlaylist();
		System.out.println(playlist1.getSongs());
		*/
		SpotifyAPIClient spotifyClient = new SpotifyAPIClient("BQDq0xeIJfE5I65MA1Q2vKNYxIVNsUayXyGSPmG80hAVfBwAZ3VSFjsyxIZgpI1wwdgIu12MVaEE9hV-tk3ocKLOFh6fN7iaSxi4HrrqxzhyGzIEI0qhuEMiAVUNsSZJPZ14GkI5HjM");
		//BQDq0xeIJfE5I65MA1Q2vKNYxIVNsUayXyGSPmG80hAVfBwAZ3VSFjsyxIZgpI1wwdgIu12MVaEE9hV-tk3ocKLOFh6fN7iaSxi4HrrqxzhyGzIEI0qhuEMiAVUNsSZJPZ14GkI5HjM

		List<Song> results = spotifyClient.searchTracks("Help herself", 5);
		for (Song s : results) {
		    System.out.println(s);
		}
		
		System.out.println("\n");
		
		List<Artist> artists = spotifyClient.searchArtist("Chungha", 5);
		for (Artist a : artists) {
			System.out.println(a);
		}
	}
	
}
