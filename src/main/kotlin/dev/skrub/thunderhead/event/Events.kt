package dev.skrub.thunderhead.event

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class onGuildMessageReceived(val event: GuildMessageReceivedEvent) : Event()