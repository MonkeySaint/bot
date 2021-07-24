package dev.skrub.thunderhead.info

object HelpInfo {
    val page = listOf<Page>(
        Page("Developer Commands", "☕"),
        Page("Commands", "📜"),
        Page("Economy Commands", EmojiInfo.currency),
        Page("Music Commands", "🎵"),
        Page("Utility Commands", "🔧")
    )
}
class Page(val name: String, val emoji: String)