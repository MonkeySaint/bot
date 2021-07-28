package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class UserInfo : Command("userinfo", "Gets user information", listOf("[user id/mention]"), 1, listOf("info")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        lateinit var member: Member
        try {
            member = if (args[0].toLongOrNull() != null) {
                event.guild.retrieveMemberById(args[0]).complete()
            } else {
                event.guild.retrieveMemberById(event.message.mentionedMembers[0].id).complete()
            }
        } catch (e: NumberFormatException) {
            event.message.channel.sendMessageQueue("Invalid ID Input!")
            return
        }
        event.message.channel.sendMessageQueue(
            EmbedBuilder()
                .setColor(member.colorRaw)
                .setTitle("${member.effectiveName}#${member.user.discriminator}")
                .addField("ID:", member.id, false)
                .addField("Joined At:", member.timeJoined.toString(), false)
                .addField("Created At:", member.timeCreated.toString(), false)
                .addField("# Of Devices Online:", member.activeClients.size.toString(), false)
                .addField("Owner?", member.isOwner.toString(), false)
                .build()
        )
    }
}