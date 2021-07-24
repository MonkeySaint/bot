package dev.skrub.thunderhead

import dev.skrub.thunderhead.util.getToken

object Launch {
    @JvmStatic
    fun main(args: Array<String>) {
        val thunderhead = Instance(getToken())
        thunderhead.start()
    }
}