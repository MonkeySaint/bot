package dev.skrub.thunderhead.command

import dev.skrub.thunderhead.Instance
import org.reflections.Reflections
import java.util.*


class CommandManager(bot: Instance) {
    companion object {
        @JvmStatic
        lateinit var commands: MutableList<Command>
    }

    init {
        //TODO: Add a check that disallows commands to share names/aliases and disallows commands to be numbers
        commands = mutableListOf()
        for (m in findCommands()) {
            try {
                val c = m.getConstructor().newInstance()
                commands.add(c)
                bot.EVENT_BUS.subscribe(c)
                println("Command ${c.name} (${m.name}) fetched")
            } catch (e: Exception) {
                System.err.println(e)
            }
        }
    }


    private fun findCommands(): ArrayList<Class<out Command>> {
        val misc = Reflections("dev.skrub.thunderhead.command.commands.misc")
        val moderation = Reflections("dev.skrub.thunderhead.command.commands.moderation")
        val list = ArrayList<Class<out Command>>()
        list.addAll(misc.getSubTypesOf(Command::class.java))
        list.addAll(moderation.getSubTypesOf(Command::class.java))
        return list
    }
}