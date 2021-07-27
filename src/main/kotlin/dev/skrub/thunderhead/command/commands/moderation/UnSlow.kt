package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class UnSlow :
    Command("unslow", "Removes slowmode from the specified channel.", listOf("[channelID]"), 2, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.MANAGE_CHANNEL)) {
            event.guild.channels.find { it.id == event.message.mentionedChannels[0].id }?.manager?.setSlowmode(0)?.queue()
                ?: kotlin.run {
                    event.message.channel.sendMessage(MessageUtil.error("Cannot find channel!")).queue(); return
                }
            event.message.channel.sendMessage(MessageUtil.success("Removed Slowmode!")).queue()
        } else {
            event.message.channel.sendMessage(MessageUtil.error("You do not have the permission to do that!")).queue()
        }
    }
}