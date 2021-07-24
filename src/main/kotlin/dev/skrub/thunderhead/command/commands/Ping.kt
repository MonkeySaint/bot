package dev.skrub.thunderhead.command.commands

import me.humboldt123.thunderhead.command.Command
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

@Suppress("Unused")
class Ping : Command("ping", "Pong! :ping_pong:", listOf(), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.message.channel.sendMessage("Pong :ping_pong:").queue()
    }
}