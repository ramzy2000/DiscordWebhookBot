package org.cduffy.discordWebhookPlugin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class DiscordWebhook implements IDiscordWebhook {
    protected String webhookUrl;

    protected static HttpClient client = HttpClient.newHttpClient();

    public DiscordWebhook(String webhookUrl)
    {
        this.webhookUrl = webhookUrl;
    }

    public void SetWebhookUrl(String url)
    {
        webhookUrl = url;
    }

    public void SendWebhook(String jsonPayload)
    {
        if(Objects.equals(jsonPayload, ""))
        {
            return;
        }


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
                DiscordWebhookPlugin.logger.info("Success! Message sent to Discord.");
            } else {
                DiscordWebhookPlugin.logger.info("Failed to send. HTTP Code: " + response.statusCode());
                DiscordWebhookPlugin.logger.info("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
