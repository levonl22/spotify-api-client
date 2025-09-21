import java.net.http.*;
import java.net.URI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import java.util.ArrayList;
import java.net.URLEncoder;

public class SpotifyAPIClient2
{
	private String accessToken;
	
	public SpotifyAPIClient2(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public List<Artist> searchArtist(String query, int limit) throws Exception {
		List<Artist> artistList = new ArrayList<>();
		
		String artistQuery = URLEncoder.encode(query, "UTF-8");
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.spotify.com/v1/search?q=" +
							artistQuery + "&type=artists&limit=" + limit))
				.header("Authorization", "Bearer " + accessToken)
				.GET().build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		JsonObject obj = JsonParser.parseString(response.body()).getAsJsonObject();
		JsonArray items = obj.getAsJsonObject("artists").getAsJsonArray("items");
		
		for (int i = 0; i < items.size(); i++) {
			JsonObject jsonArtist = items.get(i).getAsJsonObject();
			String artistName = jsonArtist.get("name").getAsString();
			//maybe add genres here
			
			Artist artist = new Artist(artistName);
			artistList.add(artist);
		}
		return artistList;
		
	}
	
	public List<Song> searchTracks(String query, int limit) throws Exception {
		List<Song> songList = new ArrayList<>();
		
		String songQuery = URLEncoder.encode(query, "UTF-8");
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.spotify.com/v1/search?q=" +
						songQuery + "&type=track&limit=" + limit))
				.header("Authorization", "Bearer " + accessToken)
				.GET()
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		JsonObject obj = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray items = obj.getAsJsonObject("tracks").getAsJsonArray("items");
        
		for (int i = 0; i < items.size(); i++) {
			JsonObject track = items.get(i).getAsJsonObject();
			String trackName = track.get("name").getAsString();
			String artistName = track.getAsJsonArray("artists")
					.get(0).getAsJsonObject().get("name").getAsString();
			int duration = track.get("duration_ms").getAsInt();
			
			Song song = new Song(trackName, new Artist(artistName), duration);
			songList.add(song);
		}
		return songList;
	}
	
	
}
