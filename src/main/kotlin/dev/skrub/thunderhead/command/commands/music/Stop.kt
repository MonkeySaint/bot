package dev.skrub.thunderhead.command.commands.music

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.musicutils.PlayerManager
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent


class Stop :
    Command("stop", "Stops Music", listOf(), 3, listOf(), false) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        PlayerManager(event.channel).musicManager.scheduler.audioPlayer.stopTrack()
        event.message.channel.sendMessageQueue(MessageUtil.success("Stopped Music!"))
    }
}