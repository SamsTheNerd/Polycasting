package com.samsthenerd.polycasting.mixin.poly.blocks;

import at.petrak.hexcasting.common.lib.HexBlocks;
import com.mojang.datafixers.util.Pair;
import com.samsthenerd.polycasting.utils.poly.AutoPolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;


@Mixin(value = HexBlocks.class, remap = false)
public class HexBlocksRegistryMixin {

    @Shadow
    @Final
    private static final Map<ResourceLocation, Pair<Block, Properties>> BLOCK_ITEMS = null;

    @Inject(
        at=@At("HEAD"),
        method="Lat/petrak/hexcasting/common/lib/HexBlocks;registerBlockItems(Ljava/util/function/BiConsumer;)V",
        cancellable = true
    )
    private static void polymerizeBlockItems(BiConsumer<Item, ResourceLocation> r, CallbackInfo ci){
        var itr = BLOCK_ITEMS.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<ResourceLocation, Pair<Block, Item.Properties>> e = (Map.Entry)itr.next();
            r.accept(new AutoPolymerBlockItem((e.getValue()).getFirst(), (e.getValue()).getSecond(), Items.AIR),
                e.getKey());
        }
    }
}