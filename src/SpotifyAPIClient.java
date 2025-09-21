import java.net.http.*;
import java.net.URI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import util.HttpUtils;

import java.util.List;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class SpotifyAPIClient
{
	private static final String BASE_URL = "https://api.spotify.com/v1/search";
	
	private final String accessToken;
	
	public SpotifyAPIClient(String accessToken) {
		this.accessToken = accessToken;
	}
	
	private String buildSearchUrl(String query, String type, int limit) {
		String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
		return BASE_URL + "?q=" + encodedQuery + "&type=" + type + "&limit=" + limit;
	}
	
	
	
	public List<Artist> searchArtist(String query, int limit) throws Exception {
		String url = buildSearchUrl(query, "artist", limit);
		String json = HttpUtils.get(url, accessToken);
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
		JsonArray items = obj.getAsJsonObject("artists").getAsJsonArray("items");
		
		List<Artist> artistList = new ArrayList<>();
		
		for (JsonElement element : items) {
			JsonObject a = element.getAsJsonObject();
			String title = a.get("name").getAsString();
			
			List<Song> songs = new ArrayList<>();
			for (JsonElement t : a.getAsJsonArray("tracks")) {
				songs.add(new Song(a.getAsString()));
			}
			
			artistList.add(new Artist(title, songs));
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
