package com.samsthenerd.polycasting.mixin.hex;

import at.petrak.hexcasting.common.msgs.IMessage;
import at.petrak.hexcasting.fabric.xplat.FabricXplatImpl;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.Iterator;

@Mixin(value = FabricXplatImpl.class, remap = false)
public class IXPlatNostractions {
    @Overwrite
    public void sendPacketToPlayer(ServerPlayer target, IMessage packet) {
        // nop
    }

    @Overwrite
    private void sendPacketToPlayers(Collection<ServerPlayer> players, IMessage packet) {
        // nop
    }
}
