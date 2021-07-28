package dev.skrub.thunderhead.command.commands.misc

import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.util.*

class Time : Command("time", "Sends the current time of some frequent timezones.", listOf(), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        event.message.channel.sendMessageQueue(
            EmbedBuilder()
                .setTitle("Current Time")
                .setColor(ColorInfo.gulf)
                .addField(
                    "US/Pacific Time",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT-7")); Date().toString() },
                    false
                )
                .addField(
                    "US/Mountain Time",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT-6")); Date().toString() },
                    false
                )
                .addField(
                    "US/Central Time",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT-5")); Date().toString() },
                    false
                )
                .addField(
                    "US/Eastern Time",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT-4")); Date().toString() },
                    false
                )
                .addField(
                    "Russia/Moscow",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT+3")); Date().toString() },
                    false
                )
                .addField(
                    "China/Beijing",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); Date().toString() },
                    false
                )
                .addField(
                    "Japan/Tokyo",
                    kotlin.run { TimeZone.setDefault(TimeZone.getTimeZone("GMT+9")); Date().toString() },
                    false
                )
                .build()
        )
    }
}