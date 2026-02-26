package com.hiword9.mtd_offline_addon

import com.mojang.authlib.GameProfile
import com.mojang.authlib.minecraft.MinecraftProfileTextures
import net.lopymine.mtd.skin.data.ParsedSkinData
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.player.PlayerModelType

fun profileTexturesToParsedSkinData(textures: MinecraftProfileTextures, modelType: PlayerModelType)
= ParsedSkinData(
    textures.skin?.url,
    textures.cape?.url,
    textures.elytra?.url,
    modelType.serializedName == "slim"
)

fun getTextures(profile: GameProfile): MinecraftProfileTextures
= Minecraft.getInstance().services().sessionService.getTextures(profile)

fun getPlayerInfo(nickname: String)
= Minecraft.getInstance().connection?.getPlayerInfo(nickname)

fun isOnServer(nickname: String)
= getPlayerInfo(nickname) != null