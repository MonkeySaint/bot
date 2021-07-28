package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Avatar : Command("avatar", "Gets user avatar", listOf("[user mention/ID]"), 1, listOf("av")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        try {
            if (args[0].toLongOrNull() != null) {
                event.message.channel.sendMessageQueue(
                    EmbedBuilder()
                        .setTitle("User Avatar:")
                        .setImage(event.guild.retrieveMemberById(args[0]).complete().user.avatarUrl)
                        .build()
                )
            } else {
                event.message.channel.sendMessageQueue(
                    EmbedBuilder()
                        .setTitle("User Avatar:")
                        .setImage(
                            event.guild.retrieveMemberById(event.message.mentionedMembers[0].id)
                                .complete().user.avatarUrl
                        )
                        .build()
                )
            }
        } catch (e: NumberFormatException) {
            event.message.channel.sendMessageQueue("Invalid ID Input!")
        }
    }
}