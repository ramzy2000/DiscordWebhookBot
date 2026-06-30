package org.cduffy.discordWebhookBot;

import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordWebhookBot extends JavaPlugin {

    public static DiscordMessageWebhook chatMessageWebhook = new DiscordMessageWebhook("");

    @Override
    public void onEnable() {
        saveDefaultConfig(); // Generates config.yml if it doesn't exist
        loadConfig();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ChatEventListener(), this);
    }

    public void loadConfig() {
        chatMessageWebhook.SetWebhookUrl(getConfig().getString("discord.webhook-url"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
