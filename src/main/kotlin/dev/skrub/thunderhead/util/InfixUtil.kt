package dev.skrub.thunderhead.util

object InfixUtil {
    operator fun String.times(factor: Int) = this.repeat(factor)
}