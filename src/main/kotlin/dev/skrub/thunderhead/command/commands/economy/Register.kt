package dev.skrub.thunderhead.command.commands.economy

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.util.EconomyUtil
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import dev.skrub.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Register : Command("register", "Registers user", listOf(), 3, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        EconomyUtil.createNewUser(
            event.member?.user
                ?: kotlin.run { event.message.channel.sendMessageQueue(MessageUtil.error("User created")); return })
        event.message.channel.sendMessageQueue(MessageUtil.success("User created"))
    }
}