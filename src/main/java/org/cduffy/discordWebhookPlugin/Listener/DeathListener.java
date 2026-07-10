package org.cduffy.discordWebhookPlugin.Listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.cduffy.discordWebhookPlugin.DiscordWebhookPlugin;
import org.cduffy.discordWebhookPlugin.Webhook.IWebhook;

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
        EntityDamageEvent.DamageCause cause = event.getEntity().getLastDamageCause().getCause();
        IWebhook webhook = DiscordWebhookPlugin.webhookFactory.CreateDeathWebhook(cause);

        if(webhook != null) {
            webhook.SendMessage(deathMessage);
        }
    }
}
