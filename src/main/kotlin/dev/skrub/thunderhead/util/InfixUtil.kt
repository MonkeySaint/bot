package dev.skrub.thunderhead.util

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.MessageEmbed
import java.net.URL

object InfixUtil {
    operator fun String.times(factor: Int) = this.repeat(factor)

    fun MessageChannel.sendMessageQueue(message: Message) = this.sendMessage(message).queue()
    fun MessageChannel.sendMessageQueue(message: CharSequence) = this.sendMessage(message).queue()
    fun MessageChannel.sendMessageQueue(message: MessageEmbed) = this.sendMessage(message).queue()

    fun MessageChannel.sendMessageComplete(message: Message) = this.sendMessage(message).complete()
    fun MessageChannel.sendMessageComplete(message: CharSequence) = this.sendMessage(message).complete()
    fun MessageChannel.sendMessageComplete(message: MessageEmbed) = this.sendMessage(message).complete()

    fun String.isValidUrl(): Boolean {
        return try {
            URL(this).toURI()
            true
        } catch (e: Exception) {
            false
        }
    }
}