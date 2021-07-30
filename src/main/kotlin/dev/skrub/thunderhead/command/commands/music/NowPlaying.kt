package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.musicutils.PlayerManager
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class NowPlaying :
    Command("nowplaying", "Displays music info", listOf(), 3, listOf("np"), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val playingTrack = PlayerManager(event.channel).musicManager.scheduler.audioPlayer.playingTrack
        event.message.channel.sendMessageQueue(
            EmbedBuilder()
                .setTitle("Now playing: ${playingTrack.info.title}")
                .setDescription("by ${playingTrack.info.author}")
                .setColor(ColorInfo.gulf)
                .build()
        )
    }
}