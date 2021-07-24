package dev.skrub.thunderhead

import me.humboldt123.thunderhead.command.CommandManager
import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.EventManager
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent


class Instance(val token: String) {
    val EVENT_BUS: EventBus = EventManager()
    fun start() {
        println("Instance created.")
        val CommandManager = CommandManager(this)
        println("Commands fetched")
        val builder = JDABuilder.create(
            token,
            setOf(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_EMOJIS,
                //GUILD_INTEGRATIONS (unused by JDA)
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_VOICE_STATES,
                //GUILD_PRESENCES, (bot lacks privilege)
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGE_TYPING

            )
        )
            .setAutoReconnect(true)
            .addEventListeners(dev.skrub.thunderhead.DiscordListener(this))
        val jda = builder.build()
        println("Builder built.")
    }
}