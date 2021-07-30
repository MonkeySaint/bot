package dev.skrub.thunderhead.musicutils

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

class GuildMusicManager(audioPlayerManager: AudioPlayerManager) {
    val audioPlayer: AudioPlayer = audioPlayerManager.createPlayer()
    val scheduler: TrackScheduler = TrackScheduler(audioPlayer)
    val audioPlayerSendHandler: AudioPlayerSendHandler = AudioPlayerSendHandler(audioPlayer)

    init {
        audioPlayer.addListener(scheduler)
    }
}