package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.info.HelpInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Help : Command("help", "A list of commands.", listOf("[command]/[page]"), 1, listOf("commands")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (args[0].toIntOrNull() == null) {
            // Provided is a command
            HelpInfo.page.forEach { page ->
                page.commands.forEach { command ->
                    if (command.name == args[0]) {
                        event.message.channel.sendMessageQueue(
                            EmbedBuilder()
                                .setColor(ColorInfo.gulf)
                                .setTitle(command.name.capitalize())
                                .setDescription(command.description)
                                .addField("Syntax", "```${command.makeSyntaxString()}```", false)
                                .build()
                        )
                        return
                    }
                }
            }
            event.message.channel.sendMessageQueue(MessageUtil.error("Command not found!"))
        } else {
            // provided is a page num
            try {
                val commandPage = HelpInfo.page[args[0].toInt() - 1]
                event.message.channel.sendMessageQueue(
                    EmbedBuilder()
                        .setColor(ColorInfo.gulf)
                        .setTitle("**${commandPage.name}** ${commandPage.emoji} **(${args[0].toInt()}/${HelpInfo.page.size})**")
                        .addField("Commands", commandPage.commands.joinToString("\n") { it.name }, false)
                        .build()
                )
            } catch (e: Exception) {
                event.message.channel.sendMessageQueue(MessageUtil.error("Page invalid!"))
                return
            }
        }
    }
}