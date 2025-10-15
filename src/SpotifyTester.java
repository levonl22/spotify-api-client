import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class SpotifyTester
{
	public static void main(String[] args) throws Exception{
		
		JsonObject storedJson = JsonParser.parseReader(new FileReader("token.json")).getAsJsonObject();
		String token = storedJson.get("access_token").getAsString();
		long expiresAt = storedJson.get("expires_at").getAsLong();
		
		Instant instant = Instant.ofEpochMilli(expiresAt);
		LocalDateTime expireTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
				
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Spotify Search Client by Levon Lau");
		if (System.currentTimeMillis() < expiresAt) {
			System.out.print("Your token is valid until " + expireTime);
		}
		else {
			System.out.print("It seems like your token has expired. Please press 'Enter' to request a new one.");
			if (input.nextLine().equals("")) {
				token = SpotifyClientCredentials.getNewAccessToken();
			}
			
		}
		SpotifyAPIClient spotifyClient = new SpotifyAPIClient(token);
		
		
		List<Song> songsResults = new ArrayList<>();
		List<Artist> artistsResults = new ArrayList<>();
		
		String userInput;
		do {
			System.out.println("\nEnter '1' to search for songs by title, '2' for Artists by name, or '0' to exit: ");
			userInput = input.nextLine();
			
			if (userInput.equals("1")) {
				System.out.println("Enter song title: ");
				songsResults = spotifyClient.searchTracks(input.nextLine(), 10);
				System.out.println("\nHere are your results:");
				for (int i = 0; i < songsResults.size(); i++) {
					System.out.print((i + 1) + ". " + songsResults.get(i) + "\n");
				}
			}
			
			else if (userInput.equals("2")) {
				System.out.println("Enter artist name: ");
				artistsResults = spotifyClient.searchArtists(input.nextLine(), 10);
				for (int i = 0; i < artistsResults.size(); i++)
					System.out.print((i + 1) + ". " + artistsResults.get(i) + "\n");
			}
			
		} while (!userInput.equals("0"));
		
		input.close();
		System.out.println("Thank you for using the Spotify Search Client!");
		
	}
	
}
