package com.hiword9.mtd_offline_addon.mixin

import com.hiword9.mtd_offline_addon.MTDOfflineAddon
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(ClientPacketListener::class)
class ClientPacketListenerMixin {
    @Inject(method = ["applyPlayerInfoUpdate"], at = [At("HEAD")])
    fun onApplyPlayerInfoUpdate(
        action: Action,
        entry: ClientboundPlayerInfoUpdatePacket.Entry,
        playerInfo: PlayerInfo,
        ci: CallbackInfo
    ) {
        when (action) {
            Action.ADD_PLAYER,
            Action.UPDATE_LISTED,
            Action.UPDATE_DISPLAY_NAME
                -> onReload(playerInfo)
            else -> {}
        }
    }

    fun onReload(playerInfo: PlayerInfo) {
        MTDOfflineAddon.skinProvider.reloadOne(playerInfo.profile.name)
    }
}
