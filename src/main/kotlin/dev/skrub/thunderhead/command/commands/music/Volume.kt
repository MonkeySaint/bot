package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.musicutils.PlayerManager
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Volume :
    Command("volume", "Changes Volume", listOf("[volume]"), 3, listOf(), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val volume = args[0].toIntOrNull()
            ?: kotlin.run { event.message.channel.sendMessageQueue(MessageUtil.error("Invalid volume provided!")); return }
        if (volume > 100 || volume < 0) {
            event.message.channel.sendMessageQueue(MessageUtil.error("Volume should be between 1 and 100!"))
            return
        }
        PlayerManager(event.channel).musicManager.scheduler.audioPlayer.volume = volume
    }
}