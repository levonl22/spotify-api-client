import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import util.HttpUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
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
	
	
	public List<Artist> searchArtists(String query, int limit) throws Exception {
		String url = buildSearchUrl(query, "artist", limit);
		String json = HttpUtils.get(url, accessToken);
		//System.out.println("RAW JSON response: \n" + json);
		
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
		JsonObject artistsObj = obj.getAsJsonObject("artists");
		
		if (artistsObj == null) {
			System.out.println("No 'artists' found for query: " + query);
			return Collections.emptyList();
		}
		
		JsonArray items = artistsObj.getAsJsonArray("items");
		if (items == null || items.size() == 0) {
			System.out.println("No artist items found for query: " + query);
			return Collections.emptyList();
		}
			
		List<Artist> artistList = new ArrayList<>();
		for (JsonElement element : items) {
			JsonObject a = element.getAsJsonObject();
			String name = a.get("name").getAsString();
			
			artistList.add(new Artist(name));
		}
		return artistList;
		
	}
	
	public List<Song> searchTracks(String query, int limit) throws Exception {
		String url = buildSearchUrl(query, "track", limit);
		String json = HttpUtils.get(url, accessToken);
		//System.out.println("RAW JSON response: \n" + json);
		
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
		JsonObject tracksObj = obj.getAsJsonObject("tracks");
		
		if (tracksObj == null) {
			System.out.println("No 'tracks' found for query: " + query);
			return Collections.emptyList();
		}
		
		JsonArray items = tracksObj.getAsJsonArray("items");
		if (items == null) {
			System.out.println("No track items found for query: " + query);
			return Collections.emptyList();
		}
        
		List<Song> songList = new ArrayList<>();
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
