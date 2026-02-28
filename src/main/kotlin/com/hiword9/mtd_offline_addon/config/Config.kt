package com.hiword9.mtd_offline_addon.config

import com.google.gson.GsonBuilder
import net.fabricmc.loader.api.FabricLoader
import java.io.FileReader
import java.io.FileWriter

class Config {
    companion object {
        private val gson = GsonBuilder().setPrettyPrinting().create()
        private val file = FabricLoader.getInstance().configDir.resolve("mtd_offline_addon.json").toFile()

        val DEFAULT: Config = Config()
        var INSTANCE: Config = load()

        fun load(): Config {
            if (!file.exists()) initConfigFile()
            return FileReader(file).use { gson.fromJson(it, Config::class.java) }
        }

        private fun initConfigFile() = DEFAULT.save()
    }

    fun save() {
        FileWriter(file).use { gson.toJson(this, it) }
    }
}