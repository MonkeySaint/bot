package dev.skrub.thunderhead.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.skrub.thunderhead.dataclass.economy.Economy
import dev.skrub.thunderhead.dataclass.economy.Human
import dev.skrub.thunderhead.dataclass.misc.BooleanOrLong
import net.dv8tion.jda.api.entities.User
import java.io.File
import kotlin.math.abs

object EconomyUtil {
    private val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

    var economyFromYaml: Economy
        private set

    fun getBalance(user: User): Int {
        economyFromYaml.users.forEach {
            if (it.id == user.idLong) {
                return it.balance
            }
        }

        return -1
    }

    fun deposit(user: User, amount: Int): Boolean {
        economyFromYaml.users.forEach {
            if (it.id == user.idLong) {
                it.balance += amount
                save()
                return true
            }
        }
        return false
    }

    fun withdraw(user: User, amount: Int): Boolean {
        economyFromYaml.users.forEach {
            if (it.id == user.idLong) {
                it.balance -= amount
                save()
                return true
            }
        }
        return false
    }

    fun getDaily(user: User): BooleanOrLong {
        val currentTime = System.currentTimeMillis() / 1000
        economyFromYaml.users.forEach {
            if (it.id == user.idLong) {
                return if ((it.lastDaily + 86400) - currentTime <= 0L) {
                    it.balance += 100
                    it.lastDaily = currentTime
                    save()
                    BooleanOrLong(true, 0)
                } else {
                    BooleanOrLong(false, abs(((it.lastDaily + 86400) - currentTime)))
                }
            }
        }

        // User is not registered
        return BooleanOrLong(false, -1L)
    }

    private fun save() {
        File(".economy.yaml").writeText(mapper.writeValueAsString(economyFromYaml))
    }

    private fun createFakeUser() {
        mapper.writeValue(File(".economy.yaml"), Economy(arrayListOf(Human(12345, 12345, 0, 0))))
    }

    fun createNewUser(user: User) {
        economyFromYaml.users.add(
            Human(
                user.idLong,
                0,
                (System.currentTimeMillis() / 1000) - 86400,
                (System.currentTimeMillis() / 1000) - 86400
            )
        )
        save()
    }

    fun canSteal(user: User): Boolean {
        val target = economyFromYaml.users.firstOrNull { it.id == user.idLong } ?: return false
        println((target.lastSteal + 7200) - System.currentTimeMillis() / 1000)
        return target.lastSteal != -1L && ((target.lastSteal + 7200) - System.currentTimeMillis() / 1000) < 0L && target.balance >= 200
    }

    fun updateRobTime(user: User): Boolean {
        economyFromYaml.users.firstOrNull { it.id == user.idLong }?.lastSteal = (System.currentTimeMillis() / 1000)
        save()
        return true
    }

    init {
        economyFromYaml = try {
            mapper.readValue(File(".economy.yaml"), Economy::class.java)
        } catch (e: Exception) {
            createFakeUser()
            mapper.readValue(File(".economy.yaml"), Economy::class.java)
        }
    }
}