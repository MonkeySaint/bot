package me.humboldt123.thunderhead.command.commands

import me.humboldt123.thunderhead.command.Command
import me.humboldt123.thunderhead.info.AskInfo
import me.humboldt123.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.net.URL

@Suppress("Unused")
class Ask : Command("ask", "The Thunderhead contains near-infinite knowledge.", listOf("[question]", "[expresion]"), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (args.isEmpty()) {
            event.message.channel.sendMessage(MessageUtil.error("You must ask a question to receive an answer.")).queue()
        } else {
            val query = args.joinToString(" ")
            try {
                // TODO: Add support for equations
                val result: Double = ExpressionBuilder(query.replace("`", "").replace("x", "*", true)).build().evaluate()
                event.message.channel.sendMessage(MessageUtil.answer(result.toString())).queue();
            } catch (e: Exception) {
                val answer = if (query.contains("scythe", true)) AskInfo.warn else AskInfo.answers.random()
                // TODO: Strip and remove of markdown
                event.channel.sendMessage("You asked: *${args.joinToString("").replace("@", "")}*").addFile(URL(answer).openStream(), "answer.png").queue()
            }
        }
    }
}