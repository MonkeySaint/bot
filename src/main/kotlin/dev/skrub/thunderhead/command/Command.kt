package dev.skrub.thunderhead.command

import me.zero.alpine.listener.Listenable
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(
    val name: String,
    val description: String,
    val syntax: List<String>,
    val page: Int,
    val aliases: List<String>
) : Listenable {
    abstract fun execute(args: List<String>, event: GuildMessageReceivedEvent)
    fun matches(string: String): Boolean {
        if (string == name) return true
        aliases.forEach {
            if (string == it)
                return true
        }
        return false
    }
    fun checkArgs(args: List<String>): Boolean {
        if (args.size == syntax.size) return true
        return false
    }
}