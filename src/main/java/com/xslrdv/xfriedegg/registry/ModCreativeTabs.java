package com.xslrdv.xfriedegg.registry;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class ModCreativeTabs {
    private static void addToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.FRIED_EGG);
            event.accept(ModItems.GOLDEN_FRIED_EGG);
            event.accept(ModItems.ENCHANTED_GOLDEN_FRIED_EGG);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.LAPIS_LAZULI_DUST);
        }
    }
	
	public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModCreativeTabs::addToTabs);
    }
}
