import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Base64;

public class SpotifyClientCredentials {

    public static String getNewAccessToken() throws Exception{
    	String clientId = System.getenv("SPOTIFY_CLIENT_ID");
        String clientSecret = System.getenv("SPOTIFY_CLIENT_SECRET");

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

        //System.out.println("Response: " + response.body());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        String accessToken = json.get("access_token").getAsString();
        int expiresIn = json.get("expires_in").getAsInt();
        
        long expiresAt = System.currentTimeMillis() + (expiresIn * 1000);
        json.addProperty("expires_at", expiresAt);
        
        try (FileWriter writer = new FileWriter("token.json")) {
        	writer.write(json.toString());
        }
        
        Instant instant = Instant.ofEpochMilli(expiresAt);
		LocalDateTime expireTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        System.out.println("Access Token: " + accessToken);
        System.out.println("Your token is valid until " + expireTime);
        
        return accessToken;
    }
}
