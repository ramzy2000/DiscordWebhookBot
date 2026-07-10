package org.cduffy.discordWebhookPlugin.Webhook;

import org.cduffy.discordWebhookPlugin.DiscordWebhookPlugin;
import org.cduffy.discordWebhookPlugin.Util;

import java.util.Dictionary;
import java.util.Hashtable;

public class DeathWebhook implements IWebhook{
    protected static String url = "";

    protected String imgUrl = "";

    public DeathWebhook() {

    }

    public static void SetWebhookUrl(String webhookUrl) {
        DeathWebhook.url = webhookUrl;
    }

    public void SetImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public void SendMessage(String message) {
        String payload = BuildPayload(message);
        Util.SendWebhook(payload, url);
    }

    public String getDeathImgUrl(String name)
    {
        String deathConfigPrefix = "discord.death.img-urls.";
        return deathConfigPrefix+name;
    }

    public void LoadConfig(String name) {
        SetImgUrl(DiscordWebhookPlugin.fileConfiguration.getString(getDeathImgUrl(name)));
    }

    protected String BuildPayload(String deathMessage) {
        String jsonPayload = """
                {
                   "username": "Minecraft Death Bot",
                   "embeds": [
                     {
                       "title": "☠️ Player Death",
                       "description": "%s",
                       "color": 16711680,
                       "image": {
                         "url": "%s"
                       },
                       "fields": [
                         {
                           "name": "World",
                           "value": "world",
                           "inline": true
                         }
                       ]
                     }
                   ]
                 }
            """;

        return String.format(jsonPayload, deathMessage, imgUrl);
    }
}
