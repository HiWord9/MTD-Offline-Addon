package com.hiword9.mtd_offline_addon.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.isxander.yacl3.api.controller.ControllerBuilder
import dev.isxander.yacl3.gui.controllers.TickBoxController
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

class ScreenFactory : ConfigScreenFactory<Screen> {
    override fun create(parentScreen: Screen?): Screen {
        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("mtd-offline-addon.config.title"))
            .category { ConfigCategory.createBuilder()
                .name(Component.translatable("mtd-offline-addon.config.category.main"))
                .option { Option.createBuilder<Boolean>()
                    .name(Component.translatable("mtd-offline-addon.config.main.modEnabled"))
                    .binding(
                        Config.DEFAULT.modEnabled,
                        { Config.INSTANCE.modEnabled },
                        { Config.INSTANCE.modEnabled = it }
                    )
                    .controller { ControllerBuilder { TickBoxController(it) } }
                    .build()
                }
                .option { Option.createBuilder<Boolean>()
                    .name(Component.translatable("mtd-offline-addon.config.main.shouldPrioritizeCache"))
                    .binding(
                        Config.DEFAULT.shouldPrioritizeCache,
                        { Config.INSTANCE.shouldPrioritizeCache },
                        { Config.INSTANCE.shouldPrioritizeCache = it }
                    )
                    .controller { ControllerBuilder { TickBoxController(it) } }
                    .build()
                }
                .build()
            }
            .save { Config.INSTANCE.save() }
            .build()
            .generateScreen(parentScreen)
    }
}