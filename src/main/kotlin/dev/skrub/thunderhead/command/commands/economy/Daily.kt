package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Daily : Command("daily", "Gets 100 balance every day", listOf(), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val tryGetDaily = EconomyUtil.getDaily(event.member?.user ?: return)
        when {
            tryGetDaily.success -> {
                event.message.channel.sendMessageQueue(MessageUtil.success("Success! Claimed 100 balance."))
            }
            tryGetDaily.remainingTime == -1L -> {
                event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run the register command first!"))
            }
            else -> {
                event.message.channel.sendMessageQueue(MessageUtil.error("You have to wait ${(tryGetDaily.remainingTime) / 3600} hours before claiming your daily!"))
            }
        }
    }
}