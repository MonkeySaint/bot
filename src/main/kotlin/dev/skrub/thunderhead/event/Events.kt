package dev.skrub.thunderhead.event

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import me.humboldt123.thunderhead.event.Event

class onGuildMessageReceived(val event: GuildMessageReceivedEvent) : Event()