package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.AskInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.objecthunter.exp4j.ExpressionBuilder
import java.net.URL

class Ask : Command(
    "ask",
    "The Thunderhead contains near-infinite knowledge.",
    listOf("[question]", "[expresion]"),
    1,
    listOf(),
    false
) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val query = args.joinToString(" ")
        try {
            // TODO: Add support for equations
            val result: Double = ExpressionBuilder(query.replace("`", "").replace("x", "*", true)).build().evaluate()
            event.message.channel.sendMessageQueue(MessageUtil.answer(result.toString()))
        } catch (e: Exception) {
            val answer = if (query.contains("scythe", true)) AskInfo.warn else AskInfo.answers.random()
            // TODO: Strip and remove of markdown
            event.channel.sendMessage("You asked: *${args.joinToString("").replace("@", "")}*")
                .addFile(URL(answer).openStream(), "answer.png").queue()
        }
    }
}