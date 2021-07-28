package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.command.CommandManager
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.info.HelpInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import dev.skrub.thunderhead.util.getPrefix
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Help : Command("help", "A list of commands.", listOf("[command?]"), 1, listOf("commands")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        when (args.size) {
            0 -> event.message.channel.sendMessageQueue(getCommandListEmbed(1))
            else -> {
                val cmd: Int? = args[0].toIntOrNull()
                if (cmd == null) {
                    event.message.channel.sendMessageQueue(getCommandEmbed(getCommand(args[0])))
                } else {
                    event.message.channel.sendMessageQueue(getCommandListEmbed(cmd))
                }
            }
        }
    }

    private fun getCommandEmbed(command: Command?): MessageEmbed {
        return if (command != null)
            EmbedBuilder()
                .setTitle(command.name)
                .setColor(ColorInfo.discord)
                .setDescription(
                    "**Description:** ${command.description} " +
                            if (command.syntax.isNotEmpty()) "\n**Use(s):**\n - `${getPrefix() + command.name} ${
                                command.syntax.joinToString(
                                    "`\n - `${getPrefix() + command.name} "
                                )
                            }`" else ""
                ).build()
        else MessageUtil.error("Not a valid command or alias.")
    }

    private fun getCommandListEmbed(page: Int?): MessageEmbed {
        if (HelpInfo.page.size - 1 < page ?: 1 || page ?: 1 < 0)
            return MessageUtil.error("Cannot find command at page $page")
        var description = ""
        CommandManager.commands.filter { it.page == page ?: 1 }.forEach {
            description += "`${getPrefix()}${it.name}`: ${it.description}\n"
        }
        return EmbedBuilder()
            .setTitle(
                HelpInfo.page[page
                    ?: 1].emoji + " **__${HelpInfo.page[page ?: 1].name}__ Page ($page/${HelpInfo.page.size - 1})**"
            )
            .setColor(ColorInfo.discord)
            .setDescription(description)
            .build()
    }

    private fun getCommand(i: String?): Command? {
        return CommandManager.commands.filter {
            it.name == i || (it.aliases.any { it == i })
        }.getOrNull(0)
    }
}