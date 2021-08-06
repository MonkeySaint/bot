package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.annotations.OverrideCommandArgsCheck
import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

@OverrideCommandArgsCheck
class Balance : Command("balance", "Gets your current balance", listOf("[user?]"), 3, listOf("bal")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (args.size == 1) {
            val specifiedUserBalance = EconomyUtil.getBalance(event.message.mentionedUsers[0])
            if (specifiedUserBalance == -1) {
                event.message.channel.sendMessageQueue(MessageUtil.error("That user is not registered!"))
                return
            }
            event.message.channel.sendMessageQueue(MessageUtil.tell(specifiedUserBalance.toString()))
            return
        }
        val balance = EconomyUtil.getBalance(event.member?.user ?: return)
        if (balance == -1) {
            event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run register command first!"))
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.tell(balance.toString()))
        }
    }
}