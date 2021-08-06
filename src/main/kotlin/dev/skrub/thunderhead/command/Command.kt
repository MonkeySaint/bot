package dev.skrub.thunderhead.command

import dev.skrub.thunderhead.annotations.OverrideCommandArgsCheck
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
        if (args.size >= syntax.size || this::class.annotations.firstOrNull { it is OverrideCommandArgsCheck } != null) return true
        return false
    }

    fun makeSyntaxString(): String {
        return "$name ${syntax.joinToString(" ")}"
    }
}