package me.humboldt123.thunderhead.util

import com.google.gson.Gson
import java.io.File

val config: Config = Gson().fromJson(File("setting.json").readText(), Config::class.java)

data class Config(
    var token: String? = null,
    var prefix: String? = null
)

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