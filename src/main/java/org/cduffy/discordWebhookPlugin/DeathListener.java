package org.cduffy.discordWebhookPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.cduffy.discordWebhookPlugin.Webhook.DiscordDeathWebhook;

public class DeathListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        String playerName = event.getPlayer().getName();
        Component deathMessageComponent = event.deathMessage();
        String deathMessage = PlainTextComponentSerializer.plainText().serialize(deathMessageComponent);
        DiscordWebhookPlugin.deathWebhook.SendWebhook(Util.buildDeathPayload(deathMessage));
    }
}
