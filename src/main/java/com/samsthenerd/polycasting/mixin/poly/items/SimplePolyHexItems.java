package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.common.items.ItemStaff;
import at.petrak.hexcasting.common.items.magic.ItemArtifact;
import at.petrak.hexcasting.common.items.magic.ItemCypher;
import at.petrak.hexcasting.common.items.magic.ItemTrinket;
import at.petrak.hexcasting.common.items.storage.ItemFocus;
import com.samsthenerd.polycasting.utils.poly.AutoModeledPolymerItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({ItemStaff.class, ItemFocus.class, ItemArtifact.class, ItemCypher.class, ItemTrinket.class})
public class SimplePolyHexItems implements AutoModeledPolymerItem {

    @Override
    public Item getPolymerItem() {
        return Items.STICK;
    }
}