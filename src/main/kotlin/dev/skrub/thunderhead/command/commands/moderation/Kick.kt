package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.exceptions.HierarchyException
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException

class Kick :
    Command(
        "kick",
        "Kicks member from the guild",
        listOf("[MemberMention]", "[reason]"),
        2,
        listOf()
    ) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.KICK_MEMBERS)) {
            try {
                event.message.mentionedMembers[0].kick(args[1]).queue()
            } catch (e: Exception) {
                if (e is InsufficientPermissionException) {
                    event.message.channel.sendMessage(MessageUtil.error("I do not have the permission to do that!"))
                        .queue()
                    return
                } else if (e is HierarchyException) {
                    event.message.channel.sendMessage(MessageUtil.error("I cannot kick users that has higher rank than me!"))
                        .queue()
                    return
                }
            }
            event.message.channel.sendMessage(MessageUtil.success("Successfully kicked user ${args[0]} with the reason ${args[1]}"))
                .queue()
        } else {
            event.message.channel.sendMessage(MessageUtil.error("You do not have the permission to do that!")).queue()
        }
    }
}