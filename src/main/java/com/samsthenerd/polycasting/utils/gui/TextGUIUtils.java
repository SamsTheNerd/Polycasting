package com.samsthenerd.polycasting.utils.gui;

import com.samsthenerd.polycasting.impl.Polycasting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

public class TextGUIUtils {
    public static final ResourceLocation NEGATIVE_SPACING_FONT = Polycasting.resLoc("negativespacing");
    public static final ResourceLocation SPACING_FONT = Polycasting.resLoc("spacing");

    public static Component makeNegativeSpace(int offset){
        return makeSomeSpace(offset, true);
    }

    public static Component makeSpace(int offset){
        return makeSomeSpace(offset, false);
    }

    private static Component makeSomeSpace(int offset, boolean negative){
        MutableComponent nspace = Component.empty();
        for(int i = 7; i >= 0; i--){
            int ofs = (int)Math.pow(2, i);
            for(int j = 0; j < (offset/ofs); j++){
                nspace.append(Component.literal(Integer.toString(i)).withStyle(Style.EMPTY.withFont(negative ? NEGATIVE_SPACING_FONT : SPACING_FONT)));
                // to cancel out default space.
                nspace.append(Component.literal("2").withStyle(Style.EMPTY.withFont(NEGATIVE_SPACING_FONT)));
            }
            offset %= ofs;
        }
        return nspace;
    }
}
