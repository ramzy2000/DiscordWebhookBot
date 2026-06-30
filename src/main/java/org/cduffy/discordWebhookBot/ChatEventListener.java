package org.cduffy.discordWebhookBot;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class ChatEventListener implements Listener {

    @EventHandler
    public void onChatMessageSent(AsyncChatEvent event) {
        String playerName = event.getPlayer().name().toString();
        String message = event.originalMessage().toString();

        SendWebhook(playerName, message);
    }

    public static HttpClient client = HttpClient.newHttpClient();
    public static void SendWebhook(String name, String message)
    {
        if(name == "" && message == "")
        {
            return;
        }

        String webhookUrl = "https://discord.com/api/webhooks/1521367363570761828/kpazq9rAxHkwHC8VKbxFSq-arDp7tdBZkzR-KlnssFqUapUkrQSI2-EWEoTTixCh85bU";

        String jsonPayload = """
            {
                "content": "%s: %s",
                "username": "Server Messager"
            }
            """;

        jsonPayload = String.format(jsonPayload, name, message);

        // 3. Build and send the HTTP POST request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webhookUrl))
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
