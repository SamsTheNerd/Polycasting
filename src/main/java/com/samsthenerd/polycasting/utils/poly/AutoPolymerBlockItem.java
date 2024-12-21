package com.samsthenerd.polycasting.utils.poly;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AutoPolymerBlockItem extends BlockItem implements AutoModeledPolymerItem {
    private final Item polymerItem;

    public AutoPolymerBlockItem(Block block, Item.Properties settings, Item virtualItem) {
        super(block, settings);
        this.polymerItem = virtualItem;
    }

    @Override
    public Item getPolymerItem() {
        return this.polymerItem;
    }

}
