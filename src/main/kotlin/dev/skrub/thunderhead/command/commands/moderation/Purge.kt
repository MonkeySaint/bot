package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException
import kotlin.concurrent.thread
import kotlin.random.Random

class Purge :
    Command("purge", "Purges amounut of messages in channels", listOf("[channelMention]", "[Amount of messages to purge]"), 2, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.MANAGE_CHANNEL)) {
            val startTime = System.currentTimeMillis()
            val deleteMessage = "deleting ${args[0]} with ${args[1]} messages... ||${Random.nextInt()}||"
            event.message.channel.sendMessage(deleteMessage).queue()
            // Use threads because some actions are blocking
            thread {
                val channel = event.message.mentionedChannels[0]
                val messages = channel.history.retrievePast(args[1].toIntOrNull() ?: kotlin.run {
                    event.message.channel.sendMessage(MessageUtil.error("Please enter the correct amount of messages to purge.")).queue()
                    return@thread
                }).complete()
                try {
                    messages.forEach{
                        if (it.contentRaw != deleteMessage)
                            it.delete().complete()
                    }

                } catch (e: InsufficientPermissionException) {
                    event.message.channel.sendMessage(MessageUtil.error("I cannot delete the messages. Do I have the permission to?"))
                }
            }
            val endTime = System.currentTimeMillis()
            event.message.channel.sendMessage("Done. Purged ${args[1]} messages in ${endTime - startTime} ms.").queue()
        } else {
            event.message.channel.sendMessage(MessageUtil.error("You do not have the permission to do that!")).queue()
        }
    }
}