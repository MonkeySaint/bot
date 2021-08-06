package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Pay : Command("pay", "Pays user", listOf("[userMention/ID]", "amount"), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (args[0].toLongOrNull() == null) {
            if (EconomyUtil.getBalance(event.member?.user ?: return) == -1) {
                event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run register command first!"))
                return
            }
            // A Mention
            val mentionedUser = event.message.mentionedMembers[0].user
            val payAmount = args[1].toIntOrNull()
                ?: kotlin.run { event.message.channel.sendMessageQueue(MessageUtil.error("Invalid amount!")); return }
            if (payAmount > EconomyUtil.getBalance(event.member?.user ?: return)) {
                event.message.channel.sendMessageQueue(MessageUtil.error("You do not have enough money!")); return
            }
            if (EconomyUtil.give(mentionedUser, payAmount)) {
                EconomyUtil.withdraw(event.member?.user ?: return, payAmount)
                event.message.channel.sendMessageQueue(MessageUtil.success("Success! Paid ${mentionedUser.name}#${mentionedUser.discriminator} $payAmount balance!"))
            } else {
                event.message.channel.sendMessageQueue(MessageUtil.error("That user does not exist! Your money has been returned!"))
            }
        }
    }
}