package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.api.HexAPI;
import at.petrak.hexcasting.common.lib.HexBlocks;
import at.petrak.hexcasting.common.lib.HexCreativeTabs;
import com.mojang.datafixers.util.Pair;
import com.samsthenerd.polycasting.utils.poly.AutoPolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.core.impl.networking.packets.PolymerItemGroupContent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;


@Mixin(value = HexCreativeTabs.class, remap = false)
public class HexCreativeTabsMixin {
//    @Inject(
//        at=@At("HEAD"),
//        method="Lat/petrak/hexcasting/common/lib/HexCreativeTabs;registerCreativeTabs(Ljava/util/function/BiConsumer;)V",
//        cancellable = true
//    )
//    private static void polymerizeCreativeTab(BiConsumer<CreativeModeTab, ResourceLocation> r, CallbackInfo ci){
//        var itr = HexCreativeTabs.TABS.entrySet().iterator();
//
//        while(itr.hasNext()) {
//            Map.Entry<ResourceLocation, CreativeModeTab> e = (Map.Entry)itr.next();
//            r.accept((CreativeModeTab)e.getValue(), (ResourceLocation)e.getKey());
//        }
//    }

    @Inject(
        at=@At("HEAD"),
        method="Lat/petrak/hexcasting/common/lib/HexCreativeTabs;register(Ljava/lang/String;Lnet/minecraft/world/item/CreativeModeTab$Builder;)Lnet/minecraft/world/item/CreativeModeTab;",
        cancellable = true
    )
    private static void polymerizeHexTabs(String name, CreativeModeTab.Builder tabBuilder, CallbackInfoReturnable<CreativeModeTab> cir) {
        CreativeModeTab tab = tabBuilder.title(Component.translatable("itemGroup." + name)).build();
        PolymerItemGroupUtils.registerPolymerItemGroup(HexAPI.modLoc("hexcasting"), tab);
        cir.setReturnValue(tab);
    }
}
