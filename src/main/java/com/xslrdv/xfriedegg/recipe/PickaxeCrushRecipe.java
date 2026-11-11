package com.xslrdv.xfriedegg.recipe;

import java.util.function.Predicate;
import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import com.xslrdv.xfriedegg.registry.ModItems;

import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class PickaxeCrushRecipe extends CustomRecipe {

    // lapis lazili to lapis_lazuli_dust
    public static final PickaxeCrushRecipe LAPIS_LAZULI = new PickaxeCrushRecipe(
            stack -> stack.is(Items.LAPIS_LAZULI),
            () -> ModItems.LAPIS_LAZULI_DUST.get());


    public static final RecipeSerializer<PickaxeCrushRecipe> LAPIS_LAZULI_SERIALIZER =
            new RecipeSerializer<>(MapCodec.unit(LAPIS_LAZULI), StreamCodec.unit(LAPIS_LAZULI));

    private final Predicate<ItemStack> material;
    private final Supplier<Item> result;

    private PickaxeCrushRecipe(Predicate<ItemStack> material, Supplier<Item> result) {
        this.material = material;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasPickaxe = false;
        boolean hasMaterial = false;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.is(ItemTags.PICKAXES) && !hasPickaxe) {
                hasPickaxe = true;
            } else if (material.test(stack) && !hasMaterial) {
                hasMaterial = true;
            } else {
                return false;
            }
        }
        return hasPickaxe && hasMaterial;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return new ItemStack(result.get());
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(ItemTags.PICKAXES) && stack.isDamageableItem()) {
                ItemStack copy = stack.copy();
                copy.setCount(1);
                copy.setDamageValue(copy.getDamageValue() + 1);
                if (copy.getDamageValue() < copy.getMaxDamage()) {
                    remaining.set(i, copy);
                }
            }
        }
        return remaining;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return LAPIS_LAZULI_SERIALIZER;
    }
}
