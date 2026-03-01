package com.hiword9.mtd_offline_addon

import com.hiword9.mtd_offline_addon.util.cache.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.lopymine.mtd.skin.data.ParsedSkinData
import net.lopymine.mtd.tag.manager.TagsSkinProviders

object MTDOfflineAddon : ClientModInitializer {
    val CACHE_MANAGER = CacheManager(
        CacheFileManager(
            ParsedSkinData::class.java,
            FabricLoader.getInstance().configDir.resolve("mtd_offline_addon_cache")
        )
    )

    override fun onInitializeClient() {
        TagsSkinProviders.registerProvider("Offline", OfflineModeSkinProvider)
        TagsSkinProviders.registerProvider("OfflineCache", CacheSkinProvider)
    }
}
