package com.example.elkmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ElkMod.MOD_ID);

    public static final RegistryObject<SoundEvent> BUGLE = register("elk_bugle");
    public static final RegistryObject<SoundEvent> BARK = register("elk_bark");
    public static final RegistryObject<SoundEvent> CHIRP = register("elk_chirp");
    public static final RegistryObject<SoundEvent> CALF = register("elk_calf");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ElkMod.MOD_ID, name)));
    }
}
