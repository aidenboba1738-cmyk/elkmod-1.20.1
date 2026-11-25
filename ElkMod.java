package com.example.elkmod;

import com.example.elkmod.block.ModBlockEntities;
import com.example.elkmod.block.ModBlocks;
import com.example.elkmod.entity.ModEntities;
import com.example.elkmod.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElkMod.MOD_ID)
public class ElkMod {
    public static final String MOD_ID = "elkmod";

    public ElkMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModSounds.SOUNDS.register(bus);
    }
}
