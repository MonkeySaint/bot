package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class UnSlow :
    Command("unslow", "Removes slowmode from the specified channel.", listOf("[channelID]"), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        //TODO: https://github.com/humboldt123/thunderhead/issues/11
        if (args.size != 1) {
            event.channel.sendMessage(MessageUtil.error("Provide 2 arguments!")).queue()
        }
        event.guild.channels.find { it.id == args[0] }?.manager?.setSlowmode(0)?.queue()
            ?: kotlin.run {
                event.message.channel.sendMessage(MessageUtil.error("Cannot find channel!")).queue(); return
            }
        event.message.channel.sendMessage(MessageUtil.success("Removed Slowmode!")).queue()
    }
}