package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.annotations.OverrideCommandArgsCheck
import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageComplete
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

@OverrideCommandArgsCheck
class Poll : Command(
    "poll",
    "Starts a poll in the specified channel.",
    listOf("[channelMention]", "[title]", "[description]", "[Reactions(AT LEAST 2)]"),
    0,
    listOf()
) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val reactions = args.drop(3)
        if (args.size <= 4) {
            event.message.channel.sendMessageQueue(MessageUtil.error("Not enough arguments!", makeSyntaxString()))
            return
        }
        val sentMessage = event.message.mentionedChannels[0].sendMessageComplete(
            EmbedBuilder()
                .setTitle("Poll: ${args[1]}")
                .setDescription("Poll started by: ${event.message.author.name}#${event.message.author.discriminator}")
                .setColor(ColorInfo.gulf)
                .addField("Details", args[2], false)
                .build()
        )

        try {
            reactions.forEach { reaction ->
                sentMessage.addReaction(reaction).queue()
            }
        } catch (e: IllegalArgumentException) {
            event.message.channel.sendMessageQueue(MessageUtil.error("Invalid reactions!"))
            return
        }
    }
}