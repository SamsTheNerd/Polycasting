package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.common.items.ItemJewelerHammer;
import at.petrak.hexcasting.common.items.ItemLens;
import at.petrak.hexcasting.common.items.ItemLoreFragment;
import at.petrak.hexcasting.common.items.ItemStaff;
import at.petrak.hexcasting.common.items.magic.*;
import at.petrak.hexcasting.common.items.pigment.ItemAmethystAndCopperPigment;
import at.petrak.hexcasting.common.items.pigment.ItemDyePigment;
import at.petrak.hexcasting.common.items.pigment.ItemPridePigment;
import at.petrak.hexcasting.common.items.pigment.ItemUUIDPigment;
import at.petrak.hexcasting.common.items.storage.*;
import com.samsthenerd.polycasting.impl.Polycasting;
import com.samsthenerd.polycasting.utils.poly.AutoModeledPolymerItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({ItemStaff.class, ItemFocus.class, ItemArtifact.class, ItemCypher.class, ItemTrinket.class,
    ItemAbacus.class, ItemSpellbook.class, ItemLens.class, ItemThoughtKnot.class, ItemJewelerHammer.class,
    ItemScroll.class, ItemSlate.class, ItemMediaBattery.class, ItemLoreFragment.class, ItemCreativeUnlocker.class,
    ItemPridePigment.class, ItemDyePigment.class, ItemUUIDPigment.class, ItemAmethystAndCopperPigment.class
})
public class SimplePolyHexItems implements AutoModeledPolymerItem {

    @Override
    public Item getPolymerItem() {
        return Items.STICK;
    }

}