package org.cduffy.discordWebhookPlugin;

import org.bukkit.event.entity.EntityDamageEvent;
import org.cduffy.discordWebhookPlugin.Webhook.*;

public class WebhookFactory {
    protected DrownWebhook drownWebhook;
    protected LavaDeathWebhook lavaDeathWebhook;
    protected DefaultMessageWebhook defaultMessageWebhook;

    public WebhookFactory() {
        drownWebhook = new DrownWebhook();
        lavaDeathWebhook = new LavaDeathWebhook();
        defaultMessageWebhook = new DefaultMessageWebhook();
    }

    public IWebhook CreateDeathWebhook(EntityDamageEvent.DamageCause damageCause) {
        switch (damageCause) {
            case EntityDamageEvent.DamageCause.DROWNING:
                return drownWebhook;
            case EntityDamageEvent.DamageCause.LAVA:
                return lavaDeathWebhook;
            default:
                return defaultMessageWebhook;
        }
    }
}
