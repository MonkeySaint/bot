package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Ping : Command("ping", "Pong! :ping_pong:", listOf(), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.message.channel.sendMessage("Ping: ${event.jda.gatewayPing}ms.").queue()
    }
}