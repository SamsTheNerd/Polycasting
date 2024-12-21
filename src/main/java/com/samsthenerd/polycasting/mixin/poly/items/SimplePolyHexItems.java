package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.common.items.ItemStaff;
import at.petrak.hexcasting.common.items.magic.ItemArtifact;
import at.petrak.hexcasting.common.items.magic.ItemCypher;
import at.petrak.hexcasting.common.items.magic.ItemTrinket;
import at.petrak.hexcasting.common.items.storage.ItemFocus;
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

@Mixin({ItemStaff.class, ItemFocus.class, ItemArtifact.class, ItemCypher.class, ItemTrinket.class})
public class SimplePolyHexItems implements AutoModeledPolymerItem {

    @Override
    public Item getPolymerItem() {
        return Items.STICK;
    }

}