package util;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Slack {
    private static HttpClient client = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void  sendMessage(JSONObject content) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder(URI.create(URL))
    .header("accept","application/json")
    .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
    .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.printf(String.format("Status: %s", response.statusCode()));
        System.out.printf(String.format("Response: %s", response.body()));
    }
}
