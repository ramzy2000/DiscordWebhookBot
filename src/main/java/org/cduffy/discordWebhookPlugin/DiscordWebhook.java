package org.cduffy.discordWebhookPlugin;

import java.net.http.HttpClient;

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
}