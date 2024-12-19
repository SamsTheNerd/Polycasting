package com.samsthenerd.polycasting.utils.poly;

import net.minecraft.resources.ResourceLocation;
import org.intellij.lang.annotations.Identifier;

public interface RegistryCallbackItem {
    void onRegistered(ResourceLocation selfId);
}