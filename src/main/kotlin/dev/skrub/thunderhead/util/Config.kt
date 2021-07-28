package dev.skrub.thunderhead.util

import com.google.gson.Gson
import dev.skrub.thunderhead.dataclass.config.Config
import java.io.File

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