package dev.skrub.thunderhead

import dev.skrub.thunderhead.command.CommandManager
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import dev.skrub.thunderhead.util.getPrefix
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DiscordListener(val bot: Instance) : ListenerAdapter() {
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (event.author.isBot) return
        val message: Message = event.message
        val content: String = message.contentRaw.removePrefix(getPrefix())
        if (content.isNotBlank() && message.contentRaw.first().toString() == getPrefix()) {
            val messageArray = content.split(" ")
            val command = messageArray[0]
            val args = messageArray.drop(1)
            CommandManager.commands.forEach { c ->
                if (c.matches(command) && !message.isWebhookMessage && !message.author.isBot) {
                    if (c.checkArgs(args)) {
                        c.execute(args, event)
                        return
                    } else {
                        event.message.channel.sendMessageQueue(
                            MessageUtil.error(
                                "Need ${c.syntax.size} arguments, provided ${args.size}!",
                                c.makeSyntaxString()
                            )
                        )
                        return
                    }
                }
            }
            event.message.channel.sendMessageQueue(MessageUtil.error("Command not found!"))
        }
    }
}