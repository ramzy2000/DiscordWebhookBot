package org.cduffy.discordWebhookPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class DiscordWebhookPlugin extends JavaPlugin implements Listener {

    public static DiscordMessageWebhook chatMessageWebhook = new DiscordMessageWebhook("");

    public static DiscordDeathWebhook deathWebhook = new DiscordDeathWebhook("");

    public static Logger logger;
    @Override
    public void onEnable() {
        saveDefaultConfig(); // Generates config.yml if it doesn't exist
        loadConfig();
        DiscordWebhookPlugin.logger = getLogger();
        DiscordWebhookPlugin.logger.warning("Loading plugin");
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    // Player `/say`
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage().toLowerCase();
        if (msg.startsWith("/say ")) {
            String name = event.getPlayer().getName();
            String message = event.getMessage().substring(5);
            chatMessageWebhook.SendWebhook(Util.buildMessagePayload(name, message));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {

    }

    public void loadConfig() {

        chatMessageWebhook.SetWebhookUrl(getConfig().getString("chat-webhook-url"));
        deathWebhook.SetWebhookUrl(getConfig().getString("death-webhook-url"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
