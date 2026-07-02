package org.cduffy.discordWebhookPlugin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class DiscordMessageWebhook extends DiscordWebhook {
    public DiscordMessageWebhook(String webhookUrl)
    {
        super(webhookUrl);
    }
}
