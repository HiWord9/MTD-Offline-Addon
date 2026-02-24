package com.hiword9.mtd_offline_addon.mixin;

import com.hiword9.mtd_offline_addon.OfflineModeSkinProvider;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "applyPlayerInfoUpdate", at = @At("HEAD"))
    void onApplyPlayerInfoUpdate(
        Action action,
        ClientboundPlayerInfoUpdatePacket.Entry entry,
        PlayerInfo playerInfo,
        CallbackInfo ci
    ) {
        switch (action) {
            case Action.ADD_PLAYER,
                 Action.UPDATE_LISTED,
                 Action.UPDATE_DISPLAY_NAME
                    -> onReload(playerInfo);
        }
    }

    @Unique
    void onReload(PlayerInfo playerInfo) {
        OfflineModeSkinProvider.INSTANCE.reloadOne(playerInfo.getProfile().name());
    }
}
