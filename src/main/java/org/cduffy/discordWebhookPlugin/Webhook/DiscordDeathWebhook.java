package org.cduffy.discordWebhookPlugin.Webhook;

import org.bukkit.entity.Player;

public class DiscordDeathWebhook extends DiscordWebhook {

    protected String imgUrl;
    public DiscordDeathWebhook(String webhook)
    {
        super(webhook);
    }

    public void SetImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String buildDeathPayload(Player player, String deathMessage)
    {
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
                           "name": "Player",
                           "value": "%s",
                           "inline": true
                         },
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

        String name = player.getName();

        return String.format(jsonPayload, deathMessage, this.imgUrl, name);
    }

    public String GetImgUrl()
    {
        return this.imgUrl;
    }

}


