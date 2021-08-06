package dev.skrub.thunderhead.info

import dev.skrub.thunderhead.command.Command

object HelpInfo {
    val page = listOf(
        Page("Misc Commands", "🤪"),
        Page("Moderation Commands", "🔨"),
        Page("Economy Commands", "💰")
    )
}

class Page(val name: String, val emoji: String) {
    val commands: ArrayList<Command> = arrayListOf()
}