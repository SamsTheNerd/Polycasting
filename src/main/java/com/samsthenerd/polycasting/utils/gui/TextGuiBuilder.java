package com.samsthenerd.polycasting.utils.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TextGuiBuilder {
    private MutableComponent rawText = null;

    public TextGuiBuilder(int xOffset, int yOffset){
        rawText = TextGUIUtils.makeNegativeSpace(xOffset).copy();
    }

    public TextGuiBuilder addText(Component text, int x, int y){
        rawText.append(TextGUIUtils.makeNegativeSpace(x));
        rawText.append(text);
        return this;
    }

    public TextGuiBuilder addTexture(TGuiTexture texture, int x, int y){
        rawText.append(TextGUIUtils.makeSpace(x));
        rawText.append(texture.getAsText());
        rawText.append(TextGUIUtils.makeNegativeSpace(texture.width()));
        return this;
    }

    // or this could be list of texts?
    public Component getAsText(){
        return rawText;
    }
}
