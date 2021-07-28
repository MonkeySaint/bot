package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Ping : Command("ping", "Pong! :ping_pong:", listOf(), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.message.channel.sendMessageQueue("Ping: ${event.jda.gatewayPing}ms.")
    }
}