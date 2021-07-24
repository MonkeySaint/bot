package dev.skrub.thunderhead.command

import me.zero.alpine.listener.Listenable
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

open class Command(open val name: String, open val description: String, open val syntax: List<String>, open val page: Int, open val aliases: List<String>) : Listenable {
    open fun execute(args: List<String>, event: GuildMessageReceivedEvent) {}
    fun matches(string: String): Boolean {
        if (string == name) return true
        aliases.forEach{
            if (string == it)
                return true
        }
        return false
    }
}