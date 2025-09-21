package util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {
	private static final HttpClient CLIENT = HttpClient.newHttpClient();
	
	public static String get(String url, String token) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Authorization", "Bearer " + token)
				.header("Accept", "application/json")
				.GET()
				.build();
		
		HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() != 200) {
			throw new IOException("HTTP GET failed: " + response.statusCode() + " for URL: " + url);
		}
		
		return response.body();
	}
	
}
