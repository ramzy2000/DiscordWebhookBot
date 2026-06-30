package org.cduffy.discordWebhookBot;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatEventListener implements Listener {



    @EventHandler
    public void onChatMessageSent(AsyncChatEvent event) {
        String playerName = event.getPlayer().name().toString();
        String message = event.originalMessage().toString();

        DiscordWebhookBot.chatMessageWebhook.SendWebhook(playerName, message);
    }
}
