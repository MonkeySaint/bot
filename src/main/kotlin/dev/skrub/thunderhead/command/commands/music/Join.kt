package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Join :
    Command("join", "Joins VC", listOf(), 3, listOf("connect"), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (event.guild.audioManager.isConnected) {
            event.message.channel.sendMessageQueue(MessageUtil.tell("I am already in a voice channel!"))
            return
        }

        val joinedVC = event.message.member?.voiceState?.channel ?: kotlin.run {
            event.message.channel.sendMessageQueue(
                "Please join a voice channel first!"
            ); return
        }
        try {
            event.guild.audioManager.openAudioConnection(joinedVC)
        } catch (e: Exception) {
            event.message.channel.sendMessageQueue(MessageUtil.error("An internal error occurred! Please try again later."))
        }
    }
}