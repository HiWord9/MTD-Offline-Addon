package com.hiword9.mtd_offline_addon.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.gui.screens.Screen

object ModMenuIntegration : ModMenuApi {
    @Override
    override fun getModConfigScreenFactory(): ConfigScreenFactory<Screen> {
        return ScreenFactory()
    }
}