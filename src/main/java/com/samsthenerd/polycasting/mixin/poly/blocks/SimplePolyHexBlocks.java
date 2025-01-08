package com.samsthenerd.polycasting.mixin.poly.blocks;

import at.petrak.hexcasting.common.blocks.BlockConjured;
import at.petrak.hexcasting.common.blocks.BlockConjuredLight;
import at.petrak.hexcasting.common.blocks.BlockFlammable;
import at.petrak.hexcasting.common.blocks.BlockQuenchedAllay;
import at.petrak.hexcasting.common.blocks.akashic.BlockAkashicBookshelf;
import at.petrak.hexcasting.common.blocks.akashic.BlockAkashicLigature;
import at.petrak.hexcasting.common.blocks.akashic.BlockAkashicRecord;
import at.petrak.hexcasting.common.blocks.circles.BlockEmptyImpetus;
import at.petrak.hexcasting.common.blocks.circles.BlockSlate;
import at.petrak.hexcasting.common.blocks.circles.directrix.BlockBooleanDirectrix;
import at.petrak.hexcasting.common.blocks.circles.directrix.BlockEmptyDirectrix;
import at.petrak.hexcasting.common.blocks.circles.directrix.BlockRedstoneDirectrix;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockLookingImpetus;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockRedstoneImpetus;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockRightClickImpetus;
import at.petrak.hexcasting.common.blocks.decoration.*;
import com.samsthenerd.polycasting.impl.Polycasting;
import com.samsthenerd.polycasting.utils.poly.RegistryCallbackBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.IdentityHashMap;

@Mixin({BlockSlate.class, BlockEmptyImpetus.class, BlockRightClickImpetus.class, BlockLookingImpetus.class,
    BlockRedstoneImpetus.class, BlockEmptyDirectrix.class, BlockRedstoneDirectrix.class, BlockBooleanDirectrix.class,
    BlockAkashicRecord.class, BlockAkashicBookshelf.class, BlockAkashicLigature.class, BlockQuenchedAllay.class,
    BlockAmethystDirectional.class, BlockSconce.class, BlockAkashicLog.class, BlockAkashicLeaves.class,
    BlockConjuredLight.class, BlockConjured.class, BlockHexDoor.class, BlockHexTrapdoor.class, BlockHexStairs.class,
    BlockHexFence.class, BlockHexFenceGate.class, BlockHexSlab.class, BlockHexWoodButton.class, BlockHexPressurePlate.class,
    BlockFlammable.class
})
public class SimplePolyHexBlocks implements PolymerBlock, PolymerTexturedBlock, RegistryCallbackBlock {

    @Override
    public Block getPolymerBlock(BlockState blockState) {
        return getPolymerBlockState(blockState).getBlock();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        var model = MODELS.get(this);
        if(model == null){
            Polycasting.LOGGER.error("couldn't find blockstate for: " + this);
            return Blocks.AIR.defaultBlockState();
        }
        return model;
    }

    @Unique
    private static IdentityHashMap<Object, BlockState> MODELS = new IdentityHashMap<>();

    @Override
    public void onRegistered(ResourceLocation selfId) {
        var blockid = new ResourceLocation(selfId.getNamespace(), "block/" + selfId.getPath());
        MODELS.put(this, PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(blockid)));
    }
}
