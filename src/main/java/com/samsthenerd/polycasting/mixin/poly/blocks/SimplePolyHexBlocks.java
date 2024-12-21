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
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({BlockSlate.class, BlockEmptyImpetus.class, BlockRightClickImpetus.class, BlockLookingImpetus.class,
    BlockRedstoneImpetus.class, BlockEmptyDirectrix.class, BlockRedstoneDirectrix.class, BlockBooleanDirectrix.class,
    BlockAkashicRecord.class, BlockAkashicBookshelf.class, BlockAkashicLigature.class, BlockQuenchedAllay.class,
    BlockAmethystDirectional.class, BlockSconce.class, BlockAkashicLog.class, BlockAkashicLeaves.class,
    BlockConjuredLight.class, BlockConjured.class, BlockHexDoor.class, BlockHexTrapdoor.class, BlockHexStairs.class,
    BlockHexFence.class, BlockHexFenceGate.class, BlockHexSlab.class, BlockHexWoodButton.class, BlockHexPressurePlate.class,
    BlockFlammable.class
})
public class SimplePolyHexBlocks implements PolymerBlock {

    @Override
    public Block getPolymerBlock(BlockState blockState) {
        return Blocks.AMETHYST_CLUSTER;
    }
}
