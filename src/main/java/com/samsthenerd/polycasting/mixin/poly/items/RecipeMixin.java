package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.common.recipe.BrainsweepRecipe;
import at.petrak.hexcasting.common.recipe.SealThingsRecipe;
import eu.pb4.polymer.core.api.item.PolymerRecipe;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.patchouli.common.recipe.ShapedBookRecipe;
import vazkii.patchouli.common.recipe.ShapelessBookRecipe;

@Mixin({
    BrainsweepRecipe.class, SealThingsRecipe.class, ShapedBookRecipe.class, ShapelessBookRecipe.class
})
public class RecipeMixin implements PolymerRecipe {
}
