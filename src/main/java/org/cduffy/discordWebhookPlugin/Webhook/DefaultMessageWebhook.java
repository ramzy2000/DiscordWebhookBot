package org.cduffy.discordWebhookPlugin.Webhook;

import org.cduffy.discordWebhookPlugin.DiscordWebhookPlugin;
import org.cduffy.discordWebhookPlugin.Util;

public class DefaultMessageWebhook extends MessageWebhook {
    public  DefaultMessageWebhook() {
        SetUrl(DiscordWebhookPlugin.fileConfiguration.getString("discord.chat-webhook-url"));
    }
}
