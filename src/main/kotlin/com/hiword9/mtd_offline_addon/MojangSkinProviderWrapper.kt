package com.hiword9.mtd_offline_addon

import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.doll.manager.StandardTotemDollManager
import net.lopymine.mtd.skin.provider.extended.MojangSkinProvider

object MojangSkinProviderWrapper {
    fun getDoll(nickname: String): TotemDollData {
        if (isOnServer(nickname))
            return getOfflineDoll(nickname)

        if (!CacheSkinProvider.canProcess(nickname))
            return getMojangDoll(nickname)

        if (shouldPrioritizeCache())
            return getCacheDoll(nickname)

        val doll = getMojangDoll(nickname)
        if (doll == StandardTotemDollManager.getStandardDoll())
            return getCacheDoll(nickname)

        return doll
    }
}

fun getOfflineDoll(nickname: String): TotemDollData
= OfflineModeSkinProvider.getOrLoadDoll(nickname)

fun getCacheDoll(nickname: String): TotemDollData
= CacheSkinProvider.getOrLoadDoll(nickname)

fun getMojangDoll(nickname: String): TotemDollData
= MojangSkinProvider.getInstance().getOrLoadDoll(nickname)

fun shouldPrioritizeCache() = true