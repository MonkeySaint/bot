package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Announce :
    Command("announce", "Sends message to specified channel.", listOf("[channel mention]", "[message]"), 2, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.MANAGE_CHANNEL)) {
            event.message.mentionedChannels[0]?.sendMessage(args[1])?.queue() ?: event.message.channel.sendMessage(MessageUtil.error("Specified Channel cannot be fonud!")).queue()
        } else {
            event.message.channel.sendMessage(MessageUtil.error("You do not have the permission to do that!")).queue()
        }
    }
}