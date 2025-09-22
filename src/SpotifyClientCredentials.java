import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Base64;

public class SpotifyClientCredentials {

    /*public static void main(String[] args) throws Exception {
        String clientId = "***REMOVED***";
        String clientSecret = "***REMOVED***";

        // Encode client_id:client_secret in Base64
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        String accessToken = json.get("access_token").getAsString();
        int expiresIn = json.get("expires_in").getAsInt();

        System.out.println("Access Token: " + accessToken);
        System.out.println("Expires in: " + expiresIn + " seconds");
    }
    */
    public static String getAccessToken() throws Exception{
    	String clientId = "***REMOVED***";
        String clientSecret = "***REMOVED***";

        // Encode client_id:client_secret in Base64
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        String accessToken = json.get("access_token").getAsString();
        int expiresIn = json.get("expires_in").getAsInt();

        System.out.println("Access Token: " + accessToken);
        System.out.println("Expires in: " + expiresIn + " seconds");
        
        return accessToken;
    }
}
