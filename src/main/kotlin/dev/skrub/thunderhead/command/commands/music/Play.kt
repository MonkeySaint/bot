package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.musicutils.PlayerManager
import dev.skrub.thunderhead.util.InfixUtil.isValidUrl
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Play :
    Command("play", "Plays music", listOf("[link/title]"), 3, listOf(), true) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (!event.guild.audioManager.isConnected && event.member?.voiceState?.channel != null) {
            event.message.channel.sendMessageQueue(MessageUtil.error("I/You have to be in a voice channel!"))
            return
        }

        if (args[0].isValidUrl()) {
            PlayerManager(event.channel).loadAndPlay(args[0])
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.tell("Searching videos on youtube is not supported yet!"))
        }
    }
}