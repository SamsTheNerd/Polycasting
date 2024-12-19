package com.samsthenerd.polycasting.mixin.poly.items;

import at.petrak.hexcasting.common.lib.HexItems;
import com.samsthenerd.polycasting.utils.poly.AutoModeledPolymerItem;
import com.samsthenerd.polycasting.utils.poly.RegistryCallbackItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HexItems.class)
public class HexItemsRegistryMixin {
    @Inject(at=@At("HEAD"), method="Lat/petrak/hexcasting/common/lib/HexItems;make(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/CreativeModeTab;)Lnet/minecraft/world/item/Item;")
    private static <T extends Item> void registerPolymerItems(ResourceLocation id, T item, @Nullable CreativeModeTab tab, CallbackInfoReturnable<T> ci){
        if(item instanceof RegistryCallbackItem regCall){
            regCall.onRegistered(id);
        }
    }
}
