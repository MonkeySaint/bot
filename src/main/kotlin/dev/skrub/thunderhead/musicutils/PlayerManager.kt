package dev.skrub.thunderhead.musicutils

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.TextChannel

class PlayerManager {
    private val musicManagers: HashMap<Long, GuildMusicManager> = HashMap()
    private val audioPlayerManager: AudioPlayerManager = DefaultAudioPlayerManager()

    private fun getMusicManager(guild: Guild): GuildMusicManager {
        return musicManagers.computeIfAbsent(guild.idLong) {
            val guildMusicManager: GuildMusicManager = GuildMusicManager(audioPlayerManager)
            guild.audioManager.sendingHandler = guildMusicManager.audioPlayerSendHandler
            return@computeIfAbsent guildMusicManager
        }
    }

    fun loadAndPlay(textChannel: TextChannel, trackUrl: String) {
        val musicManager = getMusicManager(textChannel.guild)

        audioPlayerManager.loadItemOrdered(musicManager, trackUrl, ClassLoadResultHandler(musicManager, textChannel))
    }

    init {
        AudioSourceManagers.registerRemoteSources(audioPlayerManager)
        AudioSourceManagers.registerLocalSource(audioPlayerManager)
    }
}