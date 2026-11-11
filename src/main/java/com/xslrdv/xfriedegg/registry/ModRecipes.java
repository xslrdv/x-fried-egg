package com.xslrdv.xfriedegg.registry;

import com.xslrdv.xfriedegg.XFriedEgg;
import com.xslrdv.xfriedegg.recipe.PickaxeCrushRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, XFriedEgg.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PickaxeCrushRecipe>> PICKAXE_LAPIS_LAZULI =
        SERIALIZERS.register("pickaxe_lapis_lazuli", () -> PickaxeCrushRecipe.LAPIS_LAZULI_SERIALIZER);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
