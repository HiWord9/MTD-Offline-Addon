package com.hiword9.mtd_offline_addon

import net.fabricmc.api.ClientModInitializer
import net.lopymine.mtd.tag.manager.TagsSkinProviders

class MTDOfflineAddon : ClientModInitializer {
    companion object {
        val skinProvider: OfflineModeSkinProvider = OfflineModeSkinProvider(false)
    }

    override fun onInitializeClient() {
        TagsSkinProviders.registerProvider("OfflineMode", skinProvider)
    }
}
