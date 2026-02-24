package com.hiword9.mtd_offline_addon

import com.mojang.authlib.minecraft.MinecraftProfileTextures
import net.lopymine.mtd.MyTotemDoll
import net.lopymine.mtd.api.Response
import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.skin.data.ParsedSkinData
import net.lopymine.mtd.skin.provider.StandardSkinProvider
import net.minecraft.client.Minecraft
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.PlayerModelType

object OfflineModeSkinProvider : StandardSkinProvider(false) {
    override fun loadDollFromAPI(nickname: String): Response<ParsedSkinData?>? {
        val profile = Minecraft.getInstance().connection?.getPlayerInfo(nickname)
            ?: return Response.empty(404)

        val textures = Minecraft.getInstance().services().sessionService.getTextures(profile.profile)
        if (textures.skin == null) return Response.empty(404)

        return Response.of(200, profileTexturesToParsedSkinData(textures, profile.skin.model))
    }

    override fun createNewDoll(nickname: String?): TotemDollData?
    = TotemDollData.create(nickname)

    override fun getId(value: String, type: String?): Identifier
    = MyTotemDoll.getDollTextureId("mtd_offline_addon/$type/${value.lowercase()}")

    override fun canProcess(s: String)
    = Minecraft.getInstance().connection?.getPlayerInfo(s) != null
}

fun profileTexturesToParsedSkinData(textures: MinecraftProfileTextures, modelType: PlayerModelType)
= ParsedSkinData(
    textures.skin?.url,
    textures.cape?.url,
    textures.elytra?.url,
    modelType.serializedName == "slim"
)