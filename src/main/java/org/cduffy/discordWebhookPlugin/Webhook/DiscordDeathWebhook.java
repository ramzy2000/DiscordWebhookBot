package org.cduffy.discordWebhookPlugin.Webhook;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class DiscordDeathWebhook extends DiscordWebhook {

    protected String imgUrl;

    protected boolean imgEnabled = false;

    public  static Map<EntityDamageEvent.DamageCause, String> damageWebhookMap = new HashMap<>();
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
        String imgUrl = "";
        try {
            EntityDamageEvent.DamageCause damageCause = player.getLastDamageCause().getCause();
            if(damageWebhookMap.containsKey(damageCause))
            {
                imgUrl = damageWebhookMap.get(damageCause);
                if(imgUrl.isEmpty())
                {
                    return String.format(jsonPayload, deathMessage, this.imgUrl, name);
                }
            }
            else
            {
                return String.format(jsonPayload, deathMessage, this.imgUrl, name);
            }
        }
        catch (Exception e)
        {
            return String.format(jsonPayload, deathMessage, this.imgUrl, name);
        }

        return String.format(jsonPayload, deathMessage, imgUrl, name);
    }

    public void EnableImgs(boolean imgEnabled)
    {
        this.imgEnabled = imgEnabled;
    }

    public boolean ImagesEnabled()
    {
        return this.imgEnabled;
    }


    public String GetImgUrl()
    {
        return this.imgUrl;
    }

}


