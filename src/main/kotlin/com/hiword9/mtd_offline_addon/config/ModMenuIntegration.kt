package com.hiword9.mtd_offline_addon.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.terraformersmc.modmenu.util.NullScreenFactory
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.gui.screens.Screen

object ModMenuIntegration : ModMenuApi {
    @Override
    override fun getModConfigScreenFactory(): ConfigScreenFactory<Screen> {
        if (!FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3"))
            return NullScreenFactory()
        return ScreenFactory
    }
}