package dev.skrub.thunderhead.musicutils

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.entities.TextChannel

class ClassLoadResultHandler(private val musicManager: GuildMusicManager, private val channel: TextChannel) :
    AudioLoadResultHandler {
    override fun trackLoaded(track: AudioTrack) {
        musicManager.scheduler.queue(track)
        channel.sendMessageQueue(MessageUtil.success("Adding track `${track.info.title}` to queue."))
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        channel.sendMessageQueue(MessageUtil.success("Track loaded!"))
    }

    override fun noMatches() {
        channel.sendMessageQueue(MessageUtil.error("Cannot find track!"))
    }

    override fun loadFailed(exception: FriendlyException) {
        channel.sendMessageQueue(MessageUtil.error("An internal error occurred!", exception))
    }
}