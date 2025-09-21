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
		SpotifyAPIClient spotifyClient = new SpotifyAPIClient("BQBoNuXKPurcnls978R2HBG5vouStr5cjwJfeRJ-mldO34A3fRJwbMLI9iZFh_dGpGbwX0ZrLU6qETZF3EbE7Vu4vDInkFn3QeBUivYJ883jd4qPrUZ1Q5JwX7GXFfcY1SLlNNhPLbo");
		//BQBoNuXKPurcnls978R2HBG5vouStr5cjwJfeRJ-mldO34A3fRJwbMLI9iZFh_dGpGbwX0ZrLU6qETZF3EbE7Vu4vDInkFn3QeBUivYJ883jd4qPrUZ1Q5JwX7GXFfcY1SLlNNhPLbo
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
