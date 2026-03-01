package com.hiword9.mtd_offline_addon.util.cache

open class CacheManager<T>(val fileManager: CacheFileManager<T>) {
    protected val data = fileManager.load().toMutableMap()

    operator fun set(key: String, value: T?) = write(key, value)
    operator fun get(key: String) = read(key)

    fun read(key: String): T? = data[key]

    fun write(key: String, data: T?) {
        if (data == null) this.data.remove(key)
        else this.data[key] = data

        fileManager.update(key, data)
    }
}