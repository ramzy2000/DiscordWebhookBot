package org.cduffy.discordWebhookPlugin.Webhook;

import org.cduffy.discordWebhookPlugin.DiscordWebhookPlugin;
import org.cduffy.discordWebhookPlugin.Util;

public class MessageWebhook implements IWebhook {
    protected static String url;

    public static void SetUrl(String url)
    {
        MessageWebhook.url = url;
    }

    @Override
    public void SendMessage(String message) {
        String payload = BuildPayload(message);
        Util.SendWebhook(payload, url);
    }

    String BuildPayload(String message)
    {
        String jsonPayload = """
                {
                   "content": "%s"
                }
            """;

        return String.format(jsonPayload, message);
    }
}
