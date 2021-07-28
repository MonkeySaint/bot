package dev.skrub.thunderhead

import dev.skrub.thunderhead.command.CommandManager
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
            for (c in CommandManager.commands) {
                if (c.matches(command) && !message.isWebhookMessage && !message.author.isBot) {
                    if (c.checkArgs(args)) {
                        c.execute(args, event)
                    } else {
                        event.message.channel.sendMessage(MessageUtil.error("Need ${c.syntax.size} arguments, provided ${args.size}!"))
                            .queue()
                    }
                } else {
                    event.message.channel.sendMessage(MessageUtil.error("Command not found!")).queue()
                }
            }
        }
    }
}