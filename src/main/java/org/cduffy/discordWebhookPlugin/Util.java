package org.cduffy.discordWebhookPlugin;

public class Util
{
    public static String buildMessagePayload(String name, String message)
    {
        String jsonPayload = """
            {
                "content": "%s: %s",
                "username": "Minecraft Server"
            }
            """;

        return String.format(jsonPayload, name, message);
    }

    public static String buildDeathPayload(String deathMessage)
    {
        String jsonPayload = """
            {
                "content": "%s",
                "username": "Minecraft Server"
            }
            """;

        return String.format(jsonPayload, deathMessage);
    }
}
