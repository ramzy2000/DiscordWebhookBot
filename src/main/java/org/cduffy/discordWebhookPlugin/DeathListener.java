package org.cduffy.discordWebhookPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
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
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        String playerName = event.getPlayer().getName();
        Component deathMessageComponent = event.deathMessage();
        String deathMessage = PlainTextComponentSerializer.plainText().serialize(deathMessageComponent);
        DiscordWebhookPlugin.deathWebhook.SendWebhook(Util.buildDeathPayload(deathMessage));
        Bukkit.getScheduler().runTask(plugin, () -> {
            event.getPlayer().sendMessage("Hello from main thread");
        });
    }
}
