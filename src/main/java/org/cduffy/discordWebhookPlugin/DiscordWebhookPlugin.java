package org.cduffy.discordWebhookPlugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.cduffy.discordWebhookPlugin.Webhook.*;

import java.util.logging.Logger;

public final class DiscordWebhookPlugin extends JavaPlugin implements Listener {
    private static DeathListener deathListener;

    public DiscordWebhookPlugin()
    {
        deathListener = new DeathListener(this);
    }

    public static FileConfiguration fileConfiguration;

    public static WebhookFactory webhookFactory;

    public static Logger logger;
    @Override
    public void onEnable() {
        saveDefaultConfig(); // Generates config.yml if it doesn't exist
        loadConfig();
        DiscordWebhookPlugin.logger = getLogger();
        DiscordWebhookPlugin.logger.warning("Loading plugin");

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(deathListener, this);
    }

    // Player `/say`
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Bukkit.getScheduler().runTask(this, () -> {
            String msg = event.getMessage().toLowerCase();
            if (msg.startsWith("/say ")) {
                String name = event.getPlayer().getName();
                String message = event.getMessage().substring(5);

                webhookFactory.defaultMessageWebhook.SendMessage(name + ": "+message);
            }
        });
    }


    public void loadConfig() {
        fileConfiguration = getConfig();
        // load webhook urls
        DeathWebhook.SetWebhookUrl(fileConfiguration.getString("discord.death.death-webhook-url"));
        DefaultMessageWebhook.SetUrl(fileConfiguration.getString("discord.chat-webhook-url"));

        webhookFactory = new WebhookFactory();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
