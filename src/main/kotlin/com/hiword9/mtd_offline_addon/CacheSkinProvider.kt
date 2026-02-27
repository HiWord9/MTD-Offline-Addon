package com.hiword9.mtd_offline_addon

import com.hiword9.mtd_offline_addon.MTDOfflineAddon.Companion.CACHE_MANAGER
import net.lopymine.mtd.MyTotemDoll
import net.lopymine.mtd.api.Response
import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.skin.data.ParsedSkinData
import net.lopymine.mtd.skin.provider.StandardSkinProvider
import net.minecraft.resources.Identifier

object CacheSkinProvider : StandardSkinProvider(false) {
    override fun loadDollFromAPI(nickname: String): Response<ParsedSkinData?>? {
        val cache = CACHE_MANAGER[nickname]
            ?: return Response.empty(404)
        return Response.of(200, cache)
    }

    override fun createNewDoll(nickname: String?): TotemDollData?
    = TotemDollData.create(nickname)

    override fun getId(value: String, type: String?): Identifier
    = MyTotemDoll.getDollTextureId("mtd_offline_addon_cache/$type/${value.lowercase()}")

    override fun canProcess(nickname: String): Boolean
    = CACHE_MANAGER[nickname] != null
}

fun updateCache(nickname: String, data: ParsedSkinData) {
    CACHE_MANAGER[nickname] = data
    CacheSkinProvider.cache.remove(nickname)
}