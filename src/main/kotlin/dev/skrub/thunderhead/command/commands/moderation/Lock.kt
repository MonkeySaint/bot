package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Lock : Command("lock", "Locks the specified channel.", listOf("[channelID]"), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.message.channel.sendMessage(args.joinToString(",")).queue()
        event.guild.channels.find { it.id == args[0] }?.manager?.setSlowmode(100)?.queue()
            ?: event.message.channel.sendMessage(MessageUtil.error("Cannot find channel!")).queue()
    }
}