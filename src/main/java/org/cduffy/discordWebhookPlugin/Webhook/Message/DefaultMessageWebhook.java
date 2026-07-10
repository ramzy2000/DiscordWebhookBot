package org.cduffy.discordWebhookPlugin.Webhook.Message;

import org.cduffy.discordWebhookPlugin.DiscordWebhookPlugin;

public class DefaultMessageWebhook extends MessageWebhook {
    public  DefaultMessageWebhook() {
        SetUrl(DiscordWebhookPlugin.fileConfiguration.getString("discord.chat-webhook-url"));
    }
}
