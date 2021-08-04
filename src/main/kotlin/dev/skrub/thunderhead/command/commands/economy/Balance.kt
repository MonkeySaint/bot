package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Balance: Command("balance", "Gets your current balance", listOf(), 3, listOf("bal")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val balance = EconomyUtil.getBalance(event.member?.user ?: return)
        if (balance == -1L) {
            event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run register command first!"))
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.tell(balance.toString()))
        }
    }
}