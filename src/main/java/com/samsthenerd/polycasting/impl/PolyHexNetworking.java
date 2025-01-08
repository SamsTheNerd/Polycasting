package com.samsthenerd.polycasting.impl;

import at.petrak.hexcasting.api.casting.eval.ResolvedPattern;
import at.petrak.hexcasting.common.msgs.IMessage;
import at.petrak.hexcasting.common.msgs.MsgNewSpellPatternS2C;
import at.petrak.hexcasting.common.msgs.MsgOpenSpellGuiS2C;
import com.samsthenerd.polycasting.utils.gui.TextGUIUtils;
import com.samsthenerd.polycasting.utils.gui.TextGuiBuilder;
import eu.pb4.sgui.api.elements.BookElementBuilder;
import eu.pb4.sgui.api.gui.BookGui;
import eu.pb4.sgui.api.gui.SimpleGuiBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.List;

public class PolyHexNetworking {

    // handle faking appropriate things
    public static void handleS2C(ServerPlayer player, IMessage msg){
        if(msg instanceof MsgOpenSpellGuiS2C spellGuiS2C){
            new CastingGUIManager(player, spellGuiS2C).openScreen();
        } else if(msg instanceof MsgNewSpellPatternS2C newSpellS2C){
            CastingGUIManager.updatePlayerScreen(player, newSpellS2C);
        }
    }
}
