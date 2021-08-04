package dev.skrub.thunderhead.util

import dev.skrub.thunderhead.info.ColorInfo
import dev.skrub.thunderhead.info.EmojiInfo
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

object MessageUtil {
    fun error(string: String): MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.red).setTitle("${EmojiInfo.warn} $string").build()
    }

    fun error(string: String, extra: String): MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.red).setTitle("${EmojiInfo.warn} $string")
            .addField("Extra Info", "```$extra```", false).build()
    }

    fun success(string: String): MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.green).setTitle("${EmojiInfo.check} $string").build()
    }

    fun tell(string: String): MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.gulf).setTitle(string).build()
    }

    fun answer(string: String): MessageEmbed {
        return EmbedBuilder().setColor(ColorInfo.thunderhead).setTitle("${EmojiInfo.thunderhead} $string").build()
    }
}