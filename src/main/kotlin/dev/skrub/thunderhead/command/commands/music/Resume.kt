package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.musicutils.PlayerManager
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Resume :
    Command("resume", "Resumes music", listOf(), 3, listOf(), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        PlayerManager(event.channel).musicManager.scheduler.audioPlayer.isPaused = false
    }
}