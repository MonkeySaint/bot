package me.humboldt123.thunderhead

import me.humboldt123.thunderhead.command.CommandManager
import me.humboldt123.thunderhead.util.getPrefix
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
                if (c.matches(command)) {
                    c.execute(args, event)
                }
            }
        }
    }
}