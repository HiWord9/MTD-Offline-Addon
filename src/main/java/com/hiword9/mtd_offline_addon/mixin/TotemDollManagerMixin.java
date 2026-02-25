package com.hiword9.mtd_offline_addon.mixin;

import com.hiword9.mtd_offline_addon.MojangSkinProviderWrapper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.lopymine.mtd.doll.data.TotemDollData;
import net.lopymine.mtd.doll.manager.TotemDollManager;
import net.lopymine.mtd.skin.provider.extended.MojangSkinProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TotemDollManager.class)
public class TotemDollManagerMixin {
    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/lopymine/mtd/skin/provider/extended/MojangSkinProvider;getOrLoadDoll(Ljava/lang/String;)Lnet/lopymine/mtd/doll/data/TotemDollData;"
            ),
            method = "getDoll"
    )
    private static TotemDollData onGetOrLoadDoll(
            MojangSkinProvider mojangSkinProvider,
            String nickname,
            Operation<TotemDollData> original
    ) {
        return MojangSkinProviderWrapper.INSTANCE.getDoll(nickname);
    }
}
