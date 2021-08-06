package dev.skrub.thunderhead.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.skrub.thunderhead.dataclass.config.Config
import java.io.File

val config: Config =
    ObjectMapper(YAMLFactory()).registerKotlinModule().readValue((File("settings.yaml")), Config::class.java)

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