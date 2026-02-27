package com.hiword9.mtd_offline_addon

import net.lopymine.mtd.MyTotemDoll
import net.lopymine.mtd.api.Response
import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.skin.data.ParsedSkinData
import net.lopymine.mtd.skin.provider.StandardSkinProvider
import net.minecraft.resources.Identifier

object OfflineModeSkinProvider : StandardSkinProvider(false) {
    override fun loadDollFromAPI(nickname: String): Response<ParsedSkinData?>? {
        val profile = getPlayerInfo(nickname) ?: return Response.empty(404)

        val textures = getTextures(profile.profile)
        if (textures.skin == null) return Response.empty(404)

        val data = profileTexturesToParsedSkinData(textures, profile.skin.model)
        updateCache(nickname, data)
        return Response.of(200, data)
    }

    override fun createNewDoll(nickname: String?): TotemDollData?
    = TotemDollData.create(nickname)

    override fun getId(value: String, type: String?): Identifier
    = MyTotemDoll.getDollTextureId("mtd_offline_addon/$type/${value.lowercase()}")

    override fun canProcess(nickname: String) = isOnServer(nickname)
}
