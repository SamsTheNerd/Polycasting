package com.samsthenerd.polycasting.utils.poly;


import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.intellij.lang.annotations.Identifier;

import java.util.IdentityHashMap;

// from factory tools
public interface AutoModeledPolymerItem extends PolymerItem, RegistryCallbackItem {
    IdentityHashMap<Object, PolymerModelData> MODELS = new IdentityHashMap<>();

    Item getPolymerItem();

    @Override
    default Item getPolymerItem(ItemStack itemStack, ServerPlayer player) {
        return this.getPolymerItem();
    }

    @Override
    default int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) {
        return getPolymerCustomModelData();
    }

    default int getPolymerCustomModelData() {
        var model = MODELS.get(this);
        if(model == null){
            return -1;
        }
        return model.value();
    }

    @Override
    default void onRegistered(ResourceLocation selfId) {
        var item = new ResourceLocation(selfId.getNamespace(), "item/" + selfId.getPath());
        MODELS.put(this, PolymerResourcePackUtils.requestModel(this.getPolymerItem(), item));
    }

}
