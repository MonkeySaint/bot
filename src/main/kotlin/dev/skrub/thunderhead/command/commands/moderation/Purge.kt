package dev.skrub.thunderhead.command.commands.moderation

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException
import kotlin.concurrent.thread
import kotlin.random.Random

class Purge :
    Command(
        "purge",
        "Purges amount of messages in channels",
        listOf("[channelMention]", "[Amount of messages to purge]"),
        2,
        listOf(),
        false
    ) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.message.member!!.hasPermission(Permission.MESSAGE_MANAGE)) {
            val startTime = System.currentTimeMillis()
            val deleteMessage = "deleting ${args[0]} with ${args[1]} messages... ||${Random.nextInt()}||"
            event.message.channel.sendMessageQueue(deleteMessage)
            // Use threads because some actions are blocking
            thread {
                val channel = event.message.mentionedChannels[0]
                val messages = channel.history.retrievePast(args[1].toIntOrNull() ?: kotlin.run {
                    event.message.channel.sendMessageQueue(MessageUtil.error("Please enter the correct amount of messages to purge."))
                    return@thread
                }).complete()
                try {
                    messages.forEach {
                        if (it.contentRaw != deleteMessage)
                            it.delete().complete()
                    }

                } catch (e: InsufficientPermissionException) {
                    event.message.channel.sendMessageQueue(MessageUtil.error("I cannot delete the messages. Do I have the permission to?"))
                }
            }
            val endTime = System.currentTimeMillis()
            event.message.channel.sendMessageQueue("Done. Purged ${args[1]} messages in ${endTime - startTime} ms.")
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.error("You do not have the permission to do that!"))
        }
    }
}