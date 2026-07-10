package org.cduffy.discordWebhookPlugin.Webhook;

import org.bukkit.event.entity.EntityDamageEvent;
import org.cduffy.discordWebhookPlugin.Webhook.Death.DefaultDeathWebhook;
import org.cduffy.discordWebhookPlugin.Webhook.Death.DrownWebhook;
import org.cduffy.discordWebhookPlugin.Webhook.Death.FallWebhook;
import org.cduffy.discordWebhookPlugin.Webhook.Death.LavaDeathWebhook;
import org.cduffy.discordWebhookPlugin.Webhook.Message.DefaultMessageWebhook;

public class WebhookFactory {
    protected DrownWebhook drownWebhook;
    protected LavaDeathWebhook lavaDeathWebhook;
    protected DefaultDeathWebhook defaultDeathWebhook;
    protected FallWebhook fallWebhook;
    public DefaultMessageWebhook defaultMessageWebhook;

    public WebhookFactory() {
        drownWebhook = new DrownWebhook();
        lavaDeathWebhook = new LavaDeathWebhook();
        defaultMessageWebhook = new DefaultMessageWebhook();
        defaultDeathWebhook = new DefaultDeathWebhook();
    }

    public IWebhook CreateDeathWebhook(EntityDamageEvent.DamageCause damageCause) {
        switch (damageCause) {
            case EntityDamageEvent.DamageCause.DROWNING:
                return drownWebhook;
            case EntityDamageEvent.DamageCause.LAVA:
                return lavaDeathWebhook;
            case EntityDamageEvent.DamageCause.FALL:
                return fallWebhook;
            default:
                return defaultDeathWebhook;
        }
    }
}
