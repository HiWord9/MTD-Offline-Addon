package com.hiword9.mtd_offline_addon.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.YetAnotherConfigLib
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

class ScreenFactory : ConfigScreenFactory<Screen> {
    override fun create(parentScreen: Screen?): Screen {
        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("mtd-offline-addon.config.title"))
            .category { ConfigCategory.createBuilder()
                .name(Component.translatable("mtd-offline-addon.config.category.main"))
                .build()
            }
            .save { Config.INSTANCE.save() }
            .build()
            .generateScreen(parentScreen)
    }
}