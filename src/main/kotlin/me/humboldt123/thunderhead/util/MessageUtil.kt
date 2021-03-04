package me.humboldt123.thunderhead.util

import me.humboldt123.thunderhead.info.ColorInfo
import me.humboldt123.thunderhead.info.EmojiInfo
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

object MessageUtil {
    fun error(string: String) : MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.red).setTitle("${EmojiInfo.warn} $string").build()
    }
    fun success(string: String) : MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.green).setTitle("${EmojiInfo.check} $string").build()
    }
    fun tell(string: String) : MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.discord).setTitle(string).build()
    }
    fun answer(string: String) : MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.thunderhead).setTitle("${EmojiInfo.thunderhead} $string").build()
    }
}