package dev.skrub.thunderhead.info

object HelpInfo {
    val page = listOf(
        Page("Misc Commands", "🤪"),
        Page("Moderation Commands", "🔨"),
        Page("Economy Commands", "💰")
    )
}

class Page(val name: String, val emoji: String)