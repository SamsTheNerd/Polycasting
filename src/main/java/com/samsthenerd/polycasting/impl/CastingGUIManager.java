package com.samsthenerd.polycasting.impl;

import at.petrak.hexcasting.api.HexAPI;
import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.PatternShapeMatch;
import at.petrak.hexcasting.api.casting.eval.ExecutionClientView;
import at.petrak.hexcasting.api.casting.eval.ResolvedPattern;
import at.petrak.hexcasting.api.casting.eval.ResolvedPatternType;
import at.petrak.hexcasting.api.casting.eval.env.StaffCastEnv;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.iota.IotaType;
import at.petrak.hexcasting.api.casting.iota.PatternIota;
import at.petrak.hexcasting.api.casting.math.HexAngle;
import at.petrak.hexcasting.api.casting.math.HexCoord;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.common.casting.PatternRegistryManifest;
import at.petrak.hexcasting.common.lib.HexItems;
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import at.petrak.hexcasting.common.msgs.MsgNewSpellPatternC2S;
import at.petrak.hexcasting.common.msgs.MsgNewSpellPatternS2C;
import at.petrak.hexcasting.common.msgs.MsgOpenSpellGuiS2C;
import at.petrak.hexcasting.xplat.IXplatAbstractions;
import com.samsthenerd.polycasting.utils.gui.TextGuiBuilder;
import eu.pb4.sgui.api.elements.GuiElement;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.elements.GuiElementInterface.ClickCallback;
import eu.pb4.sgui.api.gui.SimpleGui;
import eu.pb4.sgui.api.gui.SimpleGuiBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class CastingGUIManager {

    // TODO: this should probably be cleared at some point.
    private static final Map<ServerPlayer, CastingGUIManager> playerGUIManagers = new HashMap<>();

    @NotNull
    private InteractionHand handOpenedWith;
    @NotNull
    private List<ResolvedPattern> patterns;
    @NotNull
    private List<CompoundTag> cachedStack;
    @Nullable
    private CompoundTag cachedRavenmind;
    private int parenCount;

    @NotNull
    private final ServerPlayer player;

    @NotNull
    private SimpleGui castingGui;

    @Nullable
    private HexPattern currentPattern;

    public CastingGUIManager(ServerPlayer player, MsgOpenSpellGuiS2C openMsg){
        this.player = player;
        this.handOpenedWith = openMsg.hand();
        this.patterns = new ArrayList<>(openMsg.patterns());
        this.cachedStack = openMsg.stack();
        this.cachedRavenmind = openMsg.ravenmind();
        this.parenCount = openMsg.parenCount();
        initScreen();
        playerGUIManagers.put(player, this);
    }

    private void initScreen(){
        var builder = new SimpleGuiBuilder(MenuType.GENERIC_9x6, true);
        var bookGuiBuilder = new TextGuiBuilder(40, 0);
        bookGuiBuilder.addTexture(PolycastingTextures.GRID_BACKGROUND, 0, 0);
        builder.setTitle(bookGuiBuilder.getAsText());
//        builder.addSlot(ItemStack.EMPTY, (idx, type, action) -> {
//            Polycasting.LOGGER.info("boop");
//            Polycasting.LOGGER.info(Integer.toString(idx));
//            Polycasting.LOGGER.info(type.toString());
//            Polycasting.LOGGER.info(action.toString());
//        });
        this.castingGui = builder.build(player);
        this.castingGui.setAutoUpdate(true);
        updateScreen();
    }

    private void updateScreen(){
        updatePatternDrawingElements();
        updateStack();
        updateResolvedPatternElements();
    }

    private void updateStack(){
        for(int i = 0; i < 6; i++){
            if(cachedStack.size() <= i) break;
            castingGui.setSlot(i*9, makeStackElement(cachedStack.get(cachedStack.size() - i - 1)));
        }
    }

    private void updateResolvedPatternElements(){
        // TODO: limit pattern count somehow?
        int i = 0;
        for(int y = 0; y < 6; y++){
            for(int x = 2; x < 9; x++){
                if(i < patterns.size()){
                    var rpElem = makeResPatElement(patterns.get(i));
                    var ci = y*9 + x;
                    castingGui.setSlot(ci, rpElem);
                }
                i++;
            }
        }
    }

    private void updatePatternDrawingElements(){
        castingGui.setSlot(58, makePatternDrawButton(HexDir.NORTH_WEST));
        castingGui.setSlot(59, makePatternDrawButton(HexDir.NORTH_EAST));
        castingGui.setSlot(67, makePatternDrawButton(HexDir.WEST));
        castingGui.setSlot(68, makePatternDrawButton(HexDir.EAST));
        castingGui.setSlot(76, makePatternDrawButton(HexDir.SOUTH_WEST));
        castingGui.setSlot(77, makePatternDrawButton(HexDir.SOUTH_EAST));
        var castButton = makeCastButton();
        castingGui.setSlot(85, castButton);
        castingGui.setSlot(86, castButton);
    }

    private GuiElement makePatternDrawButton(HexDir dir){
        var builder = new GuiElementBuilder(PolycastingTextures.EMPTY_MODEL_DATA.item());
        builder.setCustomModelData(PolycastingTextures.EMPTY_MODEL_DATA.value());
        var newPattern = HexPattern.fromAngles("", dir);
        if(currentPattern != null){
            List<HexDir> dirs = currentPattern.directions();
            if(!dirs.isEmpty() && dirs.get(dirs.size()-1).angleFrom(dir).equals(HexAngle.BACK)){
                if(dirs.size() == 1){
                    newPattern = null;
                } else {
                    var angles = currentPattern.getAngles();
                    newPattern = new HexPattern(currentPattern.getStartDir(), angles.subList(0, angles.size()-1));
                }
            } else {
                newPattern = HexPattern.fromAngles(currentPattern.anglesSignature(), currentPattern.getStartDir());
                newPattern.tryAppendDir(dir);
            }
        }
        String newPatString = newPattern == null ? "" : newPattern.toString();
        var patName = newPattern == null ? Component.literal("None") : getPatternName(newPattern);
        builder.setName(Component.literal(dir.name()));
        builder.addLoreLine(Component.literal(newPatString));
        builder.addLoreLine(patName);
        builder.addLoreLine(Component.literal("Click to draw. Shift click to cast"));
        builder.setCallback(makePatternCallback(newPattern));
        return builder.build();
    }

    private GuiElement makeCastButton(){
        var builder = new GuiElementBuilder(PolycastingTextures.EMPTY_MODEL_DATA.item());
        builder.setCustomModelData(PolycastingTextures.EMPTY_MODEL_DATA.value());
        if(currentPattern == null){
            builder.setName(Component.literal("Nothing to cast"));
        } else {
            var patName = getPatternName(currentPattern);
            builder.setName(Component.literal("Cast: ").append(patName));
            builder.setCallback((index, type, action, gui) -> {
                this.patterns.add(new ResolvedPattern(currentPattern, HexCoord.getOrigin().plus(new HexCoord(this.patterns.size()*10, this.patterns.size()*10)), ResolvedPatternType.UNRESOLVED));
                StaffCastEnv.handleNewPatternOnServer(player, new MsgNewSpellPatternC2S(handOpenedWith, currentPattern, patterns));
                this.currentPattern = null;
                updateScreen();
            });
        }
        return builder.build();
    }

    private GuiElement makeStackElement(CompoundTag tag){
        Iota iota = IotaType.deserialize(tag, player.serverLevel());
        ItemStack focusStack = HexItems.FOCUS.getDefaultInstance();
        HexItems.FOCUS.writeDatum(focusStack, iota);
        var builder = GuiElementBuilder.from(focusStack);
        return builder.build();
    }

    private GuiElement makeResPatElement(ResolvedPattern resPat){
        ItemStack scrollStack = HexItems.SCROLL_MEDIUM.getDefaultInstance();
        var pat = resPat.getPattern();
        var resType = resPat.getType();
        HexItems.SCROLL_MEDIUM.writeDatum(scrollStack, new PatternIota(pat));
        var builder = GuiElementBuilder.from(scrollStack);
        var patName = getPatternName(pat);
        builder.setName(patName);
        builder.addLoreLine(Component.literal(pat.toString()));
        builder.addLoreLine(Component.literal(resType.toString()).withStyle(Style.EMPTY.withColor(resType.getColor())));
        return builder.build();
    }

    private ClickCallback makePatternCallback(HexPattern newPattern){
        return (index, type, action, gui) -> {
            this.currentPattern = newPattern;
            updatePatternDrawingElements();
        };
    }

    // basically directly copied from guispellcasting
    private void recvUpdate(ExecutionClientView info, int index){
        if (info.isStackClear()) {
            this.castingGui.close();
        }

        // TODO this is the kinda hacky bit
        if (info.getResolutionType() == ResolvedPatternType.UNDONE) {
            if(!this.patterns.isEmpty()){
                this.patterns.remove(this.patterns.size() - 1);
                this.patterns.stream().dropWhile(p -> p.getType() == ResolvedPatternType.ESCAPED)
                                .findFirst()
                                .ifPresent(p -> p.setType(ResolvedPatternType.UNDONE));
                if(this.patterns.size() > index){
                    this.patterns.get(index).setType(ResolvedPatternType.EVALUATED);
                }
            }
        } else if(this.patterns.size() > index && index >= 0){
            this.patterns.get(index).setType(info.getResolutionType());
        }

        this.cachedStack = info.getStackDescs();
        this.cachedRavenmind = info.getRavenmind();
        this.updateScreen();
    }

    public static void updatePlayerScreen(ServerPlayer player, MsgNewSpellPatternS2C newSpellS2C){
        var guiman = playerGUIManagers.get(player);
        if(guiman != null){
            guiman.recvUpdate(newSpellS2C.info(), newSpellS2C.index());
        }
    }

    public void openScreen(){
        castingGui.open();
    }


    private Component getPatternName(HexPattern pattern){
            PatternShapeMatch shapeMatch = PatternRegistryManifest.matchPattern(pattern, IXplatAbstractions.INSTANCE.getStaffcastVM(player, handOpenedWith).getEnv(), false);
            if(shapeMatch instanceof PatternShapeMatch.Normal normMatch){
                return HexAPI.instance().getActionI18n(normMatch.key, false);
            }
            if(shapeMatch instanceof PatternShapeMatch.Special specialMatch){
                return HexAPI.instance().getSpecialHandlerI18n(specialMatch.key);
            }
            // TODO: how to handle great spells if at all?
        return PatternIota.displayNonInline(pattern);
    }
}
