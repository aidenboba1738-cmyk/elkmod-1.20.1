package com.example.elkmod;

import com.example.elkmod.entity.ElkEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Spyglass: look at an elk and see its age, sex and antler genetics.
@Mod.EventBusSubscriber(modid = ElkMod.MOD_ID)
public class ModForgeEvents {

    @SubscribeEvent
    public static void onSpyglass(LivingEntityUseItemEvent.Tick event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!event.getItem().is(Items.SPYGLASS) || player.tickCount % 10 != 0) return;

        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0f);
        AABB box = player.getBoundingBox().expandTowards(look.scale(64.0)).inflate(2.0);
        ElkEntity best = null;
        double bestDot = 0.995;
        for (ElkEntity elk : player.level().getEntitiesOfClass(ElkEntity.class, box)) {
            Vec3 to = elk.getEyePosition().subtract(eye);
            if (to.length() > 64.0) continue;
            double dot = to.normalize().dot(look);
            if (dot > bestDot && player.hasLineOfSight(elk)) {
                best = elk;
                bestDot = dot;
            }
        }
        if (best == null) return;

        String text;
        if (best.isBull()) {
            float rating = best.getGenes().antlerRating();
            String label = rating >= 1.15f ? "High" : rating >= 0.95f ? "Medium" : "Low";
            text = "Bull, age " + best.getAgeYears() + "  |  Antler genetics: " + label;
        } else {
            text = "Cow, age " + best.getAgeYears();
        }
        player.displayClientMessage(Component.literal(text), true);
    }
}
