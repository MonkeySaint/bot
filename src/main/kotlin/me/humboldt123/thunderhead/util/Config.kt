package me.humboldt123.thunderhead.util

import com.google.gson.Gson
import java.io.File
import me.humboldt123.thunderhead.dataclass.config.Config

val config: Config = Gson().fromJson(File("setting.json").readText(), Config::class.java)

fun getToken(): String {
    if (config.token.isNullOrEmpty())
        throw NoSuchElementException("Token not found! Please read the README for more details.")
    return config.token!!
}

fun getPrefix(): String {
    if (config.prefix.isNullOrEmpty())
        throw NoSuchElementException("Prefix not found! Please read the README for more details.")
    return config.prefix!!
}