package com.hiword9.mtd_offline_addon

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Path

open class CacheFileManager<T>(val clazz: Class<T>, val path: Path) {
    protected val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    protected var filenameSuffix = ".json"

    fun update(key: String, data: T?) {
        if (data == null) delete(key)
        else write(key, data)
    }

    fun load(): Map<String, T> {
        val map = mutableMapOf<String, T>()
        val files = path.toFile().listFiles() ?: return map
        for (file in files) {
            val key = file.name.removeSuffix(filenameSuffix)
            map[key] = read(key) ?: continue
        }
        return map
    }

    protected fun read(key: String): T? {
        val file = fileForNickname(key)
        if (!file.exists()) return null
        return FileReader(file).use { gson.fromJson(it, clazz) }
    }

    protected fun write(key: String, data: T) {
        val file = fileForNickname(key)
        file.parentFile.mkdirs()
        FileWriter(file).use { gson.toJson(data, it) }
    }

    protected fun delete(key: String) {
        val file = fileForNickname(key)
        if (!file.exists()) return
        file.delete()
    }

    protected fun fileForNickname(key: String): File {
        return path.resolve("$key$filenameSuffix").toFile()
    }
}