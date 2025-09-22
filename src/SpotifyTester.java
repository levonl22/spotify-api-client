import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class SpotifyTester
{
	public static void main(String[] args) throws Exception{
		
		
		//BQDZEZJW1_0izN96vu_YM1Z3oxlKVYnk0PicUU-aZm6zTanx4WViTmh2pu4Jbtf3SlyJs4umwTEnb_g1pyhTamX5aewNWX3jqbFOOGbLJebVM_COI0q2oguq2WoL22FxEuDStMeWg3M

		/*List<Song> results = spotifyClient.searchTracks("Help herself", 1);
		for (Song s : results) {
		    System.out.println(s);
		}
		
		System.out.println("\n");
		
		List<Artist> artists = spotifyClient.searchArtist("Chungha", 1);
		for (Artist a : artists) {
			System.out.println(a);
		}
		*/
		SpotifyAPIClient spotifyClient = new SpotifyAPIClient("BQB4v6SP_irZ6SfmSzuUt4n8FKn4YRBbNe_9_BjBklTaqXzCVJ1bcmiWf4C0pYyDkdyu4sjyNWUIjyG2JuZKuTtP6hiXVIlX_2FKQ22N44w0ZUxMMaDkRh_wU8_cv1yq7VMBHWaY_D8");
		
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
