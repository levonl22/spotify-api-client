import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class SpotifyTester
{
	public static void main(String[] args) throws Exception{
		
		SpotifyAPIClient spotifyClient = new SpotifyAPIClient("BQD3_ROKwdm_6JVOS93P-n8leVF2fwN79eWldqqNHoEN5FUE7m81cxxHiLDDlyIvSVeW-4clZgIISndY6L7I3zsPEDEJMPV_cxSHoTBz9gNzzUZO5nv0XOEsCKkgcqIRJ_AjCzZbYcU");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Spotify Search Client by Levon Lau");
		System.out.println("If you need an access token, or if your previous has expired, please enter 'token'. Otherwise, press 'Enter' to continue: ");
		if (input.nextLine().equals("token")) spotifyClient = new SpotifyAPIClient(SpotifyClientCredentials.getAccessToken());
		
		
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
