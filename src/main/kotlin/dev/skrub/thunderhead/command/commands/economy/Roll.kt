package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color
import kotlin.random.Random

class Roll : Command("roll", "Rolls a dice", listOf("[guess number(1-6)]", "[amount of money to bet]"), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        if (EconomyUtil.getBalance(event.member?.user ?: return) == -1) {
            event.message.channel.sendMessageQueue(MessageUtil.error("You are not registered! Please run register command first!"))
            return
        }
        val guessNum = args[0].toIntOrNull() ?: kotlin.run {
            event.message.channel.sendMessageQueue(MessageUtil.error("Invalid number!"))
        }
        val randomNum = Random.nextInt(1, 6)

        if (EconomyUtil.getBalance(
                event.member?.user ?: return
            ) >= args[1].toIntOrNull() ?: kotlin.run { event.message.channel.sendMessageQueue(MessageUtil.error("Invalid amount!")); return }
        ) {
            if (guessNum == randomNum) {
                event.message.channel.sendMessageQueue(
                    EmbedBuilder()
                        .setTitle("You won!")
                        .setColor(Color.GREEN)
                        .setDescription("You guessed: $guessNum, dice rolled: $randomNum")
                        .addField("", "You won ${args[1].toInt() * 6}!", false)
                        .build()
                )
                EconomyUtil.give(event.member?.user ?: return, (args[1].toInt() * 6))
            } else {
                event.message.channel.sendMessageQueue(
                    EmbedBuilder()
                        .setTitle("You lost!")
                        .setColor(Color.RED)
                        .setDescription("You guessed: $guessNum, dice rolled: $randomNum")
                        .addField("", "You lost ${args[1]}!", false)
                        .build()
                )
                EconomyUtil.withdraw(event.member?.user ?: return, args[1].toInt())
            }
        } else {
            event.message.channel.sendMessageQueue("You do not have enough money!")
        }
    }
}