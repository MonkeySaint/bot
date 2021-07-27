package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Slow :
    Command("slow", "Adds slowmode to the specified channel.", listOf("[channelMention]", "[delay(seconds)]"), 2, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.MANAGE_CHANNEL)) {
            if (args[1].toIntOrNull() != null) {
                try {
                    event.guild.channels.find { it.id == event.message.mentionedChannels[0].id }?.manager?.setSlowmode(
                        args[1].toInt()
                    )?.queue()
                        ?: kotlin.run {
                            event.message.channel.sendMessage(MessageUtil.error("Cannot find channel!")).queue(); return
                        }
                } catch (e: IllegalArgumentException) {
                    event.message.channel.sendMessage(MessageUtil.error("Illegal delay seconds provided!"))
                }
                event.message.channel.sendMessage(MessageUtil.success("Added Slowmode!")).queue()
            } else {
                event.message.channel.sendMessage(MessageUtil.error("Invalid seconds!")).queue()
            }
        } else {
            event.message.channel.sendMessage(MessageUtil.error("You do not have the permission to do that!")).queue()
        }
    }
}