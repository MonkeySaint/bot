package dev.skrub.thunderhead.musicutils

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.TextChannel

class PlayerManager(private val textChannel: TextChannel) {
    private val musicManagers: HashMap<Long, GuildMusicManager> = HashMap()
    private val audioPlayerManager: AudioPlayerManager = DefaultAudioPlayerManager()
    val musicManager = getMusicManager(textChannel.guild)

    private fun getMusicManager(guild: Guild): GuildMusicManager {
        return musicManagers.computeIfAbsent(guild.idLong) {
            val guildMusicManager = GuildMusicManager(audioPlayerManager)
            guild.audioManager.sendingHandler = guildMusicManager.audioPlayerSendHandler
            return@computeIfAbsent guildMusicManager
        }
    }

    fun loadAndPlay(trackUrl: String) {
        audioPlayerManager.loadItemOrdered(musicManager, trackUrl, ClassLoadResultHandler(musicManager, textChannel))
    }

    init {
        AudioSourceManagers.registerRemoteSources(audioPlayerManager)
        AudioSourceManagers.registerLocalSource(audioPlayerManager)
    }
}