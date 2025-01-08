package com.samsthenerd.polycasting.utils.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

public record TGuiTexture(ResourceLocation fontResLoc, char repChar, int width, int height, int ascent){

    public Component getAsText(){
        return Component.literal(Character.toString(repChar)).withStyle(Style.EMPTY.withFont(fontResLoc).withColor(ChatFormatting.WHITE));
    }
}
