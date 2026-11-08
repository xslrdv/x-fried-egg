package com.xslrdv.xfriedegg.registry;

import java.util.List;

import com.xslrdv.xfriedegg.XFriedEgg;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(XFriedEgg.MODID);

    public static final DeferredItem<Item> FRIED_EGG = ITEMS.registerSimpleItem("fried_egg",
    () -> new Item.Properties().food(
        new FoodProperties.Builder()
                .nutrition(4) // 4 eat
                .saturationModifier(0.75F) // 6 eat saturation, formula nutrition * saturationModifier * 2 = eat saturation
                .build()
    ));

    public static final DeferredItem<Item> GOLDEN_FRIED_EGG = ITEMS.registerSimpleItem("golden_fried_egg",
        () -> new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(4) // 4 eat
                    .saturationModifier(1) // 8 eat saturation
                    .alwaysEdible()
                    .build(),
            Consumables.defaultFood()
                    .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
                            new MobEffectInstance(MobEffects.REGENERATION, 100, 0)
                    )))
                    .build()
        ));
}
