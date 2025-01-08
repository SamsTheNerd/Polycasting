package com.samsthenerd.polycasting.impl;

import com.samsthenerd.polycasting.utils.gui.TGuiTexture;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class PolycastingTextures {
    public static final ResourceLocation SPELLGRID_FONT = Polycasting.resLoc("spellgrid");
    public static final TGuiTexture GRID_BACKGROUND = new TGuiTexture(SPELLGRID_FONT, 'b', 240, 256, 0);

    public static final PolymerModelData EMPTY_MODEL_DATA = PolymerResourcePackUtils.requestModel(Items.WHITE_STAINED_GLASS_PANE, Polycasting.resLoc("sgui/empty"));

    public static void init(){}
}
