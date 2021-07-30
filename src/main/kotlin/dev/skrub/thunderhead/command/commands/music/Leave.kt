package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Leave :
    Command("leave", "Joins VC", listOf(), 3, listOf("disconnect"), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.guild.audioManager.closeAudioConnection()
    }
}