package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import kotlin.random.Random

class CoinFlip :
    Command("coinflip", "Flips a coin", listOf("[heads/tails]", "[amount of money to flip]"), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val flipResult = Random.nextBoolean()
        val currentBalance = EconomyUtil.getBalance(event.member?.user ?: return)
        if (currentBalance == -1L) {
            event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run the register command first!"))
            return
        }

        // this is stupid
        val guess: Boolean = when {
            args[0] == "heads" -> {
                true
            }
            args[0] == "tails" -> {
                false
            }
            else -> {
                event.message.channel.sendMessageQueue(MessageUtil.error("Invalid choice!"))
                return
            }
        }

        // this is also stupid
        if (args[1].toLongOrNull() == null || args[1].toLong() <= 0 || args[1].toLong() > EconomyUtil.getBalance(
                event.member?.user ?: return
            )
        ) {
            event.message.channel.sendMessageQueue(MessageUtil.error("Invalid amount / You do not have enough money to do that!"))
            return
        }


        event.message.channel.sendMessageQueue(
            MessageUtil.tell(
                "Landed on ${
                    if (flipResult) {
                        "heads"
                    } else {
                        "tails"
                    }
                }!"
            )
        )
        if (flipResult == guess) {
            event.message.channel.sendMessageQueue(MessageUtil.success("You won ${args[1].toLong() * 2} balance!"))
            EconomyUtil.give(event.member?.user ?: return, args[1].toLong() * 2)
        } else {
            event.message.channel.sendMessageQueue(MessageUtil.error("You lost ${args[1]} balance!"))
            EconomyUtil.withdraw(event.member?.user ?: return, args[1].toLong())
        }
    }
}