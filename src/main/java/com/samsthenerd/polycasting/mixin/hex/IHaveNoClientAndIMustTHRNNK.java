package com.samsthenerd.polycasting.mixin.hex;

import at.petrak.hexcasting.common.lib.HexSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;


@Mixin(value = HexSounds.class, remap=false)
public class IHaveNoClientAndIMustTHRNNK {
    @Inject(at=@At("HEAD"),
        method="Lat/petrak/hexcasting/common/lib/HexSounds;registerSounds(Ljava/util/function/BiConsumer;)V",
        cancellable = true)
    private static void cancelHexSoundEventRegistering(BiConsumer<SoundEvent, ResourceLocation> r, CallbackInfo cir){
        cir.cancel();
    }
}
