package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import kotlin.random.Random

class Rob : Command("rob", "Robs specified player", listOf("[userMention]"), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (EconomyUtil.getBalance(event.member?.user ?: return) == -1) {
            event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run register command first!"))
            return
        }

        val poorRobbedGuy = event.message.mentionedMembers[0].user
        if (EconomyUtil.canSteal(poorRobbedGuy)) {
            event.message.channel.sendMessageQueue(
                MessageUtil.error(
                    "Cannot rob user!",
                    "Reasons:\n1)User is not registered\n2)User does not have at least 200 balance\n3)User was already robbed in the last 2 hours"
                )
            )
            return
        }

        if (Random.nextInt(1, 10) == 5) {
            val robAmount = Random.nextInt(200, EconomyUtil.getBalance(poorRobbedGuy))
            event.message.channel.sendMessageQueue(MessageUtil.success("Rob success! You robbed $robAmount!"))
            EconomyUtil.withdraw(poorRobbedGuy, robAmount)
            EconomyUtil.deposit(event.member?.user ?: return, robAmount)
            EconomyUtil.updateRobTime(poorRobbedGuy)
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.error("Rob failed! You cannot rob that user in the next 2 hours!"))
            EconomyUtil.updateRobTime(poorRobbedGuy)
        }
    }
}