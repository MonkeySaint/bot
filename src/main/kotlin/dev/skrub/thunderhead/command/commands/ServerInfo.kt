package dev.skrub.thunderhead.command.commands

import dev.skrub.thunderhead.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color

@Suppress("Unused")
class ServerInfo : Command("serverinfo", "Gets info on the current server.", listOf(), 1, listOf("server")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.channel.sendMessage(EmbedBuilder()
            .setImage(event.guild.bannerUrl)
            .setThumbnail(event.guild.iconUrl)
            .setTitle(event.guild.name)
            .addField("Guild Members", event.guild.memberCount.toString(), false)
            .addField("Owner", event.guild.owner!!.effectiveName, false)
            .addField("Channel Count", (event.guild.textChannels.size + event.guild.voiceChannels.size).toString(), false)
            .addField("Emote Count", event.guild.emotes.size.toString(), false)
            .addField("Roles", event.guild.roles.size.toString(), false)
            .addField("Creation Date", event.guild.timeCreated.toString(), false)
            .addField("Verification", event.guild.verificationLevel.toString(), false)
            .setColor(Color(206, 141, 112))
            .build()).queue()
    }
}