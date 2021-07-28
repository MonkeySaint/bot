package dev.skrub.thunderhead.command.commands.misc

import com.google.gson.Gson
import dev.skrub.thunderhead.command.Command
import dev.skrub.thunderhead.dataclass.weather.Weather
import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.util.InfixUtil.sendMessageQueue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.net.URL

class Weather : Command("weather", "Gets weather for specified location", listOf("location"), 1, listOf()) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        val weather = Gson().fromJson(
            URL("http://wttr.in/~${args[0].replace(" ", "+")}?m&format=j1").readText(),
            Weather::class.java
        )
        event.message.channel.sendMessageQueue(
            EmbedBuilder()
                .setTitle("Weather in ${args[0]}")
                .setColor(ColorInfo.gulf)
                .setDescription(weather.current_condition[0].weatherDesc[0].value)
                .addField("Observation Time:", weather.current_condition[0].observation_time, false)
                .addField(
                    "Precipitation:",
                    "${weather.current_condition[0].precipMM}mm/${weather.current_condition[0].precipInches}",
                    false
                )
                .addField(
                    "Temperature:",
                    "${weather.current_condition[0].temp_C}°C/${weather.current_condition[0].temp_F}°F",
                    true
                )
                .addField(
                    "Feels Like:",
                    "${weather.current_condition[0].FeelsLikeC}°C/${weather.current_condition[0].FeelsLikeF}°F",
                    true
                )
                .build()
        )
    }
}