package com.samsthenerd.polycasting.utils.poly;

import net.minecraft.resources.ResourceLocation;

public interface RegistryCallbackBlock {
    void onRegistered(ResourceLocation selfId);
}
