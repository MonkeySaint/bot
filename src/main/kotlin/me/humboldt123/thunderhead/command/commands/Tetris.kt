package me.humboldt123.thunderhead.command.commands

import com.google.gson.Gson
import me.humboldt123.thunderhead.command.Command
import me.humboldt123.thunderhead.dataclass.tetrio.User
import me.humboldt123.thunderhead.util.MessageUtil
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.math.floor

class Tetris : Command("tetrio", "utils", listOf("[user] <username>"), 1, listOf("tetris")) {
    override fun execute(args: List<String>, event: GuildMessageReceivedEvent) {
        when (args[0]) {
            "user" -> {
                val result = Gson().fromJson(URL("https://ch.tetr.io/api/users/${args[1]}").readText(), User::class.java)
                if (result.success) {
                    event.message.channel.sendMessage(EmbedBuilder()
                        .setAuthor(result.data?.user?.username, "https://ch.tetr.io/u/${args[1]}", "https://tetr.io/user-content/avatars/${result.data?.user?._id}.jpg?rv=${result.data?.user?.avatar_revision}")
                        .setImage("https://tetr.io/user-content/banners/${result.data?.user?._id}.jpg?rv=${result.data?.user?.banner_revision}")
                        .addField("Badges", if(result.data?.user?.badges?.joinToString { it.label }.isNullOrEmpty()) { "No Badges" } else { result.data?.user?.badges?.joinToString { it.label } ?: "No Badges!" }, true)
                        .addField("Bio", result.data?.user?.bio ?: "Bio not set!", true)
                        .addField("Country", result.data?.user?.country ?: "Country hidden / unknown", true)
                        .addField("Friends", result.data?.user?.friend_count.toString(), true)
                        .addField("Games Played", result.data?.user?.gamesplayed.toString(), true)
                        .addField("Games Won", result.data?.user?.gameswon.toString(), true)
                        .addField("Game Time", "${TimeUnit.SECONDS.toHours(floor(result.data?.user?.gametime ?: 0.00).toLong())} Hours", true)
                        .setColor(Color.MAGENTA)
                        .build()).queue()
                    event.message.channel.sendMessage(EmbedBuilder()
                        .addField("TR", floor(result.data?.user?.league?.rating ?: 0.00).toString(), true)
                        .addField("Rank", result.data?.user?.league?.rank?.toUpperCase(), true)
                        .addField("Glicko", "${floor(result.data?.user?.league?.glicko ?: 0.00)}±${floor(result.data?.user?.league?.rd ?: 0.00)}", true)
                        .addField("APM", result.data?.user?.league?.apm.toString(), true)
                        .addField("PPS", result.data?.user?.league?.pps.toString(), true)
                        .addField("VS", result.data?.user?.league?.vs.toString(), true)
                        .addField("Decaying / Growing", result.data?.user?.league?.decaying.toString(), true)
                        .addField("Games Played", result.data?.user?.league?.gamesplayed.toString(), true)
                        .addField("Games Won", result.data?.user?.league?.gameswon.toString(), true)
                        .setThumbnail(
                            when (result.data?.user?.league?.rank?.toUpperCase()) {
                                "X" -> "https://tetr.io/res/league-ranks/x.png"
                                "U" -> "https://tetr.io/res/league-ranks/u.png"
                                "SS" -> "https://tetr.io/res/league-ranks/ss.png"
                                "S+" -> "https://tetr.io/res/league-ranks/s+.png"
                                "S-" -> "https://tetr.io/res/league-ranks/s-.png"
                                "A+" -> "https://tetr.io/res/league-ranks/a+.png"
                                "A" -> "https://tetr.io/res/league-ranks/a.png"
                                "A-" -> "https://tetr.io/res/league-ranks/a-.png"
                                "B+" -> "https://tetr.io/res/league-ranks/b+.png"
                                "B" -> "https://tetr.io/res/league-ranks/b.png"
                                "B-" -> "https://tetr.io/res/league-ranks/b-.png"
                                "C+" -> "https://tetr.io/res/league-ranks/c+.png"
                                "C" -> "https://tetr.io/res/league-ranks/c.png"
                                "C-" -> "https://tetr.io/res/league-ranks/c-.png"
                                "D+" -> "https://tetr.io/res/league-ranks/d+.png"
                                "D" -> "https://tetr.io/res/league-ranks/d.png"
                                else -> ""
                            }
                        )
                        .setColor(when(result.data?.user?.league?.rank?.toUpperCase()) {
                            "X" -> Color.MAGENTA
                            "U" -> Color.RED
                            "SS", "S+", "S-" -> Color.YELLOW
                            "A+", "A", "A-" -> Color.GREEN
                            "B+", "B", "B-" -> Color.BLUE
                            "C+", "C", "C-" -> Color.PINK
                            "D" -> Color.GRAY
                            else -> Color.BLACK
                        })
                        .build()).queue()
                } else {
                    event.message.channel.sendMessage(MessageUtil.error("Error! User does not exist / tetrio is down.")).queue()
                }
            }
            else -> {
                event.message.channel.sendMessage(MessageUtil.error("Subcommand not found!")).queue()
            }
        }
    }
}
