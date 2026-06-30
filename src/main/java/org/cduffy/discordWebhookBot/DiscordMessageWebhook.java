package org.cduffy.discordWebhookBot;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class DiscordMessageWebhook implements IDiscordWebhook {
    protected String webhookUrl;

    protected static HttpClient client = HttpClient.newHttpClient();

    public DiscordMessageWebhook(String webhookUrl)
    {
        this.webhookUrl = webhookUrl;
    }

    public void SetWebhookUrl(String url)
    {
        webhookUrl = url;
    }


    public String buildPayload(String name, String message)
    {
        String jsonPayload = """
            {
                "content": "%s: %s",
                "username": "Minecraft Server"
            }
            """;

        jsonPayload = String.format(jsonPayload, name, message);
        return  jsonPayload;
    }


    public void SendWebhook(String name, String message)
    {
        if(Objects.equals(name, "") && Objects.equals(message, ""))
        {
            return;
        }

        String jsonPayload = buildPayload(name, message);

        // 3. Build and send the HTTP POST request
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.webhookUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Discord returns a 204 No Content status code on success
            if (response.statusCode() == 204) {
                System.out.println("Success! Message sent to Discord.");
            } else {
                System.out.println("Failed to send. HTTP Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
