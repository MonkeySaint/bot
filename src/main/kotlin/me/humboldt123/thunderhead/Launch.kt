package me.humboldt123.thunderhead

import me.humboldt123.thunderhead.util.getToken

object Launch {
    @JvmStatic
    fun main(args: Array<String>) {
        val thunderhead = Instance(getToken())
        thunderhead.start()
    }
}