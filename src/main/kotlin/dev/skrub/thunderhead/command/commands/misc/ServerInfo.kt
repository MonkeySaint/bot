package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class ServerInfo : Command("serverinfo", "Gets info on the current server.", listOf(), 1, listOf("server")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.channel.sendMessageQueue(
            EmbedBuilder()
                .setImage(event.guild.bannerUrl)
                .setThumbnail(event.guild.iconUrl)
                .setTitle(event.guild.name)
                .addField("Guild Members", event.guild.memberCount.toString(), false)
                .addField(
                    "Owner",
                    "${event.guild.owner!!.effectiveName}#${event.guild.owner!!.user.discriminator}",
                    false
                )
                .addField(
                    "Channel Count",
                    (event.guild.textChannels.size + event.guild.voiceChannels.size).toString(),
                    false
                )
                .addField("Emote Count", event.guild.emotes.size.toString(), false)
                .addField("Roles", event.guild.roles.size.toString(), false)
                .addField("Creation Date", event.guild.timeCreated.toString(), false)
                .addField("Verification", event.guild.verificationLevel.toString(), false)
                .setColor(ColorInfo.thunderhead)
                .build()
        )
    }
}