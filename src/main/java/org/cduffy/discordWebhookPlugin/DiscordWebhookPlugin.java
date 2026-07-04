package org.cduffy.discordWebhookPlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cduffy.discordWebhookPlugin.Webhook.DiscordDeathWebhook;
import org.cduffy.discordWebhookPlugin.Webhook.DiscordMessageWebhook;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public final class DiscordWebhookPlugin extends JavaPlugin implements Listener {

    public static DiscordMessageWebhook chatMessageWebhook = new DiscordMessageWebhook("");

    public static DiscordDeathWebhook deathWebhook = new DiscordDeathWebhook("");

    private static DeathListener deathListener;

    public DiscordWebhookPlugin()
    {
        deathListener = new DeathListener(this);
    }


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
                chatMessageWebhook.SendWebhook(Util.buildMessagePayload(name, message));
            }
        });
    }


    public void loadConfig() {
        chatMessageWebhook.SetWebhookUrl(getConfig().getString("discord.chat-webhook-url"));
        deathWebhook.SetWebhookUrl(getConfig().getString("discord.death.death-webhook-url"));
        deathWebhook.SetImgUrl(getConfig().getString("discord.death.img-urls.default"));
        boolean enableImages = getConfig().getBoolean("discord.death.enable-images");
        deathWebhook.EnableImgs(enableImages);


        registerDamageCause(EntityDamageEvent.DamageCause.DROWNING, getDeathImgUrl("drowning"));
        registerDamageCause(EntityDamageEvent.DamageCause.LAVA, getDeathImgUrl("lava"));
        registerDamageCause(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, getDeathImgUrl("explosion"));
        registerDamageCause(EntityDamageEvent.DamageCause.FALL, getDeathImgUrl("fall"));
    }

    public String getDeathImgUrl(String name)
    {
        String deathConfigPrefix = "discord.death.img-urls.";
        return deathConfigPrefix+name;
    }

    public void registerDamageCause(EntityDamageEvent.DamageCause damageCause, String configPath)
    {
        String value = getConfig().getString(configPath);
        if(value == null)
        {
            return;
        }

        DiscordDeathWebhook.damageWebhookMap.put(damageCause, value);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
