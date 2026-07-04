package org.cduffy.discordWebhookPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cduffy.discordWebhookPlugin.Webhook.DiscordDeathWebhook;

public class DeathListener implements Listener
{
    private final DiscordWebhookPlugin plugin; // Store plugin reference
    public DeathListener(DiscordWebhookPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String playerName = event.getPlayer().getName();
        Player player = event.getPlayer();
        Component deathMessageComponent = event.deathMessage();
        String deathMessage = PlainTextComponentSerializer.plainText().serialize(deathMessageComponent);
        DiscordDeathWebhook webhook = DiscordWebhookPlugin.deathWebhook;
        if(webhook.GetImgUrl() == "") {
            webhook.SendWebhook(Util.buildDeathPayload(deathMessage));
        }
        else {
            webhook.SendWebhook(webhook.buildDeathPayload(player, deathMessage));
        }
    }
}
