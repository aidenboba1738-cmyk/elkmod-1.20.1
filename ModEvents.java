package com.example.elkmod;

import com.example.elkmod.entity.ElkEntity;
import com.example.elkmod.entity.ModEntities;
import com.example.elkmod.item.ModItems;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElkMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void addAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ELK.get(), ElkEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        var tab = event.getTabKey();
        if (tab == CreativeModeTabs.SPAWN_EGGS) event.accept(ModItems.ELK_SPAWN_EGG);
        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.RAW_ELK_MEAT);
            event.accept(ModItems.COOKED_ELK_MEAT);
            event.accept(ModItems.ELK_STEW);
            event.accept(ModItems.ELK_JERKY);
            event.accept(ModItems.ELK_SANDWICH);
        }
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(ModItems.ELK_BUGLE);
        if (tab == CreativeModeTabs.INGREDIENTS) event.accept(ModItems.ELK_HIDE);
        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) event.accept(ModItems.ELK_TROPHY);
    }

    // Any solid ground in any overworld biome, not just grass.
    @SubscribeEvent
    public static void spawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.ELK.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR);
    }
}
