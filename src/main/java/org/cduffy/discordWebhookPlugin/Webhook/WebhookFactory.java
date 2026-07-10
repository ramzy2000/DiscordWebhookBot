package org.cduffy.discordWebhookPlugin.Webhook;

import org.bukkit.event.entity.EntityDamageEvent;
import org.cduffy.discordWebhookPlugin.Webhook.Death.*;
import org.cduffy.discordWebhookPlugin.Webhook.Message.DefaultMessageWebhook;

public class WebhookFactory {
    protected DrownWebhook drownWebhook;
    protected LavaDeathWebhook lavaDeathWebhook;
    protected DefaultDeathWebhook defaultDeathWebhook;
    protected FallWebhook fallWebhook;
    protected ExplosionDeathWebhook explosionDeathWebhook;
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
            case EntityDamageEvent.DamageCause.ENTITY_EXPLOSION:
                return explosionDeathWebhook;
            default:
                return defaultDeathWebhook;
        }
    }
}
