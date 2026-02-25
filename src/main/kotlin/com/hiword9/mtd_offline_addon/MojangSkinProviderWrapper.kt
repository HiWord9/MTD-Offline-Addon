package com.hiword9.mtd_offline_addon

import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.doll.manager.StandardTotemDollManager
import net.lopymine.mtd.skin.provider.extended.MojangSkinProvider

object MojangSkinProviderWrapper {
    fun getDoll(nickname: String): TotemDollData {
        var doll = OfflineModeSkinProvider.getOrLoadDoll(nickname)
        if (doll == StandardTotemDollManager.getStandardDoll()) {
            doll = MojangSkinProvider.getInstance().getOrLoadDoll(nickname)
        }
        return doll
    }
}