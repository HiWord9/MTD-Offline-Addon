package com.hiword9.mtd_offline_addon

import com.llamalad7.mixinextras.injector.wrapoperation.Operation
import net.lopymine.mtd.doll.data.TotemDollData
import net.lopymine.mtd.skin.provider.extended.MojangSkinProvider
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import java.util.concurrent.CompletableFuture

fun onApplyPlayerInfoUpdate(
    action: Action,
    entry: ClientboundPlayerInfoUpdatePacket.Entry?,
    playerInfo: PlayerInfo,
    ci: CallbackInfo?
) {
    when (action) {
        Action.ADD_PLAYER,
        Action.UPDATE_LISTED,
        Action.UPDATE_DISPLAY_NAME ->
            reload(playerInfo)
        else -> {}
    }
}

fun onGetOrLoadDoll(
    mojangSkinProvider: MojangSkinProvider?,
    nickname: String,
    original: Operation<TotemDollData>
): TotemDollData = MojangSkinProviderWrapper.getDoll(nickname)

private fun reload(playerInfo: PlayerInfo): CompletableFuture<Void?>?
= OfflineModeSkinProvider.reloadOne(playerInfo.profile.name())